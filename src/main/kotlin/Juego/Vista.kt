package Juego

import EstadisticaYRango.MostrarEstadisticas
import Mazmorra.ExplorarMazmorra
import Mazmorra.GestionMazmorra
import Mision.MisionDIaria
import Mision.MostrarMisiones
import Personaje.Jugador
import Personaje.RevisarInventario
import T
import Tienda.TiendaVista
import barraProgreso
import colorRojo
import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.table.table
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextColors.Companion.rgb
import com.github.ajalt.mordant.rendering.Whitespace
import com.github.ajalt.mordant.widgets.Text
import com.github.ajalt.mordant.widgets.Panel
import enterContinuar
import espacios
import limpiarPantalla


object Vista {
    private var mazmorra = GestionMazmorra.generarMazmorraRandom()
    fun introduccion() {

        T.println(table {
            header { row((black on rgb("7FE9DB"))("   *** SOLO LEVELING RPG ***   ")) }
        })
        Mensaje.imprimirLento("En un mundo donde empezaron a aparecer mazmorras llenas de monstruos, algunas personas despertaron habilidades, ")
        println("")
        Mensaje.imprimirLento("estas personas son conocidas como cazadores, y tú eres uno de ellos.")
        println("")
        println("")
    }

    fun pedirNombre(): String {
        print("Bienvenido cazador, introduce tu nombre: ")
        var nombre: String
        do {
            nombre = readln().espacios()
            if (nombre.isBlank()) print("El nombre no puede estar vacío. Introduzcalo de nuevo: ")
        } while (nombre.isBlank())

        return nombre
    }

    fun menu(jugador: Jugador) {
        limpiarPantalla()
        T.println(
            Panel(
                borderStyle = TextColors.rgb("00FFFB"),
                content = Text("Buenos días que quieres hacer hoy ${jugador.nombre}\n\n" +
                        "1. Explorar una Mazmorra\n" +
                        "2. Revisar el inventario\n" +
                        "3. Ver estadisticas\n" +
                        "4. Ver las misiones diarias\n" +
                        "5. Ir a la tienda\n" +
                        "6. Saltar dia\n" +
                        "7. Salir del juego", whitespace = Whitespace.PRE),
                title = Text("** DIA ${Juego.dias} **")
            )
        )
        val opcion = pedirOpcion(7)

        elegirOpcionMenu(opcion, jugador)

    }

    private fun elegirOpcionMenu(opcion: Int, jugador: Jugador) {
        when (opcion) {
            1 -> {
                if (mazmorra.comprobarMazmorraCompletada()) T.println("** NO PUEDES VOLVER A HACER LA MAZMORRA VUELVE MAÑANA **".colorRojo())
                else {
                    ExplorarMazmorra.entrarEnMazmorra(jugador, mazmorra)
                    mazmorra.salasTerminadas()
                }
                enterContinuar()
            }
            2 -> RevisarInventario.menuInventario(jugador)
            3 -> MostrarEstadisticas.menuEstadisticas(jugador)
            4 -> MostrarMisiones.mostrarMisiones(jugador)
            5 -> TiendaVista.menuTienda(jugador)
            6 -> {
                if (mazmorra.comprobarMazmorraCompletada() && MisionDIaria.completas()) {
                    Juego.reiniciarDia()
                    jugador.quitarEfectoConsumible()
                    mazmorra = GestionMazmorra.generarMazmorraRandom()
                    println("-- Se han restablecido los efectos de las pociones")
                    barraProgreso("Pasando de dia...")
                } else T.println("** NO PUEDES AVANZAR DE DIA HASTA HABER COMPLETADO LA MAZMORRA Y LAS MISIONES DIARIAS **".colorRojo())
                enterContinuar()
            }
            7 -> Fin.salirDelJuego()
        }
    }

    fun pedirOpcion(opciones:Int) :Int {
        print("\n>> Selecciona una opcion: ")
        var opcion = -1
        do {
            try {
                opcion = readln().toInt()
                if (opcion !in (1..opciones)) {
                    T.println("**ERROR** Debe de ser una opcion valida.".colorRojo())
                    print(">> Selecciona una opcion: ")
                }
            } catch (e: NumberFormatException) {
                T.println("**ERROR** Debe de ser una opcion valida.".colorRojo())
                print(">> Selecciona una opcion: ")
            }
        } while(opcion !in (1..opciones))
        println()
        return opcion
    }


}