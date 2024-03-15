import Juego.Juego
import Personaje.Jugador
import com.github.ajalt.mordant.animation.ProgressAnimation
import com.github.ajalt.mordant.animation.progressAnimation
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.widgets.Text
import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.terminal.Terminal
import kotlin.math.pow
import kotlin.math.roundToInt

/**
 * Redondea un valor Double al número especificado de decimales.
 *
 * @param posiciones El número de decimales a redondear.
 * @return El valor Double redondeado.
 */
fun Double.redondear(posiciones: Int): Double {
    val factor = 10.0.pow(posiciones.toDouble())
    return (this * factor).roundToInt() / factor
}

val T = Terminal() // Constante para utilizar el mordant


/**
 * Elimina espacios en blanco adicionales y capitaliza la primera letra de cada palabra en una cadena.
 *
 * @return La cadena con espacios eliminados y palabras capitalizadas.
 */
fun String.espacios(): String {
    val espacios = this.split(" ").toMutableList()
    val palabras:MutableList<String> = mutableListOf()
    espacios.forEach{if (it.isNotBlank()) palabras.add(it.lowercase())}
    return palabras.joinToString(" ") { i -> i.replaceFirstChar { it.uppercase() } }
}

/**
 * Espera a que el usuario presione <ENTER> para continuar.
 */
fun enterContinuar() {
    print(">> Dale <ENTER> para continuar...")
    readln()
    println()
}

/**
 * Crea un personaje inicial con el nombre especificado.
 *
 * @param nombre El nombre del personaje.
 * @return Un objeto Jugador con el nombre y nivel iniciales.
 */
fun personajeIncial(nombre:String): Jugador {
    return Jugador(nombre, 1)
}

/**
 * Limpia la pantalla imprimiendo 50 caracteres de nueva línea.
 */
fun limpiarPantalla() {
    // Imprime 50 caracteres de nueva línea para "limpiar" la pantalla
    repeat(50) { println() }
}

/**
 * Espera durante un tiempo especificado en milisegundos.
 *
 * @param cant El tiempo de espera en milisegundos.
 */
fun tiempoEspera(cant: Long) {
    Thread.sleep(cant)
}

/**
 * Crea y devuelve una barra de progreso animada con el texto especificado.
 *
 * @param texto El texto que se mostrará junto a la barra de progreso.
 * @return Un objeto ProgressAnimation para mostrar la barra de progreso.
 */
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

/**
 * Devuelve un objeto Text con el texto especificado en color azul.
 *
 * @return El texto en color azul.
 */
fun String.colorAzul():Text {
    return Text(blue(this))
}

/**
 * Devuelve un objeto Text con el texto especificado en color rojo.
 *
 * @return El texto en color rojo.
 */
fun String.colorRojo():Text {
    return Text(red(this))
}

/**
 * Devuelve un objeto Text con el texto especificado en color verde.
 *
 * @return El texto en color verde.
 */
fun String.colorVerde():Text {
    return Text(green(this))
}

/**
 * Devuelve un objeto Text con el texto especificado en color amarillo.
 *
 * @return El texto en color amarillo.
 */
fun String.colorAmarillo():Text {
    return Text(yellow(this))
}

/**
 * Devuelve un objeto Text con el texto especificado en color rosa.
 *
 * @return El texto en color rosa.
 */
fun String.colorRosa():Text {
    return Text(magenta(this))
}

/**
 * Devuelve un objeto Text con el texto especificado en color negro.
 *
 * @return El texto en color negro.
 */
fun String.colorNegro():Text {
    return Text(black(this))
}


fun main() {

    Juego.iniciarJuego()

}