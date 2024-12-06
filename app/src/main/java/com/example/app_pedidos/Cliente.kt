package com.example.app_pedidos

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
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
    private lateinit var radioGroupPago: RadioGroup
    private lateinit var btnRegistrar: Button

    private var nombreProducto: String? = null
    private var precioProducto: Double = 0.0
    private var imagenProducto: Int = 0
    private var talla: String? = null // Cambiado a String para manejar la talla como texto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cliente)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Obtener los datos enviados
        nombreProducto = intent.getStringExtra("nombre")
        precioProducto = intent.getDoubleExtra("precio", 0.0)
        imagenProducto = intent.getIntExtra("imagen", 0)
        talla = intent.getStringExtra("talla")  // Obtener la talla como String

        // Inicializar vistas
        nombreClienteText = findViewById(R.id.nombreClienteText)
        direccionEntregaEdit = findViewById(R.id.direccionEntregaEdit)
        radioGroupPago = findViewById(R.id.radioGroupPago)
        btnRegistrar = findViewById(R.id.btnRegistrar)

        btnRegistrar.setOnClickListener {
            enviarDatos()
        }
    }

    private fun enviarDatos() {
        val nombreCliente = nombreClienteText.text.toString()
        val direccionEntrega = direccionEntregaEdit.text.toString()

        // Obtener el método de pago seleccionado
        val selectedId = radioGroupPago.checkedRadioButtonId
        val radioButton = findViewById<RadioButton>(selectedId)
        val metodoPago = radioButton?.text.toString()

        if (nombreCliente.isEmpty()) {
            nombreClienteText.error = "Ingrese un nombre"
            return
        }
        if (direccionEntrega.isEmpty()) {
            direccionEntregaEdit.error = "Ingrese una dirección"
            return
        }

//        // Enviar los datos del cliente y el método de pago
//        val intent = Intent(this, ResumenActivity::class.java).apply {
//
//            // Enviar datos del cliente
//            putExtra("nombreCliente", nombreCliente)
//            putExtra("direccionEntrega", direccionEntrega)
//            putExtra("metodoPago", metodoPago)
//            putExtra("nombreProducto", nombreProducto)
//            putExtra("precioProducto", precioProducto)
//            putExtra("imagenProducto", imagenProducto)
//            putExtra("talla", talla)
//        }
//        startActivity(intent)
    }
}
