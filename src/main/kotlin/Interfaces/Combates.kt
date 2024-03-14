package Interfaces
interface Combates<T> {

    fun comprobarVida() :Boolean

    fun atacar() :Double

    fun recibirDanio(danio:Double) :Boolean

    fun esquivar() :Boolean

}