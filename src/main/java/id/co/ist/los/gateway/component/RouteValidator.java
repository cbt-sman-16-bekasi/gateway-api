package id.co.ist.los.gateway.component;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {
    private static final AntPathMatcher pathMatcher = new AntPathMatcher();

    public static final List<String> unprotectedURLs = List.of(
            "/user-service/auth/login",
            "/auth-service/v1/auth/main-login",
            "/auth-service/v1/auth/provider/session",
            "/auth-service/v1/auth/provider/session/validate",
            "/auth-service/v1/auth/logout",
            "/auth-service/v1/auth/validate",
            "/auth-service/v2/auth/forgot-password",
            "/auth-service/v2/auth/validate",
            "/outbound-service/privy/callback",
            "/document/download",
            "/api/documenteditor",
            "/auth-service/saml2/authenticate/**",
            "/auth-service/login/saml2/sso/azure/**",
            "**/api-docs",
            "**/swagger-resources/**",
            "**/swagger-ui.html",
            "**/webjars/**"
    );

    public Predicate<ServerHttpRequest> isSecured = request ->
            unprotectedURLs.stream().noneMatch(uri -> pathMatcher.match(uri, request.getURI().getPath()));
}
