package id.co.ist.los.gateway.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

//    @GetMapping(value = "/fallback")
//    public ResponseEntity<Object> fallback(){
//        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
//                .body(BaseResponseUtils.constructResponse(
//                        ErrorEnum.DEFAULT_ERROR,
//                        null
//                ));
//    }
}
