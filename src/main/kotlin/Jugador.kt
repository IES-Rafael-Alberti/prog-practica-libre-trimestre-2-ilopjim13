import kotlin.random.Random

interface VenderJugador {
    fun venderPiedras(item: Item)
}


class Jugador(
    val nombre: String,
    var nivel: Int,

) : Combates<Jugador>, Comprar, Consumible, Equipable<Item>, VenderJugador {

    var rango = Rango.E  // El rango no lo eliges tu, se determina por tus estadisticas
    val experiencia: Experiencia = Experiencia()
    var estadisticas: Estadisticas = Estadisticas(100.0, 10.0, 8.0,12.0)
    val inventario: Inventario = Inventario()
    var nivelExperiencia = 0
    private val equipado = mutableMapOf("arma" to false, "armadura" to false)
    val equipo = mutableListOf<Item>()
    private val pociones = mutableListOf<Item.Pocion>()

    companion object {
        val cartera: Cartera = Cartera()
        const val NIVELMAX = 60
    }


    init {
        require(nivel > 0) { "El nivel debe ser mayor a 0" }
    }

    override fun comprarObjeto(item: Item) {
        inventario.agregarItem(item)
        cartera.restarDinero(item.precio)
    }

    override fun calcularDanio() :Double {
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
            val quitarResistencia = estadisticas.resistencia * 0.10
            val danioReal = danio - (quitarResistencia)
            if (quitarResistencia >= 1) {
                modificarEstadisticas(this, danioReal, "vida") {it, cant -> it - cant}
                modificarEstadisticas(this, quitarResistencia, "resistencia") {it, cant -> it - cant}
            } else modificarEstadisticas(this, danio, "vida") {it, cant -> it - cant}
            true
        }
        else false
    }

    override fun esquivar() :Boolean {
        val probabilidad = estadisticas.agilidad / (estadisticas.agilidad + 10)
        val numRand = Random.nextDouble()
        return numRand <= probabilidad
    }


    fun huir(): Boolean {
        return if ((1..100).random() <= estadisticas.agilidad * 3) true
        else false
    }

    override fun equipar(item: Item) {
        when (item) {
            is Item.Arma -> {
                if (equipado["arma"] == false) {
                    equipado["arma"] = true
                    equipo.add(item)
                    aumentarStastItem(this,  item ) {it, cant -> it + cant}
                    println("$item equipado")
                } else Mensaje.mostrar("Ya tienes un arma equipada.")
            }
            is Item.Armadura -> {
                if (equipado["armadura"] == false) {
                    equipado["armadura"] = true
                    equipo.add(item)
                    aumentarStastItem(this,  item ) {it, cant -> it + cant}
                    println("$item equipado")
                } else Mensaje.mostrar("Ya tienes una armadura equipada")
            }
            else -> Mensaje.mostrar("Este objeto no se puede equipar")
        }
    }

    override fun desequipar(item: Item) {
        if (item in equipo) {
            when (item) {
                is Item.Arma -> {
                    if (equipado["arma"] == true) {
                        equipado["arma"] = false
                        equipo.remove(item)
                        aumentarStastItem(this, item ) {it, cant -> it - cant}
                        Mensaje.mostrar("$item desequipado")
                    } else Mensaje.mostrar("Ya tienes un arma equipada.")
                }
                is Item.Armadura -> {
                    if (equipado["armadura"] != false) {
                        equipado["armadura"] = true
                        equipo.remove(item)
                        aumentarStastItem(this, item ) {it, cant -> it - cant}
                        Mensaje.mostrar("$item desequipado")
                    } else Mensaje.mostrar("Ya tienes una armadura equipada")
                }
                is Item.Pocion -> quitarEfectoConsumible()
                else -> Mensaje.mostrar("Este objeto no se puede desequipar")
            }
        }
    }

    override fun venderPiedras(item: Item) {
        inventario.consumirItem(item)
        cartera.ganarDinero(item.precio/2)
        Mensaje.mostrar("Has vendido una $item, has ganado un total de ${item.precio/2}")
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

    fun analisis(desc:String) {
        Mensaje.mostrar("Utilizando habilidad Analisis sobre $desc")
        val progreso = barraProgreso("Analizando...")
        progreso.start()
        (1..5).forEach {
            progreso.update(it.toLong()*20, 100)
            tiempoEspera(300)
        }
        progreso.stop()
        Mensaje.mostrar("\n\n** Analisis completado **")
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

    override fun usarConsumible(item: Item) {
        modificarEstadisticas(this, item.estadisticas!!.vida, "vida") { it, cant -> it + cant}
        modificarEstadisticas(this, item.estadisticas!!.fuerza, "fuerza") { it, cant -> it + cant}
        modificarEstadisticas(this, item.estadisticas!!.agilidad, "agilidad") { it, cant -> it + cant}
        modificarEstadisticas(this, item.estadisticas!!.resistencia, "resistencia") { it, cant -> it + cant}
        inventario.consumirItem(item)
        pociones.add(item as Item.Pocion)
    }

    override fun quitarEfectoConsumible() {
        pociones.forEach {item ->
            modificarEstadisticas(this, item.estadisticas.vida, "vida") { it, cant -> it - cant}
            modificarEstadisticas(this, item.estadisticas.fuerza, "fuerza") { it, cant -> it - cant}
            modificarEstadisticas(this, item.estadisticas.agilidad, "agilidad") { it, cant -> it - cant}
            modificarEstadisticas(this, item.estadisticas.resistencia, "resistencia") { it, cant -> it - cant}
        }
        pociones.removeAll(pociones)
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