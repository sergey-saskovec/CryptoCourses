package com.test.cryptocourse.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.cryptocourse.R
import com.test.cryptocourse.adapter.RecyclerAdapter
import com.test.cryptocourse.manager.NetworkManager
import com.test.cryptocourse.manager.SharedPreferencesManager
import com.test.cryptocourse.model.ModelCrypto

class ListFragment : Fragment() {

    private lateinit var listViewModel: ListViewModel
    private lateinit var recyclerListCrypto:RecyclerView
    private lateinit var sharedPreferencesManager: SharedPreferencesManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        NetworkManager().getInstance(activity)
        listViewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_list, container, false)
        recyclerListCrypto = root.findViewById(R.id.recyclerListCrypto) as RecyclerView
        sharedPreferencesManager = SharedPreferencesManager()

        if (sharedPreferencesManager.loadList(context) != null &&
            sharedPreferencesManager.loadList(context)?.isNotEmpty()!!) {
            createAdapter(sharedPreferencesManager.loadList(context)!!)
        } else {
            listViewModel.list_crypto.observe(viewLifecycleOwner, Observer {
                createAdapter(it)
            })
        }
        return root
    }

    private fun createAdapter(list: List<ModelCrypto>){
        var linearLayoutManager: LinearLayoutManager = LinearLayoutManager(activity)
        recyclerListCrypto.layoutManager = linearLayoutManager
        val adapter = RecyclerAdapter(list)
        recyclerListCrypto.adapter = adapter
        context?.let { sharedPreferencesManager.saveList(list, it) }
    }
}