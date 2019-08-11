package com.hardikfumakiya.thescoretest.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.telephony.TelephonyManager

/**
 * Created by Hardik Fumakiya this class is where validation for network is performed
 * **/
class NetworkUtil {
    companion object {
        private const val API_CONNECTION_OK = 1 // NetworkAvailable
        private const val API_CONNECTION_TIMEOUT = 2 // no NetworkAvailable
        private const val NET_NOT_PREPARE = 3 // Net no ready
        private const val NET_ERROR = 4 //net error
        private const val TIMEOUT = 3000 // TIMEOUT
        /**
         * check NetworkAvailable
         *
         * @param context
         * @return
         */
        @JvmStatic
        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
            return if (connectivityManager is ConnectivityManager) {
                val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
                networkInfo?.isConnected ?: false
            } else false
        }




        /**
         * check is3G
         *
         * @param context
         * @return boolean
         */
        @JvmStatic
        fun is3G(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetInfo = connectivityManager.activeNetworkInfo
            return activeNetInfo != null && activeNetInfo.type == ConnectivityManager.TYPE_MOBILE
        }

        /**
         * isWifi
         *
         * @param context
         * @return boolean
         */
        @JvmStatic
        fun isWifi(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetInfo = connectivityManager.activeNetworkInfo
            return activeNetInfo != null && activeNetInfo.type == ConnectivityManager.TYPE_WIFI
        }

        /**
         * is2G
         *
         * @param context
         * @return boolean
         */
        @JvmStatic
        fun is2G(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetInfo = connectivityManager.activeNetworkInfo
            return activeNetInfo != null && (activeNetInfo.subtype == TelephonyManager.NETWORK_TYPE_EDGE || activeNetInfo.subtype == TelephonyManager.NETWORK_TYPE_GPRS || activeNetInfo.subtype == TelephonyManager.NETWORK_TYPE_CDMA)
        }

        /**
         * is wifi on
         */
        @JvmStatic
        fun isWifiEnabled(context: Context): Boolean {
            val mgrConn = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val mgrTel = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            return mgrConn.activeNetworkInfo != null && mgrConn
                .activeNetworkInfo!!.state == NetworkInfo.State.CONNECTED || mgrTel
                    .networkType == TelephonyManager.NETWORK_TYPE_UMTS
        }

        /**
         * isConnected
         *
         * @param context
         * @return boolean
         */
        @JvmStatic
        fun isConnected(context: Context): Boolean {
            return ((isWifiEnabled(context) && isWifi(context)) || is2G(context) || is3G(context))
        }
    }
}