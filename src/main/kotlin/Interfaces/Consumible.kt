package Interfaces

import Item.Item

interface Consumible {

    fun usarConsumible(item: Item)

    fun quitarEfectoConsumible()

}