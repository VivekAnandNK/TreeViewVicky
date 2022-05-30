package com.vicky.treeview

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.vicky.treeview.api.ApiClient
import com.vicky.treeview.api.ApiRequest
import com.vicky.treeview.api.ICResponseEnvelope
import com.vicky.treeview.api.ServerCallback
import com.vicky.vickytree.TreeBranchModel
import io.reactivex.Observable
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    var dataList = ArrayList<TreeBranchModel>()

    var formatedList = ArrayList<TreeBranchModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getList()

    }


    fun getList(

    ) {

        val observable: Observable<ICResponseEnvelope>? =
            ApiClient.getApiInterface(this)?.getClassification()
        val apiRequest = ApiRequest()
        apiRequest.requestApi(
            this,
            Collections.singletonList(observable),
            object : ServerCallback {
                override fun onSuccess(`object`: Any?) {

                    val classification: ICResponseEnvelope? =
                        `object` as ICResponseEnvelope?
                    if (classification != null) {
                        try {
                            Log.d("TAG", "onSuccess: "+classification.icResponseBody.icResponseModel.allClassifications.size)

                        } catch (e: Exception) {
                            Log.d("TAG", "onSuccess: "+e.toString())
                        }

                    } else {
                    }
                }

                override fun onTokenExpiry() {

                }

                override fun onError(throwable: Throwable?) {

                }

                override fun onNetworkError() {

                }

                override fun onComplete() {

                }
            })
    }
}