package Enemigo

import EstadisticaYRango.Estadisticas
import EstadisticaYRango.Rango

/**
 * Clase que representa un enemigo de tipo "Ogro".
 *
 * @param nivel Nivel del enemigo.
 * @param estadisticas Estadísticas del enemigo (vida, fuerza, agilidad y resistencia).
 * @param rango Rango del enemigo.
 */
class Ogro(nivel :Int, estadisticas: Estadisticas, rango: Rango) : Enemigo(TipoEnemigo.OGRO, nivel, estadisticas, rango) {
    override val tipoEnemigo = TipoEnemigo.OGRO
}