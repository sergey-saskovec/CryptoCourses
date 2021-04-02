package com.test.cryptocourse.network

import com.test.cryptocourse.Constant
import com.test.cryptocourse.model.ModelCrypto
import okhttp3.*
import java.io.IOException


/**
 * Created by
 */
class OkHTTPHelper : Callback {
    val vs_currency:String = "usd"
    val order:String = "market_cap_desc"
    val per_page:String = "10"
    val page:String = "1"
    val sparkline:String = "false"
    private var onRequestCompleteListener : OnRequestCompleteListener? =null


    fun getJSONAnswer(callback : OnRequestCompleteListener){
        this.onRequestCompleteListener = callback
        var url = createUrl()
        var listCrypto: List<ModelCrypto>
        val request = Request.Builder()
            .url(url)
            .build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(this)
    }

    private fun createUrl(): String {
        return Constant.url+"vs_currency="+vs_currency+"&order="+order+"&per_page="+per_page+"&page="+page+"&sparkline="+sparkline
    }

    interface OnRequestCompleteListener{
        fun onSuccess(list :String)
        fun onError(message: String?)
    }

    override fun onFailure(call: Call, e: IOException) {
        onRequestCompleteListener?.onError(e.message)
    }

    override fun onResponse(call: Call, response: Response) {
        onRequestCompleteListener?.onSuccess(response.body?.string().toString())
    }
}