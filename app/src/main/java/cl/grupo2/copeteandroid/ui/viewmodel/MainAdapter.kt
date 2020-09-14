package cl.grupo2.copeteandroid.ui.viewmodel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import cl.grupo2.copeteandroid.R
import cl.grupo2.copeteandroid.base.BaseViewHolder
import cl.grupo2.copeteandroid.data.model.Drink
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.tragos_row.view.*
import java.security.AccessControlContext

class MainAdapter(private val context: Context, private val tragosList:List<Drink>,
                  private val itemClickListener: OnTragoClickListener)
    :RecyclerView.Adapter<BaseViewHolder<*>>(){

    //ver si esta interfaz la hago aparte, es para el click en el recycler
    interface OnTragoClickListener{
        fun onTragoClick(drink: Drink)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(LayoutInflater.from(context).inflate(R.layout.tragos_row,parent,false))
    }

    override fun getItemCount(): Int {
        return tragosList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder){
            is MainViewHolder -> holder.bind(tragosList[position],position)
        }
    }
    inner class MainViewHolder (itemView: View): BaseViewHolder<Drink>(itemView){
        override fun bind(item: Drink, position: Int) {
            Glide.with(context).load(item.imagen).centerCrop().into(itemView.ivImagenItem)
            itemView.tvTituloItem.text = item.nombre
            itemView.tvDescripcionItem.text = item.descripcion
            itemView.setOnClickListener{ itemClickListener.onTragoClick(item)}
        }
    }
}