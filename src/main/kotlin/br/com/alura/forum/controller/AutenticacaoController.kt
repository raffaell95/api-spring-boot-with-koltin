package br.com.alura.forum.controller

import br.com.alura.forum.controller.form.LoginForm
import br.com.alura.forum.dto.TokenDto
import br.com.alura.forum.security.TokenService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AutenticacaoController(
    val authManager: AuthenticationManager,
    val tokenService: TokenService
    ) {

    @PostMapping
    fun autenticar(@RequestBody @Valid form: LoginForm):ResponseEntity<TokenDto>{
        val dadosLogin: UsernamePasswordAuthenticationToken = form.converter()

        return try {
            val authentication: Authentication = authManager.authenticate(dadosLogin)
            val token: String = tokenService.gerarToken(authentication)
            ResponseEntity.ok(TokenDto(token, "Bearer"))
        }catch (e: AuthenticationException){
            ResponseEntity.badRequest().build()
        }


    }
}