package com.example.overflow

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class productAdapter(context: Context, productos: List<Producto>) :
    ArrayAdapter<Producto>(context, 0, productos) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Inflar el layout personalizado
        val layout = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_producto, parent, false)

        // Obtener el producto actual
        val producto = getItem(position)

        // Llenar el layout con datos del producto
        layout.findViewById<TextView>(R.id.nombreProducto).text = producto?.nombre
        layout.findViewById<ImageView>(R.id.imagenProducto).setImageResource(producto?.imagenId ?: R.mipmap.ic_launcher)

        return layout
    }
}