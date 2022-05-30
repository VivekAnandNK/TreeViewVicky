package com.vicky.vickytree


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class TreeAdapter : RecyclerView.Adapter<TreeAdapter.TreeViewHolder>() {

    var treeList = ArrayList<TreeBranchModel>()
    var dataList = ArrayList<TreeBranchModel>()

    fun setTree(treeBranchList : ArrayList<TreeBranchModel>){


        treeBranchList.forEach {
            if (it.parentId=="0"){
                treeList.add(it)
            }
            this.dataList.add(it)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreeViewHolder {
        return TreeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.tree_item, parent, false))
    }

    override fun onBindViewHolder(holder: TreeViewHolder, position: Int) {
        holder.setTreeItem(treeList[position])
        holder.rootView.setOnClickListener {
            holder.onClickNextList(dataList,treeList[position].id)
        }
    }

    override fun getItemCount(): Int {
       return treeList.size
    }


    class TreeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       lateinit var rootView :ConstraintLayout
       lateinit var recyclerView: RecyclerView
       lateinit var branchAdapter: BranchAdapter
       lateinit var tvTitle : TextView

       var isExpanded = false

       var nextList =ArrayList<TreeBranchModel>()

        fun setTreeItem(treeModel : TreeBranchModel){
            rootView = itemView.findViewById(R.id.treeConstrain)
            recyclerView=itemView.findViewById(R.id.rvTree)
            tvTitle=itemView.findViewById(R.id.tvTree)

            tvTitle.setText(treeModel.title)

            branchAdapter= BranchAdapter()
            recyclerView.apply {
                layoutManager=LinearLayoutManager(itemView.context)
                adapter=branchAdapter
            }


        }

        fun onClickNextList(dataList: ArrayList<TreeBranchModel>, id: String) {

            dataList.forEach {
                if (it.parentId==id){
                    nextList.add(it)
                }
            }
            if (nextList.isNotEmpty()){
                createNextList(nextList,dataList)
            }
            if (isExpanded){
                recyclerView.visibility=View.GONE
            }else if (!isExpanded&&nextList.isNotEmpty()){
                recyclerView.visibility=View.VISIBLE
            }

        }

        fun createNextList(
            nextList: ArrayList<TreeBranchModel>,
            dataList: ArrayList<TreeBranchModel>
        ) {
            branchAdapter.setBranch(nextList,dataList)
            branchAdapter.notifyDataSetChanged()
        }
    }
}