package gomez.abraham.organizadoreventos.ui.tareas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import gomez.abraham.organizadoreventos.databinding.FragmentTareasBinding

class TareasFragment : Fragment() {

private var _binding: FragmentTareasBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
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

    val textView: TextView = binding.textGallery
    tareasViewModel.text.observe(viewLifecycleOwner) {
      textView.text = it
    }
    return root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}