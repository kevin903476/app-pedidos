package com.example.app_pedidos

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class mostrarPedidos : AppCompatActivity() {

    private lateinit var dbHelper: MyDatabaseHelper // Clase que interactúa con la base de datos
    private lateinit var adapter: PedidoAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_pedidos)

        // Inicializar DBHelper y RecyclerView
        dbHelper = MyDatabaseHelper(this)
        recyclerView = findViewById(R.id.recyclerViewPedidos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Botón flotante para agregar un nuevo pedido
//        findViewById<FloatingActionButton>(R.id.agregarPedido).setOnClickListener {
//            startActivity(Intent(this, AgregarPedidoActivity::class.java))
//        }

        // Cargar la lista de pedidos
        loadPedidos()
    }

    private fun loadPedidos() {
        val pedidos = dbHelper.obtenerPedidos()
        adapter = PedidoAdapter(
            pedidos,
            onEliminarClick = { pedido ->
                deletePedido(pedido)
            }
        )
        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        loadPedidos() // Refrescar la lista de pedidos al reanudar la actividad
    }

    private fun viewPedidoDetails(pedido: Pedido) {
        // Aquí puedes mostrar más detalles o editar el pedido
        Toast.makeText(this, "Pedido de ${pedido.nombreCliente}", Toast.LENGTH_SHORT).show()
    }

    private fun deletePedido(pedido: Pedido) {
        dbHelper.eliminarPedido(pedido.id)
        Toast.makeText(this, "Pedido eliminado", Toast.LENGTH_SHORT).show()
        loadPedidos() // Actualizar la lista
    }
}
