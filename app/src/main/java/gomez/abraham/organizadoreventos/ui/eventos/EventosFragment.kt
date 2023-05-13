package gomez.abraham.organizadoreventos.ui.eventos

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.findFragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import gomez.abraham.organizadoreventos.AgregarEventoActivity
import gomez.abraham.organizadoreventos.R
import gomez.abraham.organizadoreventos.TabsFragment
import gomez.abraham.organizadoreventos.databinding.FragmentEventosBinding
import gomez.abraham.organizadoreventos.ui.tareas.TareasFragment
import kotlinx.android.synthetic.main.fragment_eventos.*

class EventosFragment : Fragment() {

private var _binding: FragmentEventosBinding? = null

  //Se inicializan las variables a usar en los métodos
  var eventos = ArrayList<Evento>()
  lateinit var adaptador: AdaptadorEventos
  private val binding get() = _binding!!

    // Obtener una referencia a la ubicación de los datos que deseas consultar
    val database = FirebaseDatabase.getInstance()
    val reference = database.getReference("eventos")
    //Consulta de los eventos por descripcion
    val query:Query = reference.orderByChild("descripcion")



  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

      val eventosViewModel = ViewModelProvider(this).get(EventosViewModel::class.java)

      _binding = FragmentEventosBinding.inflate(inflater, container, false)

      val root: View = binding.root
      // Se inicializan los eventos de prueba (cambiar por la consulta)
      eventosDePrueba()

      // Se inicializa la listView que pertenece a eventos
      var listView: ListView = binding.listviewEventos

    eventosViewModel.text.observe(viewLifecycleOwner) {
        //Muestra el listado de los eventos de prueba (por ahora)
        adaptador = AdaptadorEventos(requireContext(), eventos)
        listView.adapter = adaptador

    }
      //Al pulsar alguno de los elementos que conforman la lista de los eventos:
      listView.setOnItemClickListener { adapterView, view, i, l ->

          //Crea un fragmento nuevo de tareas
          val myNewFragment = TareasFragment()
          val fm: FragmentManager = requireActivity().supportFragmentManager
          val transaction = fm.beginTransaction()

          //Realiza el cambio de fragmentos dentro de la actividad
          transaction.replace(R.id.tabs_contenedor, myNewFragment)
          transaction.addToBackStack(null)
          transaction.commit()
      }

         //Al pulsar el boton flotante te manda al Activity de AgregarEvento
         binding.flotanteEventos.setOnClickListener {
             var intent = Intent(this.requireContext(), AgregarEventoActivity::class.java)
             startActivity(intent)
         }


      query.addValueEventListener(object : ValueEventListener {
          override fun onDataChange(dataSnapshot: DataSnapshot) {
              // Los datos han cambiado

              for (userSnapshot in dataSnapshot.children) {
                  // Obtener la información del usuario y hacer algo con ella
                  val username = userSnapshot.child("username").getValue(String::class.java)
                  val email = userSnapshot.child("email").getValue(String::class.java)
                  val userId = userSnapshot.key

                  // Hacer algo con la información del usuario
              }
          }

          override fun onCancelled(error: DatabaseError) {
              // Error al leer los datos
          }
      })



    return root
  }

    fun eventosDePrueba(){

        val listaDeStrings = mutableListOf<String>()
        listaDeStrings.add("elemento 1")
        listaDeStrings.add("elemento 2")
        listaDeStrings.add("elemento 3")
        var lista: List<String>

        lista = listaDeStrings.toList()

        eventos.add(Evento("Prueba de evento 1","Descripción extra", "11/05/2023","","",lista,lista))
        eventos.add(Evento("Prueba de evento 2","Descripción extra", "11/05/2023","","",lista,lista))
        eventos.add(Evento("Prueba de evento 3","Descripción extra", "11/05/2023","","",lista,lista))
        eventos.add(Evento("Prueba de evento 4","Descripción extra", "11/05/2023","","",lista,lista))
        eventos.add(Evento("Prueba de evento 5","Descripción extra", "11/05/2023","","",lista,lista))

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
