package gomez.abraham.organizadoreventos.ui.tareas

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import gomez.abraham.organizadoreventos.R
import gomez.abraham.organizadoreventos.databinding.FragmentTareasBinding
import gomez.abraham.organizadoreventos.ui.eventos.AdaptadorEventos
import kotlinx.android.synthetic.main.fragment_eventos.*
import kotlinx.android.synthetic.main.layout_tareas.*
import java.util.*
import kotlin.collections.ArrayList

class TareasFragment : Fragment() {

private var _binding: FragmentTareasBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.

    var tareas = ArrayList<Tarea>()
    private val binding get() = _binding!!



  @SuppressLint("SuspiciousIndentation")
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

    val meses = ArrayAdapter<String>(this.requireContext(),android.R.layout.simple_spinner_dropdown_item)
    meses.addAll(Arrays.asList("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"))
      binding.spinnerMeses.adapter = meses
    return root


  }

/*    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btn_pop_up.setOnClickListener{ v ->
            val popupMenu: PopupMenu = PopupMenu(requireContext(),v)
            popupMenu.menuInflater.inflate(R.menu.eventos, popupMenu.menu)



        }

    }*/

    fun tareasDePrueba(){
        tareas.add(Tarea(false, "prueba de tarea 1"))
        tareas.add(Tarea(false, "prueba de tarea 2"))
        tareas.add(Tarea(false, "prueba de tarea 3"))
        tareas.add(Tarea(false, "prueba de tarea 4"))
        tareas.add(Tarea(false, "prueba de tarea 5"))
        tareas.add(Tarea(false, "prueba de tarea 6"))
        tareas.add(Tarea(false, "prueba de tarea 7"))
        tareas.add(Tarea(false, "prueba de tarea 8"))
        tareas.add(Tarea(false, "prueba de tarea 9"))
        tareas.add(Tarea(false, "prueba de tarea 10"))
        tareas.add(Tarea(false, "prueba de tarea 11"))
        tareas.add(Tarea(false, "prueba de tarea 12"))
        tareas.add(Tarea(false, "prueba de tarea 13"))

    }


override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}