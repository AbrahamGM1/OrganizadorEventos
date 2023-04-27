package gomez.abraham.organizadoreventos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.google.android.material.bottomsheet.BottomSheetDialog

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        //Remove title bar
        supportActionBar?.hide()

        //Initialize UI elements
        val email: EditText = findViewById(R.id.email)
        val password: EditText = findViewById(R.id.password)
        val btnRegister: Button = findViewById(R.id.sign_up_button)
        val alreadyHaveAccount: Button = findViewById(R.id.sign_in_button)

        //Set event listeners
        btnRegister.setOnClickListener {
            val bottomSheet = BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.term_conditions_bottom_sheet, null)
            bottomSheet.setContentView(view)
            bottomSheet.show()

            //On click btnAceptarTerminosCondiciones go to the next activity
            val btnAceptarTerminosCondiciones: Button = view.findViewById(R.id.accept)
            val btnCancelarTerminosCondiciones: TextView = view.findViewById(R.id.cancel)

            btnAceptarTerminosCondiciones.setOnClickListener {
                val intent = Intent(this, EventosActivity::class.java)
                startActivity(intent)
                finish()
            }

            btnCancelarTerminosCondiciones.setOnClickListener {
                bottomSheet.dismiss()
            }
        }

        alreadyHaveAccount.setOnClickListener {
            val intent = Intent(this, InicioSesionActivity::class.java)
            startActivity(intent)
            finish()
        }

    }


}