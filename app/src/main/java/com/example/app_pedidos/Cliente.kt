package com.example.app_pedidos

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cliente)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        nombreClienteText = findViewById(R.id.nombreClienteText)
        direccionEntregaEdit = findViewById(R.id.direccionEntregaEdit)
        metodoPagoSpinner = findViewById(R.id.metodoPagoSpinner)
        btnRegistrar = findViewById(R.id.btnRegistrar)

        val metodosPago = arrayOf("Tarjeta de crédito", "Transferencia bancaria", "PayPal", "Efectivo")
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            metodosPago
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        metodoPagoSpinner.adapter = adapter

        btnRegistrar.setOnClickListener {
            registrarCliente()
        }
    }
    private fun registrarCliente() {
        val nombre = nombreClienteText.text.toString()
        val direccion = direccionEntregaEdit.text.toString()
        val metodoPago = metodoPagoSpinner.selectedItem.toString()

        if (nombre.isEmpty()) {
            nombreClienteText.error = "Ingrese un nombre"
            return
        }

        if (direccion.isEmpty()) {
            direccionEntregaEdit.error = "Ingrese una dirección"
            return
        }
        Toast.makeText(
            this,
            "Cliente registrado:\nNombre: $nombre\nDirección: $direccion\nMétodo de Pago: $metodoPago",
            Toast.LENGTH_LONG
        ).show()
    }
}