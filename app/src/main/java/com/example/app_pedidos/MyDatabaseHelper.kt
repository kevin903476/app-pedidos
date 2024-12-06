package com.example.app_pedidos


import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabaseHelper(context: Context) : SQLiteOpenHelper(context, NOMBRE_BASE_DATOS, null, VERSION_BASE_DATOS) {

    companion object {
        const val NOMBRE_BASE_DATOS = "tienda.db"
        const val VERSION_BASE_DATOS = 1

        // Tabla Pedidos
        const val TABLA_PEDIDOS = "pedidos"
        const val COLUMNA_ID_PEDIDO = "idPedido"
        const val COLUMNA_NOMBRE_PRODUCTO = "nombreProducto"
        const val COLUMNA_CANTIDAD = "cantidad"
        const val COLUMNA_TALLA = "talla"
        const val COLUMNA_PRECIO = "precio"
        const val COLUMNA_NOMBRE_CLIENTE = "nombreCliente"
        const val COLUMNA_DIRECCION = "direccion"
        const val COLUMNA_METODO_PAGO = "metodoPago"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val crearTablaPedidos = """
            CREATE TABLE $TABLA_PEDIDOS (
                $COLUMNA_ID_PEDIDO INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMNA_NOMBRE_PRODUCTO TEXT NOT NULL,
                $COLUMNA_CANTIDAD INTEGER NOT NULL,
                $COLUMNA_TALLA TEXT NOT NULL,
                $COLUMNA_PRECIO REAL NOT NULL,
                $COLUMNA_NOMBRE_CLIENTE TEXT NOT NULL,
                $COLUMNA_DIRECCION TEXT NOT NULL,
                $COLUMNA_METODO_PAGO TEXT NOT NULL
            )
        """
        db.execSQL(crearTablaPedidos)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLA_PEDIDOS")
        onCreate(db)
    }

    // Método para insertar un pedido
    fun insertarPedido(
        nombreProducto: String,
        cantidad: Int,
        talla: String,
        precio: Double,
        nombreCliente: String,
        direccion: String,
        metodoPago: String
    ): Long {
        val db = writableDatabase
        val valores = ContentValues().apply {
            put(COLUMNA_NOMBRE_PRODUCTO, nombreProducto)
            put(COLUMNA_CANTIDAD, cantidad)
            put(COLUMNA_TALLA, talla)
            put(COLUMNA_PRECIO, precio)
            put(COLUMNA_NOMBRE_CLIENTE, nombreCliente)
            put(COLUMNA_DIRECCION, direccion)
            put(COLUMNA_METODO_PAGO, metodoPago)
        }
        return db.insert(TABLA_PEDIDOS, null, valores)
    }

    // Método para actualizar un pedido
    fun actualizarPedido(
        id: Int,
        nombreProducto: String,
        cantidad: Int,
        talla: String,
        precio: Double,
        nombreCliente: String,
        direccion: String,
        metodoPago: String
    ): Int {
        val db = writableDatabase
        val valores = ContentValues().apply {
            put(COLUMNA_NOMBRE_PRODUCTO, nombreProducto)
            put(COLUMNA_CANTIDAD, cantidad)
            put(COLUMNA_TALLA, talla)
            put(COLUMNA_PRECIO, precio)
            put(COLUMNA_NOMBRE_CLIENTE, nombreCliente)
            put(COLUMNA_DIRECCION, direccion)
            put(COLUMNA_METODO_PAGO, metodoPago)
        }
        return db.update(TABLA_PEDIDOS, valores, "$COLUMNA_ID_PEDIDO=?", arrayOf(id.toString()))
    }

    // Método para eliminar un pedido
    fun eliminarPedido(id: Int): Int {
        val db = writableDatabase
        return db.delete(TABLA_PEDIDOS, "$COLUMNA_ID_PEDIDO=?", arrayOf(id.toString()))
    }

    // Método para obtener todos los pedidos
    fun obtenerPedidos(): List<Pedido> {
        val db = readableDatabase
        val listaPedidos = mutableListOf<Pedido>()
        val cursor = db.query(TABLA_PEDIDOS, null, null, null, null, null, "$COLUMNA_NOMBRE_PRODUCTO ASC")
        cursor.use {
            while (it.moveToNext()) {
                val pedido = Pedido(
                    id = it.getInt(it.getColumnIndexOrThrow(COLUMNA_ID_PEDIDO)),
                    nombreProducto = it.getString(it.getColumnIndexOrThrow(COLUMNA_NOMBRE_PRODUCTO)),
                    cantidad = it.getInt(it.getColumnIndexOrThrow(COLUMNA_CANTIDAD)),
                    talla = it.getString(it.getColumnIndexOrThrow(COLUMNA_TALLA)),
                    precio = it.getDouble(it.getColumnIndexOrThrow(COLUMNA_PRECIO)),
                    nombreCliente = it.getString(it.getColumnIndexOrThrow(COLUMNA_NOMBRE_CLIENTE)),
                    direccion = it.getString(it.getColumnIndexOrThrow(COLUMNA_DIRECCION)),
                    metodoPago = it.getString(it.getColumnIndexOrThrow(COLUMNA_METODO_PAGO))
                )
                listaPedidos.add(pedido)
            }
        }
        return listaPedidos
    }
}

