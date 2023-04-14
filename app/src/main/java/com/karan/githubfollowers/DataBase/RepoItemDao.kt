package com.karan.githubfollowers.DataBase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.karan.githubfollowers.Model.RepoItem
import com.karan.githubfollowers.Model.TABLE_NAME

@Dao
interface RepoItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item : RepoItem)

    @Delete
    suspend fun delete(item : RepoItem)

    @Query("SELECT * FROM $TABLE_NAME ORDER BY id DESC")
    fun getAll() : LiveData<List<RepoItem>>

    @Update
    suspend fun update(item : RepoItem)

    //@Query("SELECT * FROM $TABLE_NAME WHERE repo_id LIKE :repoId")

    @Query("SELECT EXISTS (SELECT * FROM $TABLE_NAME WHERE repo_id = :repoId)")
    fun search(repoId : Int) : Boolean



}