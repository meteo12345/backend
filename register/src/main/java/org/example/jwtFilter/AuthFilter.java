package org.example.jwtFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.catalina.User;
import org.springframework.http.HttpMethod;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class AuthFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response, FilterChain chain)
    throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String authHeader = req.getHeader("Authorization");

        if (req.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.name())) {

            chain.doFilter(request, response);
        } else {

            if (authHeader == null || !authHeader.startsWith("Bearer")) {
                throw new ServletException("Missing or Invalid Authorization "
                        + " Header");
            }
            try {
                String token = authHeader.substring(7);

                Claims claim = Jwts.parser().
                        setSigningKey(getSigningKey(req)).
                        parseClaimsJws(token).getBody();

                req.setAttribute("claims", claim);
                chain.doFilter(request, response);
            } catch (Exception e) {
                resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token. Not Authorized");
            }
        }
    }
    private String getSigningKey(HttpServletRequest request){
        return "usersecretkey";
    }

    private String generateToken() {
        String signingKey = getSigningKey(null);
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 86400000); // Set expiry time to 24 hours from now

        return Jwts.builder()
                .setSubject("username") // Set the subject (e.g., username)
                .setIssuedAt(now) // Set the token issue date
                .setExpiration(expiryDate) // Set the token expiry date
                .signWith(SignatureAlgorithm.HS256, signingKey)
                .compact();
    }
}







