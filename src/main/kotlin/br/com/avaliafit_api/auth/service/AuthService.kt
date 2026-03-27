package br.com.avaliafit_api.auth.service

import br.com.avaliafit_api.auth.dtos.LoginDto
import br.com.avaliafit_api.auth.dtos.LoginResponseDto
import br.com.avaliafit_api.config.JwtUtil
import br.com.avaliafit_api.exceptions.UnauthorizedException
import br.com.avaliafit_api.usuario.dtos.UsuarioCreateDto
import br.com.avaliafit_api.usuario.dtos.UsuarioResponseDto
import br.com.avaliafit_api.usuario.mapper.UsuarioMapper
import br.com.avaliafit_api.usuario.service.UsuarioService
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val usuarioService: UsuarioService,
    private val jwtUtil: JwtUtil,
    private val usuarioMapper: UsuarioMapper
) {

    fun login(loginDto: LoginDto): LoginResponseDto {
        val usuario = usuarioService.findByEmail(loginDto.email);
        val match   = usuarioService.comparaSenha(loginDto.hashSenha, usuario);
        if(!match)
            throw UnauthorizedException("Credenciais inválidas");

        val loginResponseDto = LoginResponseDto(
            accessToken = jwtUtil.gerar(usuario.id!!, usuario.usuarioRole),
            usuario    = usuarioMapper.toResponseDto(usuario)
        );

        return loginResponseDto;
    }

    fun create(usuarioCreateDto: UsuarioCreateDto): UsuarioResponseDto {
        return this.usuarioService.create(usuarioCreateDto, false);
    }
}