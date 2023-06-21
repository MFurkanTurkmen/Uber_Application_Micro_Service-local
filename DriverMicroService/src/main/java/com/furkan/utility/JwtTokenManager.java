package com.furkan.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.furkan.exception.DriverException;
import com.furkan.exception.EErrorType;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Date;
import java.util.Optional;

@ControllerAdvice
public class JwtTokenManager {
    private final String sifreAnahtari = "${TokenKey}";
    private final Long exTime = 1000L * 60 * 15;

    public Optional<String> createToken(Long id) {
        String token = "";
        try {
            token = JWT.create().withAudience()
                    .withClaim("id", id)
                    .withIssuer("MFT")
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + exTime))
                    .sign(Algorithm.HMAC512(sifreAnahtari));
            return Optional.of(token);
        } catch (Exception exception) {
            return Optional.empty();
        }
    }
    public Optional<Long> validToken(String token){
        try {
            Algorithm algorithm= Algorithm.HMAC512(sifreAnahtari);
            JWTVerifier verifier= JWT.require(algorithm).withIssuer("MFT").build();
            DecodedJWT decodedJWT= verifier.verify(token);
            if (decodedJWT==null) return Optional.empty();
            return Optional.of(decodedJWT.getClaim("id").asLong());

        }catch (Exception e){
            System.out.println("JwtTokenManager da validToken metodu:");
            throw new DriverException(EErrorType.INVALID_TOKEN);
        }

    }

}
