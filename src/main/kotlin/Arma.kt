//class Arma(nombre:String, rango: Rango, precio:Int, estadisticas: Estadisticas, val tipo: TipoEquipable ) :Item(nombre, rango, precio, estadisticas), Equipable {
//
//    override fun equipar(jugador: Jugador) {
//        TODO("Not yet implemented")
//    }
//
//    override fun desequipar(jugador: Jugador) {
//        TODO("Not yet implemented")
//    }
//
//}

enum class TipoEquipable {
    PESADA, LIGERA
}