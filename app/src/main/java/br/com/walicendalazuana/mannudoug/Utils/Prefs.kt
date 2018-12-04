package br.com.walicendalazuana.mannudoug.Utils

import android.content.SharedPreferences
import android.preference.PreferenceManager
import br.com.walicendalazuana.mannudoug.MannuDougApplication
import br.com.walicendalazuana.mannudoug.R

class Prefs {

    companion object {

        private const val PREF_ID = "mannuedoug"

        private fun prefs(): SharedPreferences {
            val context = MannuDougApplication.getInstance().applicationContext
            return context.getSharedPreferences(PREF_ID, 0)
        }

        fun putInt(key: String, valor: Int) = prefs().edit().putInt(key, valor).apply()

        fun getInt(key: String) = prefs().getInt(key, 0)

        fun putString(key: String, valor: String) = prefs().edit().putString(key, valor).apply()

        fun getString(key: String) = prefs().getString(key, "")!!

        var lastTabIdx: Int
            get() = getInt("tabIdx")
            set(value) = putInt("tabIdx", value)

        fun isCacheOn(): Boolean {
            val context = MannuDougApplication.getInstance().applicationContext
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            val key = context.getString(R.string.key_fazer_cache)
            return prefs.getBoolean(key, false)
        }
    }
}
