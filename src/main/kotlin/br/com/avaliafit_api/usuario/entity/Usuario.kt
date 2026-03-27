package br.com.avaliafit_api.usuario.entity

import br.com.avaliafit_api.usuario.enums.UsuarioRole
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "usuario")
class Usuario (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(length = 100, nullable = false)
    var nome: String,

    @Column(length = 100,  nullable = false,unique = true)
    var email: String,

    @Column(length = 15, nullable = false, unique = true)
    var telefone: String,

    @Column(length = 200)
    var endereco: String? = null,

    @Column(length = 100, nullable = false)
    var senha: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var usuarioRole: UsuarioRole = UsuarioRole.USER,

    @Column(nullable = false)
    var bloqueado: Boolean = false,

    @Column(nullable = false)
    var createdAt: LocalDate,

    @Column
    var updatedAt: LocalDate? = null,
) {
    @PrePersist
    fun prePersist() {
        createdAt = LocalDate.now()
    }

    @PreUpdate
    fun preUpdate() {
        updatedAt = LocalDate.now()
    }
}