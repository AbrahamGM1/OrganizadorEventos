package gomez.abraham.organizadoreventos.ui.eventos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import gomez.abraham.organizadoreventos.databinding.FragmentEventosBinding
import gomez.abraham.organizadoreventos.ui.tareas.AdaptadorTareas
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
    eventosDePrueba()
      var listView: ListView = binding.listviewEventos
      //val textView: TextView = binding.textHome
    eventosViewModel.text.observe(viewLifecycleOwner) {
      //textView.text = it
        val adapter = AdaptadorEventos(requireContext(), eventos)
        listView.adapter = adapter
    }

    return root
  }

    fun eventosDePrueba(){
        eventos.add(Evento("Prueba de evento 1", null))
        eventos.add(Evento("Prueba de evento 2", null))
        eventos.add(Evento("Prueba de evento 3", null))
        eventos.add(Evento("Prueba de evento 4", null))
        eventos.add(Evento("Prueba de evento 5", null))

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

