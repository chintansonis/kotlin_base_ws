package com.app.kotlinbasews.ui

import android.os.Bundle
import com.app.kotlinbasews.R
import com.app.kotlinbasews.api.responsepojos.User
import com.app.kotlinbasews.custom.MDToast
import com.app.kotlinbasews.helper.Functions
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity() {
    var user: User? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setShowBackMessage(false)
        if (Functions.isConnected(this@LoginActivity))
            callLoginApi()
        else
            Functions.showToast(this@LoginActivity, getString(R.string.internet_connection), MDToast.TYPE_ERROR)

        // Java
        /*if (firstName.equals("Dan")) {
            person.setTeam(programmers);
        } else if (lastName.equals("Dihiansan")) {
            person.setTeam(designers);
        } else {
            person.setTeam(others);
        }*/
// Kotlin
        when {
            edtEmail.text.toString() == "chintan" -> user?.FirstName = "dfbh"
            edtPassword.text.toString() == "1234566" -> user?.CartId = 0
        }

        /*// Java
        switch (firstName) {
            case "Dan": person.setTeam(programmers)
            break;
            case "Jay": person.setTeam(programmers)
            break;
            case "Jamie": person.setTeam(designers)
            break;
            default:
            person.setTeam(others)
        }
// Kotlin
        when (firstName) {
            "Dan", "Jay" -> person.team = programmers
            "Jamie"      -> person.team = designers
            else         -> person.team = others
        }*/

        /*// Java
        public String fullName() {
            return getFirstName() + " " + getLastName();
        }
        // Kotlin
        fun fullName() = "${firstName} ${lastName}"*/


        /*// Java
        if (name.toLowerCase().contains(firstName.toLowerCase())) {
            ...
        }
// Kotlin
        if (name.contains(firstName, true)) { ... }*/


        /*// Java
        if (message != null) {
            System.out.println(message)
        }
// Kotlin
        message?.let { println(it) }*/

        val items = listOf("apple", "banana", "kiwifruit")
        for (item in items) {
            println(item)
        }

        for (x in 1..5) {
            print(x)
        }

    }

    private fun callLoginApi() {
        showProgressDialog(false)


    }

}