package br.com.walicendalazuana.mannudoug.Fragments


import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.walicendalazuana.mannudoug.Adapter.PresenteAdapter
import br.com.walicendalazuana.mannudoug.Model.Presente
import br.com.walicendalazuana.mannudoug.R
import br.com.walicendalazuana.mannudoug.R.id.parent_view
import kotlinx.android.synthetic.main.fragment_presentes.*
import org.jetbrains.anko.browse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread


/**
 * A simple [Fragment] subclass.
 *
 */
class PresentesFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_presentes, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponent()

    }

    companion object {

        fun newInstance(): PresentesFragment = PresentesFragment()
    }

    private fun initComponent() {
        //recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        recyclerView.isNestedScrollingEnabled = false


        //set data and list adapter
        doAsync{
            val items : List<Presente> = listOf(Presente(R.drawable.pontofrio,"Lista Ponto Frio","46 Itens",R.drawable.pontofrio),
                    Presente(R.drawable.camicado,"Lista Camicado","45 Itens",R.drawable.camicado))

            uiThread {
                val mAdapter = PresenteAdapter(items)
                recyclerView.adapter = mAdapter

                // on item list clicked
                mAdapter.setOnItemClickListener(object : PresenteAdapter.OnItemClickListener {
                    override fun onItemClick(view: View, obj: Presente, position: Int) {
                        if(position == 0){
                            activity?.browse("https://casamento.pontofrio.com.br/#/lista-presentes/756599")
                        }else{
                            activity?.browse("https://www.camicado.com.br/lista/convidado/nosso19")
                        }
                    }
                })
            }

        }


    }


}
