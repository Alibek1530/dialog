package uz.tis.dialog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*


class ProfilFillViewModel : ViewModel() {
    var list = arrayListOf<ModelDialogCity>()

    val queryResult = MutableLiveData<List<ModelDialogCity>>()
    init {
        list.add(ModelDialogCity(1,"Tashkent"))
        list.add(ModelDialogCity(2,"Samarqand"))
        list.add(ModelDialogCity(3,"Farg'ona"))
        list.add(ModelDialogCity(4,"Andijan"))
        list.add(ModelDialogCity(5,"Namangan"))
        list.add(ModelDialogCity(6,"Buxoro"))
        list.add(ModelDialogCity(7,"Qarshi"))
        list.add(ModelDialogCity(8,"Temiz"))
        list.add(ModelDialogCity(9,"Navoi"))
        list.add(ModelDialogCity(10,"Nukus"))
        list.add(ModelDialogCity(11,"Urganch"))
        list.add(ModelDialogCity(12,"Beruniy"))
        list.add(ModelDialogCity(13,"Chirchiq"))
        list.add(ModelDialogCity(14,"Chimyon"))
        list.add(ModelDialogCity(15,"Gazalkent"))
        list.add(ModelDialogCity(16,"Bekobod"))
    }


    fun setQuerySearch(query: String) {
        if (!query.equals("")){
            var queryy = query.trim().toLowerCase(Locale.getDefault())
            var listReturn = arrayListOf<ModelDialogCity>()
            if (queryy.length==0){
                listReturn.addAll(list)
            }else{
                for (wp in list){
                    if (wp.city.toLowerCase(Locale.getDefault()).contains(queryy)) {
                        listReturn.add(wp)
                    }
                }
                queryResult.value=listReturn
            }
        }else{
            queryResult.value=list
        }

    }
    fun setPoss(pos:Int):String{
        return queryResult.value?.get(pos)?.city!!
    }

}