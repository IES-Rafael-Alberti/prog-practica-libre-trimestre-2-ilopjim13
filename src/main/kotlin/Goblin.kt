class Goblin( nivel :Int, estadisticas: Estadisticas, rango: Rango) :Enemigo(TipoEnemigo.GOBLIN, nivel, estadisticas, rango) {

    override val tipoEnemigo = TipoEnemigo.GOBLIN
    override fun calcularDanio(): Double {
        TODO("Not yet implemented")
    }

    override fun atacar(): Double {
        TODO("Not yet implemented")
    }

    override fun recibirDanio(danio: Double): Boolean {
        TODO("Not yet implemented")
    }

    override fun esquivar(): Boolean {
        TODO("Not yet implemented")
    }

    override fun huir(): Boolean {
        TODO("Not yet implemented")
    }


}