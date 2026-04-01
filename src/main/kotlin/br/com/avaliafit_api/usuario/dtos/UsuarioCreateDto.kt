package br.com.avaliafit_api.usuario.dtos

import br.com.avaliafit_api.cliente.entity.Cliente
import br.com.avaliafit_api.usuario.enums.UsuarioRole
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size


data class UsuarioCreateDto (

    @NotBlank(message="O nome é obrigatório")
    @Size(min=3, max=100, message="O nome deve ter entre 3 e 100 caracteres")
    @Schema(description = "Nome completo do usuário", example = "Eduardo Rodrigo")
    val nome: String,

    @NotBlank(message="O email é obrigatório")
    @Email(message="O email deve ser valido")
    @Schema(description = "Email do usuário", example = "eduardo@email.com")
    val email: String,

    @NotBlank(message = "O número de telefone é obrigatório")
    @Pattern(
        regexp = "\\d{11}",
        message = "O número de telefone deve ter 11 dígitos (DD + número)"
    )
    val telefone: String,

    @Size(min=3, max=200, message="O endereço deve ter entre 3 e 200 caracteres")
    @Schema(description = "Endereço completo do usuário", example = "Rua Carlos Vagner, numero 123, bairro Centro, Venâncio Aires/RS ")
    val endereco: String? = null,

    @NotBlank(message="A senha é obrigatória")
    @Size(min = 6, max = 20, message = "A senha deve ter entre 6 e 20 caracteres")
    @Pattern(
        regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).+$",
        message = "A senha deve conter pelo menos uma letra maiúscula, uma letra minúscula e um número"
    )
    @JsonProperty("password")
    val senha: String,

    @Schema(description = "Permissão do usuario", example = "ADMIN | USER")
    val usuarioRole: UsuarioRole? = null,

    @Schema(description = "Id do cliente que contratou o sistema", example = "3")
    val clienteId: Long? = null
)