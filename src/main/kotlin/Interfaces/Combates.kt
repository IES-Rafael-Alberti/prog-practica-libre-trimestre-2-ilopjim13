package Interfaces

/**
 * Interfaz que define las acciones de combate para un personaje o enemigo.
 */
interface Combates<T> {

    fun comprobarVida() :Boolean
    fun atacar() :Double
    fun recibirDanio(danio:Double) :Boolean
    fun esquivar() :Boolean

}