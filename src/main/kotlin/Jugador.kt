class Jugador(
    val nombre: String,
    var nivel: Int,

) : Combates<Jugador>, Compras {

    var rango = Rango.E  // El rango no lo eliges tu, se determina por tus estadisticas
    val experiencia: Experiencia = Experiencia()
    val estadisticas: Estadisticas = Estadisticas(100.0, 10.0, 8.0,12.0)
    val inventario: Inventario = Inventario()
    val historialCombate: List<String> = mutableListOf()

    companion object {
        val cartera: Cartera = Cartera()
        const val NIVELMAX = 60
    }



    init {
        require(nivel > 0) { "El nivel debe ser mayor a 0" }
    }

    override fun comprarObjeto() {
        TODO("Not yet implemented")
    }

    override fun calcularDanio(): Double {
        return estadisticas.fuerza / 0.75
    }

    override fun atacar(): Double {
        TODO("Not yet implemented")
    }

    override fun recibirDanio(danio: Double): Boolean {
        TODO("Cuanto daño deberia recibir el jugador")
    }

    override fun esquivar(): Boolean {
        TODO("Not yet implemented")
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