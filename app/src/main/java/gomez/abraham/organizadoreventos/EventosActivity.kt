package gomez.abraham.organizadoreventos
import ViewPagerAdapter
import android.content.ClipData
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.SearchView
import android.widget.TextView
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
import gomez.abraham.organizadoreventos.ui.eventos.EventosFragment
import gomez.abraham.organizadoreventos.ui.tareas.AdaptadorTareas
import gomez.abraham.organizadoreventos.ui.tareas.Tarea
import kotlinx.android.synthetic.main.fragment_tabs.*
import kotlinx.android.synthetic.main.fragment_eventos.*
import kotlinx.android.synthetic.main.fragment_presupuesto.*
import kotlinx.android.synthetic.main.fragment_presupuesto.view.*
import kotlinx.android.synthetic.main.fragment_tareas.*
import org.w3c.dom.Text

class EventosActivity : AppCompatActivity() {



    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityEventosBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEventosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /////////tabs
        val adapter:ViewPagerAdapter = ViewPagerAdapter(supportFragmentManager,lifecycle)
        //////////////////


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
            R.id.nav_menu, R.id.nav_menu, R.id.nav_menu), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        println(R.id.nav_menu)
        println(R.id.nav_eventos)


        // OnClickListener to InvitadosActivity
       // guestsButton.setOnClickListener {
       //     val intent = Intent(this, Invitados::class.java)
      //      startActivity(intent)
       // }

    }


   override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.eventos, menu)
        val buscar: MenuItem? = menu?.findItem(R.id.action_search)
        val searchView = buscar?.actionView as SearchView

        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                p0?.let {
                   TODO()
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                TODO()
            }

        })

        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_eventos)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_settings -> {
                var intent = Intent(this, AjustesActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }



}