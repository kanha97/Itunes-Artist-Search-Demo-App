package com.devkanhaiya.itunessearchprojectkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.devkanhaiya.itunessearchprojectkotlin.DataBase.OffData
import com.devkanhaiya.itunessearchprojectkotlin.Networking.requestAPI
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_itunes.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

private const val  BASE_URL= "https://itunes.apple.com"

class MainActivity : AppCompatActivity() {
    lateinit var countDownTimer : CountDownTimer

    lateinit var viewModel: ItunesModel

    var querys :String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setSupportActionBar(toolbar)


        setUpSearchView()
        makeApirequest()


        rv_recyclerview.layoutManager = GridLayoutManager(this,2)
        val mAdapter: ItunesAdapter=ItunesAdapter()

        rv_recyclerview.adapter = mAdapter
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(ItunesModel::class.java)

        viewModel.allData.observe(this, Observer {list ->
            list?.let {
                mAdapter.updateList(it)
            }

        })

    }

    private fun setUpSearchView() {
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                search_view.clearFocus()
                search_view.setQuery("",false)
                search_view.onActionViewCollapsed()
                querys= query.toString()
                recent.text = "$querys search......"
                makeApirequest()
                return true
            }


            override fun onQueryTextChange(newText: String?): Boolean {
                querys= newText.toString()
                makeApirequest()
                return true            }



        })
    }


    private fun makeApirequest() {
        progress_bar.visibility = View.VISIBLE
        val api= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(requestAPI::class.java)

        GlobalScope.launch(Dispatchers.IO) {

            try {
                val response = api.getData(querys)


                viewModel.deleteData()

                for(article in response.results){

                    Log.i("MainActivity","response = $article")


                    viewModel.insertData(OffData(article.trackName,
                        article.artworkUrl100,
                        article.previewUrl))

                }
                withContext(Dispatchers.Main){
                    progress_bar.visibility = View.GONE

                }

            }catch (e : Exception){

                Log.e("MainActivity", "error = $e")
                withContext(Dispatchers.Main) {
                    progress_bar.visibility = View.GONE

                    attemptRequestAgain()

                }

            }
        }
    }



    private fun attemptRequestAgain() {
        countDownTimer = object : CountDownTimer(5 * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                progress_bar.visibility = View.GONE

                recent.visibility = View.VISIBLE
                Toast.makeText(
                    applicationContext,
                    "Trying To Retrieve Data Check Your Internet Connection  ${millisUntilFinished/1000}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onFinish() {
                makeApirequest()
                countDownTimer.cancel()
            }

        }
        countDownTimer.start()
    }


}