package com.sds.bootcamp.app.config.jwt;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sds.bootcamp.app.errors.exceptionhandler.Problema;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		var status = HttpServletResponse.SC_UNAUTHORIZED;
		//pegando o erro gerado lá no filtro.
		final String tokenError = (String) request.getAttribute("tokenError");
				
		if(tokenError!=null) {
			response.sendError(status, tokenError + " veiio daqui");	
		}else {
			Problema res = new Problema();
			res.setDatetime(null);
			res.setErrors(null);
			res.setStatus(status);
			res.setTitle(authException.getMessage());
			
			response.setContentType("application/json");
			response.setStatus(status);
			ObjectMapper mapper = new ObjectMapper();
			OutputStream out = response.getOutputStream();
			mapper.writeValue(out, res);
			out.flush();
			
		}
	}
}
