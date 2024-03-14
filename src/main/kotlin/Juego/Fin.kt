package Juego

import kotlin.system.exitProcess

object Fin {

    fun gameOver() {
        Mensaje.mostrar("Has muerto que tio más malo, con lo fácil que es y lo desvalanceado que está como puedes morir...")
        exitProcess(130)
    }

    fun finalSecreto() {
        println("Enhorabuena as desbloqueado el final secreto")
        exitProcess(130)
    }

    fun salirDelJuego() {
        Mensaje.mostrar("Espero que lo hayais disfrutado, Solo leveling RPG volverá mucho mejor...")
        exitProcess(130)
    }

}