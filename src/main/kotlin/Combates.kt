
interface Combates<T> {

    fun calcularDanio() :Double

    fun atacar() :Double

    fun recibirDanio(danio:Double) :Boolean

    fun esquivar() :Boolean

}