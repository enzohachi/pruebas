package cl.grupo2.copeteandroid.copete.presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder <T> (itemView: View): RecyclerView.ViewHolder(itemView){
    abstract fun bind (item: T, position: Int)
}