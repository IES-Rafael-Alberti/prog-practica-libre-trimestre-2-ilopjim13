import kotlin.random.Random

class Goblin(nivel :Int, estadisticas: Estadisticas, rango: Rango) :Enemigo(TipoEnemigo.GOBLIN, nivel, estadisticas, rango) {

    override val tipoEnemigo = TipoEnemigo.GOBLIN

    override fun calcularDanio() = estadisticas.fuerza / 0.75


    override fun atacar() :Double {
        return calcularDanio()
    }

    override fun recibirDanio(danio:Double) :Boolean {
        return if (!esquivar()) {
            estadisticas.restarVidaEnemigo(this, danio)
            true
        }
        else false

    }

    override fun esquivar() :Boolean {
        val probabilidad = estadisticas.agilidad / (estadisticas.agilidad + 10)
        val numRand = Random.nextDouble()
        return numRand <= probabilidad
    }

    override fun huir(): Boolean {
        TODO("Not yet implemented")
    }


}