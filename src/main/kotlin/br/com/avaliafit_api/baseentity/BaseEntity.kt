package com.api.ero_erp.baseentity

import jakarta.persistence.*
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        protected set

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime? = null
        protected set

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null
        protected set

    @PrePersist
    fun prePersist() {
        this.createdAt = LocalDateTime.now()
    }

    @PreUpdate
    fun preUpdate() {
        this.updatedAt = LocalDateTime.now()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BaseEntity) return false
        return id != null && id == other.id
    }

    override fun hashCode(): Int {
        return this::class.hashCode()
    }
}