package gomez.abraham.organizadoreventos.ui.tareas

import android.annotation.SuppressLint
import android.content.Intent
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
import gomez.abraham.organizadoreventos.AgregarEventoActivity
import gomez.abraham.organizadoreventos.AgregarTareaActivity
import gomez.abraham.organizadoreventos.EventosActivity
import gomez.abraham.organizadoreventos.R
import gomez.abraham.organizadoreventos.databinding.FragmentTareasBinding
import gomez.abraham.organizadoreventos.ui.eventos.AdaptadorEventos
import kotlinx.android.synthetic.main.fragment_eventos.*
import kotlinx.android.synthetic.main.layout_tareas.*
import java.util.*
import kotlin.collections.ArrayList

class TareasFragment : Fragment() {

    //Inicializamos las variables a usar en los métodos
    private var _binding: FragmentTareasBinding? = null
    var tareas = ArrayList<Tarea>()
    private val binding get() = _binding!!



  @SuppressLint("SuspiciousIndentation")
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    val tareasViewModel = ViewModelProvider(this).get(TareasViewModel::class.java)

    _binding = FragmentTareasBinding.inflate(inflater, container, false)

    val root: View = binding.root

    //Se limpian todas las tareas
    tareas.clear()
    //Se cargan todas las tareas (cambiar por consulta)
    tareasDePrueba()

    //Variable que almacena el listado de tareas que se mostrará dentro del fragment
    var listView: ListView = binding.listviewTareas

    tareasViewModel.text.observe(viewLifecycleOwner) {
        //Muestra el listado de tareas
        val adapter = AdaptadorTareas(requireContext(), tareas)
        listView.adapter = adapter
    }

      //Al pulsar el flotante se manda al activity de agregar tarea
      binding.flotanteTareas.setOnClickListener {
          var intent = Intent(this.requireContext(), AgregarTareaActivity::class.java)
          startActivity(intent)
      }

    //Crea un arreglo con todos los meses y los mete dentro del spinner del fragment
    val meses = ArrayAdapter<String>(this.requireContext(),android.R.layout.simple_spinner_dropdown_item)
    meses.addAll(Arrays.asList("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"))
    binding.spinnerMeses.adapter = meses

    return root

  }

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