//class Pociones(nombre:String, rango: Rango, precio:Int,estadisticas: Estadisticas,val tipo: TipoPociones ) :Item(nombre, rango, precio, estadisticas), Usable {
//    override fun usarObjeto() {
//        TODO("Not yet implemented")
//    }
//}

enum class TipoPociones {
    VIDA, FUERZA, AGILIDAD, RESISTENCIA
}