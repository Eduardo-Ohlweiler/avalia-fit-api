package br.com.avaliafit_api.role.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "role")
class Role (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "nome", nullable = false, length = 100)
    val nome: String,

    @Column(name = "nome", nullable = true, length = 255)
    val descricao: String? = null
)