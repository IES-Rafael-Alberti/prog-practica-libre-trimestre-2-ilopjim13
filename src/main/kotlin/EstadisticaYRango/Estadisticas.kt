package EstadisticaYRango

import redondear

data class Estadisticas(var vida:Double, var fuerza:Double, var agilidad:Double, var resistencia:Double) {

    override fun toString(): String {
        return "Estadisticas --> Vida: ${vida.redondear(2)}, Fuerza: ${fuerza.redondear(2)}, Agilidad: ${agilidad.redondear(2)}, Resistencia: ${resistencia.redondear(2)}"
    }

}