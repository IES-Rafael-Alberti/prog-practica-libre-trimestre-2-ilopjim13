import kotlin.random.Random

sealed class Enemigo(open val tipoEnemigo: TipoEnemigo, val nivel:Int, val estadisticas: Estadisticas, val rango:Rango) : Combates<Enemigo> {

    fun comprobarVida():Boolean {
        return estadisticas.vida == 0.0
    }

    override fun calcularDanio(): Double {
        val probabilidad = (estadisticas.fuerza * estadisticas.agilidad) / 100
        val suerte = (0..1000).random()/100
        return if (probabilidad >= suerte) estadisticas.fuerza + probabilidad
        else estadisticas.fuerza * 0.85
    }


    override fun atacar() :Double {
        return calcularDanio()
    }

    override fun recibirDanio(danio:Double) :Boolean {
        return if (!esquivar()) {
            modificarEstadisticas(this, danio, "vida") {it, cant -> it - cant}
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

    override fun toString(): String {
        return "$tipoEnemigo de nivel: $nivel y rango $rango"
    }

    fun soltarMaterial(): Item.Material {
        return when (rango) {
            Rango.E -> Item.Material("Piedra de Rango E", 10, Rango.E, null)
            Rango.D -> Item.Material("Piedra de Rango D", 20, Rango.E, null)
            Rango.C -> Item.Material("Piedra de Rango C", 30, Rango.E, null)
            Rango.B -> Item.Material("Piedra de Rango B", 40, Rango.E, null)
            Rango.A -> Item.Material("Piedra de Rango A", 50, Rango.E, null)
            Rango.S -> Item.Material("Piedra de Rango S", 75, Rango.E, null)
        }
    }

    fun experienciaASumar() :Int {
        return when (tipoEnemigo) {
            TipoEnemigo.GOBLIN -> 25
            TipoEnemigo.OGRO -> (25..50).random()
            TipoEnemigo.ORCO -> (50..80).random()
            TipoEnemigo.CAZADOR -> (80..100).random()
            TipoEnemigo.BOSS -> (100..150).random()
        }
    }



}