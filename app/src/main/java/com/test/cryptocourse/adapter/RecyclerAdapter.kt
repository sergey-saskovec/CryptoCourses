package com.test.cryptocourse.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.cryptocourse.R
import com.test.cryptocourse.model.ModelCrypto

class RecyclerAdapter(private val dataSet: List<ModelCrypto>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView = view.findViewById(R.id.ivCurrencyIcon)
        var tvCurrencySym: TextView = view.findViewById(R.id.tvCurrencySym)
        var tvCurrencyName: TextView = view.findViewById(R.id.tvCurrencyName)
        var tvCurrencyMarketCap: TextView = view.findViewById(R.id.tvCurrencyMarketCap)
        var tvCurrencyPrice: TextView = view.findViewById(R.id.tvCurrencyPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.crypto_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var modelCrypto: ModelCrypto = dataSet[position]
        Glide.with(holder.itemView)
            .load(modelCrypto.image)
            .into(holder.imageView)
        holder.tvCurrencyName.text = modelCrypto.name
        holder.tvCurrencySym.text = modelCrypto.symbol
        holder.tvCurrencyMarketCap.text = modelCrypto.marketCap.toString()
        holder.tvCurrencyPrice.text = modelCrypto.currentPrice.toString()
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}