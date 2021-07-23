package com.arpit.themealdbapi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class MyAdapter( val context: Context , val meals: List<Meal> ) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView){
        var tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        var tvCategory = itemView.findViewById<TextView>(R.id.tvCategory)
        var tvTags = itemView.findViewById<TextView>(R.id.tvTags)
        var tvArea= itemView.findViewById<TextView>(R.id.tvArea)
        var ivIcon = itemView.findViewById<ImageView>(R.id.ivIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent , false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.tvTitle.text = meals[position].strMeal
        holder.tvCategory.text = meals[position].strCategory
        holder.tvTags.text = meals[position].strTags
        holder.tvArea.text = meals[position].strArea

       Glide.with(context).load(meals[position].strMealThumb).into(holder.ivIcon)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("title" , meals[position].strMeal)
            intent.putExtra("category" , meals[position].strCategory)
            intent.putExtra("tags" , meals[position].strTags)
            intent.putExtra("area" , meals[position].strArea)
            intent.putExtra("thumb" ,meals[position].strMealThumb)
            intent.putExtra("instructions" ,meals[position].strInstructions)
            intent.putExtra("youtube" ,meals[position].strYoutube)
            context.startActivity(intent)

        }

        var lastPosition = -1
        val animation: Animation = AnimationUtils.loadAnimation(
            context,
            if (position > lastPosition) R.anim.up_from_bottom else R.anim.down_from_top
        )
        holder.itemView.startAnimation(animation)
        lastPosition = position
    }

    override fun getItemCount() = meals.size

    override fun onViewDetachedFromWindow(holder: MyViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.clearAnimation()
    }
}

