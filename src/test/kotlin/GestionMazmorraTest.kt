import Enemigo.Enemigo
import Enemigo.Goblin
import Enemigo.TipoEnemigo
import EstadisticaYRango.Estadisticas
import EstadisticaYRango.Rango
import Mazmorra.GestionMazmorra
import com.github.ajalt.mordant.widgets.Text
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class GestionMazmorraTest {

    @Test
    fun textoSala() {
        val enemigo:Enemigo = Goblin(5, Estadisticas(1.0, 1.0, 1.0, 1.0), Rango.E)
        val enemigo1:Enemigo = Goblin(5, Estadisticas(1.0, 1.0, 1.0, 1.0), Rango.E)
        val lista0 = mutableMapOf<Enemigo, Boolean>()
        val lista1 = mutableMapOf(enemigo to false)
        val lista2 = mutableMapOf(enemigo to false, enemigo1 to false)


        assertEquals(Text("Esta sala no tiene enemigos.").toString(), GestionMazmorra.textoSala(lista0).toString())
        assertEquals(Text("Solo hay un enemigo en la sala").toString(), GestionMazmorra.textoSala(lista1).toString())
        assertEquals(Text("Hay un total de 2 enemigos en la sala").toString(), GestionMazmorra.textoSala(lista2).toString())

    }

    @Test
    fun textoSalaFinal() {
        val enemigo:Enemigo = Goblin(5, Estadisticas(1.0, 1.0, 1.0, 1.0), Rango.E)
        val enemigo1:Enemigo = Goblin(5, Estadisticas(1.0, 1.0, 1.0, 1.0), Rango.E)
        val lista1 = mutableMapOf(enemigo to false)
        val lista2 = mutableMapOf(enemigo to false, enemigo1 to false)


        assertEquals(Text("Solo está el BOSS en la sala").toString(), GestionMazmorra.textoSalaFinal(lista1).toString())
        assertEquals(Text("Hay un total de 1 enemigos en la sala además del BOSS").toString(), GestionMazmorra.textoSalaFinal(lista2).toString())
    }
}