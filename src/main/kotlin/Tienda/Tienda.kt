package Tienda

import EstadisticaYRango.Rango
import Interfaces.VentaTienda
import Item.Item
import Juego.Mensaje
import Personaje.Jugador

/**
 * Clase que representa una tienda en el juego.
 */
open class Tienda: VentaTienda {

    companion object {
        val inventarioEnTienda = mutableMapOf<Item, Int>()
        var inventarioDiario = mutableMapOf<Item, Int>()

        /**
         * Actualiza el inventario diario de la tienda.
         *
         * @return El inventario diario actualizado.
         */
        fun actualizarTiendaDiaria(): MutableMap<Item, Int> {
            inventarioDiario = mutableMapOf()
            val lista = inventarioEnTienda.toList()
            lista.shuffled()
            var cont = 0
            var item: Pair<Item, Int>
            do {
                if(lista.isNotEmpty()) {
                    item = lista[(0..28).random()]
                    if (item.first !in inventarioDiario) inventarioDiario[item.first] = item.second.also { cont++ }
                }
            } while (cont != 5)
            return inventarioDiario
        }

        /**
         * Agrega los ítems proporcionados a la tienda, asignando cantidades aleatorias según su rango.
         *
         * @param itemsEnTienda La lista de ítems que se van a agregar a la tienda.
         */
        fun agregarItemsATienda(itemsEnTienda: List<Item>) {
            itemsEnTienda.forEach {

                when (it.rango) {
                    Rango.E -> inventarioEnTienda[it] = (1..20).random()
                    Rango.D -> inventarioEnTienda[it] = (1..15).random()
                    Rango.C -> inventarioEnTienda[it] = (1..10).random()
                    Rango.B -> inventarioEnTienda[it] = (1..6).random()
                    Rango.A -> inventarioEnTienda[it] = (1..3).random()
                    Rango.S -> inventarioEnTienda[it] = 1
                }


            }
        }

        /**
         * Actualiza las cantidades de un ítem en el inventario de la tienda y en el inventario diario.
         *
         * @param item El ítem cuyas cantidades se van a actualizar.
         */
        private fun actualizarCantidades(item: Item) {
            inventarioEnTienda[item] = inventarioEnTienda[item]!! - 1
            inventarioDiario[item] = inventarioDiario[item]!! - 1
        }


        /**
         * Comprueba si el ID proporcionado corresponde a una piedra en el inventario del jugador.
         *
         * @param id El ID a comprobar.
         * @param jugador El jugador cuyo inventario se va a consultar.
         * @return `true` si el ID corresponde a una piedra, `false` en caso contrario.
         */
        fun comprobarIdPiedra(id:Int, jugador: Jugador):Boolean {
            var idCorrecto = false
            jugador.inventario.inventario.forEach {
                if (it.key.id == id) idCorrecto =  true
            }
            return idCorrecto
        }

        /**
         * Comprueba si el ID proporcionado corresponde a un ítem en el inventario diario de la tienda.
         *
         * @param id El ID a comprobar.
         * @return `true` si el ID corresponde a un ítem en el inventario diario, `false` en caso contrario.
         */
        fun comprobarId(id:Int) :Boolean {
            var idCorrecto = false
            inventarioDiario.forEach {
                if (it.key.id == id) idCorrecto =  true
            }
            return idCorrecto
        }

    }
    /**
     * Realiza una venta de un ítem al jugador, actualizando las cantidades en los inventarios.
     *
     * @param jugador El jugador que realiza la compra.
     * @param item El ítem que se va a vender.
     */
    override fun venta(jugador: Jugador, item: Item) {
        if (inventarioDiario[item] != 0) {
            if (Jugador.cartera.dinero >= item.precio) {
                jugador.comprarObjeto(item)
                actualizarCantidades(item)
            }
            else Mensaje.mostrar("No tienes suficiente dinero para comprar este objeto.")
        }
        else Mensaje.mostrar("No quedan mas cantidades de este Objeto.")
    }







}