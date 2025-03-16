package id.co.ist.los.gateway.component;

import id.co.ist.los.gateway.model.dto.Division;
import id.co.ist.los.gateway.model.dto.Position;
import id.co.ist.los.gateway.model.dto.UserRefactor;
import id.co.ist.los.gateway.model.dto.user.GetUserResponseDto;
import id.co.ist.los.gateway.model.dto.user.RoleDto;
import id.co.ist.los.gateway.model.dto.user.UserGroupDto;
import id.co.ist.los.gateway.utils.JsonUtils;
import id.co.ist.los.gateway.utils.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class AuthFilter implements GlobalFilter {

    @Value("${authentication.enabled}")
    private boolean authEnabled;

    @Autowired
    RouteValidator routeValidator;

    @Autowired
    RedisService redisService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if(!authEnabled) {
            System.out.println("Authentication is disabled. To enable it, make \"authentication.enabled\" property as true");
            return chain.filter(exchange);
        }
        ServerHttpRequest request = exchange.getRequest();
        log.info("PATH {}", request.getURI().getPath());
        if(routeValidator.isSecured.test(request)) {
            String token = "";
            log.info("Filter auth");
            if(request.getHeaders().containsKey("Authorization")) {
                token = this.getAuthHeader(request);
                if(token.isEmpty() || token.isBlank()) {
                    return this.onError(exchange, "Token is empty or blank");
                }

                UserRefactor user = getUserFromRedis(token);
                if(Objects.isNull(user)) {
                    return this.onError(exchange, "User data is empty or blank");
                }

                this.populateRequestWithHeaders(exchange, user, token);
            } else {
                return this.onError(exchange, "No token found in the request");
            }

            log.info("Filter auth success");
        }
        return chain.filter(exchange);
    }
    private Mono<Void> onError(ServerWebExchange exchange, String err) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json");
        var bodyError = "{\"error_detail\": \"" + err + "\",\"message\":\"" + err + "\"}";
        byte[] bytes = bodyError.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        return response.writeWith(Flux.just(buffer));
    }

    private String getAuthHeader(ServerHttpRequest request) {
        return  request.getHeaders().getOrEmpty("Authorization").get(0);
    }

    private String getUserId(ServerHttpRequest request) {
        return  request.getHeaders().getOrEmpty("User-Id").get(0);
    }

    private void populateRequestWithHeaders(ServerWebExchange exchange, UserRefactor data, String token) {
        log.info("USER-ID {}", data.getId());
        exchange.getRequest()
                .mutate()
                .header("User-Id", String.valueOf(data.getId()))
                .header("Authorization", token)
//                .header("Division", JsonUtils.objectAsStringJson(data.getUserDivision().stream().map(Division::getDivisionCode).collect(Collectors.toList())))
//                .header("Role", JsonUtils.objectAsStringJson(List.of(data.getUserRoleRefactor().getRoleCode())))
//                .header("Position", JsonUtils.objectAsStringJson(data.getUserPosition().stream().map(Position::getPositionCode).collect(Collectors.toList())))
                .build();
    }

    private UserRefactor getUserFromRedis(String token) {
        log.info("Token {}", token);
        String tokenBearer = token.split(" ")[token.split(" ").length - 1];
        log.info("{} Token", tokenBearer);

        var data = redisService.getDataFromJsonString(tokenBearer, UserRefactor.class);
        log.info("User Data {}", data);

        return data;
    }


}
