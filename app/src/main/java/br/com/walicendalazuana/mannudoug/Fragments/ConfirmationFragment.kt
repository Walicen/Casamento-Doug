package br.com.walicendalazuana.mannudoug.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.walicendalazuana.mannudoug.R

class ConfirmationFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirmation, container, false)
    }


    companion object {
        fun newInstance(): ConfirmationFragment = ConfirmationFragment()
    }

}
