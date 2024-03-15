package Enemigo

import EstadisticaYRango.Estadisticas
import EstadisticaYRango.Rango

/**
 * Clase que representa un enemigo de tipo "Boss".
 *
 * @param nivel Nivel del enemigo.
 * @param estadisticas Estadísticas del enemigo (vida, fuerza, agilidad y resistencia).
 * @param rango Rango del enemigo.
 */
class Boss(nivel :Int, estadisticas: Estadisticas, rango: Rango) : Enemigo(TipoEnemigo.BOSS, nivel, estadisticas, rango) {
    override val tipoEnemigo = TipoEnemigo.BOSS
}