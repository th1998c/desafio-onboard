package com.flexpag.paymentscheduler.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.flexpag.paymentscheduler.model.entities.Usuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	//@Value("${scheduler.jwt.secret}")
	private String secret = "rm'!@N=Ke!~p8VTA2ZRK~nMDQX5Uvm!m'D&]{@Vr?G;2?XhbC:Qa#9#eMLN\\}x3?JR3.2zr~v)gYF^8\\:8>:XfB:Ww75N/emt9Yj[bQMNCWwW\\J?N,nvH.<2\\.r~w]*e~vgak)X\"v8H`MH/7\"2E`,^k@n<vE-wD3g9JWPy;CrY*.Kd2_D])=><D?YhBaSua5hW%{2]_FVXzb9`8FH^b[X3jzVER&:jw2<=c38=>L/zBq`}C6tT*cCSVC^c]-L}&/";
	//@Value("${scheduler.jwt.expiration}")
	private String expiration = "86400000";

	
	public String gerarToken(Authentication authentication) {
		Date hoje = new Date();
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
		Usuario usuario = (Usuario) authentication.getPrincipal();
		return Jwts.builder()
				.setIssuer("API payment scheduler")
				.setSubject(usuario.getId().toString())
				.setIssuedAt(hoje)
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

}
