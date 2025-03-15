package ufes.grad.mobile.communitylink.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ufes.grad.mobile.communitylink.databinding.ActivityAdminBinding

class AdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
