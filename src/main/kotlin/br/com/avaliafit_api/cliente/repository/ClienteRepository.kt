package br.com.avaliafit_api.cliente.repository

import br.com.avaliafit_api.cliente.entity.Cliente
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClienteRepository: JpaRepository<Cliente, Long> {
}