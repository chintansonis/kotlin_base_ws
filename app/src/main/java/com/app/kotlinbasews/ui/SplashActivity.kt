package com.app.kotlinbasews.ui

import android.os.Bundle
import android.os.CountDownTimer
import com.app.kotlinbasews.R
import com.app.kotlinbasews.helper.Functions

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        navigateToLogin()
        setShowBackMessage(false)
    }

    private fun navigateToLogin() {
        val longestDelay:Long=1200
        val longestDuration:Long=3000
        var oneTouchTimer = object : CountDownTimer(longestDelay+longestDuration+500,1000){
            override fun onFinish() {
                Functions.fireIntent(this@SplashActivity,LoginActivity::class.java,true,true)
            }
            override fun onTick(millisUntilFinished: Long) {
            }

        }
        oneTouchTimer.start()
    }

}
