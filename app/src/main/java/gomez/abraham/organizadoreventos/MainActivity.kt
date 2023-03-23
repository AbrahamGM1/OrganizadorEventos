package gomez.abraham.organizadoreventos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var boton: Button = findViewById(R.id.boton_prueba)

        boton.setOnClickListener {
            var intent = Intent(this, EventosActivity::class.java)
            startActivity(intent)
        }

    }
}