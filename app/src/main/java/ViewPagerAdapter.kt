import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import gomez.abraham.organizadoreventos.ui.eventos.EventosFragment
import gomez.abraham.organizadoreventos.ui.slideshow.PresupuestoFragment
import gomez.abraham.organizadoreventos.ui.tareas.TareasFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle):FragmentStateAdapter(fragmentManager,lifecycle){
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0->{
                EventosFragment()
            }
            1 ->{
                TareasFragment()
            }
            else ->{
                PresupuestoFragment()
            }
        }
    }

}