package gomez.abraham.organizadoreventos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Invitados : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invitados)

        val addGuestActivity: FloatingActionButton = findViewById(R.id.add_guest_activity_Button)





        //setOnClickListeners
        addGuestActivity.setOnClickListener {
            var intent = Intent(this, AgregarInvitado::class.java)
            startActivity(intent)


        }

        //

    }
}