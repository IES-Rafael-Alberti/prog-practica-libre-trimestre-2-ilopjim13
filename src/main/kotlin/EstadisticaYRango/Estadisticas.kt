package EstadisticaYRango

import redondear

/**
 * Clase que representa las estadísticas de un personaje o enemigo.
 *
 * @param vida Puntos de vida del personaje o enemigo.
 * @param fuerza Nivel de fuerza del personaje o enemigo.
 * @param agilidad Nivel de agilidad del personaje o enemigo.
 * @param resistencia Nivel de resistencia del personaje o enemigo.
 */
data class Estadisticas(var vida:Double, var fuerza:Double, var agilidad:Double, var resistencia:Double) {

    /**
     * Devuelve una representación en cadena de las estadísticas.
     *
     * @return Cadena con los valores de vida, fuerza, agilidad y resistencia.
     */
    override fun toString(): String {
        return "Estadisticas --> Vida: ${vida.redondear(2)}, Fuerza: ${fuerza.redondear(2)}, Agilidad: ${agilidad.redondear(2)}, Resistencia: ${resistencia.redondear(2)}"
    }

}