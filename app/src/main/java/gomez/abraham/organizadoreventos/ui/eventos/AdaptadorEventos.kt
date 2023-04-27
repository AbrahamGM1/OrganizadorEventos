package gomez.abraham.organizadoreventos.ui.eventos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import gomez.abraham.organizadoreventos.R
import kotlinx.android.synthetic.main.layout_evento.view.*
import kotlinx.android.synthetic.main.nav_header_eventos.view.*

class AdaptadorEventos: BaseAdapter {

    var context: Context
    var Eventos = ArrayList<Evento>()

    constructor(context: Context,eventos:ArrayList<Evento>){
        this.context = context
        this.Eventos = eventos
    }

    override fun getCount(): Int {
        return Eventos.size
    }

    override fun getItem(p0: Int): Any {
    return Eventos[p0]
    }

    override fun getItemId(p0: Int): Long {
    return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var inflador = LayoutInflater.from(context)
        var vista = inflador.inflate(R.layout.layout_evento,null)
        var evento = Eventos[p0]
        vista.tv_evento.text = evento.nombreEvento

        return vista
    }

}
