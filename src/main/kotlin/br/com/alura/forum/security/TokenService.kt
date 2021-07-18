package br.com.alura.forum.security

import br.com.alura.forum.model.Usuario
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.core.env.Environment
import org.springframework.core.env.get
import org.springframework.core.env.getProperty
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.util.*

@Service
class TokenService(env: Environment) {

    val expiration: Long = 86400000
    val secret: String = "LH!39jp&o1Ah"

    fun gerarToken(authentication: Authentication): String {
        val logado = authentication.principal as Usuario
        val hoje = Date()
        val dataExpiracao = Date(hoje.time + expiration)
        return Jwts.builder()
            .setIssuer("API do forum da alura")
            .setSubject(logado.id.toString())
            .setIssuedAt(hoje)
            .setExpiration(dataExpiracao)
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact()
    }

    fun isTokenValido(token: String?): Boolean {
        return try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
            true
        }catch (e: Exception){
            false
        }
    }

    fun getIdUsuario(token: String?): Long {
       val claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body
       return claims.subject.toLong()
    }
}