package com.example.exercise5

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise5.ui.home.HomeViewModel

class ViewPagerAdapter(private var images: List<Int>, private var activity: FragmentActivity) : RecyclerView.Adapter<ViewPagerAdapter.Pager2ViewHolder>() {

    inner class Pager2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.imageView)

        init {
            itemImage.setOnClickListener { v: View ->
                val position = adapterPosition
                Toast.makeText(itemView.context, "You clicked item #${position + 1}", Toast.LENGTH_SHORT).show()

                val data: SharedPreferences = activity.getSharedPreferences("image",
                    AppCompatActivity.MODE_PRIVATE
                )
//                val act = this.requireActivity()
                val editor = data.edit()
                editor.putInt("img", position)
                editor.apply()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerAdapter.Pager2ViewHolder {
        return Pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.image_page, parent, false))
    }

    override fun getItemCount(): Int {
        return 4
    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.Pager2ViewHolder, position: Int) {
        holder.itemImage.setImageResource(images[position])
    }


}