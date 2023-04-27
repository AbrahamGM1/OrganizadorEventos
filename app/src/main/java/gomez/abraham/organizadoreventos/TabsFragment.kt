package gomez.abraham.organizadoreventos

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import java.util.Objects.requireNonNull

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TabsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TabsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
//////////////////
lateinit var tabLayout:TabLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = view.findViewById<ViewPager>(R.id.view_pager)
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        tabLayout.tabMode = TabLayout.MODE_FIXED
        tabLayout.setBackgroundColor(Color.WHITE)

        viewPager.adapter = MyPagerAdapter(childFragmentManager)

        this.tabLayout = tabLayout

        tabLayout.setupWithViewPager(viewPager)
        (requireNonNull(tabLayout.getTabAt(0))?.view as LinearLayout).visibility = View.GONE


}


    override fun onResume() {
        super.onResume()
        val selectedTabPosition = tabLayout.selectedTabPosition
        when (selectedTabPosition) {
            1 -> {
                // Si estamos en el segundo fragmento, seleccionamos el primer fragmento.
                tabLayout.getTabAt(0)?.select()
            }
            2 -> {
                // Si estamos en el tercer fragmento, seleccionamos el segundo fragmento.
                tabLayout.getTabAt(0)?.select()
            }
            3 -> {
                // Si estamos en el tercer fragmento, seleccionamos el segundo fragmento.
                tabLayout.getTabAt(0)?.select()
            }
            else -> {
                // Si estamos en cualquier otra posición, seleccionamos el primer fragmento.
                tabLayout.getTabAt(0)?.select()
            }
        }
    }



//////////////////
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflater =inflater.inflate(R.layout.fragment_tabs, container, false)


    return inflater

    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TabsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TabsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}