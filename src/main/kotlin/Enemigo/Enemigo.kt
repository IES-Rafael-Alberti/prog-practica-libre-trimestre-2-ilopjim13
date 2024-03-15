package Enemigo

import EstadisticaYRango.Estadisticas
import Interfaces.Combates
import Item.Item
import EstadisticaYRango.Rango
import EstadisticaYRango.modificarEstadisticas
import kotlin.random.Random

/**
 * Clase abstracta que representa un enemigo.
 *
 * @param tipoEnemigo Tipo de enemigo.
 * @param nivel Nivel del enemigo.
 * @param estadisticas Estadísticas del enemigo (vida, fuerza, agilidad y resistencia).
 * @param rango Rango del enemigo.
 */
abstract class Enemigo(open val tipoEnemigo: TipoEnemigo, private val nivel:Int, val estadisticas: Estadisticas, val rango: Rango) : Combates<Enemigo> {

    /**
     * Comprueba si el enemigo está sin vida.
     *
     * @return Boolean true si el enemigo está sin vida y false en caso contrario.
     */
    override fun comprobarVida():Boolean {
        return estadisticas.vida == 0.0
    }

    /**
     * Realiza un ataque y retorna el daño y dependiendo de la fuerza y la agilidad del enemigo crea una probabilidad para
     * hacer un ataque critico
     *
     * @return El daño infligido por el ataque.
     */
    override fun atacar() :Double {
        val probabilidad = (estadisticas.fuerza * estadisticas.agilidad) / 100
        val suerte = (0..1000).random()/100
        return if (probabilidad >= suerte) estadisticas.fuerza + probabilidad
        else estadisticas.fuerza * 0.85
    }

    /**
     * Recibe daño del oponente si no puede esquivar
     *
     * @param danio Cantidad de daño recibido.
     * @return Boolean true si el enemigo no esquiva el ataque y recibe daño, false si logra esquivarlo.
     */
    override fun recibirDanio(danio:Double) :Boolean {
        return if (!esquivar()) {
            modificarEstadisticas(this, danio, "vida") { it, cant -> it - cant }
            true
        }
        else false

    }

    /**
     * Intenta esquivar un ataque, dependiendo de la agilidad del enemigo el la probabilidad es mayor.
     *
     * @return Boolean true si el enemigo logra esquivar el ataque, false en caso contrario.
     */
    override fun esquivar() :Boolean {
        val probabilidad = estadisticas.agilidad / (estadisticas.agilidad + 10)
        val numRand = Random.nextDouble()
        return numRand <= probabilidad
    }

    /**
     * Muestra la información del enemigo
     *
     * @return String con la información sobre el tipo de enemigo, nivel y rango.
     */
    override fun toString(): String {
        return "$tipoEnemigo de nivel: $nivel y rango $rango"
    }

    /**
     * Genera un material que el enemigo puede soltar al ser derrotado.
     *
     * @return Material generado según el rango del enemigo.
     */
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

    /**
     * Calcula la experiencia que se debe sumar al jugador al derrotar a este enemigo.
     *
     * @return Int Experiencia a sumar.
     */
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