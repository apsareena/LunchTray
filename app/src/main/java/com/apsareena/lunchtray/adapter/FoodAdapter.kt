package com.apsareena.lunchtray.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apsareena.lunchtray.databinding.ListItemBinding
import com.apsareena.lunchtray.model.FoodItem
import com.google.android.material.radiobutton.MaterialRadioButton


class FoodAdapter(private val dataset: List<FoodItem>, private val context: Context, private val mainDish: String, val chosenPos: (Int)->Unit):
    RecyclerView.Adapter<FoodAdapter.ItemViewHolder>() {
    private lateinit var previousRecyclerViewItem: MaterialRadioButton
    private var lastSelectedPosition = -1

    class ItemViewHolder(val binding: ListItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(context: Context, item: FoodItem) {
            binding.foodTitle.text = context.resources.getString(item.title)
            binding.foodDescription.text = context.resources.getString(item.desc)
            binding.foodPrice.text = context.resources.getString(item.price)
        }
    }

        override fun getItemId(position: Int): Long = position.toLong()

        override fun getItemCount(): Int = dataset.size

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ItemViewHolder {
            val bindingAdapter =
                ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ItemViewHolder(bindingAdapter)
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            val item = dataset[position]
            holder.bind(context, item)
            val binder = holder.binding.foodTitle
            if (mainDish == binder.text){
                binder.isChecked = true
                previousRecyclerViewItem = binder
            }
            binder.setOnClickListener {
                binder.isChecked = true
                if (lastSelectedPosition != -1 || this::previousRecyclerViewItem.isInitialized){
                    previousRecyclerViewItem.isChecked= false
                }
                lastSelectedPosition = holder.adapterPosition
                chosenPos(lastSelectedPosition)
                previousRecyclerViewItem = binder
            }
        }
}
