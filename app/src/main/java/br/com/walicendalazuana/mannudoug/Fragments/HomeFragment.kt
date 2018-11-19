package br.com.walicendalazuana.mannudoug.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.walicendalazuana.mannudoug.R
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [HomeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class HomeFragment : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        Log.i("UMAHORA", "Entrou no onCreateView()")
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("UMAHORA", "Entrou no onCreate()")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("UMAHORA", "Entrou no onViewCreated()")
        setCounter()
    }

    fun setCounter() {
        cv_countDownView.tag = "Contador"
        var time: Long = getDaysMissing()//61 * 24 * 60 * 60 * 1000L
        Log.i( "MyLong","Long é $time")
        cv_countDownView.start(time)
    }

    public fun getDaysMissing(): Long {

        val hoje = Calendar.getInstance()
        val casamento = Calendar.getInstance()
        casamento.set(2019, Calendar.JANUARY, 19, 17,30, 0)
        hoje.time
        val hojeInMillis = hoje.timeInMillis
        val casamentoInMillis = casamento.timeInMillis

        val diff = casamentoInMillis - hojeInMillis

        return diff

    }


    companion object {

        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}
