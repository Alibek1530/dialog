package uz.tis.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var btn:Button
    lateinit var mDialogCity: AlertDialog
    var listCity = arrayListOf<ModelDialogCity>()
    lateinit var recyclerSearchCity: RecyclerView
    var adapterCity = AdapterCity()
    private lateinit var viewModel: ProfilFillViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btn=findViewById<Button>(R.id.btn)

        viewModel = ViewModelProvider(this).get(ProfilFillViewModel::class.java)


        btn.setOnClickListener {
            setDialogCity()
        }
    }

    private fun setDialogCity() {
        lateinit var searchCity: SearchView
        lateinit var dialog: View


        var btClose: ImageView

        dialog =
            LayoutInflater.from(this).inflate(R.layout.dialog_city, null)
        btClose = dialog.findViewById(R.id.btCloseCity)
        recyclerSearchCity = dialog.findViewById(R.id.rvSearchCity)
        searchCity = dialog.findViewById(R.id.searchViewCity)


        var bulder = this.let {
            AlertDialog.Builder(it).setView(dialog)
        }

        mDialogCity = bulder!!.show()

        mDialogCity!!.setCancelable(false)

//        val displayMetrics = DisplayMetrics()
//        this.windowManager.defaultDisplay.getRealMetrics(displayMetrics)
//        var width = displayMetrics.widthPixels
//
//        var wid = width * 0.5 / 12.0

        val back = ColorDrawable(Color.TRANSPARENT)
        val inset = InsetDrawable(back, wid.toInt(), 0, wid.toInt(), 0)

        mDialogCity!!.getWindow()!!.setBackgroundDrawable(inset)

        recyclerSearchCity.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapterCity.setListCity(viewModel.list)
        recyclerSearchCity.adapter = adapterCity


        btClose.setOnClickListener {
            mDialogCity.dismiss()
        }

        searchCity.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                viewModel.setQuerySearch(p0.toString())
                return true
            }
        })
        viewModel.queryResult.observe(this, {
            var cityDiff = ProfilFillDiffUtilCallback(viewModel.list, it)
            var cityDiffUtil = DiffUtil.calculateDiff(cityDiff)
            adapterCity.setListCity(it)
            cityDiffUtil.dispatchUpdatesTo(adapterCity)
        })
    }
}