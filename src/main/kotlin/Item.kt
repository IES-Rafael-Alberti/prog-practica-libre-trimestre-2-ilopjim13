sealed class Item() {

    abstract val nombre:String
    abstract val rango:Rango
    abstract val precio:Int
    abstract val estadisticas:Estadisticas

    val listaDeItems = mutableListOf<Item>()
    val id: Int

    init {
        id = ++ident
    }

    companion object {
        var ident = 0
    }

    data class Pocion(override val nombre: String, override val rango: Rango, override val precio: Int, override val estadisticas: Estadisticas, val tipo: TipoPociones) : Item() {
        override fun toString() = "$nombre, Rango $rango ,Id $id"
    }
    data class Arma(override val nombre: String, override val rango: Rango, override val precio: Int, override val estadisticas: Estadisticas, val tipo: TipoEquipable) : Item(){
        override fun toString() = "$nombre, Rango $rango ,Id $id"
    }
    data class Armadura(override val nombre: String, override val rango: Rango, override val precio: Int, override val estadisticas: Estadisticas, val tipo: TipoEquipable) : Item(){
        override fun toString() = "$nombre, Rango $rango ,Id $id"
    }

}

enum class TipoPociones {
    VIDA, FUERZA, AGILIDAD, RESISTENCIA
}

enum class TipoEquipable {
    PESADA, LIGERA
}