package gomez.abraham.organizadoreventos.models

data class Invitado (
        var nombre: String,
        var apellido: String,
        var edad: String,
        var genero: String,
        var tipoMenu: String,
        var telefono: String,
        var correo: String,
        var direccion: String,
        var confirmado: Boolean
)