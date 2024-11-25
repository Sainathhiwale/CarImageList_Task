package com.examen.carimagetask.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.examen.carimagetask.R
import com.examen.carimagetask.data.model.CarList
import com.examen.carimagetask.databinding.CarListAdapterLayoutBinding

class CarListAdapter(private val carList: List<CarList>): RecyclerView.Adapter<CarListAdapter.MyViewHolder>() {
    private var onItemClickCarListener: OnItemClickCarListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
          val viewBinding = CarListAdapterLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
          return MyViewHolder(viewBinding)

    }

    override fun getItemCount(): Int {
       return carList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       val carItem = carList[position]
         holder.bind(carItem)
         holder.itemView.tag
         holder.itemView.setOnClickListener{
             onItemClickCarListener?.onItemClickCar(carItem)
         }

    }

    fun setOnItemClickCarListener(onItemClickCarListener: OnItemClickCarListener) {
        this.onItemClickCarListener = onItemClickCarListener
    }

    class MyViewHolder(private val binding: CarListAdapterLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(carList: CarList) {
            binding.cartItemName.text = carList.name
            binding.cartItemPrice.text = carList.price.toString()
            binding.cartItemBrand.text = carList.brand
            binding.cartItemId.text = carList.id.toString()
            Glide.with(binding.root.context).load(carList.imageUrl).into(binding.cartItemImage)

        }
    }

    interface OnItemClickCarListener{
        fun onItemClickCar(carList: CarList)
    }
}