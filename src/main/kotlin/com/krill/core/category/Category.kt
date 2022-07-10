package com.krill.core.category

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import javax.persistence.*

@Entity
@Table(name = "categories")
class Category(
    val name: String,
    val color: String
) : PanacheEntityBase {
    @Id
    @GeneratedValue(generator = "categories_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
        name = "categories_seq",
        sequenceName = "categories_seq",
        allocationSize = 50
    )
    var id: Long? = 0
}
