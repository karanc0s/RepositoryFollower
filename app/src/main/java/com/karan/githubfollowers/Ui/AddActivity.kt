package com.karan.githubfollowers.Ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.karan.githubfollowers.Model.RepoItem
import com.karan.githubfollowers.R
import com.karan.githubfollowers.Repository.Repository
import com.karan.githubfollowers.Response.RepoResponse
import com.karan.githubfollowers.Retrofit.RetrofitClient
import com.karan.githubfollowers.ViewModels.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG = " Add Activity : "
class AddActivity : AppCompatActivity() {

    private lateinit var viewModel : MainViewModel
    private lateinit var etRepoName : AppCompatEditText
    private lateinit var etOwnerName : AppCompatEditText
    private lateinit var btnAddRepo : AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#24292e")))

        initialize()
        viewModel = MainViewModel()

        btnAddRepo.setOnClickListener{
            val repo = etRepoName.text.toString()
            val owner = etOwnerName.text.toString()

            if(repo.isEmpty()){
                etRepoName.error = "Please Fill"
            }else if(owner.isEmpty()){
                etRepoName.error = "Please Fill"
            }else{
                Log.d(TAG, "onCreate: ,$repo, ,$owner," )
                CoroutineScope(Dispatchers.IO).launch {
                    getRepo(owner , repo)
                }
            }

        }

    }

    private fun initialize(){
        etRepoName = findViewById(R.id.et_add_reponame)
        etOwnerName = findViewById(R.id.et_add_ownername)
        btnAddRepo = findViewById(R.id.btn_add)
    }

    fun getRepo(owner : String , repo : String) {

        val response = RetrofitClient.getInstance().getRepo(owner , repo)

        response.enqueue(object : Callback<RepoResponse> {
            override fun onResponse(call: Call<RepoResponse>, response: Response<RepoResponse>) {
                Log.d(TAG, "onResponse: "+response.code() +"\n"+ response.body() +"\n" + response.message() + "")

                if(response.code() == 200 ){
                    val item = getItem(response.body())

                    if(item != null){

                        if(!Repository.searchItem(applicationContext,item.repoId!!)) {
                            viewModel.insert(applicationContext, item)
                            finish()
                        }else{
                            Toast.makeText(applicationContext , "Already Added" , Toast.LENGTH_SHORT).show()
                        }
                    }

                }else{
                    Toast.makeText(applicationContext , "Error Code : ${response.code()}" , Toast.LENGTH_SHORT).show()
                }



            }

            override fun onFailure(call: Call<RepoResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: $t")
                Toast.makeText(applicationContext , "Error Code : $t" , Toast.LENGTH_SHORT).show()

            }
        })
    }
    private fun getItem(body: RepoResponse?): RepoItem {
        return RepoItem(
            body?.id,
            body?.nodeId,
            body?.name,
            body?.description,
            body?.owner?.login,
            body?.htmlUrl,
            body?.owner?.avatarUrl
        )
    }

//    fun contains(item: RepoItem) : Boolean{
//        val repoId = item.repoId!!
//        val item2 = viewModel.searchItem(applicationContext , repoId)
//
//        if(item2.repoId == repoId){
//            return true
//        }
//        return false
//    }



}