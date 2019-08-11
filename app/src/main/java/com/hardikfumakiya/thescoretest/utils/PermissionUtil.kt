package com.hardikfumakiya.thescoretest.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.hardikfumakiya.thescoretest.base.BaseActivity
import com.hardikfumakiya.thescoretest.Injector
import timber.log.Timber

/**
 * Created by Hardik Fumakiya this class is where permission validation is performed
 * **/
class PermissionUtil {
    lateinit var activity: BaseActivity
    init {
        Injector.get().inject(this)
    }

    companion object {
        const val PERMISSION_REQUEST_CODE = 123
        val list = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            listOf(
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.SYSTEM_ALERT_WINDOW
            )
        } else {
            listOf(
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        }
    }
    // Check permissions at runtime
    fun checkPermissions(activity: BaseActivity) {
        this.activity = activity

        if (isPermissionsGranted(activity) != PackageManager.PERMISSION_GRANTED) {
            showAlert(activity)
        } else {
            Timber.d("Permissions already granted.")
        }
    }



    // Check permissions status
    private fun isPermissionsGranted(activity: BaseActivity): Int {
        // PERMISSION_GRANTED : Constant Value: 0
        // PERMISSION_DENIED : Constant Value: -1
        var counter = 0
        for (permission in list) {
            counter += isPermissionsGranted(activity, permission)
        }
        return counter
    }

    fun isPermissionsGranted(activity: BaseActivity, permission: String): Int {
        // PERMISSION_GRANTED : Constant Value: 0
        // PERMISSION_DENIED : Constant Value: -1
        return ContextCompat.checkSelfPermission(activity, permission)
    }



    // Find the first denied permission
    private fun deniedPermission(activity: BaseActivity): String {
        for (permission in list) {
            if (ContextCompat.checkSelfPermission(activity, permission)
                == PackageManager.PERMISSION_DENIED) return permission
        }
        return ""
    }


    // Show alert dialog to request permissions
    private fun showAlert(activity: BaseActivity) {
        requestPermissions(activity)
    }


    // Request the permissions at run time
    private fun requestPermissions(activity: BaseActivity) {
        val permission = deniedPermission(activity)
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
            // Show an explanation asynchronously
            Timber.d("Should show an explanation.")
        } else {
            ActivityCompat.requestPermissions(activity, list.toTypedArray(), PERMISSION_REQUEST_CODE)
        }
    }


    // Process permissions result
    fun processPermissionsResult(requestCode: Int, permissions: Array<String>,
                                 grantResults: IntArray): Boolean {
        var result = 0
        if (grantResults.isNotEmpty()) {
            for (item in grantResults) {
                result += item
            }
        }
        if (result == PackageManager.PERMISSION_GRANTED) return true
        return false
    }

    fun hasPermission(context: Context, permission: String): Boolean {
        return ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
    }



}