package gomez.abraham.organizadoreventos

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.viewmodel.viewModelFactory
import com.google.android.material.tabs.TabLayout
import gomez.abraham.organizadoreventos.ui.eventos.EventosFragment
import gomez.abraham.organizadoreventos.ui.slideshow.PresupuestoFragment
import gomez.abraham.organizadoreventos.ui.tareas.TareasFragment
import java.util.logging.Filter

class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragments = listOf(
        EventosFragment(),
        TareasFragment(),
        InvitadosFragment(),
        PresupuestoFragment()
    )

    val eventosFragment:Fragment = fragments.get(0)
    val tareasFragment:Fragment = fragments.get(1)
    val invitadosFragment:Fragment = fragments.get(2)
    val presupuestoFragment:Fragment = fragments.get(3)





    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            //0 -> "Eventos"
            1 -> "Tareas"
            2 -> "Invitados"
            3 -> "Presupuesto"
            else -> ""
        }
    }


}