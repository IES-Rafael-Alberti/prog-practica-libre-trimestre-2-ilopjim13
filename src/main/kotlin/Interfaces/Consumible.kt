package Interfaces

import Item.Item

/**
 * Interfaz que define las acciones relacionadas con consumibles.
 */
interface Consumible {
    fun usarConsumible(item: Item)
    fun quitarEfectoConsumible()
}