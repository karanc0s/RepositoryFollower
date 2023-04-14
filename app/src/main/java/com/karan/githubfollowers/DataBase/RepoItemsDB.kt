package com.karan.githubfollowers.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.karan.githubfollowers.Model.RepoItem

private const val DATABSE_NAME = "Fav_Repo_DB"

@Database(entities = [RepoItem::class] , version = 1)

abstract class RepoItemsDB : RoomDatabase(){

    abstract fun getDao() : RepoItemDao

    companion object{
        var sInstance : RepoItemsDB? = null

        @Synchronized
        fun getInstance(context : Context) : RepoItemsDB{

            if(sInstance == null) {
                sInstance = Room.databaseBuilder(
                    context.applicationContext,
                    RepoItemsDB::class.java,
                    DATABSE_NAME
                ).allowMainThreadQueries().build()
            }
            return sInstance!!
        }
    }
}