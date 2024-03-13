import com.github.ajalt.mordant.animation.ProgressAnimation
import com.github.ajalt.mordant.animation.progressAnimation
import com.github.ajalt.mordant.rendering.Whitespace.*
import com.github.ajalt.mordant.widgets.Text
import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextStyle
import com.github.ajalt.mordant.terminal.Prompt
import com.github.ajalt.mordant.terminal.Terminal
import com.github.ajalt.mordant.widgets.ProgressBar
import kotlin.math.pow
import kotlin.math.roundToInt

fun Double.redondear(posiciones: Int): Double {
    val factor = 10.0.pow(posiciones.toDouble())
    return (this * factor).roundToInt() / factor
}

val T = Terminal()

fun imprimirLento(texto: String) {
    texto.forEach {
        print(it)
        tiempoEspera(50)
    }
}

fun String.espacios(): String {
    val espacios = this.split(" ").toMutableList()
    val palabras:MutableList<String> = mutableListOf()
    espacios.forEach{if (it.isNotBlank()) palabras.add(it)}
    return palabras.joinToString(" ") { i -> i.replaceFirstChar { it.uppercase() } }
}

fun enterContinuar() {
    print(">> Dale <ENTER> para continuar...")
    readln()
    println()
}

fun personajeIncial(nombre:String):Jugador {
    return Jugador(nombre, 1)
}

fun limpiarPantalla() {
    // Imprime 50 caracteres de nueva línea para "limpiar" la pantalla
    repeat(50) { println() }
}

fun tiempoEspera(cant: Long) {
    Thread.sleep(cant)
}

fun barraProgreso(texto:String):ProgressAnimation {
    val t = Terminal()
    val progreso = t.progressAnimation {
        text(texto)
        percentage()
        progressBar()
        completed()
        //speed("s")
    }
    return progreso
}

fun String.colorAzul():Text {
    return Text(blue(this))
}
fun String.colorRojo():Text {
    return Text(red(this))
}
fun String.colorVerde():Text {
    return Text(green(this))
}
fun String.colorAmarillo():Text {
    return Text(yellow(this))
}
fun String.colorRosa():Text {
    return Text(magenta(this))
}
fun String.colorNegro():Text {
    return Text(black(this))
}


fun main() {

    Juego.iniciarJuego()


}