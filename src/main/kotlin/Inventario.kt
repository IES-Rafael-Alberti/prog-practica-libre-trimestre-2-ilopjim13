class Inventario {
    val inventario = mutableMapOf<Items, Int>()

    fun agregarItem(item: Items) {
        if (item in inventario) {
            inventario[item] = inventario[item]!! + 1
            println("Item repetido añadido al inventario.")
        }
        else {
            inventario[item] = 1
            println("Item nuevo añadido al inventario.")
        }
    }

    fun consumirItem(item: Items) {
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