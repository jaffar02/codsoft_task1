package com.alphadev.codsoft_task1

import android.hardware.camera2.CameraManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.alphadev.codsoft_task1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var cameraManager: CameraManager ?= null
    var cameraId = ""
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var mp = MediaPlayer.create(this, R.raw.on_off_sound)

        binding.button.setOnClickListener {
            if (flashState()){
                binding.button.setImageDrawable(resources.getDrawable(R.drawable.off))
                try {
                    cameraManager = getSystemService(CAMERA_SERVICE) as CameraManager?
                    cameraId = cameraManager?.cameraIdList?.get(0).toString()
                    cameraManager?.setTorchMode(cameraId, false)
                    mp.start ()

                }catch (ex: java.lang.Exception){
                    ex.printStackTrace()
                }
            }else{
                binding.button.setImageDrawable(resources.getDrawable(R.drawable.on))
                try {
                    cameraManager = getSystemService(CAMERA_SERVICE) as CameraManager?
                    cameraId = cameraManager?.cameraIdList?.get(0).toString()
                    cameraManager?.setTorchMode(cameraId, true)
                    mp.start ()
                }catch (ex: java.lang.Exception){
                    ex.printStackTrace()
                }
            }
        }
    }

    private fun flashState(): Boolean{
        if (binding.button.drawable.constantState == resources.getDrawable(R.drawable.on).constantState){
            return true
        }else if (binding.button.drawable.constantState == resources.getDrawable(R.drawable.off).constantState){
            return false
        }
        return false
    }
}