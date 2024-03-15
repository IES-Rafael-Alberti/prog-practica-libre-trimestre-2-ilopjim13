import EstadisticaYRango.Estadisticas
import EstadisticaYRango.Rango
import Personaje.Jugador
import Tienda.Tienda
import org.junit.jupiter.api.Test

class TiendaTest {

    @Test
    fun comprobarIdPiedra() {
        val jugador = Jugador("Prueba", 2)
        val item = Item.Item.Material("Pierda", 5, Rango.E, Estadisticas(5.0,4.0,3.0,2.0))
        jugador.inventario.agregarItem(item)
        if (Tienda.comprobarIdPiedra(1, jugador)) assert(true)
        else assert(false)
    }
}