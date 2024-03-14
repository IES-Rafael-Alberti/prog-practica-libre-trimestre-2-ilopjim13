package Mision

import Juego.Juego
import Juego.Mensaje
import Personaje.Jugador
import Juego.Vista
import T
import colorAzul
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.Whitespace
import com.github.ajalt.mordant.widgets.Panel
import com.github.ajalt.mordant.widgets.Text
import enterContinuar
import limpiarPantalla

object MostrarMisiones: MisionDIaria() {


    fun mostrarMisiones(jugador: Jugador) {
        var opcion: Int
        do {
            limpiarPantalla()

            T.println(
                Panel(
                    borderStyle = TextColors.rgb("00FFFB"),
                    content = Text(
                        "\n1 - Correr 100Km ${if (misiones[Mision.CORRER.desc] == true) TextColors.green("10/10") else TextColors.red("0") + TextColors.green("/10")}\n" +
                                "2 - Hacer 100 flexiones ${if (misiones[Mision.FLEXION.desc] == true) TextColors.green("100/100") else TextColors.red("0") + TextColors.green("/100")}\n" +
                                "3 - Hacer 100 abdominales ${if (misiones[Mision.ABDOMINAL.desc] == true) TextColors.green("100/100") else TextColors.red("0") + TextColors.green("/100")}\n" +
                                "4 - Hacer 100 dominadas ${if (misiones[Mision.DOMINADA.desc] == true) TextColors.green("100/100") else TextColors.red("0") + TextColors.green("/100")}\n" +
                                "5 - Volver", whitespace = Whitespace.PRE),
                    title = Text("** MISIONES **")
                )
            )

            opcion = Vista.pedirOpcion(5)

            elegirOpcionMision(opcion, jugador)

        }while (opcion != 5)
    }

    private fun elegirOpcionMision(opcion: Int, jugador: Jugador) {
        when (opcion) {
            1 ->  {
                if(misiones[Mision.CORRER.desc] == false) correr10km(jugador)
                else Mensaje.mostrarConColores("Esta misión ya ha sido completada, vuleve mañana.".colorAzul())
            }
            2 ->  {
                if(misiones[Mision.FLEXION.desc] == false) realizar100Flexiones(jugador)
                else Mensaje.mostrarConColores("Esta misión ya ha sido completada, vuleve mañana.".colorAzul())
            }
            3 ->  {
                if(misiones[Mision.ABDOMINAL.desc] == false) realizar100Abdominales(jugador)
                else Mensaje.mostrarConColores("Esta misión ya ha sido completada, vuleve mañana.".colorAzul())
            }
            4 ->  {
                if(misiones[Mision.DOMINADA.desc] == false) realizar100Dominadas(jugador)
                else Mensaje.mostrarConColores("Esta misión ya ha sido completada, vuleve mañana.".colorAzul())
            }
        }
        enterContinuar()
    }

}