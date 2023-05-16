package gomez.abraham.organizadoreventos.ui.eventos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.findFragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
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

    //Referencia a la base de datos
    val db = FirebaseFirestore.getInstance()
    //Se consultan todos los elementos que conforman la colección de eventos
    val eventosRef = db.collection("eventos")


  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

      val eventosViewModel = ViewModelProvider(this).get(EventosViewModel::class.java)

      _binding = FragmentEventosBinding.inflate(inflater, container, false)

      val root: View = binding.root

      // Se inicializa la listView que pertenece a eventos
      var listView: ListView = binding.listviewEventos

    eventosViewModel.text.observe(viewLifecycleOwner) {
        adaptador = AdaptadorEventos(requireContext(), eventos)
        listView.adapter = adaptador

    }

      val eventosList: MutableList<DocumentSnapshot> = mutableListOf()

      //Sacar un listado con todos los id de los eventos
      db.collection("eventos").get()
          .addOnSuccessListener { querySnapshot ->
              for (documentSnapshot in querySnapshot) {
                  eventosList.add(documentSnapshot)
              }
          }
          .addOnFailureListener { exception ->
            exception.printStackTrace()
          }


      //Al pulsar alguno de los elementos que conforman la lista de los eventos:
      listView.setOnItemClickListener { adapterView, view, i, l ->
          val eventoSeleccionado = eventosList[i]
          val idEvento = eventoSeleccionado.id
          //Crea un fragmento nuevo de tareas
          val myNewFragment = TareasFragment()

          //Le manda el id al fragment de tareas para que pueda hacer la consulta de tareas
          myNewFragment.setIdEvento(idEvento)

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

    return root
  }

    //Al crearse el fragmento se utilizan las consultas de la base de datos
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Si se pudo consultar exitosamente, crea objetos evento para llenar la lista de eventos
        eventosRef.get().addOnSuccessListener{querySnapshot ->
            for (document in querySnapshot){
                //Obtiene la descripcion almacenada en la base de datos
                val descripcion = document.getString("descripcion")

                //lista de prueba(cambiar por los invitados y las tareas)
                val listaDeStrings = mutableListOf<String>()
                listaDeStrings.add("elemento 1")
                var lista: List<String>
                lista = listaDeStrings.toList()
                //Crea un objeto tipo evento con la información sacada de la base de datos y lo añade a la lista a mostrar
                eventos.add(Evento(descripcion!!,"Descripción extra", "11/05/2023","","",lista,lista))
                adaptador.notifyDataSetChanged()



            }
        }.addOnFailureListener{exception->
            //Avisa que no jaló la base de datos
            Toast.makeText(requireContext(),"Error al consultar",Toast.LENGTH_SHORT)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
