package gomez.abraham.organizadoreventos.ui.eventos

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
import gomez.abraham.organizadoreventos.R
import gomez.abraham.organizadoreventos.TabsFragment
import gomez.abraham.organizadoreventos.databinding.FragmentEventosBinding
import gomez.abraham.organizadoreventos.ui.tareas.TareasFragment
import kotlinx.android.synthetic.main.fragment_eventos.*

class EventosFragment : Fragment() {

private var _binding: FragmentEventosBinding? = null

    // This property is only valid between onCreateView and
  // onDestroyView.
  var eventos = ArrayList<Evento>()
    lateinit var adaptador: AdaptadorEventos
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val eventosViewModel =
            ViewModelProvider(this).get(EventosViewModel::class.java)

    _binding = FragmentEventosBinding.inflate(inflater, container, false)
    val root: View = binding.root
    //eventosDePrueba()
      var listView: ListView = binding.listviewEventos

      //val textView: TextView = binding.textHome
    eventosViewModel.text.observe(viewLifecycleOwner) {
      //textView.text = it
        adaptador = AdaptadorEventos(requireContext(), eventos)
        listView.adapter = adaptador

    }
      listView.setOnItemClickListener { adapterView, view, i, l ->
          val myNewFragment = TareasFragment()
          val fm: FragmentManager = requireActivity().supportFragmentManager
          val transaction = fm.beginTransaction()
          val oldFragment = view.findFragment<EventosFragment>()
          transaction.replace(R.id.tabs_contenedor, myNewFragment)
          transaction.addToBackStack(null)
          transaction.commit()


      }


    return root
  }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // create new fragment
        // create new fragment

        }



    /*fun eventosDePrueba(){
        eventos.add(Evento("Prueba de evento 1", null))
        eventos.add(Evento("Prueba de evento 2", null))
        eventos.add(Evento("Prueba de evento 3", null))
        eventos.add(Evento("Prueba de evento 4", null))
        eventos.add(Evento("Prueba de evento 5", null))

    }*/



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
