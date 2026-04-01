package br.com.avaliafit_api.usuario.entity

import br.com.avaliafit_api.cliente.entity.Cliente
import br.com.avaliafit_api.grupo.entity.Grupo
import br.com.avaliafit_api.usuario.enums.UsuarioRole
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
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
    var createdAt: LocalDate = LocalDate.now(),

    @Column
    var updatedAt: LocalDate? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    var cliente: Cliente,

    @ManyToMany
    @JoinTable(
        name = "usuario_grupo",
        joinColumns = [JoinColumn(name = "usuario_id")],
        inverseJoinColumns = [JoinColumn(name = "grupo_id")]
    )
    var grupos: MutableSet<Grupo> = mutableSetOf()
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