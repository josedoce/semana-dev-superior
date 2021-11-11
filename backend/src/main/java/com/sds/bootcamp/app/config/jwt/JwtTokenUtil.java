package com.sds.bootcamp.app.config.jwt;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {
	private static final long serialVersionUID = -13829183734923L;
	private static long remembeMe = 24;
	public static final long JWT_TOKEN_VALIDITY = remembeMe * 60 * 60;

	@Value("${jwt.secret}")
	String secret;
	
	public static void setRemembe(long days) {
		remembeMe = days;
	}
	
	//retorna o user name do token jwt
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	//retorna expiration date do token jwt 
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	//para retornar qualquer informação do token nos iremos precisar da secret key
	public Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();			
	}

	//check if the token has expired
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	//gera token para user
	public String generateToken(String id, String user) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, id, user);
	}

	//Cria o token e define tempo de expiração pra ele
	private String doGenerateToken(Map<String, Object> claims, String id, String subject) {	
		return Jwts.builder()
			.setClaims(claims)
			.setId(id)
			.setSubject(subject)
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
			.signWith(SignatureAlgorithm.HS512, this.secret)
			.compact();
	}

	//valida o token
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}