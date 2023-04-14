package com.karan.githubfollowers.Retrofit

import com.karan.githubfollowers.Response.RepoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface Channel {

    @GET("/repos/{owner}/{repo}")
    fun getRepo(@Path("owner") ownerName : String ,
                @Path("repo") repoName : String) : Call<RepoResponse>


    @GET("repositories")
    fun getPublicRepos() : Call<Object>


}