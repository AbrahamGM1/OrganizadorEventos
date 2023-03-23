package gomez.abraham.organizadoreventos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.content.ContextCompat.startActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Wait 3 seconds and then go to the next activity
        Handler().postDelayed({
            val intent = Intent(this, InicioSesionActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}