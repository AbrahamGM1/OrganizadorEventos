package gomez.abraham.organizadoreventos.ui.tareas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import gomez.abraham.organizadoreventos.R
import kotlinx.android.synthetic.main.layout_evento.view.*
import kotlinx.android.synthetic.main.layout_tareas.view.*
import org.w3c.dom.Text

class AdaptadorTareas: BaseAdapter {

    var context: Context
    var tareas = ArrayList<Tarea>()


    inner class TareasViewHolder(val v:View):RecyclerView.ViewHolder(v){
        var checkTarea:CheckBox
        var nombreTarea: TextView
        var mMenus: Button
        init{
            checkTarea = v.findViewById<CheckBox>(R.id.check_tarea)
            nombreTarea = v.findViewById<TextView>(R.id.tv_nombre_tarea)
            mMenus = v.findViewById<Button>(R.id.btn_pop_up)
            mMenus.setOnClickListener{popupMenus()}
        }

        private fun popupMenus(){
            val popupMenus = PopupMenu(context,v)
            popupMenus.inflate(R.menu.eventos)
        }

    }
    constructor(context: Context, tareas:ArrayList<Tarea>){
        this.context = context
        this.tareas = tareas
    }

    override fun getCount(): Int {
        return tareas.size
    }

    override fun getItem(p0: Int): Any {
        return tareas[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var inflador = LayoutInflater.from(context)
        var vista = inflador.inflate(R.layout.layout_tareas,null)
        var tarea = tareas[p0]
        val mMenus = vista.findViewById<Button>(R.id.btn_pop_up)
        vista.check_tarea.isChecked = tarea.completada
        vista.tv_nombre_tarea.text = tarea.nombre

        vista.btn_pop_up.setOnClickListener {
            val popupMenus = PopupMenu(this.context,vista.btn_pop_up)
            popupMenus.inflate(R.menu.menu_tareas)

            ///Menu popup de las tareas
            popupMenus.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.popup_editar->{
                        Toast.makeText(this.context,"Editar",Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.popup_eliminar->{
                        Toast.makeText(this.context,"Eliminar",Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> true
                }
            }
            popupMenus.show()
            ///

        }




        return vista
    }



}