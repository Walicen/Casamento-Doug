package br.com.walicendalazuana.mannudoug.Utils

import android.app.Activity
import android.os.Build
import android.support.annotation.ColorRes
import android.view.View
import android.view.WindowManager

class Tools {

    companion object {

        fun setSystemBarColor(act: Activity, @ColorRes color: Int) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val window = act.window
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.statusBarColor = act.resources.getColor(color)
            }
        }

        fun setSystemBarLight(act: Activity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val view = act.findViewById<View>(android.R.id.content)
                var flags = view.systemUiVisibility
                flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                view.systemUiVisibility = flags
            }
        }
    }
}