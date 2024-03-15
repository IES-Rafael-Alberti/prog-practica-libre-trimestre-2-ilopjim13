package Tienda

import Item.Item
import Juego.Mensaje
import Personaje.Jugador
import Personaje.RevisarInventario
import Juego.Vista
import T
import com.github.ajalt.mordant.rendering.*
import com.github.ajalt.mordant.table.Borders
import com.github.ajalt.mordant.table.table
import com.github.ajalt.mordant.terminal.Terminal
import com.github.ajalt.mordant.widgets.Text
import enterContinuar
import limpiarPantalla

object TiendaVista : Tienda() {

    /**
     * Genera y muestra por pantalla una tabla con los productos diarios de la tienda en la que apareceran con colores
     * diferentes dependiendo del rango del objeto que se vende
     */
    private fun mostrarTienda() {
        T.println(table {
            borderType = BorderType.SQUARE_DOUBLE_SECTION_SEPARATOR
            borderStyle = TextColors.rgb("#4b25b9")
            align = TextAlign.RIGHT
            tableBorders = Borders.NONE
            header {
                style = TextColors.rgb("ffffff") + TextStyles.bold
                column(0) {
                    align = TextAlign.LEFT
                }
                row("Item","Rango", "Precio", "Unidades", "Id") { cellBorders = Borders.BOTTOM }
            }
            body {
                column(1) {
                    cellBorders = Borders.ALL
                    align = TextAlign.CENTER
                }

                column(2) {
                    style = TextColors.rgb("F3FF00")
                    cellBorders = Borders.ALL
                    align = TextAlign.CENTER
                }

                column(3) {
                    align = TextAlign.CENTER
                    style = TextColors.rgb("00FFF0")
                    cellBorders = Borders.ALL
                }
                column(4) {
                    align = TextAlign.CENTER
                    style = TextColors.rgb("FF0000")
                }

                rowStyles(TextStyle(), TextStyles.dim.style)
                cellBorders = Borders.TOP_BOTTOM

                inventarioDiario.forEach {
                    column(0) {
                        align = TextAlign.LEFT
                        cellBorders = Borders.ALL
                    }
                    val color = it.key.comprobarRangoParaColor()
                    val texto = Text(color(it.key.nombre))
                    val rango = Text(color(it.key.rango.desc))
                    row(texto,rango, it.key.precio, it.value, it.key.id)
                }
            }
            footer {
                column(1) {
                    cellBorders = Borders.NONE
                }
                column(2) {
                    cellBorders = Borders.NONE
                }
                style(italic = true)
                row {
                    cells("Tu cartera: ${Jugador.cartera.dinero}", "", "")
                }
            }
        })
    }

    /**
     * Muestra el menú de la tienda y permite al jugador realizar acciones como comprar o vender objetos.
     *
     * @param jugador El jugador que interactúa con la tienda.
     */
    fun menuTienda(jugador: Jugador) {
        do {
            limpiarPantalla()
            mostrarTienda()
            Mensaje.mostrar("\n1- Comprar Objeto")
            Mensaje.mostrar("2- Vender Objeto")
            Mensaje.mostrar("3- Volver")

            val opcion = Vista.pedirOpcion(3)
            elegirOpcionTienda(opcion, jugador)
        } while (opcion != 3)

    }

    /**
     * Permite al jugador elegir una opción en el menú de la tienda.
     *
     * @param opcion La opción seleccionada por el jugador.
     * @param jugador El jugador que interactúa con la tienda.
     */
    private fun elegirOpcionTienda(opcion:Int, jugador: Jugador) {
        when (opcion) {
            1 -> menuVenta(jugador)
            2 -> menuCompra(jugador)
        }
    }

    /**
     * Muestra el menú de compra de la tienda, donde el jugador puede vender piedras.
     *
     * @param jugador El jugador que interactúa con la tienda.
     */
    private fun menuCompra(jugador: Jugador) {
        limpiarPantalla()
        RevisarInventario.revisarInventario(jugador)
        var idObjeto = -1
        do {
            Mensaje.mostrarEnLinea(">> Introduce el Id de la piedra a vender: ")
            try {
                idObjeto = readln().toInt()
            } catch (e: NumberFormatException) {
                Mensaje.mostrar("**ERROR** El Id debe ser un numero")
            }
            if(!comprobarIdPiedra(idObjeto, jugador)) Mensaje.mostrar("Este id no corresponde a ninguna piedra del jugador.")
        } while(!comprobarIdPiedra(idObjeto, jugador))

        val items = jugador.inventario.inventario.filter { it.key.id == idObjeto }
        lateinit var item: Item
        items.map {item = it.key}
        if (item is Item.Material) jugador.venderPiedras(item)
        else Mensaje.mostrar("Este objeto no es una piedra...")

        enterContinuar()

    }

    /**
     * Muestra el menú de venta en la tienda, donde el jugador puede comprar objetos.
     *
     * @param jugador El jugador que interactúa con la tienda.
     */
    private fun menuVenta(jugador: Jugador) {
        var idObjeto = -1
        do {
           Mensaje.mostrarEnLinea(">> Introduce el Id del objeto: ")
           try {
               idObjeto = readln().toInt()
           } catch (e: NumberFormatException) {
               Mensaje.mostrar("**ERROR** El Id debe ser un numero")
           }
           if(!comprobarId(idObjeto)) Mensaje.mostrar("Este id no corresponde a ninguno de la tienda.")
        } while(!comprobarId(idObjeto))

        val items = inventarioDiario.filter { it.key.id == idObjeto }
        lateinit var item: Item
        items.map {item = it.key}

        venta(jugador, item)

        enterContinuar()
    }




}