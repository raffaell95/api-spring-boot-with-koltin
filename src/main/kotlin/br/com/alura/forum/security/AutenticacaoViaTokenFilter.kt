package br.com.alura.forum.security

import br.com.alura.forum.repository.UsuarioRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

class AutenticacaoViaTokenFilter(
    val tokenService: TokenService,
    val repository: UsuarioRepository
    ): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = recuperarToken(request)
        val valido: Boolean = tokenService.isTokenValido(token)
        if(valido){
            autenticarCliente(token)
        }
        filterChain.doFilter(request, response)
    }

    private fun autenticarCliente(token: String?) {
        val idUsuario: Long = tokenService.getIdUsuario(token)
        val usuario = repository.getById(idUsuario)
        val authentication = UsernamePasswordAuthenticationToken(usuario, null, usuario.authorities)
        SecurityContextHolder.getContext().setAuthentication(authentication)
    }

    private fun recuperarToken(request: HttpServletRequest): String? {
        val token = request.getHeader("Authorization")
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")){
            return null
        }
        return token.substring(7, token.length)
    }

}