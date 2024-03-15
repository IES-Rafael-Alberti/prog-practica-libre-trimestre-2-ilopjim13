package Mazmorra

import Enemigo.*
import EstadisticaYRango.Estadisticas
import EstadisticaYRango.Rango

/**
 * Clase que genera enemigos para una mazmorra.
 */
object GenerarEnemigos {

    /**
     * Genera un mapa de enemigos.
     *
     * @param cantidad la cantidad de enemigos a generar.
     * @param salas el número total de salas en la mazmorra.
     * @param sala el número de la sala actual.
     * @return un mapa de enemigos generados.
     */
    fun generarEnemigos(cantidad:Int, salas: Int,sala: Int): MutableMap<Enemigo, Boolean> {
        var numRandom:Int
        val enemigos = mutableMapOf<Enemigo, Boolean>()
        var enemigosSalaBoss = 1

        repeat(cantidad){

            numRandom = if(sala != salas) (1..4).random()
            else {
                if(enemigosSalaBoss == cantidad) 5
                else {
                    enemigosSalaBoss++
                    (1..4).random()
                }
            }
            when (numRandom) {
                1 -> enemigos[generarGoblinAleatorio()] = false
                2 -> enemigos[generarOgroAleatorio()] = false
                3 -> enemigos[generarOrcoAleatorio()] = false
                4 -> enemigos[generarCazadorAleatorio()] = false
                5 -> enemigos[generarBossAleatorio()] = false
            }
        }
        return enemigos
    }

    /**
     * Genera un goblin aleatorio.
     *
     * @return un enemigo de tipo goblin con estadísticas y nivel aleatorios.
     */
    private fun generarGoblinAleatorio() : Enemigo {
        val nivel = (1..5).random()
        val estadisticas = comprobarRango(Rango.E)
        return Goblin(nivel, estadisticas, Rango.E)
    }

    /**
     * Genera un ogro aleatorio.
     *
     * @return un enemigo de tipo goblin con estadísticas y nivel aleatorios.
     */
    private fun generarOgroAleatorio() : Enemigo {
        val nivel = (5..10).random()
        val rango = listOf(Rango.E, Rango.D, Rango.C).random()
        val estadisticas = comprobarRango(rango)
        return Ogro(nivel, estadisticas, rango)
    }

    /**
     * Genera un orco aleatorio.
     *
     * @return un enemigo de tipo goblin con estadísticas y nivel aleatorios.
     */
    private fun generarOrcoAleatorio() : Enemigo {
        val nivel = (10..25).random()
        val rango = listOf(Rango.D, Rango.C, Rango.B).random()
        val estadisticas = comprobarRango(rango)
        return Orco(nivel, estadisticas, rango)
    }

    /**
     * Genera un cazador aleatorio.
     *
     * @return un enemigo de tipo goblin con estadísticas y nivel aleatorios.
     */
    private fun generarCazadorAleatorio() : Enemigo {
        val nivel = (25..40).random()
        val rango = listOf(Rango.C, Rango.E, Rango.D, Rango.B, Rango.A).random()
        val estadisticas = comprobarRango(rango)
        return Cazador(nivel, estadisticas, rango)
    }

    /**
     * Genera un boss aleatorio.
     *
     * @return un enemigo de tipo goblin con estadísticas y nivel aleatorios.
     */
    private fun generarBossAleatorio() : Enemigo {
        val nivel = (40..60).random()
        val estadisticas = comprobarRango(Rango.S)
        return Boss(nivel, estadisticas, Rango.S)
    }

    /**
     * Comprueba las estadísticas de un enemigo según su rango.
     *
     * @param rango el rango del enemigo.
     * @return las estadísticas generadas para el enemigo de forma aleatoria.
     */
    private fun comprobarRango(rango: Rango): Estadisticas {
        return when (rango) {
            Rango.E -> Estadisticas((15..30).random().toDouble(), (1..4).random().toDouble(), (3..6).random().toDouble(), (1..5).random().toDouble())
            Rango.D -> Estadisticas((15..40).random().toDouble(), (2..5).random().toDouble(), (3..7).random().toDouble(), (2..7).random().toDouble())
            Rango.C -> Estadisticas((20..50).random().toDouble(), (3..6).random().toDouble(), (3..8).random().toDouble(), (3..9).random().toDouble())
            Rango.B -> Estadisticas((30..60).random().toDouble(), (4..7).random().toDouble(), (4..9).random().toDouble(), (4..10).random().toDouble())
            Rango.A -> Estadisticas((40..70).random().toDouble(), (5..8).random().toDouble(), (4..10).random().toDouble(), (5..11).random().toDouble())
            Rango.S -> Estadisticas((50..80).random().toDouble(), (6..9).random().toDouble(), (5..11).random().toDouble(), (6..12).random().toDouble())
        }
    }

}