package com.app.kotlinbasews.ui

import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import com.app.kotlinbasews.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        navigateToLogin()

    }

    private fun navigateToLogin() {
        val longestDelay:Long=1200
        val longestDuration:Long=3000
        var oneTouchTimer = object : CountDownTimer(longestDelay+longestDuration+500,1000){
            override fun onFinish() {

                RootActivity.Builder.create(this@SplashScreenActivity).startActivity()
            }
            override fun onTick(millisUntilFinished: Long) {
            }

        }
        oneTouchTimer.start()
    }

}
