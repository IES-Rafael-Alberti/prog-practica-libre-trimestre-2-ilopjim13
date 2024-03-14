package Personaje

import Interfaces.Equipable
import Item.Item
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

    fun revisarInventario(jugador: Jugador) {
        val t = Terminal()
        t.println(
            Panel(
                borderStyle = TextColors.magenta,
                content = textoInventario(jugador),
                title = Text("** INVENTARIO **")
            )
        )
    }

    fun menuInventario(jugador: Jugador) {
        do {
            revisarInventario(jugador)
            println("¿Que quiere hacer?")
            println("1- Equipar objeto")
            println("2- Desequipar objeto")
            println("3- Ver equipo equipado")
            println("4- Volver")

            val opcion = Vista.pedirOpcion(4)
            elegirOpcionInventario(opcion, jugador)
        } while (opcion != 4)

    }

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
                } else println("No tienes objetos equipables.")
                enterContinuar()
            }
            2 -> {
                if(jugador.equipo.isNotEmpty()) {
                    val id = pedirId(jugador)
                    val items = jugador.inventario.inventario.filter { it.key.id == id }
                    val item = items.keys.firstOrNull()
                    if (item != null && item is Item.Arma) item.desequipar(jugador)
                    else if (item != null && item is Item.Armadura) item.desequipar(jugador)
                } else println("No tienes objetos equipados.")
                enterContinuar()
            }
            3 -> mostrarEquipo(jugador)
        }
    }

    private fun mostrarEquipo(jugador: Jugador) {
        val equipo = jugador.equipo
        if (equipo.isNotEmpty()) {
            equipo.forEach {
                when (it) {
                    is Item.Arma -> println("- Arma: $it")
                    is Item.Armadura -> println("- Armadura: $it")
                    else -> println()
                }
            }
        } else T.println(">> No tienes nada equipado.".colorAzul())
        enterContinuar()
    }

    private fun pedirId(jugador: Jugador) :Int {
        var id = -1
        do {
            print(">> Introduce el Id del equipo a equipar: ")
            try {
                id = readln().toInt()
            } catch (e: NumberFormatException) {
                println("**ERROR** El Id debe ser un numero")
            }
            if(comprobarId(id, jugador)) println("Este id no corresponde a ningun equipo.")
        } while(comprobarId(id, jugador))
        return id
    }

    private fun comprobarId(id: Int, jugador: Jugador) :Boolean {
        val item = jugador.inventario.inventario.filter { it.key.id == id }
        return item.isEmpty()
    }


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