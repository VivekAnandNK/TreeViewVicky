package com.vicky.treeview.api

import com.vicky.treeview.api.ICResponseEnvelope
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiInterface {


    @Headers("Content-Type: text/xml;charset=UTF-8")
    @GET("v3/bcb080be-2730-4b55-af0e-5cfe9ca321c3")
    fun getClassification(): Observable<ICResponseEnvelope>

}
