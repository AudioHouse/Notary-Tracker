package com.audiohouse.notarytracker.core;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class TokenCore {

    private static final String SECRET_KEY = "MY-SUPER-SECRET-KEY";
    private static final long TTL_MILLIS = 3600000L;
    private static final String ISSUER = "nt_server";

    public String generateJWT(String tokenId, String subject) {
        // The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter
                .parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes,
                signatureAlgorithm.getJcaName());

        // Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder()
                .setId(tokenId)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(ISSUER)
                //.claim("isAdmin", isAdmin)
                .signWith(signatureAlgorithm, signingKey);

        // if it has been specified, let's add the expiration
        if (TTL_MILLIS > 0) {
            long expMillis = nowMillis + TTL_MILLIS;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        // Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public Claims getJWTClaims(String jwtToken) {
        try {
            return Jwts.parser()
                    .setSigningKey(
                            DatatypeConverter.parseBase64Binary(SECRET_KEY))
                    .parseClaimsJws(jwtToken).getBody();
        } catch (MalformedJwtException | ExpiredJwtException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Invalid JWT Token: " + e.getMessage());
        }
    }

    public String getUserIdFromToken(String jwtToken) {
        Claims claims = getJWTClaims(jwtToken);
        return claims.getSubject();
    }


}
