package com.test.cryptocourse.ui.list

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.test.cryptocourse.model.ModelCrypto
import com.test.cryptocourse.network.OkHTTPHelper
import org.json.JSONArray

class ListViewModel : ViewModel() {

    val okHTTPHelper: OkHTTPHelper? = OkHTTPHelper()
    private val _list_crypto = MutableLiveData<List<ModelCrypto>>().apply {
        value = getListCrypto()
    }
    val list_crypto: LiveData<List<ModelCrypto>> = _list_crypto


    private fun getListCrypto():List<ModelCrypto>{
        var list: List<ModelCrypto>? = emptyList()
        okHTTPHelper?.getJSONAnswer(object : OkHTTPHelper.OnRequestCompleteListener {
            override fun onSuccess(string: String) {
                list = convertJSONArray(string)
                _list_crypto.postValue(list)
            }
            override fun onError(message: String?) {}
        })
        return list!!
    }

    private fun convertJSONArray(jsonString: String):List<ModelCrypto>{
        val list = ArrayList<ModelCrypto>()
        val jsonArray = JSONArray(jsonString)
        val gson: Gson = Gson()
        if (jsonArray != null) {
            val len = jsonArray.length()
            for (i in 0 until len) {
                list.add(gson.fromJson(jsonArray[i].toString(), ModelCrypto::class.java))
            }
        }
        return list
    }
}