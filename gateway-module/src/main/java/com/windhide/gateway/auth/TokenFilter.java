package com.windhide.gateway.auth;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.fastjson.JSON;
import com.windhide.gateway.respone.ResponseCodeEnum;
import com.windhide.gateway.respone.ResponseResult;
import com.windhide.gateway.respone.TokenAuthenticationException;
import com.windhide.gateway.util.JWTUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;

@Component
@Log4j2
public class TokenFilter implements GlobalFilter, Ordered {

    final static String TOKEN = "token";
    @Value("${secretKey:WindHide}")
    private String secretKey;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        String uri = serverHttpRequest.getURI().getPath();

        HashSet<String> passUri = new HashSet<>();
        passUri.add("/employee/employeeLogin");
        passUri.add("/user/userLogin");
        passUri.add("/auth/login/employee");
        passUri.add("/auth/login/user");
        passUri.add("/user/insert"); // 放行注册
        passUri.add("/upload/avatar"); // 放行上传
        passUri.add("/static/food/"); //放行静态资源
        passUri.add("/static/avatar/"); //放行静态资源



        //  检查白名单
        if (passUri.contains(uri)) {
            log.info("========== 放行 ========== > url -> {}", uri);
            return chain.filter(exchange);
        } else if (uri.contains("/static/")) {
            log.info("========== 放行 ========== > url -> {}", uri);
            return chain.filter(exchange);
        } else if (uri.contains("/upload/")) {
            log.info("========== 放行 ========== > url -> {}", uri);
            return chain.filter(exchange);
        } else {
            log.info("xxxxxxxxxx 拦截 xxxxxxxxxx > url -> {}", uri);
        }

        String token = serverHttpRequest.getHeaders().getFirst("Authorization");

        try {
            if (token.indexOf("Bearer") != -1) {
                token = token.split(" ")[1];
            }
        } catch (Exception e) {
            return getVoidMono(serverHttpResponse, ResponseCodeEnum.TOKEN_MISSION);
        }
        log.info("token -> {}", token);
        if (StringUtils.isBlank(token)) {
            serverHttpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
            return getVoidMono(serverHttpResponse, ResponseCodeEnum.TOKEN_MISSION);
        }

        String userId = JWTUtil.getUserId(token);
        if (userId == null) {
            return getVoidMono(serverHttpResponse, ResponseCodeEnum.TOKEN_INVALID);
        }

        // 检查Redis中是否有此Token(退出登录有删除token)
        HashOperations<String, String, String> hashOperations = stringRedisTemplate.opsForHash();
        String redisToken = hashOperations.get(userId, TOKEN);
        if (!token.equals(redisToken)) {
            return getVoidMono(serverHttpResponse, ResponseCodeEnum.TOKEN_INVALID);
        }

        try {
            JWTUtil.verifyToken(token, secretKey);
        } catch (TokenAuthenticationException ex) {
            return getVoidMono(serverHttpResponse, ResponseCodeEnum.TOKEN_INVALID);
        } catch (Exception ex) {
            return getVoidMono(serverHttpResponse, ResponseCodeEnum.UNKNOWN_ERROR);
        }


        ServerHttpRequest mutableReq = serverHttpRequest.mutate().header("userId", userId).build();
        ServerWebExchange mutableExchange = exchange.mutate().request(mutableReq).build();
        ServerHttpRequest req = exchange.getRequest().mutate()
                .header("systemFrom", "gateway").build();
        return chain.filter(mutableExchange);

    }

    private Mono<Void> getVoidMono(ServerHttpResponse serverHttpResponse, ResponseCodeEnum responseCodeEnum) {
        serverHttpResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        ResponseResult responseResult = ResponseResult.error(responseCodeEnum.getCode(), responseCodeEnum.getMessage());
        DataBuffer dataBuffer = serverHttpResponse.bufferFactory().wrap(JSON.toJSONString(responseResult).getBytes());
        return serverHttpResponse.writeWith(Flux.just(dataBuffer));
    }

    @Override
    public int getOrder() {
        return -100;
    }
}