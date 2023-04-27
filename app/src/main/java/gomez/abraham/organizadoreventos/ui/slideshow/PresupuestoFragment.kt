package gomez.abraham.organizadoreventos.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import gomez.abraham.organizadoreventos.InvitadosFragment
import gomez.abraham.organizadoreventos.R
import gomez.abraham.organizadoreventos.databinding.FragmentPresupuestoBinding
import gomez.abraham.organizadoreventos.ui.eventos.EventosFragment
import gomez.abraham.organizadoreventos.ui.tareas.TareasFragment

class PresupuestoFragment : Fragment() {

private var _binding: FragmentPresupuestoBinding? = null

  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val presupuestoViewModel =
            ViewModelProvider(this).get(PresupuestoViewModel::class.java)

    _binding = FragmentPresupuestoBinding.inflate(inflater, container, false)
    val root: View = binding.root
      //


    presupuestoViewModel.text.observe(viewLifecycleOwner) {
    }
    return root
  }


override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}




