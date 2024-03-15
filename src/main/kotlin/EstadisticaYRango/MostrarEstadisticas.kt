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

/**
 * Objeto que muestra las estadísticas del jugador en un formato visual.
 */
object MostrarEstadisticas {

    /**
     * Muestra las estadísticas del jugador en un panel con colores.
     *
     * @param jugador Jugador cuyas estadísticas se mostrarán.
     */
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

    /**
     * Muestra el menú de estadísticas y permite al jugador gastar puntos de experiencia.
     *
     * @param jugador Jugador cuyas estadísticas se mostrarán y modificarán.
     */
    fun menuEstadisticas(jugador: Jugador) {

        do {
            mostrarEstadisticas(jugador)

            Mensaje.mostrarConColores(Text("\nTienes un total de ${TextColors.yellow(jugador.nivelExperiencia.toString())} puntos para subir."))
            Mensaje.mostrar("1. Gastar punto de experiencia")
            Mensaje.mostrar("2. Salir")

            val opcion = Vista.pedirOpcion(2)
            elegirOpcionStats(opcion, jugador)
        } while (opcion != 2)

    }

    /**
     * Función que permite al jugador elegir una opción relacionada con las estadísticas.
     *
     * @param opcion Opción seleccionada por el jugador.
     * @param jugador Jugador cuyas estadísticas se modificarán.
     */
    private fun elegirOpcionStats(opcion:Int, jugador: Jugador) {
        when (opcion) {
            1 -> {
                if (jugador.nivelExperiencia > 0) elegirStatASubir(jugador)
                else Mensaje.mostrar("No tienes suficientes puntos para gastar\n")
                enterContinuar()
            }
        }
    }

    /**
     * Permite al jugador elegir qué estadística subir.
     *
     * @param jugador Jugador cuyas estadísticas se modificarán.
     */
    private fun elegirStatASubir(jugador: Jugador) {
        Mensaje.mostrar("¿Donde quieres gastarlo?")
        Mensaje.mostrar("1. Vida")
        Mensaje.mostrar("2. Fuerza")
        Mensaje.mostrar("3. Agilidad")
        Mensaje.mostrar("4. Resistencia")

        val opcion = Vista.pedirOpcion(4)

        jugador.subirStat(opcion)

    }



}