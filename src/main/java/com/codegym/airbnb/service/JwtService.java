package com.codegym.airbnb.service;

import com.codegym.airbnb.model.UserPrinciple;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

@Component
@Slf4j
@Service
public class JwtService {
    // Đoạn JWT_SECRET này là bí mật, chỉ có phía server biết
    private final String JWT_SECRET = "Wonderland";

    //Thời gian có hiệu lực của chuỗi jwt
    private final long JWT_EXPIRATION = 604800000L;

    // Tạo ra jwt từ thông tin user
    public String generateToken(Authentication authentication) {
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION * 1000);
        // Tạo chuỗi json web token từ id của user.
        return Jwts.builder()
                .setSubject(userPrinciple.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    // Lấy thông tin user từ jwt
    public String getUsernameFromJWT(String token) {
        String username = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody().getSubject();
        return username;
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
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
