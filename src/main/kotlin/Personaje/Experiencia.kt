package Personaje

/**
 * Clase que representa la experiencia del jugador.
 */
class Experiencia {
    var limitePorNivel = 150
    var experienciaActual = 0

    /**
     * Aumenta la experiencia del jugador y verifica si debe subir de nivel.
     *
     * @param jugador El jugador al que se le aumenta la experiencia.
     * @param cant La cantidad de experiencia a agregar.
     */
    fun aumentarExperiencia(jugador: Jugador, cant:Int) {
        val experiencia = jugador.experiencia.experienciaActual
        val limite = jugador.experiencia.limitePorNivel

        if (experiencia + cant >= limite) {
            jugador.experiencia.experienciaActual = limite
            jugador.subirNivel()
        }
        else {
            jugador.experiencia.experienciaActual += cant
        }
    }

}