package com.example.app_pedidos

import android.os.Bundle
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

        val nombre = intent.getStringExtra("nombre")
        val precio = intent.getDoubleExtra("precio", 0.0)
        val talla = intent.getStringExtra("talla")
        val cantidad = intent.getIntExtra("cantidad", 0)
        val imagen = intent.getIntExtra("imagen", 0)


        findViewById<TextView>(R.id.textViewNombre).text = nombre
        findViewById<TextView>(R.id.textViewPrecio).text = "Precio: $${precio}"
        findViewById<TextView>(R.id.textViewTalla).text = "Talla: $talla"
        findViewById<TextView>(R.id.textViewCantidad).text = "Cantidad: $cantidad"
        findViewById<ImageView>(R.id.imageViewProducto).setImageResource(imagen)
    }
}