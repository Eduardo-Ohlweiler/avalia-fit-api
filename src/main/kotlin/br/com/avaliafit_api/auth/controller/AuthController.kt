package br.com.avaliafit_api.auth.controller

import br.com.avaliafit_api.auth.dtos.LoginDto
import br.com.avaliafit_api.auth.dtos.LoginResponseDto
import br.com.avaliafit_api.auth.service.AuthService
import br.com.avaliafit_api.usuario.dtos.UsuarioCreateDto
import br.com.avaliafit_api.usuario.dtos.UsuarioResponseDto
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val authService: AuthService
) {

    fun create(usuarioCreateDto: UsuarioCreateDto): UsuarioResponseDto {
        return this.authService.create(usuarioCreateDto);
    }

    fun login(loginDto: LoginDto): LoginResponseDto {
        return this.authService.login(loginDto);
    }
}