package br.com.alura.forum.controller.form

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken


data class LoginForm(
    val email: String,
    val senha: String
){
    fun converter():UsernamePasswordAuthenticationToken{
        return UsernamePasswordAuthenticationToken(
            email,
            senha
        )
    }
}

