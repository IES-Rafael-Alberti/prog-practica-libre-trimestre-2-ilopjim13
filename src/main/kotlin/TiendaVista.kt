import com.github.ajalt.mordant.rendering.*
import com.github.ajalt.mordant.table.Borders
import com.github.ajalt.mordant.table.table
import com.github.ajalt.mordant.terminal.Terminal

object TiendaVista {

    fun mostrarTienda() {
        val t = Terminal()
        val inventarioDiario = Tienda.actualizarTiendaDiaria()
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
                row("Item", "Precio", "Unidades") { cellBorders = Borders.BOTTOM }
            }
            body {
                style = TextColors.rgb("00FFF0")

                column(1) {
                    style = TextColors.rgb("F3FF00")
                    cellBorders = Borders.ALL
                }
                //42FF00
                column(2) {
                    align = TextAlign.CENTER
                }

                rowStyles(TextStyle(), TextStyles.dim.style)
                cellBorders = Borders.TOP_BOTTOM

                    inventarioDiario.forEach {
                        column(0) {
                            align = TextAlign.LEFT
                            cellBorders = Borders.ALL
                            style = comprobarRango(it.key)
                        }
                        row(it.key.nombre, it.key.precio, it.value)
                    }

                row("Average annual expenditures", "$61,332", "9")
                row("  Food", "7,310", "13")
                row("  Housing","24,298", "5")
                row("  Apparel and services", "1,434", "22")
                row("  Transportation", "12,295", "11")
                row("  Healthcare", "5,850", "5")
                row("  Entertainment","3,458", "22")
                row("  Education", "1,335", "8")
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
                    cells("Tu cartera: ${Jugador.cartera}", "", "")
                }
            }
        })
    }

    private fun comprobarRango(items: Items) :TextColors {
        return when (items.rango) {
            Rango.E -> TextColors.gray
            Rango.D -> TextColors.green
            Rango.C -> TextColors.blue
            Rango.B -> TextColors.cyan
            Rango.A -> TextColors.red
            Rango.S -> TextColors.yellow
        }
    }


}