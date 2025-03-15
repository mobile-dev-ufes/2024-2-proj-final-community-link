package ufes.grad.mobile.communitylink.view

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ufes.grad.mobile.communitylink.databinding.ActivityLoginBinding
import ufes.grad.mobile.communitylink.utils.Utilities
import ufes.grad.mobile.communitylink.view.fragments.LoginFragment
import java.util.Locale

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Utilities.loadFragment(this, LoginFragment())
    }

    private fun toggleLanguage() {
        val newLang = if (getCurrentLanguage() == "en") "pt" else "en"
        setAppLocale(newLang)
        recreate() // Restart activity to apply changes
    }

    private fun getCurrentLanguage(): String {
        return resources.configuration.locales[0].language
    }

    private fun setAppLocale(language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val config = Configuration()
        config.setLocale(locale)

        val context = createConfigurationContext(config)
        resources.updateConfiguration(config, context.resources.displayMetrics)

        // Save preference (optional)
        val prefs = getSharedPreferences("Settings", MODE_PRIVATE).edit()
        prefs.putString("My_Lang", language)
        prefs.apply()
    }

}
