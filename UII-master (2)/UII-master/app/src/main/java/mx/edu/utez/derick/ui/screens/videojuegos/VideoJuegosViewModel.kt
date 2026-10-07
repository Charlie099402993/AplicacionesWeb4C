package mx.edu.utez.derick.ui.screens.videojuegos

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

//es posible definir una clase de datos
data class VideoJuego(
    val id: Int,
    val titulo: String,
    val descripcion: String,
    val esFavorito: Boolean = false
)

class VideoJuegosViewModel: ViewModel() {
    //Codigo de logica
    //Estado Interno
    private val _elementos = MutableStateFlow<List<VideoJuego>>(emptyList())
    //estado publico
    val elementos: StateFlow<List<VideoJuego>> = _elementos.asStateFlow()

    init{
        val videojuego1 = VideoJuego(1,"Mario","aventura",true)
        val videojuego2 = VideoJuego(2,"Mario2","aventura",false)
        val videojuego3 = VideoJuego(3,"Mario3","aventura",true)
        _elementos.value = listOf(videojuego1,videojuego2,videojuego3)
    }

    fun actualizarFavorito(id: Int){
        _elementos.update { listaActual ->
            listaActual.map { item ->
                if (item.id == id){
                    item.copy(esFavorito = !item.esFavorito)
                } else {
                    item
                }
            }
        }
    }

}