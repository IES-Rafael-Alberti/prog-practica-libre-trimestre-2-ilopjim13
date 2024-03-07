import kotlin.random.Random

interface Combates<T> {

    fun calcularDanio(jugador: T) :Double {
        return when (jugador) {
            is Jugador -> jugador.estadisticas.fuerza / 0.75
            is Enemigo -> jugador.estadisticas.fuerza / 0.75
            else -> 0.0
        }
    }

    fun atacar(atacante: T, atacado: T) :Double {
        return when (jugador) {
            is Jugador -> jugador.estadisticas.fuerza / 0.75
            is Enemigo -> jugador.estadisticas.fuerza / 0.75
            else -> 0.0
        }
    }

    fun recibirDanio(danio:Double, jugador: T) :Boolean {
        return when (jugador) {
            is Jugador -> {
                if (!esquivar(jugador)) {
                jugador.estadisticas.vida  -= danio
                true
                }
                else false
            }
            is Enemigo -> {
                if (!esquivar(jugador)) {
                    jugador.estadisticas.vida  -= danio
                    true
                }
                else false
            }
            else -> false
        }
    }

    fun esquivar(jugador: T) :Boolean {
        return when (jugador) {
            is Jugador -> {
                val probabilidad = jugador.estadisticas.agilidad / (jugador.estadisticas.agilidad + 10)
                val numRand = Random.nextDouble()
                numRand <= probabilidad
            }
            is Enemigo -> {
                val probabilidad = jugador.estadisticas.agilidad / (jugador.estadisticas.agilidad + 10)
                val numRand = Random.nextDouble()
                numRand <= probabilidad
            }
            else -> false
        }

    }

    fun huir() :Boolean
}