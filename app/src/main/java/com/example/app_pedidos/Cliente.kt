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
    private var cantidad: String? = null
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
        cantidad = intent.getStringExtra("cantidad")

        // Inicializar vistas
        nombreClienteText = findViewById(R.id.nombreClienteText)
        direccionEntregaEdit = findViewById(R.id.direccionEntregaEdit)
        radioGroupPago = findViewById(R.id.radioGroupPago)
        btnRegistrar = findViewById(R.id.btnRegistrar)

        btnRegistrar.setOnClickListener {
            if (enviarDatos()) {
                // Solo navegar a la siguiente actividad si el envío de datos es exitoso
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }

        private fun enviarDatos(): Boolean {
            val nombreCliente = nombreClienteText.text.toString()
            val direccionEntrega = direccionEntregaEdit.text.toString()

            // Validar campos obligatorios
            if (nombreCliente.isEmpty()) {
                nombreClienteText.error = "Ingrese un nombre"
                return false
            }
            if (direccionEntrega.isEmpty()) {
                direccionEntregaEdit.error = "Ingrese una dirección"
                return false
            }

            // Validar que se haya seleccionado un método de pago
            val selectedId = radioGroupPago.checkedRadioButtonId
            if (selectedId == -1) {
                // Ningún RadioButton seleccionado
                Toast.makeText(this, "Seleccione un método de pago", Toast.LENGTH_SHORT).show()
                return false
            }

            // Obtener el método de pago seleccionado
            val radioButton = findViewById<RadioButton>(selectedId)
            val metodoPago = radioButton.text.toString()

            // Crear una instancia de la base de datos
            val dbHelper = MyDatabaseHelper(this)

            // Convertir el ID de la imagen a un String para guardarlo en la base de datos
            val imagen = imagenProducto.toString()

            // Insertar los datos en la base de datos
            val resultado = dbHelper.insertarPedido(
                nombreProducto ?: "",
                cantidad?.toInt() ?: 0,  // Convierte la cantidad a Integer
                talla ?: "",             // Talla como String
                precioProducto,
                nombreCliente,
                direccionEntrega,
                metodoPago,
                imagen
            )

            if (resultado != -1L) {
                Toast.makeText(this, "Pedido registrado exitosamente", Toast.LENGTH_SHORT).show()
                return true
            } else {
                Toast.makeText(this, "Error al registrar el pedido", Toast.LENGTH_SHORT).show()
                return false
            }
        }
}
