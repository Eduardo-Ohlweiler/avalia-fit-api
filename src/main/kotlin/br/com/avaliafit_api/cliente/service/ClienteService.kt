package br.com.avaliafit_api.cliente.service

import br.com.avaliafit_api.cliente.entity.Cliente
import br.com.avaliafit_api.cliente.repository.ClienteRepository
import br.com.avaliafit_api.exceptions.NotFoundException
import br.com.avaliafit_api.usuario.entity.Usuario
import br.com.avaliafit_api.usuario.service.UsuarioService
import org.springframework.stereotype.Service

@Service
class ClienteService(
    private val clienteRepository: ClienteRepository,
    private val usuarioService:    UsuarioService
) {

    fun findById(id: Long): Cliente {

        val cliente = clienteRepository.findById(id);
        if(!cliente.isPresent)
            throw NotFoundException("Cliente no encontrado");

        return cliente.get();
    }
}