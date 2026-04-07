package br.com.avaliafit_api.role.repository

import br.com.avaliafit_api.role.entity.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository: JpaRepository<Role, Long> {

    fun existsByNomeIgnoreCase(nome: String): Boolean;

    fun findByNomeIgnoreCase(nome: String): Role?;
}