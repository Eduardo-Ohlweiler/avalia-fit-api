package br.com.avaliafit_api.usuario.mapper

import br.com.avaliafit_api.usuario.dtos.UsuarioResponseDto
import br.com.avaliafit_api.usuario.entity.Usuario

object UsuarioMapper {

    fun toResponseDto(usuario: Usuario): UsuarioResponseDto {
        return UsuarioResponseDto(
            id          = usuario.id!!,
            nome        = usuario.nome,
            email       = usuario.email,
            telefone    = usuario.telefone,
            endereco    = usuario.endereco,
            usuarioRole = usuario.usuarioRole,
            bloqueado   = usuario.bloqueado,
            createdAt   = usuario.createdAt,
            updatedAt   = usuario.updatedAt
        )
    }
}