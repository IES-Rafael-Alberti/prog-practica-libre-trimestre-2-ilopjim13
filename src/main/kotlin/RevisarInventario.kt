import com.github.ajalt.mordant.rendering.Whitespace
import com.github.ajalt.mordant.terminal.Terminal
import com.github.ajalt.mordant.widgets.Panel
import com.github.ajalt.mordant.widgets.Text

object RevisarInventario {

    private fun revisarInventario(jugador: Jugador) {
        val t = Terminal()
        t.println(
            Panel(
                content = textoInventario(jugador),
                title = Text("** INVENTARIO **")
            )
        )
    }

    fun menuInventario(jugador: Jugador) {
        revisarInventario(jugador)
        println("¿Que quiere hacer?")
        println("1- Utilizar objeto")
        println("2- Leer descripcion")
        println("3- Volver")

        Vista.pedirOpcion(3)
    }

    private fun textoInventario(jugador: Jugador):Text {
        var texto = ""
        jugador.inventario.inventario.forEach {
            if (jugador.inventario.inventario.size != 1) texto += "- ${it.key}: ${it.value}\n "
            else texto += "- ${it.key}: ${it.value} "
        }
        return Text(texto, whitespace = Whitespace.PRE)
    }


}