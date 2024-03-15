package Mazmorra

import Enemigo.Enemigo
import EstadisticaYRango.Rango

/**
 * Clase que representa una mazmorra con nombre y rango.
 *
 * @property nombre el nombre de la mazmorra.
 * @property rango el rango de dificultad de la mazmorra.
 */
class Mazmorra(val nombre:String, val rango: Rango) {

    var salas:MutableMap<Int, MutableMap<Enemigo, Boolean>> = generarSalas()

    /**
     * Comprueba si la mazmorra ha sido completada (todos los enemigos derrotados).
     *
     * @return Boolean true si la mazmorra está completada, false en caso contrario.
     */
    fun comprobarMazmorraCompletada() :Boolean {
        val comprobar = salas.all { it -> it.value.all { it.value } }
        return comprobar
    }

    /**
     * Marca todas las salas como terminadas (todos los enemigos derrotados).
     */
    fun salasTerminadas() {
        salas.forEach { (_, value) ->
            value.forEach {
                value[it.key] = true
            }
        }
    }

    companion object {
        private var salas:MutableMap<Int, MutableMap<Enemigo, Boolean>> = mutableMapOf()

        /**
         * Genera las salas y enemigos aleatoriamente.
         *
         * @return Las salas con los enemigos.
         */
        fun generarSalas() :MutableMap<Int, MutableMap<Enemigo, Boolean>> {
            var sala = 1
            salas = mutableMapOf()
            val numSalas = (2..5).random()
            for(i in 1..numSalas) {
                val numEnemigos = if(numSalas != sala) (0..3).random()
                                  else (1..3).random()
                val listaEnemigos = GenerarEnemigos.generarEnemigos(numEnemigos, numSalas, sala)
                sala++
                salas[i] = listaEnemigos
            }
            return salas
        }
    }

}



