package com.test.cryptocourse.ui.converter

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.test.cryptocourse.R
import com.test.cryptocourse.manager.NetworkManager
import com.test.cryptocourse.manager.SharedPreferencesManager
import com.test.cryptocourse.model.ModelCrypto
import com.test.cryptocourse.ui.list.ListViewModel


class ConverterFragment : Fragment() {

    private lateinit var listViewModel: ListViewModel
    private lateinit var sharedPreferencesManager: SharedPreferencesManager
    private lateinit var list: List<ModelCrypto>
    private lateinit var imageCrypto: ImageView
    private lateinit var countCrypto: EditText
    private lateinit var course: EditText
    private lateinit var fiat: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        NetworkManager().getInstance(activity)
        listViewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        sharedPreferencesManager = SharedPreferencesManager()
        val root = inflater.inflate(R.layout.fragment_converter, container, false)
        initView(root)
        setClick()

        if (sharedPreferencesManager.loadList(context) != null &&
            sharedPreferencesManager.loadList(context)?.isNotEmpty()!!) {
            list = sharedPreferencesManager.loadList(context)!!
        } else {
            listViewModel.list_crypto.observe(viewLifecycleOwner, Observer {
                list = it
            })
        }
        return root
    }

    private fun initView(root: View) {
        imageCrypto = root.findViewById(R.id.imageCrypto) as ImageView
        countCrypto = root.findViewById(R.id.countCrypto) as EditText
        course = root.findViewById(R.id.course) as EditText
        fiat = root.findViewById(R.id.fiat) as EditText
    }

    private fun setClick() {
        var priceCrypto: Double? = null
        imageCrypto.setOnClickListener(View.OnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setTitle(R.string.choose_ctypto)

            var listCrypto = arrayOfNulls<String>(list.size)
            for (i in 0 until list.size) {
                listCrypto[i] = list[i].id
            }
            builder.setItems(
                listCrypto
            ) { dialog, which ->
                priceCrypto = list[which].currentPrice!!
                course.setText(
                    "1 " + list[which].symbol
                        .toString() + " = " + priceCrypto.toString() + "$"
                )
                Glide.with(context)
                    .load(list.get(which).image)
                    .into(imageCrypto)
            }
            val dialog = builder.create()
            dialog.show()
        })

        countCrypto.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                try {
                    fiat.setText(
                        java.lang.String.valueOf(
                            java.lang.String.valueOf(countCrypto.text)
                                .toDouble() * priceCrypto!!
                        )
                    )
                } catch (e: NumberFormatException) {
                    fiat.setText("0")
                    Toast.makeText(context, R.string.number_format_exception, Toast.LENGTH_LONG).show()
                } catch (e: NullPointerException) {
                    fiat.setText("0")
                    Toast.makeText(context, R.string.new_cryptocurrency, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}