package Enemigo

import EstadisticaYRango.Estadisticas
import EstadisticaYRango.Rango

class Cazador(nivel :Int, estadisticas: Estadisticas, rango: Rango) : Enemigo(TipoEnemigo.CAZADOR, nivel, estadisticas, rango) {
    override val tipoEnemigo = TipoEnemigo.CAZADOR
}