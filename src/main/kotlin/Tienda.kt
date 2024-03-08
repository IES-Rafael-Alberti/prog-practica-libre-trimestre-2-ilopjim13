open class Tienda:Venta {

    companion object {
        val inventarioEnTienda = mutableMapOf<Item, Int>()
        val inventarioDiario = mutableMapOf<Item, Int>()

        fun actualizarTiendaDiaria(): MutableMap<Item, Int> {
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

    }

    private fun actualizarCantidades(item: Item) {
        inventarioEnTienda[item] = inventarioEnTienda[item]!! - 1
        inventarioDiario[item] = inventarioDiario[item]!! - 1
    }

    override fun venta(jugador: Jugador, item: Item) {
        jugador.comprarObjeto(item)
        actualizarCantidades(item)
    }

    fun comprobarId(id:Int) :Boolean {
        var idCorrecto = false
        inventarioDiario.forEach {
            if (it.key.id == id) idCorrecto =  true
        }
        return idCorrecto
    }





}