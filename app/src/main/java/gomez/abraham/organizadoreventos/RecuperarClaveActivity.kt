package gomez.abraham.organizadoreventos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat.startActivity

class RecuperarClaveActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar_clave)

        //Initialize UI elements
        val email: EditText = findViewById(R.id.email)
        val btnRecuperarClave: Button = findViewById(R.id.send_code)

        //Set event listeners
        btnRecuperarClave.setOnClickListener {
            val intent = Intent(this, CambiarClaveActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}