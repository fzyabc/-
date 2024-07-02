package com.fzy.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JwtUtils {
    static final String key="fzy";
    public static String createToken(String id){
        return JWT.create().withClaim("id",id)
                .sign(Algorithm.HMAC256(key));
    }
    public static String decodeToken(String token){
       return JWT.require(Algorithm.HMAC256(key)).build().verify(token).getClaim("id").asString();
    }
}
