import kotlin.random.Random

class Jugador(
    val nombre: String,
    var nivel: Int,

) : Combates<Jugador>, Comprar {

    var rango = Rango.E  // El rango no lo eliges tu, se determina por tus estadisticas
    val experiencia: Experiencia = Experiencia()
    val estadisticas: Estadisticas = Estadisticas(100.0, 10.0, 8.0,12.0)
    val inventario: Inventario = Inventario()
    var nivelExperiencia = 0

    companion object {
        val cartera: Cartera = Cartera()
        const val NIVELMAX = 60
    }



    init {
        require(nivel > 0) { "El nivel debe ser mayor a 0" }
    }

    override fun comprarObjeto(item: Item) {
        inventario.agregarItem(item)
        cartera.ganarDinero(item.precio)
    }

    override fun calcularDanio() :Double {
        return estadisticas.fuerza / 0.75

    }

    override fun atacar() :Double {
        return calcularDanio()
    }

    override fun recibirDanio(danio:Double) :Boolean {
        return if (!esquivar()) {
            estadisticas.restarVida(this, danio)
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
        return "Cazador $nombre, nivel: $nivel y Rango $rango"
    }

    fun subirNivel() {
        if(nivel < NIVELMAX) {
            nivel++
            nivelExperiencia++
            experiencia.limitePorNivel += 5
        }
        println("** ENHORABUENA HAS SUBIDO DE NIVEL")
        actualizarRango()

    }

    fun comprobarVida():Boolean {
        return estadisticas.vida == 0.0
    }

    private fun actualizarRango() {
        when (nivel) {
            in (1..10) -> rango = Rango.E
            in (11..20) -> rango = Rango.D
            in (21..30) -> rango = Rango.C
            in (31..40) -> rango = Rango.B
            in (41..50) -> rango = Rango.A
            in (51..60) -> rango = Rango.S
        }
    }

}