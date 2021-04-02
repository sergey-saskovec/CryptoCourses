package com.test.cryptocourse.model

import com.google.gson.annotations.SerializedName
import java.util.*

class ModelCrypto {
    @SerializedName("id")
    var id: String? = null
    @SerializedName("symbol")
    var symbol: String? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("image")
    var image: String? = null
    @SerializedName("current_price")
    var currentPrice: Double? = null
    @SerializedName("market_cap")
    var marketCap: String? = null
    @SerializedName("market_cap_rank")
    var marketCapRank: Int? = null
    @SerializedName("fullyDilutedValuation")
    var fullyDilutedValuation: Int? = null
    @SerializedName("totalVolume")
    var totalVolume: Int? = null
    @SerializedName("high_24h")
    var high24h: Double? = null
    @SerializedName("low_24h")
    var low24h: Double? = null
    @SerializedName("price_change_24h")
    var priceChange24h: Double? = null
    @SerializedName("priceChangePercentage24h")
    var priceChangePercentage24h: Double? = null
    @SerializedName("marketCapChange24h")
    var marketCapChange24h: Int? = null
    @SerializedName("marketCapChangePercentage24h")
    var marketCapChangePercentage24h: Double? = null
    @SerializedName("circulatingSupply")
    var circulatingSupply: Int? = null
    @SerializedName("totalSupply")
    var totalSupply: Int? = null
    @SerializedName("maxSupply")
    var maxSupply: Int? = null
    @SerializedName("ath")
    var ath: Double? = null
    @SerializedName("athChangePercentage")
    var athChangePercentage: Double? = null
    @SerializedName("athDate")
    var athDate: String? = null
    @SerializedName("atl")
    var atl: Double? = null
    @SerializedName("atlChangePercentage")
    var atlChangePercentage: Double? = null
    @SerializedName("atlDate")
    var atlDate: String? = null
    @SerializedName("roi")
    var roi: Any? = null
    @SerializedName("lastUpdated")
    var lastUpdated: String? = null
    private val additionalProperties: MutableMap<String, Any> = HashMap()
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}