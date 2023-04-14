package com.karan.githubfollowers.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val TAG = "RetrofitClient :"
class RetrofitClient {
    companion object {
        private val BASE_URL: String = "https://api.github.com/"
        private var sInstance : Channel? = null

        @Synchronized
        fun getInstance(): Channel {
            if (sInstance == null) {
                val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                sInstance = retrofit.create(Channel::class.java)
            }
            return sInstance!!
        }


    }
}