package cl.grupo2.copeteandroid.copete.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.grupo2.copeteandroid.copete.domain.Copete
import cl.grupo2.copeteandroid.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_copete.view.*


class MainAdapter(private val context: Context, private val tragosList:List<Copete>,
                  private val itemClickListener: OnCopeteClickListener
) :RecyclerView.Adapter<BaseViewHolder<*>>(){

    interface OnCopeteClickListener{
        fun onCopeteClick(drink: Copete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(LayoutInflater.from(context).inflate(R.layout.item_copete,parent,false))
    }

    override fun getItemCount(): Int {
        return tragosList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder){
            is MainViewHolder -> holder.bind(tragosList[position],position)
        }
    }
    inner class MainViewHolder (itemView: View): BaseViewHolder<Copete>(itemView){
        override fun bind(item: Copete, position: Int) {
            Glide.with(context).load(item.imagen).centerCrop().into(itemView.ivImagenItem)
            itemView.tvTituloItem.text = item.nombre
            itemView.tvDescripcionItem.text = item.descripcion
            itemView.setOnClickListener{ itemClickListener.onCopeteClick(item)}
        }
    }
}