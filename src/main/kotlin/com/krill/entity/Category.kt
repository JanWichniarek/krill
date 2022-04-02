package com.krill.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.Entity

@Entity
class Category(
        val name: String,
        val color: String
) : PanacheEntity()
