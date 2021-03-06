package br.com.walicendalazuana.mannudoug

import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import br.com.walicendalazuana.mannudoug.Fragments.ConfirmationFragment
import br.com.walicendalazuana.mannudoug.Fragments.HomeFragment
import br.com.walicendalazuana.mannudoug.Fragments.MapsFragment
import br.com.walicendalazuana.mannudoug.Fragments.PresentesFragment
import br.com.walicendalazuana.mannudoug.Model.Convidado
import br.com.walicendalazuana.mannudoug.R.string.presentes
import br.com.walicendalazuana.mannudoug.Utils.Prefs
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_confirmation.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity(), AnkoLogger {


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                switchFragment(HomeFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
//                message.setText(R.string.title_dashboard)
                val maps = MapsFragment.newInstance()
                switchFragment(maps)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                val presentes = PresentesFragment.newInstance()
                switchFragment(presentes)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_confirma_presenca -> {
                val confirmation = ConfirmationFragment.newInstance()
                switchFragment(confirmation)
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.about, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if(item!!.itemId == R.id.action_about){
            startActivity<AboutActivity>()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        switchFragment(HomeFragment.newInstance())


    }

    fun salvar_presenca_firebase(convidado: Convidado) {

        val db = FirebaseFirestore.getInstance()
        val refPresenca = db.collection("presenca").document()

        refPresenca.set(convidado)
    }

    fun showDialogConfirmacao() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
        dialog.setContentView(R.layout.dialog_contagem)
        dialog.getWindow().setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        dialog.setCancelable(true)
        dialog.show()
    }

    fun confirmacao_presenca(view: View) {
        val nome = edt_nome.text.toString()
        val email = edt_email.text.toString()

        if (nome.length > 5) {
            if (validar_email(email)) {
                val conv = Convidado(nome, email)

                Prefs.putString("PRESENCA", "CONFIRMADA")
                Prefs.putString("NOME", nome)
                Prefs.putString("EMAIL", email)
                salvar_presenca_firebase(conv)
                toast("Sua presença foi confirmada $nome - $email!")
                switchFragment(ConfirmationFragment.newInstance())
            } else {
                toast("Por favor digite um email válido!")
            }
        } else {
            toast("É impossivel que seu Nome Completo não possua mais que 5 letras")
        }


    }

    fun validar_email(email: String): Boolean {
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true
        }
        return false
    }

    fun switchFragment(fragment: Fragment) {
        supportFragmentManager
                .popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.container, fragment, "fragment")//FragmentAbstrato.KEY
                .commit()

    }

    fun showRouteChurch(view: View) {
        var latChurch = "-25.409932"
        var lonChurch = "-49.255429"
        var igreja = "Igreja Senhor Bom Jesus do Cabral, Curitiba, Paraná, Brasil"
        igreja = Uri.encode(igreja)
        var navigate = "google.navigation:q=$igreja"
        var navigateUri = Uri.parse(navigate)
        var intent = Intent(Intent.ACTION_VIEW, navigateUri)

        intent.setPackage("com.google.android.apps.maps")

        if (intent.resolveActivity(packageManager) == null) {
            val dirAction = "dir_action=navigate"
            val destination = "destination=$igreja"
            navigate = "https://www.google.com/maps/dir/?api=1&$dirAction&$destination"
            navigateUri = Uri.parse(navigate)
            intent = Intent(Intent.ACTION_VIEW, navigateUri)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            toast(getString(R.string.apps_need_maps)).show()
        }


    }

    fun showRouteSallon(view: View) {

        var sallon = "Salão Bento Munhoz, Jockey Clube do Paraná, Curitiba, Paraná, Brasil"
        sallon = Uri.encode(sallon)
        var navigate = "google.navigation:q=$sallon"
        var navigateUri = Uri.parse(navigate)
        var intent = Intent(Intent.ACTION_VIEW, navigateUri)

        intent.setPackage("com.google.android.apps.maps")

        if (intent.resolveActivity(packageManager) == null) {
            val dirAction = "dir_action=navigate"
            val destination = "destination=$sallon"
            navigate = "https://www.google.com/maps/dir/?api=1&$dirAction&$destination"
            navigateUri = Uri.parse(navigate)
            intent = Intent(Intent.ACTION_VIEW, navigateUri)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            toast(getString(R.string.apps_need_maps)).show()
        }


    }


}
