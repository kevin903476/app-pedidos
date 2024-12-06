package com.example.app_pedidos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializa el RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Cargar los productos en el RecyclerView
        loadProducts()
    }

    private fun loadProducts() {
        // Lista de productos (puedes agregar más según tu necesidad)
        val productos = listOf(
            Producto("Camiseta", 15.99, R.drawable.ic_launcher_background),
            Producto("Pantalón", 25.99, R.drawable.ic_launcher_background),
            Producto("Zapatos", 45.50, R.drawable.ic_launcher_background)
        )

        // Configurar el adaptador con la lista de productos
        val adapter = ProductoAdapter(this, productos)
        recyclerView.adapter = adapter
    }
}
