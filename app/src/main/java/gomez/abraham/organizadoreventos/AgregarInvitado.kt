package gomez.abraham.organizadoreventos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import gomez.abraham.organizadoreventos.models.Invitado

class AgregarInvitado : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_invitado)

        supportActionBar?.setTitle("Agregar invitado")

        // Inicializar la base de datos
        //val database = FirebaseDatabase.getInstance()
        //val reference = database.getReference("eventos")
        val eventoRef = FirebaseFirestore.getInstance().collection("eventos").document("cL8wKxtfggGa5XV0U8EU")

        // Obtener referencia a los views
        val nombreEditText: EditText = findViewById(R.id.nombre_edit)
        val apellidoEditText: EditText = findViewById(R.id.apellidos_edit)
        val telefonoEditText: EditText = findViewById(R.id.telefono_edit)
        val correoEditText: EditText = findViewById(R.id.correo_edit)
        val direccionEditText: EditText = findViewById(R.id.direccion_edit)
        val agregarButton: Button = findViewById(R.id.add_guest_Button)
        val edadRadioGroup: RadioGroup = findViewById(R.id.edadRadioGroup)
        val generoRadioGroup: RadioGroup = findViewById(R.id.generoRadioGroup)
        val tipoMenuSpinner: Spinner = findViewById(R.id.menu_spinner)
        val confirmacionRadioGroup: RadioGroup = findViewById(R.id.confirmacion_group)

        agregarButton.setOnClickListener {
            val nombre = nombreEditText.text.toString()
            val apellido = apellidoEditText.text.toString()
            val telefono = telefonoEditText.text.toString()
            val correo = correoEditText.text.toString()
            val direccion = direccionEditText.text.toString()

            val edadRadioButtonId = edadRadioGroup.checkedRadioButtonId
            val edadRadioButton: RadioButton = findViewById(edadRadioButtonId)
            val edad = edadRadioButton.text.toString()

            val generoRadioButtonId = generoRadioGroup.checkedRadioButtonId
            val generoRadioButton: RadioButton = findViewById(generoRadioButtonId)
            val genero = generoRadioButton.text.toString()

            val confirmacionRadioButtonId = confirmacionRadioGroup.checkedRadioButtonId
            val confirmacionRadioButton: RadioButton = findViewById(confirmacionRadioButtonId)
            val confirmacion = confirmacionRadioButton.text.toString() == "Invitación aceptada"


            val tipoMenu = tipoMenuSpinner.selectedItem.toString()

            val invitado = Invitado(
                nombre,
                apellido,
                edad,
                genero,
                tipoMenu,
                telefono,
                correo,
                direccion,
                confirmacion
            )

            Log.d("TAG", invitado.toString())
            eventoRef.update("invitados", FieldValue.arrayUnion(invitado))

            Toast.makeText(
                applicationContext,
                "Invitado agregado exitosamente",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}