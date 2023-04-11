package gomez.abraham.organizadoreventos

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import gomez.abraham.organizadoreventos.ui.eventos.EventosFragment
import gomez.abraham.organizadoreventos.ui.slideshow.PresupuestoFragment
import gomez.abraham.organizadoreventos.ui.tareas.TareasFragment

class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragments = listOf(
        EventosFragment(),
        TareasFragment(),
        PresupuestoFragment()
    )

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Tab 1"
            1 -> "Tab 2"
            2 -> "Tab 3"
            else -> ""
        }
    }
}