package br.com.avaliafit_api.cliente.service

import br.com.avaliafit_api.cliente.entity.Cliente
import br.com.avaliafit_api.cliente.repository.ClienteRepository
import br.com.avaliafit_api.exceptions.NotFoundException
import org.springframework.stereotype.Service

@Service
class ClienteService(
    private val clienteRepository: ClienteRepository,
) {

    fun findById(id: Long): Cliente {

        val cliente = clienteRepository.findById(id);
        if(!cliente.isPresent)
            throw NotFoundException("Cliente no encontrado");

        return cliente.get();
    }
}