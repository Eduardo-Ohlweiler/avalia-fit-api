package br.com.avaliafit_api.usuario.service

import br.com.avaliafit_api.config.JwtUtil
import br.com.avaliafit_api.exceptions.ConflictException
import br.com.avaliafit_api.exceptions.NotFoundException
import br.com.avaliafit_api.usuario.dtos.UsuarioCreateDto
import br.com.avaliafit_api.usuario.dtos.UsuarioResponseDto
import br.com.avaliafit_api.usuario.entity.Usuario
import br.com.avaliafit_api.usuario.enums.UsuarioRole
import br.com.avaliafit_api.usuario.mapper.UsuarioMapper
import br.com.avaliafit_api.usuario.repository.UsuarioRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.Optional

@Service
class UsuarioService(
    private val usuarioRepository:  UsuarioRepository,
    private val usuarioMapper:      UsuarioMapper,
    private val passwordEncoder:    PasswordEncoder
) {

    fun findById(id: Long): Usuario {
        val usuario = usuarioRepository.findById(id);
        if(!usuario.isPresent)
            throw NotFoundException("Usuario no encontrado");

        return usuario.get();
    }

    fun findByIdResponse(id: Long): UsuarioResponseDto {
        val usuario = this.findById(id);
        return usuarioMapper.toResponseDto(usuario);
    }

    fun findByEmail(email: String): Usuario {
        val usuario = usuarioRepository.findByEmail(email);
        if(!usuario.isPresent)
            throw NotFoundException("Usuario no encontrado");
        return usuario.get();
    }

    fun create(usuarioCreateDto: UsuarioCreateDto, logado: Boolean = true): UsuarioResponseDto {

        if(usuarioRepository.existsByEmail(usuarioCreateDto.email))
            throw ConflictException("Email já está cadastrado, por favor verifique!")

        var role: UsuarioRole   = UsuarioRole.USER;
        val usuarioLogado       = getUsuarioLogado();
        if(logado){
            if(usuarioLogado.isPresent){
                val user  = usuarioLogado.get();
                if (user.usuarioRole == UsuarioRole.ADMIN && usuarioCreateDto.usuarioRole != null)
                    role = usuarioCreateDto.usuarioRole
            }
        }

        val usuario = Usuario(
            nome        = usuarioCreateDto.nome,
            email       = usuarioCreateDto.email,
            telefone    = usuarioCreateDto.telefone,
            endereco    = usuarioCreateDto.endereco,
            senha       = passwordEncoder.encode(usuarioCreateDto.senha),
            usuarioRole = role,
            createdAt   = LocalDate.now(),
        );

        val usuarioSalvo = usuarioRepository.save(usuario);
        return usuarioMapper.toResponseDto(usuarioSalvo);
    }

    fun comparaSenha(senha: String, usuario: Usuario): Boolean {
        return this.passwordEncoder.matches(senha, usuario.senha);
    }

    fun getUsuarioLogado(): Optional<Usuario> {
        val auth = SecurityContextHolder.getContext().authentication;
        if (auth == null || !auth.isAuthenticated || auth.principal == "anonymousUser")
            return Optional.empty()

        val usuarioId = auth.principal as Long;
        return usuarioRepository.findById(usuarioId);
    }
}