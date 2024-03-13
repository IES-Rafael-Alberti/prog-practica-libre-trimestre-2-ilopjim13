package Enemigo

import EstadisticaYRango.Estadisticas
import EstadisticaYRango.Rango

class Orco(nivel :Int, estadisticas: Estadisticas, rango: Rango) : Enemigo(TipoEnemigo.ORCO, nivel, estadisticas, rango) {
    override val tipoEnemigo = TipoEnemigo.ORCO
}