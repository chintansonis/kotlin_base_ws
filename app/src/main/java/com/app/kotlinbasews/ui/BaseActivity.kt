package com.app.kotlinbasews.ui

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.widget.Toast
import com.app.kotlinbasews.R
import java.util.*

open class BaseActivity : AppCompatActivity() {
    var screenWidth: Int = 0
    var screenHeight: Int = 0
    var fragmentBackStack: Stack<Fragment>? = null
    var showBackMessage: Boolean? = true
    var doubleBackToExitPressedOnce: Boolean = false
    val dialog: Dialog? = null


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
        if (doubleBackToExitPressedOnce){
            finish()
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this@BaseActivity, "press back again", Toast.LENGTH_SHORT).show()
        Handler().postDelayed({
            doubleBackToExitPressedOnce = false
        }, 1000)

    }


    override fun onBackPressed() {
        if (fragmentBackStack!!.size <= 1) {
            if (showBackMessage!!) {
                doubleTapOnBackPress()
            } else {
                finish()
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        } else {

        }
    }


}