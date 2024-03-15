package Juego

import colorRojo
import kotlin.system.exitProcess

/**
 * Objeto Fin que contiene funciones relacionadas con el final del juego.
 */
object Fin {

    /**
     * Muestra un mensaje de "Game Over" cuando el jugador muere y finaliza la ejecución del programa.
     */
    fun gameOver() {
        Mensaje.mostrarConColores("** GAME OVER**".colorRojo())
        Mensaje.mostrar("Has muerto... con lo fácil que es y lo desvalanceado que está, como puedes morir...")
        exitProcess(0)
    }

    /**
     * Muestra un mensaje de felicitación por desbloquear el final secreto aun que no tiene XDDD
     */
    fun finalSecreto() {
        println("Enhorabuena as desbloqueado el final secreto")
        exitProcess(0)
    }

    /**
     * Muestra un mensaje de despedida y finaliza la ejecución del programa.
     */
    fun salirDelJuego() {
        Mensaje.mostrar("Espero que lo hayais disfrutado, Solo leveling RPG volverá mucho mejor...")
        exitProcess(0)
    }

}