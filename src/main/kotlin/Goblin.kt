class Goblin(nivel :Int, estadisticas: Estadisticas, rango: Rango) :Enemigo(TipoEnemigo.GOBLIN, nivel, estadisticas, rango) {
    override val tipoEnemigo = TipoEnemigo.GOBLIN
}