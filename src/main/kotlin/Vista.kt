import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.rendering.TextStyles.*
import com.github.ajalt.mordant.table.table
import com.github.ajalt.mordant.terminal.Terminal
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.Whitespace
import com.github.ajalt.mordant.widgets.Text
import com.github.ajalt.mordant.widgets.Panel


object Vista {
    fun introduccion() {
        val t = Terminal()
        t.println(table {
            header { row((black on brightGreen)("*** SOLO LEVELING RPG ***")) }
        })
        t.println(
            Panel(
                content = Text("Hola que tal", whitespace = Whitespace.NORMAL, width = 17),
                title = Text("Estadisticas")
            )
        )

        println("En un mundo donde empezaron a aparecer mazmorras llenas de monstruos, algunas personas despertaron habilidades, estas personas son conocidas como cazadores, y tú eres uno de ellos.\n")
    }

    fun pedirNombre(): String {
        print("Bienvenido cazador, introduce tu nombre: ")
        var nombre: String
        do {
            nombre = readln()
            if (nombre.isBlank()) print("El nombre no puede estar vacío. Introduzcalo de nuevo: ")
        } while (nombre.isBlank())

        return nombre
    }

    fun menu(jugador: Jugador) {
        limpiarPantalla()
        println("\n** DIA ${Juego.dias} **\n")
        mostrarEstadisticas(jugador)
        println("\nBuenos días que quieres hacer hoy cazador")
        println("1. Explorar una Mazmorra")
        println("2. Revisar el inventario")
        println("3. Ver las misiones diarias")
        println("4. Ir a la tienda")

        val opcion = pedirOpcion(4)
    }

    fun pedirOpcion(opciones:Int) :Int {
        println("\n>> Selecciona una opcion: ")
        var opcion = 1
        do {
            try {
                opcion = readln().toInt()
                if (opcion !in (1..opciones)) println("**ERROR** Debes elegir una opcion correcta.\n>> Selecciona una opcion: ")
            } catch (e: NumberFormatException) {
                println("**ERROR** Debe de ser una opcion valida.\n>> Selecciona una opcion: ")
            }
        } while(opcion !in (1..opciones))
        return opcion
    }

    private fun mostrarEstadisticas(jugador: Jugador) {
        val t = Terminal()
        t.println(table {
            borderStyle = TextColors.rgb("#4b25b9")
            header {
                style = cyan + bold
                row("CAZADOR", jugador.nombre.uppercase(), "NIVEL", jugador.nivel, "RANGO", jugador.rango) }
            body   { row("VIDA", "${jugador.estadisticas.vida}")
                     row("FUERZA", "${jugador.estadisticas.fuerza}")
                     row("AGILIDAD", "${jugador.estadisticas.agilidad}")
                     row("RESISTENCIA", "${jugador.estadisticas.resistencia}")}
        })
    }
}