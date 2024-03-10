import kotlin.random.Random

class Jugador(
    val nombre: String,
    var nivel: Int,

) : Combates<Jugador>, Comprar, Consumible, Equipable {

    var rango = Rango.E  // El rango no lo eliges tu, se determina por tus estadisticas
    val experiencia: Experiencia = Experiencia()
    var estadisticas: Estadisticas = Estadisticas(100.0, 10.0, 8.0,12.0)
    private val estadisticasSinObjetos = estadisticas.copy()
    private var estadisticasConObjetos = estadisticas.copy()
    val inventario: Inventario = Inventario()
    var nivelExperiencia = 0
    private val equipado = mutableMapOf("arma" to false, "armadura" to false)
    private val equipados = mutableListOf<Item>()

    companion object {
        val cartera: Cartera = Cartera()
        const val NIVELMAX = 60
    }


    init {
        require(nivel > 0) { "El nivel debe ser mayor a 0" }
    }

    override fun comprarObjeto(item: Item) {
        inventario.agregarItem(item)
        cartera.restarDinero(item.id)
    }

    override fun calcularDanio() :Double {
        return estadisticas.fuerza / 0.75

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
        return if ((1..100).random() <= estadisticas.agilidad * 3) true
        else false
    }

    override fun equipar(item: Item) {
        when (item) {
            is Item.Arma -> if (equipado["arma"] != false) {
                equipado["arma"] = true
                equipados.add(item)
            }
            is Item.Armadura -> if (equipado["armadura"] != false) {
                equipado["armadura"] = true
                equipados.add(item)
            }
            is Item.Pocion -> TODO()
        }
    }

    override fun desequipar(item: Item) {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return "Cazador $nombre, nivel: $nivel y Rango $rango"
    }

    fun subirNivel() {
        if(nivel < NIVELMAX) {
            nivel++
            nivelExperiencia++
            experiencia.limitePorNivel += 35
            experiencia.experienciaActual = 0
        }
        T.println("\n** ENHORABUENA HAS SUBIDO DE NIVEL** ".colorAmarillo())
        actualizarRango()
    }

    fun subirStat(opcion:Int) {
        when (opcion) {
            1 -> modificarEstadisticas(this, 25.0, "vida") {it, cant -> it + cant}
            2 -> modificarEstadisticas(this, 1.0, "fuerza") {it, cant -> it + cant}
            3 -> modificarEstadisticas(this, 1.0, "agilidad") {it, cant -> it + cant}
            4 -> modificarEstadisticas(this, 1.0, "resistencia") {it, cant -> it + cant}
        }
        nivelExperiencia -= 1
    }

    override fun usarConsumible(pocion: Item) {
        modificarEstadisticas(this, pocion.estadisticas.vida, "vida") {it, cant -> it + cant}
        modificarEstadisticas(this, pocion.estadisticas.fuerza, "fuerza") {it, cant -> it + cant}
        modificarEstadisticas(this, pocion.estadisticas.agilidad, "agilidad") {it, cant -> it + cant}
        modificarEstadisticas(this, pocion.estadisticas.resistencia, "resistencia") {it, cant -> it + cant}
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