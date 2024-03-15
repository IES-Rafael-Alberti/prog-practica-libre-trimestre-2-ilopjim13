package Personaje

import Item.Item
import Juego.Mensaje
import T
import colorAmarillo
import colorRosa
import colorVerde

/**
 * Clase que representa el inventario del jugador.
 */
class Inventario {
    val inventario = mutableMapOf<Item, Int>()

    /**
     * Agrega un item al inventario.
     *
     * @param item El item a agregar.
     */
    fun agregarItem(item: Item) {
        if (item in inventario) {
            inventario[item] = inventario[item]!! + 1
            Mensaje.mostrarConColores("Item repetido añadido al inventario.".colorVerde())
        }
        else {
            inventario[item] = 1
            Mensaje.mostrarConColores("Item nuevo añadido al inventario.".colorVerde())
        }
    }

    /**
     * Consume un item del inventario.
     *
     * @param item El item a consumir.
     */
    fun consumirItem(item: Item) {
        if (item in inventario) {
            if (inventario[item]!! > 1) {
                inventario[item] = inventario[item]!! - 1
                Mensaje.mostrarConColores("${item.nombre} consumido, aún te queda un total de ${inventario[item]}.".colorRosa())
            } else {
                inventario.remove(item)
                Mensaje.mostrarConColores("${item.nombre} consumido, ese era el último.".colorRosa())
            }
        }
    }

}