import com.github.ajalt.mordant.rendering.*
import com.github.ajalt.mordant.table.Borders
import com.github.ajalt.mordant.table.table
import com.github.ajalt.mordant.terminal.Terminal
import com.github.ajalt.mordant.widgets.Text

object TiendaVista :Tienda() {

    fun mostrarTienda() {
        val t = Terminal()
        val inventarioDiario = actualizarTiendaDiaria()
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
                row("Item", "Precio", "Unidades", "Id") { cellBorders = Borders.BOTTOM }
            }
            body {
                column(1) {
                    style = TextColors.rgb("F3FF00")
                    cellBorders = Borders.ALL
                }
                //42FF00
                column(2) {
                    align = TextAlign.CENTER
                    style = TextColors.rgb("00FFF0")
                }
                column(3) {
                    align = TextAlign.CENTER
                    style = TextColors.rgb("FF0000")
                }

                rowStyles(TextStyle(), TextStyles.dim.style)
                cellBorders = Borders.TOP_BOTTOM

                    inventarioDiario.forEach { it ->
                        column(0) {
                            align = TextAlign.LEFT
                            cellBorders = Borders.ALL
                        }
                        val color = comprobarRango(it.key)
                        val texto = Text(color(it.key.nombre))
                        row(texto, it.key.precio, it.value, it.key.id)
                    }

                //row("Average annual expenditures", "$61,332", "9")
                //row("  Food", "7,310", "13")
                //row("  Housing","24,298", "5")
                //row("  Apparel and services", "1,434", "22")
                //row("  Transportation", "12,295", "11")
                //row("  Healthcare", "5,850", "5")
                //row("  Entertainment","3,458", "22")
                //row("  Education", "1,335", "8")
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
        mostrarTienda()
        println()
        println("1- Comprar Objeto")
        println("2- Vender Objeto")
        println("3- Volver")

        val opcion = Vista.pedirOpcion(3)
        elegirOpcionTienda(opcion, jugador)
    }

    private fun elegirOpcionTienda(opcion:Int, jugador: Jugador) {
        when (opcion) {
            1 -> menuVenta(jugador)
        }
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

    private fun comprobarRango(item: Item) :TextColors {
        return when (item.rango) {
            Rango.E -> TextColors.gray
            Rango.D -> TextColors.green
            Rango.C -> TextColors.blue
            Rango.B -> TextColors.cyan
            Rango.A -> TextColors.red
            Rango.S -> TextColors.yellow
        }
    }


}