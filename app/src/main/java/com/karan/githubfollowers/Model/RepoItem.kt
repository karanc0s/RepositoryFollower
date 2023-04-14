package com.karan.githubfollowers.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

const val TABLE_NAME : String = "repo_item"

@Entity(tableName = TABLE_NAME)
data class RepoItem(

    @ColumnInfo(name = "repo_id")
    val repoId : Int?,

    val nodeId : String?,

    val repoName : String?,

    val repoDescription : String?,

    val repoOwner : String? ,

    val repoUrl : String?,

    val ownerIconUrl : String?

) : Serializable{

    @PrimaryKey(autoGenerate = true)
    var id : Int? = null
}
