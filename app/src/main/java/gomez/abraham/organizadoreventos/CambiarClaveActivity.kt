package gomez.abraham.organizadoreventos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat.startActivity

class CambiarClaveActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cambiar_clave)

        //Initialize UI elements
        val password: EditText = findViewById(R.id.new_password)
        val confirmPassword: EditText = findViewById(R.id.repeat_new_password)
        val btnCambiarClave: Button = findViewById(R.id.reset_password)

        //Set event listeners
        btnCambiarClave.setOnClickListener {
            val intent = Intent(this, InicioSesionActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}