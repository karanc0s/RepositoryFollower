package com.karan.githubfollowers

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.karan.githubfollowers.Adapter.ItemAdapter
import com.karan.githubfollowers.Interfaces.EventHandler
import com.karan.githubfollowers.Model.RepoItem
import com.karan.githubfollowers.Response.RepoResponse
import com.karan.githubfollowers.Retrofit.RetrofitClient
import com.karan.githubfollowers.Ui.AddActivity
import com.karan.githubfollowers.ViewModels.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG = " Main Activity : "

class MainActivity : AppCompatActivity() , EventHandler{

    private lateinit var recyclerView: RecyclerView
    private lateinit var itemViewModel: MainViewModel
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var itemList: ArrayList<RepoItem>
    private val touchCallback = object : ItemTouchHelper.SimpleCallback(
        0 , ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            val item = itemList[position]

            when(direction){
                ItemTouchHelper.RIGHT->{
                    itemViewModel.delete(this@MainActivity , item)

                }
                ItemTouchHelper.LEFT->{
                    itemViewModel.delete(this@MainActivity , item)
                }
            }
        }

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#24292e")))



        itemViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        initialize()

        itemViewModel.getAllItems(this).observe(this){
            itemAdapter.setData(it as ArrayList<RepoItem>)
            itemList = it
        }


        val itemTouchHelper = ItemTouchHelper(touchCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        //getRes()
    }

    //// Initializations
    private fun initialize(){
        recyclerView = findViewById(R.id.rv_repo_items)
        itemAdapter = ItemAdapter(applicationContext , ArrayList<RepoItem>() , this )
        recyclerView.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(context , LinearLayoutManager.VERTICAL , false)
            setHasFixedSize(true)
        }
    }



    /*fun getRes(){
        val response = RetrofitClient.getInstance().getRepo("exelban" , "stats")

        response.enqueue(object : Callback<RepoResponse>{
            override fun onResponse(call: Call<RepoResponse>, response: Response<RepoResponse>) {
                Log.d(TAG, "onResponse: "+response.code() +"\n"+ response.body() +"\n" + response.message() +

                        "")
            }

            override fun onFailure(call: Call<RepoResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: $t")
            }
        })


    }*/

    private fun share(repoName : String, repoUrl : String){
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TITLE, repoName)
            putExtra(Intent.EXTRA_TEXT , repoUrl)

            type = "text/*"


        }

        val shareIntent = Intent.createChooser(sendIntent, "null")

        startActivity(shareIntent)
    }

    /// MENUS
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.main , menu )
        return true;
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_add -> {
                /// add activity
                startActivity(Intent(this  , AddActivity::class.java ))
                //add()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onClickEvent(position: Int) {
        //Toast.makeText(applicationContext , " position : "+ position , Toast.LENGTH_SHORT).show()
        /// open web browser with that url
        val url = itemList[position].repoUrl
        if(url!=null && url.isNotEmpty()) {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browserIntent)
        }
    }

    override fun onShare(position: Int) {
        val repo = itemList[position].repoName!!
        val url = itemList[position].repoUrl!!
        share(repo , url)
    }


}