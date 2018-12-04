package br.com.walicendalazuana.mannudoug

import android.support.multidex.MultiDexApplication
import android.util.Log

class MannuDougApplication : MultiDexApplication() {
    // Chamado quando o Android criar o processo do aplicativo
    override fun onCreate() {
        super.onCreate()
// Salva a instância para termos acesso como Singleton
        appInstance = this
    }

    companion object {
        private const val TAG = "MannuDougApplication"

        // Singleton da classe Application
        private var appInstance: MannuDougApplication? = null

        fun getInstance(): MannuDougApplication {
            if (appInstance == null) {
                throw IllegalStateException("Configure a classe de Application no AndroidManifest.xml")
            }
            return appInstance!!
        }

    }

    // Chamado quando o Android finalizar o processo do aplicativo
    override fun onTerminate() {
        super.onTerminate()
        Log.d(TAG, "MannuDougApplication.onTerminate()")
    }
}