package EstadisticaYRango

import Enemigo.Enemigo
import Item.Item
import Personaje.Jugador

/**
 * Modifica las estadísticas de un jugador o enemigo según la operación especificada.
 *
 * @param jugador Jugador o enemigo cuyas estadísticas se modificarán.
 * @param cant Cantidad a modificar.
 * @param estadistica Nombre de la estadística a modificar ("vida", "fuerza", "agilidad" o "resistencia").
 * @param operacion Función que define cómo se aplicará la modificación (por ejemplo, suma o resta).
 */
fun <T> modificarEstadisticas(jugador: T, cant: Double, estadistica:String, operacion: (Double, Double) -> Double) {
    when (jugador) {
        is Jugador -> {
            when (estadistica) {
                "vida" -> jugador.estadisticas.vida = maxOf(operacion(jugador.estadisticas.vida, cant), 0.0)
                "fuerza" -> jugador.estadisticas.fuerza = maxOf(operacion(jugador.estadisticas.fuerza, cant), 0.0)
                "agilidad" -> jugador.estadisticas.agilidad = maxOf(operacion(jugador.estadisticas.agilidad, cant), 0.0)
                "resistencia" -> jugador.estadisticas.resistencia = maxOf(operacion(jugador.estadisticas.resistencia, cant), 0.0)
            }
        }
        is Enemigo -> {
            when (estadistica) {
                "vida" -> jugador.estadisticas.vida = maxOf(operacion(jugador.estadisticas.vida, cant), 0.0)
                "fuerza" -> jugador.estadisticas.fuerza = maxOf(operacion(jugador.estadisticas.fuerza, cant), 0.0)
                "agilidad" -> jugador.estadisticas.agilidad = maxOf(operacion(jugador.estadisticas.agilidad, cant), 0.0)
                "resistencia" -> jugador.estadisticas.resistencia = maxOf(operacion(jugador.estadisticas.resistencia, cant), 0.0)
            }
        }
    }
}

/**
 * Modifica todas las estadísticas de un jugador según la operación especificada.
 *
 * @param jugador Jugador cuyas estadísticas se modificarán.
 * @param cant Cantidad a modificar.
 * @param operacion Función que define cómo se aplicará la modificación (por ejemplo, suma o resta).
 */
fun modificarTodasEstadisticas(jugador: Jugador, cant: Double, operacion: (Double, Double) -> Double) {
    jugador.estadisticas.vida = maxOf(operacion(jugador.estadisticas.vida, 6.25), 0.0)
    jugador.estadisticas.fuerza = maxOf(operacion(jugador.estadisticas.fuerza, cant), 0.0)
    jugador.estadisticas.agilidad = maxOf(operacion(jugador.estadisticas.agilidad, cant), 0.0)
    jugador.estadisticas.resistencia = maxOf(operacion(jugador.estadisticas.resistencia, cant), 0.0)
}

/**
 * Aumenta las estadísticas de un jugador según los valores de un ítem.
 *
 * @param jugador Jugador cuyas estadísticas se modificarán.
 * @param item Ítem que proporciona estadísticas adicionales.
 * @param operacion Función que define cómo se aplicará la modificación (por ejemplo, suma o resta).
 */
fun aumentarStastItem(jugador: Jugador, item: Item, operacion: (Double, Double) -> Double) {
    jugador.estadisticas.vida = maxOf(operacion(jugador.estadisticas.vida, item.estadisticas!!.vida), 0.0)
    jugador.estadisticas.fuerza = maxOf(operacion(jugador.estadisticas.fuerza, item.estadisticas!!.fuerza), 0.0)
    jugador.estadisticas.agilidad = maxOf(operacion(jugador.estadisticas.agilidad, item.estadisticas!!.agilidad), 0.0)
    jugador.estadisticas.resistencia = maxOf(operacion(jugador.estadisticas.resistencia, item.estadisticas!!.resistencia), 0.0)
}
