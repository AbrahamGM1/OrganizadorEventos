package gomez.abraham.organizadoreventos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import gomez.abraham.organizadoreventos.databinding.ActivityInicioSesionBinding

class InicioSesionActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityInicioSesionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        binding = ActivityInicioSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.loginButton.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            Toast.makeText(this, "Iniciando sesión...", Toast.LENGTH_SHORT).show()
            when{
                email.isEmpty() -> {
                    binding.email.error = "Email Required"
                    binding.email.requestFocus()
                }
                password.isEmpty() -> {
                    binding.password.error = "Password Required"
                    binding.password.requestFocus()
                }
                else -> {
                    signIn(email, password)
                }
            }
        }

        binding.registerButton.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.forgotPassword.setOnClickListener {
            val intent = Intent(this, RecuperarClaveActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun signIn(email:String, password:String){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Sign in success
                Log.d("TAG", "signInWithEmail:success")
                Toast.makeText(baseContext, "Authentication success.", Toast.LENGTH_SHORT).show()
                reload()
            } else {
                // Sign in failed
                Log.w("TAG", "signInWithEmail:failure", task.exception)
                Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                // updateUI(null)
            }
        }
    }

    private fun reload() {
        val intent = Intent(this, EventosActivity::class.java)
        startActivity(intent)
        finish()
    }
}