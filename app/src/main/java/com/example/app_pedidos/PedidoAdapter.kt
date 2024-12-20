package com.example.app_pedidos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PedidoAdapter(
    private val pedidos: List<Pedido>, // Lista de pedidos
    private val onEliminarClick: (Pedido) -> Unit,
    private val onPedidoClick: ((Pedido) -> Unit)? = null, // Evento opcional al hacer clic
    private val onPedidoLongClick: ((Pedido) -> Unit)? = null // Evento opcional al hacer clic prolongado
) : RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder>() {

    inner class PedidoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreProductoTextView: TextView = view.findViewById(R.id.nombreProducto)
        val cantidadTextView: TextView = view.findViewById(R.id.cantidad)
        val tallaTextView: TextView = view.findViewById(R.id.talla)
        val precioTextView: TextView = view.findViewById(R.id.precio)
        val nombreClienteTextView: TextView = view.findViewById(R.id.nombreCliente)
        val direccionTextView: TextView = view.findViewById(R.id.direccion)
        val metodoPagoTextView: TextView = view.findViewById(R.id.metodoPago)
        val imagenPedidoImageView: ImageView = view.findViewById(R.id.imagenPedido) // Imagen del pedido
        val btnEliminar: Button = itemView.findViewById(R.id.btnEliminarPedido)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pedido, parent, false)
        return PedidoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PedidoViewHolder, position: Int) {
        val pedido = pedidos[position]

        // Asignar valores a los TextViews

        holder.nombreProductoTextView.text = "Producto: ${pedido.nombreProducto}"
        holder.cantidadTextView.text = "Cantidad: ${pedido.cantidad}"
        holder.tallaTextView.text = "Talla: ${pedido.talla}"
        holder.precioTextView.text = "Precio: $${pedido.precio}"
        holder.nombreClienteTextView.text = "Cliente: ${pedido.nombreCliente}"
        holder.direccionTextView.text = "Dirección: ${pedido.direccion}"
        holder.metodoPagoTextView.text = "Método de pago: ${pedido.metodoPago}"

        // Asignar la imagen desde los recursos drawable usando setImageResource
        if (pedido.imagen != 0) {
            holder.imagenPedidoImageView.setImageResource(pedido.imagen)
        } else {
            holder.imagenPedidoImageView.setImageResource(R.drawable.ic_launcher_background) // Imagen predeterminada
        }

        // Configurar eventos de clic y clic prolongado
        holder.itemView.setOnClickListener {
            onPedidoClick?.invoke(pedido) // Llamar al callback de clic si está definido
        }
        holder.itemView.setOnLongClickListener {
            onPedidoLongClick?.invoke(pedido) // Llamar al callback de clic prolongado si está definido
            true // Indicar que el evento ha sido manejado
        }
        // Acción del botón eliminar
        holder.btnEliminar.setOnClickListener {
            onEliminarClick(pedido)
        }
    }

    override fun getItemCount() = pedidos.size


}
