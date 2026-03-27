package br.com.avaliafit_api.auth.dtos

import br.com.avaliafit_api.usuario.dtos.UsuarioResponseDto

data class LoginResponseDto (
    val accessToken: String,
    val usuario:    UsuarioResponseDto
)