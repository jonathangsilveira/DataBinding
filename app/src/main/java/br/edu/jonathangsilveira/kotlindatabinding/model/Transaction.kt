package br.edu.jonathangsilveira.kotlindatabinding.model

data class Transaction(
    val id: Long,
    val method: Int,
    val description: String,
    val value: Double
)