package com.example.app_pedidos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Inicializa el RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        findViewById<Button>(R.id.button).setOnClickListener {
            startActivity(Intent(this, mostrarPedidos::class.java))
        }
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
