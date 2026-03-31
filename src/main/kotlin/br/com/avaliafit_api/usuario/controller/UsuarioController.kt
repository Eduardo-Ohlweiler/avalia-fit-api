package br.com.avaliafit_api.usuario.controller

import br.com.avaliafit_api.usuario.dtos.UsuarioResponseDto
import br.com.avaliafit_api.usuario.service.UsuarioService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/usuario")
@Tag(name = "Usuario", description = "Operações relacionadas aos usuários do sistema")
class UsuarioController(
    private val usuarioService: UsuarioService
) {

    @Operation(summary = "Busca usuario por id")
    @ApiResponses(
        value = arrayOf(
            ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            ApiResponse(responseCode = "404", description = "Usuario não encontrado")
        )
    )
    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): UsuarioResponseDto{
        var usuario: UsuarioResponseDto = usuarioService.findByIdResponse(id);
        return usuario;
    }
}