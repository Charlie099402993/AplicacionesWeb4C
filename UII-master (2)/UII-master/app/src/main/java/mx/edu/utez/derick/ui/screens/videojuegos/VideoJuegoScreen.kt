package mx.edu.utez.derick.ui.screens.videojuegos

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun VideoJuegoScreen(
    viewModel: VideoJuegosViewModel = viewModel()
) {
    val listaElementos by viewModel.elementos.collectAsStateWithLifecycle()
    Column(){
        Text(
            "Mis VideoJuegos Favoritos",
            style = MaterialTheme.typography.titleLarge
        )
        ListaContenido(
            elementos =  listaElementos,
            onItemClick = { id -> viewModel.actualizarFavorito(id) }
        )
    }
}