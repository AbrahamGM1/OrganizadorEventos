package gomez.abraham.organizadoreventos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.nav_header_eventos.*

class AjustesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajustes)

        //Buttons in Settings
        val editUsernameButton: Button = findViewById(R.id.edit_username_button)
        val editEmailButton: Button = findViewById(R.id.edit_email_button)
        val editPasswordButton: Button = findViewById(R.id.edit_password_button)


        // OnclickListener for each option in settings


            //Edit username onClickListener
        editUsernameButton.setOnClickListener {
            var intent = Intent(this,EditarNombreUsuario::class.java)
            startActivity(intent)

        }

            //Edit email onClickListener
        editEmailButton.setOnClickListener {
            var intent = Intent(this,EditarCorreoActivity::class.java)
            startActivity(intent)
        }

            //Edit password onClickListener
        editPasswordButton.setOnClickListener {
            var intent = Intent(this, EditarNombreUsuario::class.java)
            startActivity(intent)

        }

        //-------------------->
    }
}