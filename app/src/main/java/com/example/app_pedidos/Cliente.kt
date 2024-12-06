package com.example.app_pedidos

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Cliente : AppCompatActivity() {

    private lateinit var nombreClienteText: TextView
    private lateinit var direccionEntregaEdit: EditText
    private lateinit var metodoPagoSpinner: Spinner
    private lateinit var btnRegistrar: Button

    private var nombreProducto: String? = null
    private var precioProducto: Double = 0.0
    private var imagenProducto: Int = 0
    private var talla: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cliente)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        nombreProducto = intent.getStringExtra("nombre")
        precioProducto = intent.getDoubleExtra("precio", 0.0)
        imagenProducto = intent.getIntExtra("imagen", 0)
        talla = intent.getIntExtra("imagen", 0)

        // Inicializar vistas
        nombreClienteText = findViewById(R.id.nombreClienteText)
        direccionEntregaEdit = findViewById(R.id.direccionEntregaEdit)
        metodoPagoSpinner = findViewById(R.id.metodoPagoSpinner)
        btnRegistrar = findViewById(R.id.btnRegistrar)

        // Configurar Spinner
        val metodosPago = arrayOf("Tarjeta de crédito", "Transferencia bancaria", "PayPal", "Efectivo")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, metodosPago)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        metodoPagoSpinner.adapter = adapter

        // Acción al registrar
        btnRegistrar.setOnClickListener {
            enviarDatos()
        }
    }

    private fun enviarDatos() {
        val nombreCliente = nombreClienteText.text.toString()
        val direccionEntrega = direccionEntregaEdit.text.toString()
        val metodoPago = metodoPagoSpinner.selectedItem.toString()

        if (nombreCliente.isEmpty()) {
            nombreClienteText.error = "Ingrese un nombre"
            return
        }
        if (direccionEntrega.isEmpty()) {
            direccionEntregaEdit.error = "Ingrese una dirección"
            return
        }

        // Crear Intent para enviar datos a la siguiente actividad
//        val intent = Intent(this, ResumenActivity::class.java)
//        intent.putExtra("nombreCliente", nombreCliente)
//        intent.putExtra("direccionEntrega", direccionEntrega)
//        intent.putExtra("metodoPago", metodoPago)
//
//        // Agregar datos del producto
//        intent.putExtra("nombreProducto", nombreProducto)
//        intent.putExtra("precioProducto", precioProducto)
//        intent.putExtra("imagenProducto", imagenProducto)
//
//        startActivity(intent)
    }

}