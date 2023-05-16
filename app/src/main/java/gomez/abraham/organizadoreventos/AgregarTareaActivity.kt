package gomez.abraham.organizadoreventos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import gomez.abraham.organizadoreventos.ui.eventos.Evento
import gomez.abraham.organizadoreventos.ui.tareas.Tarea
import kotlinx.android.synthetic.main.activity_agregar_evento.*
import kotlinx.android.synthetic.main.activity_agregar_tarea.*
import java.util.*

class AgregarTareaActivity : AppCompatActivity() {

    private lateinit var idEvento: String

    fun getIdEvento(): String {
        return idEvento
    }

    fun setIdEvento(idEvento: String) {
        this.idEvento = idEvento
    }


    val db = FirebaseFirestore.getInstance()
    //Se consultan todos los elementos que conforman la colección de eventos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_tarea)

        val colores = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item)
        colores.addAll(Arrays.asList("Rojo", "Azul", "Rosa", "Amarillo", "Verde", "Blanco", "Naranja", "Morado"))
        spinner_colores.adapter = colores


        btn_agregar_tarea.setOnClickListener {

            val titulo = et_titulo.text.toString()
            val info = et_info.text.toString()
            val fecha = et_fecha.text.toString()
            var completado = false
            val firestore = FirebaseFirestore.getInstance()
            val eventosCollectionRef = firestore.collection("eventos")
            val eventoId = intent.getStringExtra("idEvento")
            val eventoRef = eventosCollectionRef.document(eventoId!!)

            eventoRef.get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val nuevaTarea = Tarea(completado, titulo, info, fecha) // Nueva tarea a agregar

                    val eventosCollectionRef = firestore.collection("eventos")
                    val eventoRef = eventosCollectionRef.document(eventoId)

                    eventoRef.update("tareas", FieldValue.arrayUnion(nuevaTarea))
                        .addOnSuccessListener {
                            // La nueva tarea se agregó correctamente al arreglo "tareas"
                            Toast.makeText(this, "Se agregó la nueva tarea", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener { exception ->
                            // Ocurrió un error al agregar la nueva tarea
                            Toast.makeText(this, "Error al agregar la nueva tarea", Toast.LENGTH_SHORT).show()
                        }
                }
            }



        }


    }



}