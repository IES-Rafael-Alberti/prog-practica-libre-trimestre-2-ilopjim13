import com.github.ajalt.mordant.table.table
import com.github.ajalt.mordant.terminal.Terminal
import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.rendering.TextStyle
import com.github.ajalt.mordant.rendering.BorderType.Companion.SQUARE_DOUBLE_SECTION_SEPARATOR
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextAlign.*
import com.github.ajalt.mordant.rendering.TextStyles
import com.github.ajalt.mordant.table.Borders
import com.github.ajalt.mordant.table.Borders.NONE
import com.github.ajalt.mordant.table.Borders.BOTTOM
import com.github.ajalt.mordant.table.Borders.ALL
import com.github.ajalt.mordant.table.Borders.LEFT_BOTTOM
import com.github.ajalt.mordant.table.Borders.TOP_BOTTOM

class Tienda (val itemsEnTienda: List<Items>) {

    companion object {
        val inventarioEnTienda = mutableMapOf<Items, Int>()

        fun actualizarTiendaDiaria(): MutableMap<Items, Int> {
            val lista = inventarioEnTienda.toList()
            val inventarioDiario = mutableMapOf<Items, Int>()
            lista.shuffled()
            while (lista.size == 5) {
                val item = lista.last()
                if (item.first !in inventarioDiario) inventarioDiario[item.first] = item.second
            }
            return inventarioDiario
        }
    }

    fun actualizarCantidades(item: Items, inventarioDiario: MutableMap<Items, Int>) {
        inventarioEnTienda[item] = inventarioEnTienda[item]!! - 1
        inventarioDiario[item] = inventarioDiario[item]!! - 1
    }

    fun agregarItemsATienda() {
        itemsEnTienda.forEach {

            when (it.rango) {
                Rango.E -> inventarioEnTienda[it] = (1..20).random()
                Rango.D -> inventarioEnTienda[it] = (1..15).random()
                Rango.C -> inventarioEnTienda[it] = (1..10).random()
                Rango.B -> inventarioEnTienda[it] = (1..6).random()
                Rango.A -> inventarioEnTienda[it] = (1..3).random()
                Rango.S -> inventarioEnTienda[it] = 1
            }


        }
    }

}