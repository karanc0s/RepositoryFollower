package com.karan.githubfollowers.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.karan.githubfollowers.DataBase.RepoItemsDB
import com.karan.githubfollowers.Model.RepoItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val TAG = "REPO :- "
class Repository {
    companion object{
        private lateinit var db : RepoItemsDB

        private fun getDB(context: Context) : RepoItemsDB{
            return RepoItemsDB.getInstance(context)
        }

        fun getAllItems(context: Context) : LiveData<List<RepoItem>>{
            db = getDB(context)
            return db.getDao().getAll()
        }
        fun searchItem(context: Context , repoId : Int) : Boolean{
            db = getDB(context)
            return db.getDao().search(repoId)
        }
        fun insertItem(context: Context , item: RepoItem){
            db = getDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                db.getDao().insert(item)
            }
        }
        fun deleteItem(context: Context , item: RepoItem){
            db = getDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                db.getDao().delete(item)
            }
        }
        fun updateItem(context: Context , item: RepoItem){
            db = getDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                db.getDao().update(item)
            }
        }






    }
}