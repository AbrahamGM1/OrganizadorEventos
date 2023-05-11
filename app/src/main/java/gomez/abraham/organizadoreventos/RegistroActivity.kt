package gomez.abraham.organizadoreventos

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class RegistroActivity : AppCompatActivity() {

    val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        //Remove title bar
        supportActionBar?.hide()

        //Initialize UI elements
        val email: EditText = findViewById(R.id.email)
        val password: EditText = findViewById(R.id.password)
        val username: EditText = findViewById(R.id.username)
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

                auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {3
                            //
                            val user = auth.currentUser
                            //Set user display name
                            val profileUpdates = UserProfileChangeRequest.Builder()
                                .setDisplayName(username.text.toString())
                                .build()

                            user?.updateProfile(profileUpdates)

                            val sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)
                            val editor = sharedPreferences.edit()
                            //Add user data to local storage
                            editor.putString("email", email.text.toString())
                            editor.putString("username", username.text.toString())
                            editor.apply()
                            // Sign in success, go to the next activity
                            val intent = Intent(this, EventosActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            //Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Ha sucedido un error, por favor intentelo de nuevo.",
                                Toast.LENGTH_SHORT).show()
                        }
                    }


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