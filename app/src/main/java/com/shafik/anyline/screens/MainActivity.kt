package com.shafik.anyline.screens

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.shafik.anyline.databinding.ActivityMainBinding
import com.shafik.anyline.util.MyExtensionFunctions.printToLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observers()

        binding.mainBtnUploadImage.setOnClickListener {
            binding.mainTvLogs.text = ""
            viewModel.uploadImages()
        }
    }

    private fun observers() {
        viewModel.isButtonEnable.observe(this@MainActivity) {
            try {
                binding.mainBtnUploadImage.isEnabled = it
                binding.mainPb.isVisible = !it
            } catch (e: Exception) {
                "mainBtnUploadImage catch: ${e.message}".printToLog("main")
            }
        }

        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.stateFlowLog.collectLatest { strLog ->
                run {
                    strLog?.let {
                        binding.mainTvLogs.append(it)
                    }
                }
            }
        }
    }
}