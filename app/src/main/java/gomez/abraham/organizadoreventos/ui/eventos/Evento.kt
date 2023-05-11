package gomez.abraham.organizadoreventos.ui.eventos
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference
import gomez.abraham.organizadoreventos.models.Invitado


data class Evento(
    var nombre: String,
    var descripcion: String,
    var fecha: Timestamp,
    var imagen: DocumentReference,
    var invitados: List<Invitado>,
    var tareas: List<String>
)