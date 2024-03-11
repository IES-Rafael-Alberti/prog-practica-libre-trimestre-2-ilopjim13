import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.Whitespace
import com.github.ajalt.mordant.terminal.Terminal
import com.github.ajalt.mordant.widgets.Panel
import com.github.ajalt.mordant.widgets.Text

object MostrarEstadisticas {

    private fun mostrarEstadisticas(jugador: Jugador) {
        val t = Terminal()

        t.println(
            Panel(
                borderStyle = TextColors.rgb("00FFFB"),
                content =  Text("CAZADOR ${jugador.nombre.uppercase()} | NIVEL ${jugador.nivel} | RANGO ${jugador.rango}\nVIDA:        ${jugador.estadisticas.vida.redondear(2)}\nFUERZA:      ${jugador.estadisticas.fuerza.redondear(2)}\nAGILIDAD:    ${jugador.estadisticas.agilidad.redondear(2)}\nRESISTENCIA: ${jugador.estadisticas.resistencia.redondear(2)}", whitespace = Whitespace.PRE),
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
                if (jugador.nivelExperiencia > 0) elegirStatASubir(jugador)
                else println("No tienes suficientes puntos para gastar\n")
                enterContinuar()
            }
        }
    }

    private fun elegirStatASubir(jugador: Jugador) {
        println("¿Donde quieres gastarlo?")
        println("1. Vida")
        println("2. Fuerza")
        println("3. Agilidad")
        println("4. Resistencia")

        val opcion = Vista.pedirOpcion(4)

        jugador.subirStat(opcion)

    }



}