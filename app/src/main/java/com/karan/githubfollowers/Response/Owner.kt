package com.karan.githubfollowers.Response

import com.google.gson.annotations.SerializedName

data class Owner (
    @SerializedName("login"       ) var login      : String? = null,  // ownerName
    @SerializedName("id"          ) var id         : Int?    = null,
    @SerializedName("node_id"     ) var nodeId     : String? = null,
    @SerializedName("avatar_url"  ) var avatarUrl  : String? = null,   // iconUrl
    @SerializedName("gravatar_id" ) var gravatarId : String? = null,
    @SerializedName("url"         ) var url        : String? = null,
    @SerializedName("html_url"    ) var htmlUrl    : String? = null

)