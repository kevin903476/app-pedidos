package com.example.app_pedidos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProductoDetalle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_producto_detalle)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Recibir los datos enviados por el adapter
        val nombre = intent.getStringExtra("nombre")
        val precio = intent.getDoubleExtra("precio", 0.0)
        val imagen = intent.getIntExtra("imagen", 0)  // Obtener el recurso de la imagen

        // Asignar los datos a las vistas
        val btnContinuar = findViewById<Button>(R.id.btnRegistrar)
        btnContinuar.setOnClickListener {
            val intent = Intent(this, Cliente::class.java)
            intent.putExtra("nombreProducto", nombre)
            intent.putExtra("precioProducto", precio)
            intent.putExtra("imagenProducto", imagen)
            startActivity(intent)
        }
    }
}
