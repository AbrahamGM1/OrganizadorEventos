package gomez.abraham.organizadoreventos.ui.tareas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import gomez.abraham.organizadoreventos.R
import kotlinx.android.synthetic.main.layout_evento.view.*
import kotlinx.android.synthetic.main.layout_tareas.view.*

class AdaptadorTareas: BaseAdapter {

    var context: Context
    var tareas = ArrayList<Tarea>()

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
        vista.check_tarea.isChecked = tarea.completada
        vista.tv_nombre_tarea.text = tarea.nombre
        vista.tv_fecha_tarea.text = tarea.fecha

        return vista
    }

}