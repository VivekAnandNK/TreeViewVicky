package com.vicky.vickytree

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class BranchAdapter : RecyclerView.Adapter<BranchAdapter.BranchViewHolder>() {

    var branchList = ArrayList<TreeBranchModel>()
    var dataList = ArrayList<TreeBranchModel>()


    fun setBranch(treeBranchList: ArrayList<TreeBranchModel>, dataList: ArrayList<TreeBranchModel>){

        this.branchList.addAll(treeBranchList)
        this.dataList=dataList

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BranchViewHolder {
        return BranchViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.branch_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BranchViewHolder, position: Int) {
        holder.setBranchItem(branchList[position])
        holder.rootView.setOnClickListener {
            holder.onClickNextList(dataList,branchList[position].id)
        }
    }

    override fun getItemCount(): Int {
       return branchList.size
    }

    class BranchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        lateinit var rootView:ConstraintLayout
       lateinit var rvBranch : RecyclerView
        lateinit var branchAdapter: BranchAdapter
        lateinit var tvTitle : TextView
        var nextList =ArrayList<TreeBranchModel>()

        var isExpanded=false

        fun setBranchItem(branchModel:TreeBranchModel){
            rvBranch=itemView.findViewById(R.id.rvbranch)
            rootView = itemView.findViewById(R.id.branchConstrain)
            tvTitle=itemView.findViewById(R.id.tvBranch)

            tvTitle.setText(branchModel.title)

            branchAdapter= BranchAdapter()
            rvBranch.apply {
                layoutManager= LinearLayoutManager(itemView.context)
                adapter=branchAdapter
            }

        }

        fun onClickNextList(dataList: ArrayList<TreeBranchModel>, id: String) {

            if (nextList.isEmpty()){
                dataList.forEach {
                    if (it.parentId==id){
                        nextList.add(it)
                    }
                }
            }
            if (nextList.isNotEmpty()){
                createNextList(nextList,dataList)
            }


            if (isExpanded){
                rvBranch.visibility=View.GONE
            }else if (!isExpanded&&nextList.isNotEmpty()){
                rvBranch.visibility=View.VISIBLE
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