package Enemigo

import EstadisticaYRango.Estadisticas
import EstadisticaYRango.Rango

class Ogro(nivel :Int, estadisticas: Estadisticas, rango: Rango) : Enemigo(TipoEnemigo.OGRO, nivel, estadisticas, rango) {
    override val tipoEnemigo = TipoEnemigo.OGRO
}