package com.d121211040.photography


import android.app.Application
import com.d121211040.photography.data.AppContainer
import com.d121211040.photography.data.DefaultAppContainer

class MyApplication:Application() {
    lateinit var container: AppContainer

    override fun onCreate(){
        super.onCreate()
        container = DefaultAppContainer()
    }
}