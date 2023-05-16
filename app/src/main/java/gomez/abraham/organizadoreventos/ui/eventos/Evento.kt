package gomez.abraham.organizadoreventos.ui.eventos

// import com.google.firebase.Timestamp
// import com.google.firebase.firestore.DocumentReference


data class Evento(
    var descripcion: String,
    var extra: String,
    var fecha: String,
    var color: String,
    var imagen: String,
    var invitados: List<String>,
    var tareas: List<String>
) {
    constructor() : this("", "", "", "", "", listOf(), listOf())
}