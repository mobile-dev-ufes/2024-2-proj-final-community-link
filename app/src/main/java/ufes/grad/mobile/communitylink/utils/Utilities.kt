package ufes.grad.mobile.communitylink.utils

import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import java.util.Locale

class Utilities {
    companion object {
        fun notify(context: Context?, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        fun setLocale(context: Context, languageCode: String) {
            val locale = Locale(languageCode)
            Locale.setDefault(locale)

            val config = Configuration()
            config.setLocale(locale)

            context.resources.updateConfiguration(config, context.resources.displayMetrics)
        }
    }
}
