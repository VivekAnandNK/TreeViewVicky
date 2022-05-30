package com.vicky.treeview

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vicky.treeview.api.ApiClient
import com.vicky.treeview.api.ApiRequest
import com.vicky.treeview.api.ICResponseEnvelope
import com.vicky.treeview.api.ServerCallback
import com.vicky.vickytree.TreeAdapter
import com.vicky.vickytree.TreeBranchModel
import io.reactivex.Observable
import java.util.*

class MainActivity : AppCompatActivity() {

    var dataList = ArrayList<TreeBranchModel>()

    var formatedList = ArrayList<TreeBranchModel>()

    lateinit var recyclerView: RecyclerView
    lateinit var treeAdapter: TreeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getList()

        recyclerView = findViewById(R.id.recyclermain)
        treeAdapter = TreeAdapter()
        recyclerView.apply {
            adapter=treeAdapter
            layoutManager=LinearLayoutManager(this@MainActivity)
        }

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
                            Log.d(
                                "TAG",
                                "onSuccess: " + classification.icResponseBody.icResponseModel.allClassifications.size
                            )

                            var listOfClassification = ArrayList<TreeBranchModel>()



                            classification.icResponseBody.icResponseModel.allClassifications.forEach {
                                var treeBranchModel = TreeBranchModel()
                                treeBranchModel.id = it.classificationID.toString()
                                treeBranchModel.parentId = it.parentClassificationID.toString()
                                treeBranchModel.title = it.classificationDesc
                                listOfClassification.add(treeBranchModel)
                                // treeBranchModel.index=
                            }

                            treeAdapter.setTree(listOfClassification)


                        } catch (e: Exception) {
                            Log.d("TAG", "onSuccess: " + e.toString())
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