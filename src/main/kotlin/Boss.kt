class Boss(nivel :Int, estadisticas: Estadisticas, rango: Rango) :Enemigo(TipoEnemigo.BOSS, nivel, estadisticas, rango) {
    override val tipoEnemigo = TipoEnemigo.BOSS
}