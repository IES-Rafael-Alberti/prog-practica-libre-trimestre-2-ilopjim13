package Mazmorra

import Enemigo.Enemigo
import EstadisticaYRango.Rango
import com.github.ajalt.mordant.widgets.Text

object GestionMazmorra {

    fun generarMazmorraRandom() : Mazmorra {
        return Mazmorra(nombreRandom(), rangoRandom())
    }

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

    private fun rangoRandom() = listOf(Rango.E, Rango.D, Rango.C, Rango.B, Rango.A, Rango.S).random()

    fun mostrarInfoMazmorra(mazmorra: Mazmorra) {
        var numSalas = 0
        mazmorra.salas.forEach { numSalas = it.key }
        println("Nombre: ${mazmorra.nombre} de rango ${mazmorra.rango} y con un total de $numSalas salas")
    }

    fun textoSala(lista:MutableMap<Enemigo, Boolean>): Text {
        var num = 0
        if (lista.isEmpty()) return Text("Esta sala no tiene enemigos.")
        else lista.forEach { _ -> num++ }
        return if (num == 1) Text("Solo hay un enemigo en la sala")
        else Text("Hay un total de $num enemigos en la sala")
    }

    fun textoSalaFinal(lista:MutableMap<Enemigo, Boolean>): Text {
        var num = 0
        lista.forEach { _ -> num++ }
        return if (num == 1) Text("Solo está el BOSS en la sala")
        else Text("Hay un total de ${num -1} enemigos en la sala además del BOSS")
    }

}