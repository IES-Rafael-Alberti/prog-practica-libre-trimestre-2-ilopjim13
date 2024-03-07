import com.github.ajalt.mordant.rendering.Whitespace.*
import com.github.ajalt.mordant.widgets.Text
import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.terminal.Terminal
import kotlin.math.pow
import kotlin.math.roundToInt

fun Double.redondear(posiciones: Int): Double {
    val factor = 10.0.pow(posiciones.toDouble())
    return (this * factor).roundToInt() / factor
}

fun personajeIncial(nombre:String):Jugador {
    return Jugador(nombre, 1)
}

fun limpiarPantalla() {
    // Imprime 50 caracteres de nueva línea para "limpiar" la pantalla
    repeat(50) { println() }
}

fun String.colorAzul():Text {
    return Text(blue(this))
}


fun main() {

    val t = Terminal()

    t.println("hola".colorAzul())

    Juego.iniciarJuego()

}