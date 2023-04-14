package com.karan.githubfollowers.ViewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.karan.githubfollowers.Model.RepoItem
import com.karan.githubfollowers.Repository.Repository

class MainViewModel : ViewModel() {


    fun insert(context: Context , item : RepoItem){
        Repository.insertItem(context , item)
    }

    fun getAllItems(context: Context) : LiveData<List<RepoItem>>{
        return Repository.getAllItems(context)
    }

    fun searchItem(context: Context , repoId : Int) : Boolean{
        return Repository.searchItem(context, repoId)
    }

    fun delete(context: Context , item : RepoItem){
        Repository.deleteItem(context , item)
    }

    fun update(context: Context , item : RepoItem){
        Repository.updateItem(context , item)
    }


}