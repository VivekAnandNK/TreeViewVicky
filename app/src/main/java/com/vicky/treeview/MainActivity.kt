package com.vicky.treeview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vicky.vickytree.TreeBranchModel

class MainActivity : AppCompatActivity() {

    var dataList = ArrayList<TreeBranchModel>()

    var formatedList = ArrayList<TreeBranchModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }
}