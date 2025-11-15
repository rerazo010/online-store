//package com.api_gateway.configuration;
//
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.stereotype.Component;
//
//@Component
//public class JwtAuthFilter extends AbstractGatewayFilterFactory<JwtAuthFilter.Config> {
//    private final JwtUtil jwtUtil;
//    private final List<String> whiteList = List.of("/auth/login", "/auth/refresh");
//
//    public JwtAuthFilter(JwtUtil jwtUtil) { super(Config.class); this.jwtUtil = jwtUtil; }
//
//    @Override
//    public GatewayFilter apply(Config config) {
//        return (exchange, chain) -> {
//            String path = exchange.getRequest().getURI().getPath();
//            if (whiteList.stream().anyMatch(path::contains)) {
//                return chain.filter(exchange);
//            }
//            String auth = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
//            if (auth == null || !auth.startsWith("Bearer ")) {
//                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//                return exchange.getResponse().setComplete();
//            }
//            String token = auth.substring(7);
//            if (!jwtUtil.isTokenValid(token)) {
//                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//                return exchange.getResponse().setComplete();
//            }
//            var userDetails = jwtUtil.decode(token);
//            ServerHttpRequest mutated = exchange.getRequest().mutate()
//              .header("X-USER-ID", userDetails.getId())
//              .header("X-USER-ROLE", userDetails.getRole()).build();
//            return chain.filter(exchange.mutate().request(mutated).build());
//        };
//    }
//
//    public static class Config {}
//}
//
