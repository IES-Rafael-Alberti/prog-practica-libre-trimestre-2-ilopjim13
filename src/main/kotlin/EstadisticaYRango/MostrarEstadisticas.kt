package EstadisticaYRango

import Juego.Mensaje
import Personaje.Jugador
import Juego.Vista
import T
import colorAzul
import colorRojo
import com.github.ajalt.mordant.rendering.BorderType
import com.github.ajalt.mordant.rendering.TextAlign
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.Whitespace
import com.github.ajalt.mordant.terminal.Terminal
import com.github.ajalt.mordant.widgets.Panel
import com.github.ajalt.mordant.widgets.Text
import enterContinuar
import redondear

object MostrarEstadisticas {

    private fun mostrarEstadisticas(jugador: Jugador) {
        T.println(
            Panel(
                borderStyle = TextColors.rgb("D2FFA4"),
                content =  Text(
                    TextColors.brightBlue(" CAZADOR ${jugador.nombre.uppercase()} |") +
                        TextColors.brightBlue(" RANGO ${jugador.rango} |") +
                        TextColors.brightBlue(" NIVEL ${jugador.nivel} \n") +
                        " VIDA:       ${jugador.nombre.map { "" }.joinToString(" ")}           " +
                            TextColors.yellow("${jugador.estadisticas.vida.redondear(2)}\n") +
                        " FUERZA:     ${jugador.nombre.map { "" }.joinToString(" ")}           " +
                            TextColors.yellow("${jugador.estadisticas.fuerza.redondear(2)}\n") +
                        " AGILIDAD:   ${jugador.nombre.map { "" }.joinToString(" ")}           " +
                            TextColors.yellow("${jugador.estadisticas.agilidad.redondear(2)}\n") +
                        " RESISTENCIA:${jugador.nombre.map { "" }.joinToString(" ")}           " +
                            TextColors.yellow("${jugador.estadisticas.resistencia.redondear(2)}"), whitespace = Whitespace.PRE),
                title = Text(TextColors.rgb("D2FFA4")("** ESTADISTICAS **"))
            )
        )
    }


    fun menuEstadisticas(jugador: Jugador) {

        do {
            mostrarEstadisticas(jugador)

            println()
            Mensaje.mostrarConColores(Text("Tienes un total de ${TextColors.yellow(jugador.nivelExperiencia.toString())} puntos para subir."))
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