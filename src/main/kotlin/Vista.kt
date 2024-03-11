import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.table.table
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.Whitespace
import com.github.ajalt.mordant.widgets.Text
import com.github.ajalt.mordant.widgets.Panel


object Vista {
    var mazmorra = ExplorarMazmorra.generarMazmorraRandom()
    fun introduccion() {
        T.println(table {
            header { row((black on brightGreen)("*** SOLO LEVELING RPG ***")) }
        })

        println("En un mundo donde empezaron a aparecer mazmorras llenas de monstruos, algunas personas despertaron habilidades, estas personas son conocidas como cazadores, y tú eres uno de ellos.\n")
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
                content = Text("Buenos días que quieres hacer hoy cazador\n\n1. Explorar una Mazmorra\n2. Revisar el inventario\n3. Ver estadisticas\n4. Ver las misiones diarias\n5. Ir a la tienda\n6. Saltar dia", whitespace = Whitespace.PRE),
                title = Text("** DIA ${Juego.dias} **")
            )
        )
        val opcion = pedirOpcion(6)

        elegirOpcionMenu(opcion, jugador)

    }

    private fun elegirOpcionMenu(opcion: Int, jugador: Jugador) {
        when (opcion) {
            1 -> {
                if (mazmorra.comprobarMazmorraCompletada()) T.println("** NO PUEDES VOLVER A HACER LA MASMORRA VUELVE MAÑANA **".colorRojo())
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
                    mazmorra = ExplorarMazmorra.generarMazmorraRandom()
                    println("-- Se han restablecido los efectos de las pociones")
                    barraProgreso("Pasando de dia...")
                } else T.println("** NO PUEDES AVANZAR DE DIA HASTA HABER COMPLETADO LA MAZMORRA Y LAS MISIONES DIARIAS **".colorRojo())
                enterContinuar()
                }

        }
    }

    fun pedirOpcion(opciones:Int) :Int {
        print("\n>> Selecciona una opcion: ")
        var opcion = -1
        do {
            try {
                opcion = readln().toInt()
                if (opcion !in (1..opciones)) print("**ERROR** Debes elegir una opcion correcta.\n>> Selecciona una opcion: ")
            } catch (e: NumberFormatException) {
                print("**ERROR** Debe de ser una opcion valida.\n>> Selecciona una opcion: ")
            }
        } while(opcion !in (1..opciones))
        println()
        return opcion
    }


}