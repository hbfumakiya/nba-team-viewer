package com.hardikfumakiya.thescoretest.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hardikfumakiya.thescoretest.utils.NetworkUtil
import com.hardikfumakiya.thescoretest.utils.PermissionUtil
import com.hardikfumakiya.thescoretest.utils.PermissionUtil.Companion.PERMISSION_REQUEST_CODE
import timber.log.Timber

/**
 * Created by Hardik Fumakiya this class is base activity of all app activities
 * **/

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var permissionUtil: PermissionUtil


    fun toast(msgID: Int) {
        Toast.makeText(this, getString(msgID), Toast.LENGTH_SHORT).show()
    }


    fun isConnected(): Boolean {
        return NetworkUtil.isConnected(this)
    }


    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.backStackEntryCount == 0)
            finish()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionUtil = PermissionUtil()
        permissionUtil.checkPermissions(this)


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                val isPermissionsGranted = permissionUtil.processPermissionsResult(requestCode, permissions, grantResults)

                if (isPermissionsGranted) {
                    Timber.d("Permissions granted.")
                } else {
                    Timber.d("Permissions denied.")
                }
                return
            }
        }
    }

}