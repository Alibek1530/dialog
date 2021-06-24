package uz.tis.dialog

import androidx.recyclerview.widget.DiffUtil

class ProfilFillDiffUtilCallback(private val oldList: List<ModelDialogCity>, private val newList: List<ModelDialogCity>) :
    DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int):Boolean{
       return oldList[oldItemPosition]!=newList[newItemPosition]
    }


}