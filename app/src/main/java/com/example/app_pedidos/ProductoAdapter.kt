package com.example.app_pedidos

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductoAdapter(
    private val context: Context,
    private val productos: List<Producto>
) : RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_producto, parent, false)
        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = productos[position]
        holder.txtNombre.text = producto.nombre
        holder.txtPrecio.text = "$${producto.precio}"

        // Asignar imagen si está disponible (puedes cambiar según tus datos)
        holder.imgProducto.setImageResource(R.drawable.ic_launcher_background)

        // Acción del botón "Ver detalles"
        holder.btnVerDetalles.setOnClickListener {
//            val intent = Intent(context, DetalleProductoActivity::class.java).apply {
//                putExtra("NOMBRE_PRODUCTO", producto.nombre)
//                putExtra("PRECIO_PRODUCTO", producto.precio)
//            }
//            context.startActivity(intent)
        }
    }

    override fun getItemCount() = productos.size

    class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProducto: ImageView = itemView.findViewById(R.id.imgProducto)
        val txtNombre: TextView = itemView.findViewById(R.id.txtNombreProducto)
        val txtPrecio: TextView = itemView.findViewById(R.id.txtPrecioProducto)
        val btnVerDetalles: Button = itemView.findViewById(R.id.btnVerDetalles)
    }
}
