abstract class Enemigo(open val tipoEnemigo: TipoEnemigo, val nivel:Int, val estadisticas: Estadisticas, val rango:Rango) : Combates<Enemigo> {

    fun comprobarVida():Boolean {
        return estadisticas.vida == 0.0
    }
}