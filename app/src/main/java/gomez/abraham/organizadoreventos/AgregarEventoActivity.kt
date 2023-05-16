package gomez.abraham.organizadoreventos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_agregar_evento.*
import java.lang.ref.Reference
import java.util.Objects

class AgregarEventoActivity : AppCompatActivity() {

    var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    var file = 1
    val database = Firebase.database
    val myRef = database.getReference("eventos")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_evento)

        val txtEvento = findViewById<EditText>(R.id.txt_evento)
        val txtExtra = findViewById<EditText>(R.id.txt_extra)
        val txtColor = findViewById<EditText>(R.id.txt_color)
        val txtFecha = findViewById<EditText>(R.id.txt_fecha)



        adjuntar_imagen.setOnClickListener {
            fileUpload()
        }


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
                confirmar()
                insertar(evento,extra,color,fecha,imagenDescargaUrl!!)
                var intent = Intent(this, EventosActivity::class.java)
                startActivity(intent)
            }


        }



    }

    // Declara una variable de instancia para guardar la URL de descarga
    private var imagenDescargaUrl: String? = null

    fun fileUpload(){
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        startActivityForResult(intent, file)
    }

    //Obtiene el url de la imagen
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==file){
            if (resultCode== RESULT_OK){
                val FileUri = data!!.data
                val folder: StorageReference = FirebaseStorage.getInstance().getReference().child("eventos")
                val fileName: StorageReference = folder.child("file"+FileUri!!.lastPathSegment)
                fileName.putFile(FileUri).addOnSuccessListener {taskSnapshot->
                    fileName.downloadUrl.addOnSuccessListener{uri->
                        // Guarda la URL de descarga en la variable de instancia
                        imagenDescargaUrl = uri.toString()
                        Toast.makeText(this,imagenDescargaUrl,Toast.LENGTH_SHORT)
                    }
                }
            }
        }
    }

    //guarda la imagen según
    fun confirmar(){
        if(imagenDescargaUrl != null){
            val hashMap = HashMap<String, String>()
            hashMap["imagen"] = java.lang.String.valueOf(imagenDescargaUrl)

            val mutableMap: MutableMap<String, Any> = hashMapOf()
            mutableMap.putAll(hashMap)
            myRef.updateChildren(mutableMap).addOnSuccessListener {
                // Actualización de datos en la base de datos exitosa
                Toast.makeText(this, "Datos actualizados con éxito", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                // Ocurrió un error al actualizar los datos en la base de datos
                Toast.makeText(this, "Error al actualizar los datos", Toast.LENGTH_SHORT).show()
            }
        }else{
            // La imagen aún no se ha cargado, muestra un mensaje al usuario para que adjunte la imagen primero
            Toast.makeText(this, "Por favor, adjunte una imagen primero", Toast.LENGTH_SHORT).show()
        }
    }


    // Función que se encarga de insertar dentro de la base de datos un nuevo evento
    private fun insertar(evento: String, extra: String, color: String, fecha: String, imagen: String) {

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
        map.put("imagen",imagen)

        // Intenta agregar el mapa creado, en caso de que pueda se lo hace saber al usuario, y en caso de que no se pueda pues también
        firestore.collection("eventos").add(map).addOnSuccessListener(OnSuccessListener{ documentReference ->
            Toast.makeText(this,"Evento agregado con éxito",Toast.LENGTH_SHORT)
        }).addOnFailureListener(OnFailureListener {exception ->
            Toast.makeText(this,"No se pudo agregar el evento",Toast.LENGTH_SHORT)
        })
    }


}