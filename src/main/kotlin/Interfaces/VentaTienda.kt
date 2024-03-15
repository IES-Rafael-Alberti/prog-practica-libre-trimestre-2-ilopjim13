package Interfaces

import Item.Item
import Personaje.Jugador

/**
 * Interfaz que define la acción de venta en la tienda.
 */
interface VentaTienda {
    fun venta(jugador: Jugador, item: Item)
}