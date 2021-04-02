package com.test.cryptocourse.manager

import android.app.Activity
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import androidx.annotation.Nullable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.material.snackbar.Snackbar
import com.test.cryptocourse.R

class NetworkManager: LifecycleObserver {

    private var mActivity: FragmentActivity? = null

    private fun NetworkManager(activity: FragmentActivity) {
        this.mActivity = activity
        mActivity!!.lifecycle.addObserver(this)
    }

    fun getInstance(activity: FragmentActivity?) {
        NetworkManager(activity!!)
    }
    private fun isNetworkOnline(context: Context?): Boolean {
        val cm = context!!.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
        return capabilities?.hasCapability(NET_CAPABILITY_INTERNET) == true
    }

    private fun onNetworkCondition(@Nullable activity: Activity?) {
        if (activity != null && !activity.isFinishing && !isNetworkOnline(activity)) {
            Snackbar.make(
                activity.findViewById(android.R.id.content),
                R.string.no_network,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        onNetworkCondition(mActivity)
    }
}