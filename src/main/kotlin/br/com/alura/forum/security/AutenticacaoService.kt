package br.com.alura.forum.security

import br.com.alura.forum.model.Usuario
import br.com.alura.forum.repository.UsuarioRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class AutenticacaoService(
    val repository: UsuarioRepository
): UserDetailsService {

    override fun loadUserByUsername(userName: String): UserDetails {
        val usuario: Optional<Usuario> = repository.findByEmail(userName)
        if(usuario.isPresent) return usuario.get()
        throw UsernameNotFoundException("Dados invalidos")
    }

}
