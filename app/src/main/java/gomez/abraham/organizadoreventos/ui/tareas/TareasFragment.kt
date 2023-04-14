package gomez.abraham.organizadoreventos.ui.tareas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import gomez.abraham.organizadoreventos.databinding.FragmentTareasBinding
import gomez.abraham.organizadoreventos.ui.eventos.AdaptadorEventos
import kotlinx.android.synthetic.main.fragment_eventos.*

class TareasFragment : Fragment() {

private var _binding: FragmentTareasBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.

    var tareas = ArrayList<Tarea>()
    private val binding get() = _binding!!



  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val tareasViewModel =
            ViewModelProvider(this).get(TareasViewModel::class.java)

    _binding = FragmentTareasBinding.inflate(inflater, container, false)
    val root: View = binding.root
      tareas.clear()
    tareasDePrueba()
    var listView: ListView = binding.listviewTareas
    tareasViewModel.text.observe(viewLifecycleOwner) {
        val adapter = AdaptadorTareas(requireContext(), tareas)
        listView.adapter = adapter
    }
    return root
  }

    fun tareasDePrueba(){
        tareas.add(Tarea(false, "prueba de tarea 1"))
        tareas.add(Tarea(false, "prueba de tarea 2"))
        tareas.add(Tarea(false, "prueba de tarea 3"))
        tareas.add(Tarea(false, "prueba de tarea 4"))
        tareas.add(Tarea(false, "prueba de tarea 5"))
    }


override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}