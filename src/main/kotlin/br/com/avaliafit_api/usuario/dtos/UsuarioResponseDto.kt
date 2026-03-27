package br.com.avaliafit_api.usuario.dtos

import br.com.avaliafit_api.usuario.enums.UsuarioRole
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

data class UsuarioResponseDto(

    @Schema(description = "ID do usuário", example = "1")
    val id: Long,

    @Schema(description = "Nome do usuário", example = "Eduardo Rodrigo")
    val nome: String,

    @Schema(description = "Email do usuário", example = "eduardo@email.com")
    val email: String,

    @Schema(description = "Telefone com DDD", example = "51992006747")
    val telefone: String,

    @Schema(description = "Endereço completo", example = "Rua Carlos Wagner, 123")
    val endereco: String?,

    @Schema(description = "Permissão do usuário", example = "USER")
    val usuarioRole: UsuarioRole,

    @Schema(description = "Usuário bloqueado", example = "false")
    val bloqueado: Boolean,

    @Schema(description = "Data de criação", example = "2026-03-14")
    val createdAt: LocalDate,

    @Schema(description = "Data da última atualização", example = "2026-03-15")
    val updatedAt: LocalDate?
)