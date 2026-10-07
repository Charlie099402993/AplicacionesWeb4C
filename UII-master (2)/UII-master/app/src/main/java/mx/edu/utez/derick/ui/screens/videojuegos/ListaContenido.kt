package mx.edu.utez.derick.ui.screens.videojuegos

import android.R.attr.onClick
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.edu.utez.derick.ui.theme.ProyectoTheme

//Composable de contenido (Sin que use el view model ES UI)
@Composable
fun ListaContenido (
    elementos: List<VideoJuego>,
    onItemClick: (Int) -> Unit
) {
    LazyColumn(
        Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = elementos,
            key = { item -> item.id }
        ){
            item ->
            ItemTarjeta( item=item, onClick = { onItemClick(item.id) } )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListaContenidoPreview(){
    ProyectoTheme{
        val v1 = VideoJuego(1,"Zelda", "aventura", true)
        val v2 = VideoJuego(2,"Mario", "aventura", true)
        val v3 = VideoJuego(3,"Clash Royal", "RPG", false)
        val lista = listOf(v1,v2,v3)
        ListaContenido(lista, {})
    }
}