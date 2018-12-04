package br.com.walicendalazuana.mannudoug.Fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import br.com.walicendalazuana.mannudoug.MainActivity
import br.com.walicendalazuana.mannudoug.R
import br.com.walicendalazuana.mannudoug.Utils.Prefs
import kotlinx.android.synthetic.main.fragment_confirmation.*
import kotlinx.android.synthetic.main.fragment_confirmation.view.*
import org.jetbrains.anko.toast

class ConfirmationFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirmation, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(Prefs.getString("PRESENCA") == "CONFIRMADA"){
            edt_nome.setText(Prefs.getString("NOME"))
            edt_nome.isEnabled = false

            edt_email.setText(Prefs.getString("EMAIL"))
            edt_email.isEnabled = false
            btn_confirmacao.isEnabled = false
            showDialogConfirmacao()
        }


    }
    fun showDialogConfirmacao() {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
        dialog.setContentView(R.layout.dialog_presenca_confirmada)
        dialog.getWindow().setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(true)
        dialog.show()
    }


    companion object {
        fun newInstance(): ConfirmationFragment = ConfirmationFragment()
    }


}
