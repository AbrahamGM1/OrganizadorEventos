package gomez.abraham.organizadoreventos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_agregar_evento.*
import java.util.Objects

class AgregarEventoActivity : AppCompatActivity() {

    var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_evento)

        val txtEvento = findViewById<EditText>(R.id.txt_evento)
        val txtExtra = findViewById<EditText>(R.id.txt_extra)
        val txtColor = findViewById<EditText>(R.id.txt_color)
        val txtFecha = findViewById<EditText>(R.id.txt_fecha)


        btn_agregar_evento.setOnClickListener {

            // Se obtienen los valores de los campos ingresados del formulario
            var evento = txtEvento.text.toString().trim()
            var extra = txtExtra.text.toString().trim()
            var color = txtColor.text.toString().trim()
            var fecha = txtFecha.text.toString().trim()

            // Se comprueba si existe un valor dentro del campo del nombre del evento
            if (evento.isEmpty()){
                Toast.makeText(this,"Ingrese el nombre del evento",Toast.LENGTH_SHORT)
            }else{
                //Se guarda en la base de datos los campos ingresados
                insertar(evento,extra,color,fecha)
                var intent = Intent(this, EventosActivity::class.java)
                startActivity(intent)
            }


        }

    }

    // Función que se encarga de insertar dentro de la base de datos un nuevo evento
    private fun insertar(evento: String, extra: String, color: String, fecha: String) {

        var map: MutableMap<String,Any> = HashMap()
        var listaInvitados = listOf<String>("")
        var listaTareas = listOf<String>("")

        // Se agregan todos los campos que conforman el mapa del evento
        map.put("descripcion",evento)
        map.put("extra",extra)
        map.put("color",color)
        map.put("fecha",fecha)
        map.put("invitados",listaInvitados)
        map.put("tareas",listaTareas)
        map.put("imagen","")

        // Intenta agregar el mapa creado, en caso de que pueda se lo hace saber al usuario, y en caso de que no se pueda pues también
        firestore.collection("eventos").add(map).addOnSuccessListener(OnSuccessListener{ documentReference ->
            Toast.makeText(this,"Evento agregado con éxito",Toast.LENGTH_SHORT)
        }).addOnFailureListener(OnFailureListener {exception ->
            Toast.makeText(this,"No se pudo agregar el evento",Toast.LENGTH_SHORT)
        })
    }


}