import com.github.ajalt.mordant.rendering.*
import com.github.ajalt.mordant.table.Borders
import com.github.ajalt.mordant.table.table
import com.github.ajalt.mordant.terminal.Terminal
import com.github.ajalt.mordant.widgets.Text

object TiendaVista :Tienda() {

    private fun mostrarTienda() {
        val t = Terminal()
        t.println(table {
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

    fun menuTienda(jugador: Jugador) {
        do {
            limpiarPantalla()
            mostrarTienda()
            println()
            println("1- Comprar Objeto")
            println("2- Vender Objeto")
            println("3- Volver")

            val opcion = Vista.pedirOpcion(3)
            elegirOpcionTienda(opcion, jugador)
        } while (opcion != 3)

    }

    private fun elegirOpcionTienda(opcion:Int, jugador: Jugador) {
        when (opcion) {
            1 -> menuVenta(jugador)
            2 -> menuCompra(jugador)
        }
    }

    private fun menuCompra(jugador: Jugador) {
        limpiarPantalla()
        RevisarInventario.revisarInventario(jugador)
        var idObjeto = -1
        do {
            print(">> Introduce el Id de la piedra a vender: ")
            try {
                idObjeto = readln().toInt()
            } catch (e: NumberFormatException) {
                println("**ERROR** El Id debe ser un numero")
            }
            if(!comprobarIdPiedra(idObjeto, jugador)) println("Este id no corresponde a ninguna piedra del jugador.")
        } while(!comprobarIdPiedra(idObjeto, jugador))

        val items = jugador.inventario.inventario.filter { it.key.id == idObjeto }
        lateinit var item:Item
        items.map {item = it.key}
        if (item is Item.Material) jugador.venderPiedras(item)
        else println("Este objeto no es una piedra...")

        enterContinuar()

    }




    private fun menuVenta(jugador: Jugador) {
        var idObjeto = -1
        do {
           print(">> Introduce el Id del objeto: ")
           try {
               idObjeto = readln().toInt()
           } catch (e: NumberFormatException) {
                println("**ERROR** El Id debe ser un numero")
           }
           if(!comprobarId(idObjeto)) println("Este id no corresponde a ninguno de la tienda.")
        } while(!comprobarId(idObjeto))

        val items = inventarioDiario.filter { it.key.id == idObjeto }
        lateinit var item:Item
        items.map {item = it.key}

        venta(jugador, item)

        enterContinuar()

    }




}