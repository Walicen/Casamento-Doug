package br.com.walicendalazuana.mannudoug.Model

import android.graphics.drawable.Drawable


class Presente(var image: Int = 0,
               //var imageDrw: Drawable? = null,
               var title: String? = null,
               var brief: String? = null,
               var image_bg: Int = 0)

data class Convidado(val nome:String, val email:String)