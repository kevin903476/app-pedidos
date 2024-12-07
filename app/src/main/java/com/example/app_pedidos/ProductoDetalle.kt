package com.example.app_pedidos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
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

        // Asignar los valores a los elementos de la vista
        findViewById<TextView>(R.id.textViewNombre).text = nombre
        findViewById<TextView>(R.id.textViewPrecio).text = "Precio: $${precio}"

        // Establecer la imagen
        findViewById<ImageView>(R.id.imageViewProducto).setImageResource(imagen)

        // Obtener el RadioGroup y los RadioButton
        val radioGroup: RadioGroup = findViewById(R.id.radioGroupTalla)
        val cantidad: EditText = findViewById(R.id.editCantidad)

        // Asignar el botón de continuar
        val btnContinuar = findViewById<Button>(R.id.btnRegistrar)
        btnContinuar.setOnClickListener {
            if (validarDatos(radioGroup, cantidad, nombre, precio, imagen)) {
                // Obtener la talla seleccionada del RadioGroup
                val selectedId = radioGroup.checkedRadioButtonId
                val selectedRadioButton = findViewById<RadioButton>(selectedId)
                val tallaSeleccionada = selectedRadioButton.text.toString()
                val cantidadIngresada = cantidad.text.toString()

                // Crear el Intent para pasar los datos a la actividad Cliente
                val intent = Intent(this, Cliente::class.java).apply {
                    putExtra("nombre", nombre)
                    putExtra("precio", precio)
                    putExtra("imagen", imagen)
                    putExtra("talla", tallaSeleccionada)  // Enviar la talla seleccionada
                    putExtra("cantidad", cantidadIngresada)
                }

                // Iniciar la actividad Cliente
                startActivity(intent)
            }
        }
    }

    private fun validarDatos(
        radioGroup: RadioGroup,
        cantidad: EditText,
        nombre: String?,
        precio: Double,
        imagen: Int
    ): Boolean {
        // Validar selección de talla
        val selectedId = radioGroup.checkedRadioButtonId
        if (selectedId == -1) {
            Toast.makeText(this, "Seleccione una talla", Toast.LENGTH_SHORT).show()
            return false
        }

        // Validar cantidad
        val cantidadText = cantidad.text.toString().trim()
        if (cantidadText.isEmpty()) {
            cantidad.error = "Ingrese una cantidad"
            return false
        }

        val cantidadValue = cantidadText.toIntOrNull()
        if (cantidadValue == null || cantidadValue <= 0) {
            cantidad.error = "La cantidad debe ser mayor a 0"
            return false
        }

        return true
    }

}

