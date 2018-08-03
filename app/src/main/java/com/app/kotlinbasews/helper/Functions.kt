package com.app.kotlinbasews.helper

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.net.ConnectivityManager
import android.widget.EditText
import com.app.kotlinbasews.R
import com.app.kotlinbasews.custom.MDToast
import com.app.kotlinbasews.ui.BaseActivity
import java.util.regex.Pattern

object Functions {
        val EMAIL_PATTERN: String = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"

        fun fireIntent(baseActivity: BaseActivity, cls: Class<*>, isNewActivity: Boolean) {
            val i = Intent(baseActivity, cls)
            baseActivity.startActivity(i)
            if (!isNewActivity) {
                baseActivity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            } else {
                baseActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
        }

        fun fireIntent(baseActivity: BaseActivity, cls: Class<*>, isNewActivity: Boolean, isFinished: Boolean) {
            if (isFinished)
                baseActivity.finish()
            val i = Intent(baseActivity, cls)
            baseActivity.startActivity(i)
            if (!isNewActivity) {
                baseActivity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            } else {
                baseActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
        }

        open fun fireIntent(baseActivity: BaseActivity, intent: Intent, isNewActivity: Boolean) {
            baseActivity.startActivity(intent)
            if (!isNewActivity) {
                baseActivity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            } else {
                baseActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
        }

        open fun fireIntent(baseActivity: BaseActivity, isNewActivity: Boolean) {
            baseActivity.finish()
            if (!isNewActivity) {
                baseActivity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            } else {
                baseActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
        }

        open fun fireIntentForResult(baseActivity: BaseActivity, cls: Class<*>, requestCode: Int, isNewActivity: Boolean) {
            val intent = Intent(baseActivity, cls)
            baseActivity.startActivityForResult(intent, requestCode)
            if (!isNewActivity) {
                baseActivity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            } else {
                baseActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
        }

    fun getRegularFont(_context: Context): Typeface {
        return Typeface.createFromAsset(_context.assets, "fonts/regular.ttf")
    }

    fun getBoldFont(_context: Context): Typeface {
        return Typeface.createFromAsset(_context.assets, "fonts/bold.ttf")
    }

    fun emailValidation(email: String): Boolean {
        val pattern = Pattern.compile(EMAIL_PATTERN)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

    fun toStr(editText: EditText): String {
        return editText.text.toString().trim { it <= ' ' }
    }

    fun toLength(editText: EditText): Int {
        return editText.text.toString().trim { it <= ' ' }.length
    }

    fun isConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    fun showToast(context: Context, message: String, type: Int) {
        val mdToast = MDToast.makeText(context, message, MDToast.LENGTH_SHORT, type)
        mdToast.show()
    }

}

