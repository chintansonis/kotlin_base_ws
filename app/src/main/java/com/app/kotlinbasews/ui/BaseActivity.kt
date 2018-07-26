package com.app.kotlinbasews.ui

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import com.app.kotlinbasews.R
import com.app.kotlinbasews.helper.IncompleteBuilderException
import java.util.*

open class BaseActivity : AppCompatActivity() {
    var screenWidth: Int = 0
    var screenHeight: Int = 0
    var fragmentBackStack: Stack<Fragment>? = null
    var showBackMessage: Boolean? = true
    var doubleBackToExitPressedOnce: Boolean = false
    val dialog: Dialog? = null

    abstract class Builder<T>(private val context: Context) {
        protected var intent: Intent

        init {
            intent = createIntent(context)
        }

        protected abstract fun createIntent(context: Context): Intent

        private fun addBundle(bundle: Bundle): T {
            intent.extras!!.putAll(bundle)
            return this as T
        }

        private fun setFlags(flags: Int): T {
            intent.flags = flags
            return this as T
        }

        private fun addFlags(flags: Int): T {
            intent.addFlags(flags)
            return this as T
        }

        private fun addExtras(extras: Bundle): T {
            intent.putExtras(extras)
            return this as T
        }

        private fun addExtras(extras: Intent): T {
            intent.putExtras(extras)
            return this as T
        }

        private fun setData(data: Uri): T {
            intent.data = data
            return this as T
        }

        fun build(): Intent {
            if (checkIntentCompleteness()) {
                return intent
            }
            throw IncompleteBuilderException("Not all required intent values provided")
        }

        protected open fun checkIntentCompleteness(): Boolean {
            return true
        }

        fun startActivityAndFinish() {
            when (context) {
                is Activity -> {
                    context.startActivity(intent)
                    context.finish()
                }
            }
        }

        fun startActivity() {
            when (context) {
                is Activity -> context.startActivity(intent)
            }
        }

        /*fun startActivityForResult(@RequestCodes.Requests requestCode: Int) {
            when (context) {
                is Activity -> context.startActivityForResult(intent, requestCode)
            }
        }*/

    }

    fun setShowBackMessage(showBackMessage: Boolean) {
        this.showBackMessage = showBackMessage
    }

    fun getFragments(): Stack<Fragment>? {
        return fragmentBackStack
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentBackStack = Stack()
    }

    fun getWidthAndHeight() {
        val displaymetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displaymetrics)
        screenHeight = displaymetrics.heightPixels
        screenWidth = displaymetrics.widthPixels
    }

    /**
     * To add fragment to a tab. tag -> Tab identifier fragment -> Fragment to show, in tab identified by tag shouldAnimate ->
     * should animate transaction. false when we switch tabs, or adding first fragment to a tab true when when we are pushing more
     * fragment into navigation stack. shouldAdd -> Should add to fragment navigation stack (mStacks.get(tag)). false when we are
     * switching tabs (except for the first time) true in all other cases.
     *
     * @param fragment      the fragment
     * @param shouldAnimate the should animate
     * @param shouldAdd     the should add
     */
    @Synchronized
    fun pushAddFragments(fragment: Fragment?, shouldAnimate: Boolean, shouldAdd: Boolean) {
        try {
            if (fragment != null) {
                fragmentBackStack?.push(fragment)
                val manager = supportFragmentManager
                val ft = manager.beginTransaction()
                ft.replace(R.id.contentFrame, fragment, fragmentBackStack?.size.toString())
                ft.commit()
                manager.executePendingTransactions()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Select the second last fragment in current tab's stack.. which will be shown after the fragment transaction given below
     *
     * @param shouldAnimate the should animate
     */
    @Synchronized
    fun popFragments(shouldAnimate: Boolean) {
        var fragment: Fragment? = null
        if (fragmentBackStack!!.size > 1)
            fragment = fragmentBackStack!!.elementAt(fragmentBackStack!!.size - 2)
        else if (!fragmentBackStack!!.isEmpty())
            fragment = fragmentBackStack!!.elementAt(fragmentBackStack!!.size - 1)

        var manager: FragmentManager = supportFragmentManager

        var ft: FragmentTransaction = manager.beginTransaction()

        if (fragment!!.isAdded())
            ft.remove(fragmentBackStack!!.elementAt(fragmentBackStack!!.size - 1))
        if (fragmentBackStack!!.size > 1) {
            ft.show(fragment).commit()
        } else
            ft.replace(R.id.contentFrame, fragment).commit()
        fragmentBackStack!!.pop()
        manager.executePendingTransactions()
    }

    fun doubleTapOnBackPress() {
        if (doubleBackToExitPressedOnce) finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        return
        this.doubleBackToExitPressedOnce = true
        Handler().postDelayed({
            doubleBackToExitPressedOnce = false
        }, 1000)

    }

    override fun onBackPressed() {
        if (fragmentBackStack!!.size <= 1) {
            if (showBackMessage!!)
                doubleTapOnBackPress()
        } else {
            finish()
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
    }


}