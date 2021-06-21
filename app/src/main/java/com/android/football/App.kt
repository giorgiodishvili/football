package com.android.football

import android.app.Application
import android.content.Context
import java.lang.ref.WeakReference

class App : Application() {
    companion object {
        lateinit var context: WeakReference<Context>
        fun getContext(): Context? {
            return context.get()
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = WeakReference(applicationContext)
    }


}