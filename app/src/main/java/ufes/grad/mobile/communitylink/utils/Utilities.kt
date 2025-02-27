package ufes.grad.mobile.communitylink.utils

import android.app.Application
import android.widget.Toast

class Utilities {
    companion object{
        fun notify(application: Application, message: String){
            Toast.makeText(application.applicationContext, message, Toast.LENGTH_SHORT).show()
        }
    }
}