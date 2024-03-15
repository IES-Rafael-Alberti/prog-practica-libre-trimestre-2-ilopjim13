package Mazmorra

import Enemigo.Enemigo
import EstadisticaYRango.Rango
import Juego.Mensaje
import com.github.ajalt.mordant.widgets.Text

/**
 * Clase para gestionar la creación y visualización de mazmorras.
 */
object GestionMazmorra {

    /**
     * Genera una mazmorra aleatoria con nombre y rango aleatorios.
     *
     * @return una mazmorra generada.
     */
    fun generarMazmorraRandom() : Mazmorra {
        return Mazmorra(nombreRandom(), rangoRandom())
    }

    /**
     * Genera un nombre aleatorio para la mazmorra.
     *
     * @return un nombre de mazmorra aleatorio.
     */
    private fun nombreRandom() :String {
        return listOf(
            "Caverna de las Sombras Profundas",
            "Cripta del Lamento Eterno",
            "Abismo de los Antiguos",
            "Catacumbas del Olvido",
            "Mazmorra de la Serpiente de Obsidiana",
            "Torre de los Susurros Oscuros",
            "Foso de los Espíritus Perdidos",
            "Pasaje de las Almas Errantes",
            "Gruta de los Espectros",
            "Antro de la Noche Infinita",
            "Cámara de los Enigmas Arcanos",
            "Calabozo de los Espejismos",
            "Sala de los Mil Suspiros"
        ).random()
    }

    /**
     * Genera un rango aleatorio para la mazmorra.
     *
     * @return un rango de dificultad aleatorio.
     */
    private fun rangoRandom() = listOf(Rango.E, Rango.D, Rango.C, Rango.B, Rango.A, Rango.S).random()


    /**
     * Muestra la información sobre la mazmorra.
     *
     * @param mazmorra la mazmorra cuya información se mostrará.
     */
    fun mostrarInfoMazmorra(mazmorra: Mazmorra) {
        var numSalas = 0
        mazmorra.salas.forEach { numSalas = it.key }
        Mensaje.mostrar("Nombre: ${mazmorra.nombre} de rango ${mazmorra.rango} y con un total de $numSalas salas")
    }

    /**
     * Genera un texto descriptivo para la sala actual basado en la cantidad de enemigos presentes.
     *
     * @param lista el mapa de enemigos presentes en la sala y su estado.
     * @return un texto descriptivo sobre la cantidad de enemigos en la sala.
     */
    fun textoSala(lista:MutableMap<Enemigo, Boolean>): Text {
        var num = 0
        if (lista.isEmpty()) return Text("Esta sala no tiene enemigos.")
        else lista.forEach { _ -> num++ }
        return if (num == 1) Text("Solo hay un enemigo en la sala")
        else Text("Hay un total de $num enemigos en la sala")
    }

    /**
     * Genera un texto descriptivo para la sala final basado en la cantidad de enemigos presentes junto al boss.
     *
     * @param lista el mapa de enemigos presentes en la sala final y su estado.
     * @return un texto descriptivo sobre la cantidad de enemigos en la sala final.
     */
    fun textoSalaFinal(lista:MutableMap<Enemigo, Boolean>): Text {
        var num = 0
        lista.forEach { _ -> num++ }
        return if (num == 1) Text("Solo está el BOSS en la sala")
        else Text("Hay un total de ${num -1} enemigos en la sala además del BOSS")
    }

}