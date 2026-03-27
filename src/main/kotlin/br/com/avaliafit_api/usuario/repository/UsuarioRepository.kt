package br.com.avaliafit_api.usuario.repository

import br.com.avaliafit_api.usuario.entity.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UsuarioRepository: JpaRepository<Usuario, Long> {

    fun existsByEmail(email: String): Boolean;
    fun existsByTelefone(telefone: String): Boolean;

    fun findByEmail(email: String): Optional<Usuario>;
}