import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.Whitespace
import com.github.ajalt.mordant.terminal.Terminal
import com.github.ajalt.mordant.widgets.Panel
import com.github.ajalt.mordant.widgets.Text

object MostrarEstadisticas {

    fun mostrarEstadisticas(jugador: Jugador) {
        val t = Terminal()

        t.println(
            Panel(
                borderStyle = TextColors.rgb("00FFFB"),
                content =  Text("CAZADOR ${jugador.nombre.uppercase()} | NIVEL ${jugador.nivel} | RANGO ${jugador.rango}\nVIDA:        ${jugador.estadisticas.vida}\nFUERZA:      ${jugador.estadisticas.fuerza}\nAGILIDAD:    ${jugador.estadisticas.agilidad}\nRESISTENCIA: ${jugador.estadisticas.resistencia}", whitespace = Whitespace.PRE),
                title = Text("** ESTADISTICAS **")
            )
        )


//        t.println(table {
//            borderStyle = TextColors.rgb("#4b25b9")
//            header {
//                style = cyan + bold
//                row("CAZADOR", jugador.nombre.uppercase(), "NIVEL", jugador.nivel, "RANGO", jugador.rango) }
//            body   { row("VIDA", "${jugador.estadisticas.vida}")
//                     row("FUERZA", "${jugador.estadisticas.fuerza}")
//                     row("AGILIDAD", "${jugador.estadisticas.agilidad}")
//                     row("RESISTENCIA", "${jugador.estadisticas.resistencia}")}
//        })
    }


    fun menuEstadisticas(jugador: Jugador) {

        do {
            mostrarEstadisticas(jugador)

            println()
            println("Tienes un total de ${jugador.nivelExperiencia} puntos para subir.")
            println("1. Gastar punto de experiencia")
            println("2. Salir")

            val opcion = Vista.pedirOpcion(2)
            elegirOpcionStats(opcion, jugador)
        } while (opcion != 2)

    }

    private fun elegirOpcionStats(opcion:Int, jugador: Jugador) {
        when (opcion) {
            1 -> {
                if (jugador.nivelExperiencia > 0) subirStat(jugador)
                else println("No tienes suficientes puntos para gastar\n")
                enterContinuar()
            }
        }
    }

    private fun subirStat(jugador: Jugador) {
        println("¿Donde quieres gastarlo?")
        println("1. Vida")
        println("2. Fuerza")
        println("3. Agilidad")
        println("4. Resistencia")

        val opcion = Vista.pedirOpcion(4)

        elegirStatASubir(opcion, jugador)

    }

    private fun elegirStatASubir(opcion:Int, jugador: Jugador) {
        when (opcion) {
            1 -> jugador.estadisticas.aumentarVida(jugador, 25.0)
            2 -> jugador.estadisticas.aumentarFuerza(jugador, 1.0)
            3 -> jugador.estadisticas.aumentarAgilidad(jugador, 1.0)
            4 -> jugador.estadisticas.aumentarResistencia(jugador, 1.0)
        }
    }

}