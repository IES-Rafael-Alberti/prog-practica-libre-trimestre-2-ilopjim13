package Personaje

import Interfaces.Combates
import Interfaces.Comprar
import Interfaces.Consumible
import Interfaces.Equipable
import EstadisticaYRango.Estadisticas
import Item.Item
import Juego.Mensaje
import EstadisticaYRango.Rango
import T
import EstadisticaYRango.aumentarStastItem
import barraProgreso
import colorAmarillo
import EstadisticaYRango.modificarEstadisticas
import colorAzul
import colorRojo
import colorVerde
import enterContinuar
import tiempoEspera
import kotlin.random.Random

/**
 * Se utiliza para implementar la funcionalidad de vender piedras u otros objetos en el juego.
 */
interface VenderJugador {
    fun venderPiedras(item: Item)
}

/**
 * Clase que representa al jugador en el juego.
 *
 * @param nombre El nombre del jugador.
 * @param nivel El nivel actual del jugador.
 */
class Jugador(
    val nombre: String,
    var nivel: Int
) : Combates<Jugador>, Comprar, Consumible, VenderJugador {

    var rango = Rango.E  // El rango no lo eliges tu, se determina por tus estadisticas
    val experiencia: Experiencia = Experiencia()
    var estadisticas: Estadisticas = Estadisticas(100.0, 10.0, 8.0, 12.0)
    val inventario: Inventario = Inventario()
    var nivelExperiencia = 0
    val equipado = mutableMapOf("arma" to false, "armadura" to false)
    val equipo = mutableListOf<Item>()
    private val pociones = mutableListOf<Item.Pocion>()

    companion object {
        val cartera: Cartera = Cartera()
        const val NIVELMAX = 60
    }


    init {
        require(nivel > 0) { "El nivel debe ser mayor a 0" }
    }

    /**
     * Compra un objeto y lo agrega al inventario.
     *
     * @param item El objeto a comprar.
     */
    override fun comprarObjeto(item: Item) {
        inventario.agregarItem(item)
        cartera.restarDinero(item.precio)
    }

    /**
     * Realiza un ataque y retorna el daño y dependiendo de la fuerza y la agilidad del jugador crea una probabilidad para
     * hacer un ataque critico
     *
     * @return El daño infligido por el ataque.
     */
    override fun atacar() :Double {
        val critico = (estadisticas.fuerza * estadisticas.agilidad) / 100
        val suerte = (0..1000).random()/100
        return if (critico >= suerte) estadisticas.fuerza + critico
        else estadisticas.fuerza * 0.85
    }

    /**
     * Recibe daño del oponente si no puede esquivar
     *
     * @param danio Cantidad de daño recibido.
     * @return Boolean true si el jugador no esquiva el ataque y recibe daño, false si logra esquivarlo.
     */
    override fun recibirDanio(danio:Double) :Boolean {
        return if (!esquivar()) {
            val quitarResistencia = estadisticas.resistencia * 0.10
            val danioReal = danio - (quitarResistencia)
            if (quitarResistencia >= 1) {
                modificarEstadisticas(this, danioReal, "vida") { it, cant -> it - cant }
                modificarEstadisticas(this, quitarResistencia, "resistencia") { it, cant -> it - cant }
            } else modificarEstadisticas(this, danio, "vida") { it, cant -> it - cant }
            true
        }
        else false
    }

    /**
     * Intenta esquivar un ataque, dependiendo de la agilidad del jugador el la probabilidad es mayor.
     *
     * @return Boolean true si el jugador logra esquivar el ataque, false en caso contrario.
     */
    override fun esquivar() :Boolean {
        val probabilidad = estadisticas.agilidad / (estadisticas.agilidad + 10)
        val numRand = Random.nextDouble()
        return numRand <= probabilidad
    }

    /**
     * Simula la posibilidad de huir de una sala de la mazmorra, se calcula la posibilidad mediante la agilidad del jugador
     */
    fun huir(): Boolean {
        return if ((1..100).random() <= estadisticas.agilidad * 3) true
        else false
    }

    /**
     * Vende un [item] de tipo [Item.Material] y actualiza el inventario y la cartera.
     *
     * @param item El ítem que se va a vender.
     */
    override fun venderPiedras(item: Item) {
        inventario.consumirItem(item)
        cartera.ganarDinero(item.precio/2)
        Mensaje.mostrar("Has vendido una $item, has ganado un total de ${item.precio / 2}")
    }

    /**
     * Imprime por pantalla la informacion del Cazador.
     *
     * @return String muestra el nombre, nivel y rango del Cazador.
     */
    override fun toString(): String {
        return "Cazador $nombre, nivel: $nivel y Rango $rango"
    }

    /**
     * Incrementa el nivel del Cazador si aún no ha alcanzado el nivel máximo.
     * También actualiza la experiencia.
     */
    fun subirNivel() {
        if(nivel < NIVELMAX) {
            nivel++
            nivelExperiencia++
            experiencia.limitePorNivel += 35
            experiencia.experienciaActual = 0
        }
        Mensaje.mostrarConColores("\n** ENHORABUENA HAS SUBIDO DE NIVEL** ".colorAmarillo())
        actualizarRango()
    }

    /**
     * Activa la habilidad "Robo de Vida", que roba las estadísticas del enemigo y restaura las estadísticas iniciales.
     *
     * @param estadisticasIniciales Las estadísticas iniciales del personaje antes del combate.
     */
    fun roboDeVida(estadisticasIniciales: Estadisticas) {
        Mensaje.mostrarConColores("\n** Activando Habilidad Robo de Vida **".colorRojo())
        Mensaje.mostrar(">> Roba las estadisticas de tu enemigo y vuelves a tu estado normal.\n")
        val progreso = barraProgreso("Robo de Vida...")
        progreso.start()
        (1..5).forEach {
            progreso.update(it.toLong()*20, 100)
            tiempoEspera(300)
        }
        progreso.stop()
        estadisticas = estadisticasIniciales
        Mensaje.mostrarConColores("\n\n** ROBO DE VIDA COMPLETADO TUS ESTADISTICAS HAN VUELTO A LA NORMALIDAD **".colorVerde())
        enterContinuar()
    }

    /**
     * Utiliza la habilidad "Análisis" sobre un objetivo específico para obtener la informacion sobre ese objeto.
     *
     * @param desc Descripción del objetivo a analizar.
     */
    fun analisis(desc:String) {
        Mensaje.mostrarConColores("Utilizando habilidad Analisis sobre $desc".colorAzul())
        val progreso = barraProgreso("Analizando...")
        progreso.start()
        (1..5).forEach {
            progreso.update(it.toLong()*20, 100)
            tiempoEspera(300)
        }
        progreso.stop()
        Mensaje.mostrarConColores("\n\n** Analisis completado **".colorVerde())
    }

    /**
     * Incrementa una estadística específica del personaje segun la opcion elegida.
     *
     * @param opcion Opción que determina qué estadística se va a incrementar:
     * 1: Vida
     * 2: Fuerza
     * 3: Agilidad
     * 4: Resistencia
     */
    fun subirStat(opcion:Int) {
        when (opcion) {
            1 -> modificarEstadisticas(this, 25.0, "vida") { it, cant -> it + cant }
            2 -> modificarEstadisticas(this, 1.0, "fuerza") { it, cant -> it + cant }
            3 -> modificarEstadisticas(this, 1.0, "agilidad") { it, cant -> it + cant }
            4 -> modificarEstadisticas(this, 1.0, "resistencia") { it, cant -> it + cant }
        }
        nivelExperiencia -= 1
    }

    /**
     * Utiliza un consumible [item] y actualiza las estadísticas del personaje.
     * También consume el ítem del inventario y lo agrega a la lista de pociones.
     *
     * @param item El consumible que se va a usar.
     */
    override fun usarConsumible(item: Item) {
        modificarEstadisticas(this, item.estadisticas!!.vida, "vida") { it, cant -> it + cant }
        modificarEstadisticas(this, item.estadisticas!!.fuerza, "fuerza") { it, cant -> it + cant }
        modificarEstadisticas(this, item.estadisticas!!.agilidad, "agilidad") { it, cant -> it + cant }
        modificarEstadisticas(this, item.estadisticas!!.resistencia, "resistencia") { it, cant -> it + cant }
        inventario.consumirItem(item)
        pociones.add(item as Item.Pocion)
    }

    /**
     * Elimina los efectos de los consumibles previamente usados.
     * Restaura las estadísticas originales del personaje.
     */
    override fun quitarEfectoConsumible() {
        pociones.forEach {item ->
            modificarEstadisticas(this, item.estadisticas.vida, "vida") { it, cant -> it - cant }
            modificarEstadisticas(this, item.estadisticas.fuerza, "fuerza") { it, cant -> it - cant }
            modificarEstadisticas(this, item.estadisticas.agilidad, "agilidad") { it, cant -> it - cant }
            modificarEstadisticas(this, item.estadisticas.resistencia, "resistencia") { it, cant -> it - cant }
        }
        pociones.removeAll(pociones)
    }


    /**
     * Comprueba si la vida del personaje es igual a cero.
     *
     * @return `true` si la vida es igual a cero, `false` en caso contrario.
     */
    override fun comprobarVida():Boolean {
        return estadisticas.vida == 0.0
    }

    /**
     * Actualiza el rango del personaje según su nivel.
     */
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