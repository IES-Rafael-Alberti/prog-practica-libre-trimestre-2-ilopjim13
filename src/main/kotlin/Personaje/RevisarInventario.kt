package Personaje

import Interfaces.Equipable
import Item.Item
import Juego.Mensaje
import T
import Juego.Vista
import colorAzul
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.Whitespace
import com.github.ajalt.mordant.terminal.Terminal
import com.github.ajalt.mordant.widgets.Panel
import com.github.ajalt.mordant.widgets.Text
import enterContinuar

object RevisarInventario {

    /**
     * Muestra el inventario del jugador en una interfaz gráfica.
     *
     * @param jugador El jugador cuyo inventario se va a revisar.
     */
    fun revisarInventario(jugador: Jugador) {
        T.println(
            Panel(
                borderStyle = TextColors.magenta,
                content = textoInventario(jugador),
                title = Text("** INVENTARIO **")
            )
        )
    }

    /**
     * Muestra un menú interactivo para gestionar el inventario del jugador.
     *
     * @param jugador El jugador cuyo inventario se va a gestionar.
     */
    fun menuInventario(jugador: Jugador) {
        do {
            revisarInventario(jugador)
            Mensaje.mostrar("¿Que quiere hacer?")
            Mensaje.mostrar("1- Equipar objeto")
            Mensaje.mostrar("2- Desequipar objeto")
            Mensaje.mostrar("3- Ver equipo equipado")
            Mensaje.mostrar("4- Volver")

            val opcion = Vista.pedirOpcion(4)
            elegirOpcionInventario(opcion, jugador)
        } while (opcion != 4)

    }

    /**
     * Realiza una acción según la opción seleccionada en el menú de inventario.
     *
     * @param opcion La opción seleccionada por el jugador.
     * @param jugador El jugador cuyo inventario se va a gestionar.
     */
    private fun elegirOpcionInventario(opcion: Int, jugador: Jugador) {
        when (opcion) {
            1 -> {
                val equipables = jugador.inventario.inventario.filter { it.key is Equipable<*> }
                if(equipables.isNotEmpty()) {
                    val id = pedirId(jugador)
                    val items = jugador.inventario.inventario.filter { it.key.id == id }
                    val item = items.keys.firstOrNull()
                    if (item != null && item is Item.Arma) item.equipar(jugador)
                    else if (item != null && item is Item.Armadura) item.equipar(jugador)
                } else Mensaje.mostrar("No tienes objetos equipables.")
                enterContinuar()
            }
            2 -> {
                if(jugador.equipo.isNotEmpty()) {
                    val id = pedirId(jugador)
                    val items = jugador.inventario.inventario.filter { it.key.id == id }
                    val item = items.keys.firstOrNull()
                    if (item != null && item is Item.Arma) item.desequipar(jugador)
                    else if (item != null && item is Item.Armadura) item.desequipar(jugador)
                } else Mensaje.mostrar("No tienes objetos equipados.")
                enterContinuar()
            }
            3 -> mostrarEquipo(jugador)
        }
    }

    /**
     * Muestra el equipo actualmente equipado por el jugador.
     *
     * @param jugador El jugador cuyo equipo se va a mostrar.
     */
    private fun mostrarEquipo(jugador: Jugador) {
        val equipo = jugador.equipo
        if (equipo.isNotEmpty()) {
            equipo.forEach {
                when (it) {
                    is Item.Arma -> Mensaje.mostrar("- Arma: $it")
                    is Item.Armadura -> Mensaje.mostrar("- Armadura: $it")
                }
            }
        } else Mensaje.mostrarConColores(">> No tienes nada equipado.".colorAzul())
        enterContinuar()
    }

    /**
     * Solicita al jugador que introduzca un ID de equipo y verifica si es válido.
     *
     * @param jugador El jugador cuyo inventario se va a consultar.
     * @return El ID válido introducido por el jugador.
     */
    private fun pedirId(jugador: Jugador) :Int {
        var id = -1
        do {
            Mensaje.mostrarEnLinea(">> Introduce el Id del equipo a equipar: ")
            try {
                id = readln().toInt()
            } catch (e: NumberFormatException) {
                Mensaje.mostrar("**ERROR** El Id debe ser un numero")
            }
            if(comprobarId(id, jugador)) Mensaje.mostrar("Este id no corresponde a ningun equipo.")
        } while(comprobarId(id, jugador))
        return id
    }

    /**
     * Verifica si el ID proporcionado corresponde a un equipo en el inventario del jugador.
     *
     * @param id El ID a comprobar.
     * @param jugador El jugador cuyo inventario se va a consultar.
     * @return `true` si el ID no corresponde a ningún equipo, `false` en caso contrario.
     */
    private fun comprobarId(id: Int, jugador: Jugador) :Boolean {
        val item = jugador.inventario.inventario.filter { it.key.id == id }
        return item.isEmpty()
    }

    /**
     * Genera un texto que representa el inventario del jugador.
     *
     * @param jugador El jugador cuyo inventario se va a consultar.
     * @return Un objeto Text que contiene la descripción del inventario.
     */
    private fun textoInventario(jugador: Jugador):Text {
        var texto = ""
        var cont = 1
        val inventario = jugador.inventario.inventario
        if (inventario.isNotEmpty()) {
            inventario.forEach {
                texto += if (inventario.size != cont++) "- ${it.key}: ${it.value}\n"
                else "- ${it.key}: ${it.value} "
            }
        }else texto = "- Inventario vacio... "
        return Text(texto, whitespace = Whitespace.PRE)
    }


}