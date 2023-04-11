package gomez.abraham.organizadoreventos
import ViewPagerAdapter
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import gomez.abraham.organizadoreventos.databinding.ActivityEventosBinding
import gomez.abraham.organizadoreventos.ui.eventos.AdaptadorEventos
import gomez.abraham.organizadoreventos.ui.eventos.Evento
import gomez.abraham.organizadoreventos.ui.tareas.AdaptadorTareas
import gomez.abraham.organizadoreventos.ui.tareas.Tarea
import kotlinx.android.synthetic.main.fragment_tabs.*
import kotlinx.android.synthetic.main.fragment_eventos.*
import kotlinx.android.synthetic.main.fragment_presupuesto.*
import kotlinx.android.synthetic.main.fragment_presupuesto.view.*
import kotlinx.android.synthetic.main.fragment_tareas.*

class EventosActivity : AppCompatActivity() {



    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityEventosBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEventosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /////////tabs
        val adapter:ViewPagerAdapter = ViewPagerAdapter(supportFragmentManager,lifecycle)
//            view_pager.adapter = adapter


     //   TabLayoutMediator(tab_layout,view_pager){tab,position ->
   //         when(position){
   //             0->tab.text = "Tareas"
  //              1->tab.text = "Invitados"
 //              2->tab.text = "Presupuesto"
 //           }
//        }.attach()


        //////


        setSupportActionBar(binding.appBarEventos.toolbar)
        binding.appBarEventos.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            var intent = Intent(this, AgregarTareaActivity::class.java)
            startActivity(intent)
        }

        //Buttons Navbar
        //val guestsButton: Button = findViewById(R.id.boton_invitados)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_eventos)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf( R.id.nav_menu,
            R.id.nav_eventos, R.id.nav_tarea, R.id.nav_presupuesto), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)




        // OnClickListener to InvitadosActivity
       // guestsButton.setOnClickListener {
       //     val intent = Intent(this, Invitados::class.java)
      //      startActivity(intent)
       // }

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.eventos, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_eventos)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}