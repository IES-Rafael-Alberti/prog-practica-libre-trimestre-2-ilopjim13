class Inventario {
    val inventario = mutableMapOf<Item, Int>()

    fun agregarItem(item: Item) {
        if (item in inventario) {
            inventario[item] = inventario[item]!! + 1
            T.println("Item repetido añadido al inventario.".colorAmarillo())
        }
        else {
            inventario[item] = 1
            T.println("Item nuevo añadido al inventario.".colorAmarillo())
        }
    }

    fun consumirItem(item: Item) {
        if (item in inventario) {
            if (inventario[item]!! > 1) {
                inventario[item] = inventario[item]!! - 1
                println("${item.nombre} consumido, aún te queda un total de ${inventario[item]}.")
            } else {
                inventario.remove(item)
                println("${item.nombre} consumido, ese era el último.")
            }
        }
    }




}