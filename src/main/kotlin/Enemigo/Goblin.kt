package Enemigo

import EstadisticaYRango.Estadisticas
import EstadisticaYRango.Rango

/**
 * Clase que representa un enemigo de tipo "Goblin".
 *
 * @param nivel Nivel del enemigo.
 * @param estadisticas Estadísticas del enemigo (vida, fuerza, agilidad y resistencia).
 * @param rango Rango del enemigo.
 */
class Goblin(nivel :Int, estadisticas: Estadisticas, rango: Rango) : Enemigo(TipoEnemigo.GOBLIN, nivel, estadisticas, rango) {
    override val tipoEnemigo = TipoEnemigo.GOBLIN
}