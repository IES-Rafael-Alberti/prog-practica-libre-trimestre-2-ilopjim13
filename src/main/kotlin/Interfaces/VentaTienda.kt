package Interfaces

import Item.Item
import Personaje.Jugador

interface VentaTienda {
    fun venta(jugador: Jugador, item: Item)
}