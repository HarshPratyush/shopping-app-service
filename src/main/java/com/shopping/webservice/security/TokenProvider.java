package com.shopping.webservice.security;

import com.shopping.webservice.properties.SecurityProperties;
import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Slf4j
@Service
public class TokenProvider {


    @Autowired
    private SecurityProperties securityProperties;

    public String createToken(Authentication authentication) {
        JwtUserDetails userPrincipal = (JwtUserDetails) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.toInstant().plus(securityProperties.getTokenExpiration()).toEpochMilli());

        return Jwts.builder()
                .setSubject(userPrincipal.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate).
                setIssuer(securityProperties.getIssuer())
                .signWith(SignatureAlgorithm.HS512, securityProperties.getSecret())
                .compact();
    }

    public String getUserName(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(securityProperties.getSecret())
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(securityProperties.getSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

}
