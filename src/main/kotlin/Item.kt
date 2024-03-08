//abstract class Item(val nombre: String, val rango :Rango, val precio: Int, val estadisticas: Estadisticas) {
//
//    val listaDeItems = mutableListOf<Item>()
//
//    val id: Int
//
//    init {
//        id = ++ident
//    }
//
//    companion object {
//        var ident = 0
//    }
//
//
//}

sealed class Item(val nombre: String, val rango: Rango, val precio: Int, val estadisticas: Estadisticas) {

    val listaDeItems = mutableListOf<Item>()

    val id: Int

    init {
        id = ++ident
    }

    companion object {
        var ident = 0
    }

    class Pocion(nombre: String, rango: Rango, precio: Int, estadisticas: Estadisticas, val tipo: TipoPociones) : Item(nombre, rango, precio, estadisticas)
    class Arma(nombre: String, rango: Rango, precio: Int, estadisticas: Estadisticas, val tipo: TipoEquipable) : Item(nombre, rango, precio, estadisticas)
    class Armadura(nombre: String, rango: Rango, precio: Int, estadisticas: Estadisticas, val tipo: TipoEquipable) : Item(nombre, rango, precio, estadisticas)
}