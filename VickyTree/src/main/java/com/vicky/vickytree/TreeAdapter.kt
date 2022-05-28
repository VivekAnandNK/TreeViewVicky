package com.vicky.vickytree

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TreeAdapter : RecyclerView.Adapter<TreeAdapter.TreeViewHolder>() {

    var treeList = ArrayList<TreeModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreeViewHolder {

    }

    override fun onBindViewHolder(holder: TreeViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
       return treeList.size
    }


    class TreeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}