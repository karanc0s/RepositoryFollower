package com.karan.githubfollowers.Response

import com.google.gson.annotations.SerializedName

data class RepoResponse(
    @SerializedName("id"          ) var id          : Int?     = null,   // repoID
    @SerializedName("node_id"     ) var nodeId      : String?  = null,   // nodeId
    @SerializedName("name"        ) var name        : String?  = null,   // repoName
    @SerializedName("full_name"   ) var fullName    : String?  = null,
    //@SerializedName("private"     ) var private     : Boolean? = null,
    @SerializedName("owner"       ) var owner       : Owner?   = Owner(),
    @SerializedName("html_url"    ) var htmlUrl     : String?  = null,    // repoUrl
    @SerializedName("description" ) var description : String?  = null,    // repoDescription
    @SerializedName("fork"        ) var fork        : Boolean? = null,
    @SerializedName("url"         ) var url         : String?  = null,
    @SerializedName("language"    ) var language    : String?  = null,
    @SerializedName("visibility"  ) var visibility  : String?  = null
)
