package com.krill.core.category

data class CategoryDTO(
    val id: Long?,
    val name: String,
    val color: String
) {
    fun toEntity(): Category {
        val category = Category(name, color)
        category.id = id
        return category
    }

    companion object {
        fun from(source: Category): CategoryDTO = CategoryDTO(
            source.id,
            source.name,
            source.color
        )
    }
}