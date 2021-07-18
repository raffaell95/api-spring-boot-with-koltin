package br.com.alura.forum.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*
import javax.swing.Spring

@Entity
data class Usuario(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val nome: String,
        val email: String,
        val senha: String,
        @ManyToMany(fetch = FetchType.EAGER)
        val perfis: List<Perfil> = ArrayList()
): UserDetails {
        override fun getAuthorities(): List<Perfil> {
                return perfis
        }

        override fun getPassword(): String {
                return senha
        }

        override fun getUsername(): String {
                return email
        }

        override fun isAccountNonExpired(): Boolean {
                return true
        }

        override fun isAccountNonLocked(): Boolean {
                return true
        }

        override fun isCredentialsNonExpired(): Boolean {
                return true
        }

        override fun isEnabled(): Boolean {
                return true
        }


}
