package br.com.walicendalazuana.mannudoug

import android.app.Dialog
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.Window
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.info
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*


class MainActivity : AppCompatActivity(), AnkoLogger {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        button.setOnClickListener { showDialogContagem() }
        getDaysMissing()
    }

    private fun showDialogContagem() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
        dialog.setContentView(R.layout.dialog_contagem)
        dialog.getWindow().setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        dialog.setCancelable(true)
        dialog.show()
    }


    public fun getDaysMissing(){

        val hoje = Calendar.getInstance()
        val casamento = Calendar.getInstance()
        val a = "19/01/2019"

        val formato = SimpleDateFormat("dd/MM/YYYY")
        //val dtCasamento =
        casamento.time = formato.parse(a)


        val dataFormatada = formato.format(hoje.time)
        val casamentoFormatada = formato.format(casamento.time)
        info("Vamos testar essa parada ${dataFormatada} - ${casamento.time}")



    }
}
