package com.example.app_pedidos

// Clase de datos para un pedido
data class Pedido(
    val id: Int,
    val nombreProducto: String,
    val cantidad: Int,
    val talla: String,
    val precio: Double,
    val nombreCliente: String,
    val direccion: String,
    val metodoPago: String
)
