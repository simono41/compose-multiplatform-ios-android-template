import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun App() {
    MaterialTheme {
        val runGame = remember { mutableStateOf(false) }

        if (!runGame.value) {
            Column {
                Spacer(modifier = Modifier.size(50.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(onClick = { runGame.value = true }) {
                        Text(text = "Start Game")
                    }
                }
            }
        } else {

            val fieldPos = remember { mutableStateListOf<Field>() }
            val selectedPos = remember { mutableStateOf(0) }
            val houses = remember { mutableStateOf(0) }
            val population = remember { mutableStateOf(0) }
            val maxPopulation = remember { mutableStateOf(0) }

            if (fieldPos.size != 100) {
                for (i in 0..99) {
                    fieldPos.add(i, Field(text = "x"))
                }
            }

            Column {
                Row {
                    Text(
                        text = "Einwohneranzahl ${population.value} / ${maxPopulation.value}"
                    )
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(10)
                ) {
                    itemsIndexed(fieldPos) { field, item: Field ->
                        TextButton(onClick = {
                            fieldPos[field] = fieldPos[field].copy(text = "o")
                            selectedPos.value = field
                        }) {
                            Text(text = item.text)
                        }
                    }
                }

                Row {
                    Button(onClick = {
                        fieldPos[selectedPos.value] = fieldPos[selectedPos.value].copy(text = "H", isBuild = true)
                        houses.value++
                        maxPopulation.value = houses.value * 5
                    }) {
                        Text("Haus")
                    }
                }
            }
        }
    }
}

expect fun getPlatformName(): String