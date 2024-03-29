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
import com.github.ajalt.mordant.rendering.*
import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.table.table
import com.github.ajalt.mordant.rendering.TextColors.Companion.rgb
import com.github.ajalt.mordant.table.Borders
import com.github.ajalt.mordant.widgets.Text
import com.github.ajalt.mordant.widgets.Panel
import enterContinuar
import espacios
import limpiarPantalla

/**
 * Objeta Vista que contiene funciones relacionadas con la interfaz del juego.
 */
object Vista {

    private var mazmorra = GestionMazmorra.generarMazmorraRandom()

    /**
     * Muestra la introducción del juego.
     */
    fun introduccion() {

        T.println(table {
            borderType = BorderType.ROUNDED
            borderStyle = TextStyle(color = cyan, bold = true)
            header { row(Text((black on TextColors.rgb("D5E3FF"))(
                "  __                            \n" +
                        " (_  _ | _  |  _    _ |o._  _   \n" +
                        " __)(_)|(_) |_(/_\\/(/_||| |(_|  \n" +
                        "                            _|  "))) }
        })
        Mensaje.imprimirLento("En un mundo donde empezaron a aparecer mazmorras llenas de monstruos, algunas personas despertaron habilidades, ")
        Mensaje.mostrar("")
        Mensaje.imprimirLento("estas personas son conocidas como cazadores, estos cazadores deben luchar contra monstruos para proteger a la raza humana")
        Mensaje.mostrar("")
        Mensaje.imprimirLento("de una aniquilación segura, y tú eres uno de ellos. Te adentrarás a las mazmorras mientras subes de nivel para acabar con los monstruos.")
        Mensaje.mostrar("")
        Mensaje.imprimirLento("Buena suerte cazador, yo el sistema te guiaré durante tu aventura, comencemos.")
        Mensaje.mostrar("\n")
    }

    /**
     * Solicita al jugador que ingrese su nombre.
     *
     * @return El nombre ingresado por el jugador.
     */
    fun pedirNombre(): String {
        var nombre: String
        do {
            Mensaje.mostrarEnLinea(">> Bienvenido cazador, introduce tu nombre: ")
            nombre = readln().espacios()
            if (nombre.isBlank()) Mensaje.mostrarConColores("El nombre no puede estar vacío. Introduzcalo de nuevo".colorRojo())
        } while (nombre.isBlank())

        return nombre
    }

    /**
     * Muestra el menú de opciones al jugador.
     *
     * @param jugador el jugador que va a jugar la partida.
     */
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

    /**
     * Dependiendo de la opción seleccionada por el jugador en el menú llama a otra funcion o termina el juego.
     *
     * @param opcion La opción seleccionada por el jugador.
     * @param jugador El objeto `Jugador` actual.
     */
    private fun elegirOpcionMenu(opcion: Int, jugador: Jugador) {
        when (opcion) {
            1 -> {
                if (mazmorra.comprobarMazmorraCompletada()) Mensaje.mostrarConColores("** NO PUEDES VOLVER A HACER LA MAZMORRA VUELVE MAÑANA **".colorRojo())
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
                    Mensaje.mostrar("-- Se han restablecido los efectos de las pociones")
                    barraProgreso("Pasando de dia...")
                } else Mensaje.mostrarConColores("** NO PUEDES AVANZAR DE DIA HASTA HABER COMPLETADO LA MAZMORRA Y LAS MISIONES DIARIAS **".colorRojo())
                enterContinuar()
            }
            7 -> Fin.salirDelJuego()
        }
    }

    /**
     * Solicita al usuario seleccionar una opción del menú.
     *
     * @param opciones: Int El número total de opciones disponibles.
     * @return La opción seleccionada por el usuario.
     */
    fun pedirOpcion(opciones:Int) :Int {
        Mensaje.mostrarEnLinea("\n>> Selecciona una opcion: ")
        var opcion = -1
        do {
            try {
                opcion = readln().toInt()
                if (opcion !in (1..opciones)) {
                    Mensaje.mostrarConColores("**ERROR** Debe de ser una opcion valida.".colorRojo())
                    print(">> Selecciona una opcion: ")
                }
            } catch (e: NumberFormatException) {
                Mensaje.mostrarConColores("**ERROR** Debe de ser una opcion valida.".colorRojo())
                Mensaje.mostrarEnLinea(">> Selecciona una opcion: ")
            }
        } while(opcion !in (1..opciones))
        println()
        return opcion
    }


}