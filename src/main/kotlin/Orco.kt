class Orco(nivel :Int, estadisticas: Estadisticas, rango: Rango) :Enemigo(TipoEnemigo.ORCO, nivel, estadisticas, rango) {
    override val tipoEnemigo = TipoEnemigo.ORCO
}