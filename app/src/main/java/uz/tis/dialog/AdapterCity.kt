package uz.tis.dialog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterCity() :
    RecyclerView.Adapter<AdapterCity.MyHolder>() {

    lateinit var list: List<ModelDialogCity>

    fun setListCity(listt: List<ModelDialogCity>){
        this.list=listt
    }

    inner class MyHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var tvCity: TextView

        init {
            tvCity = view.findViewById(R.id.tvDialogCity)
        }

        fun bind(pos: Int) {
            //         view.layoutParams.width=ViewGroup.LayoutParams.MATCH_PARENT
            tvCity.text = list[pos].city

            view.setOnClickListener {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        return MyHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(position)
    }

}