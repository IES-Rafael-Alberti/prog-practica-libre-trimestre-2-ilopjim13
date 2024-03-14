package Item

import EstadisticaYRango.Estadisticas
import EstadisticaYRango.Rango
import EstadisticaYRango.aumentarStastItem
import Interfaces.Equipable
import Juego.Mensaje
import Personaje.Jugador
import com.github.ajalt.mordant.rendering.TextColors

sealed class Item {

    abstract val nombre:String
    abstract val rango: Rango
    abstract val precio:Int
    abstract val estadisticas: Estadisticas?

    val listaDeItems = mutableListOf<Item>()
    val id: Int

    init {
        id = ++ident
    }

    companion object {
        var ident = 0
    }
    fun comprobarRangoParaColor() : TextColors {
        return when (rango) {
            Rango.E -> TextColors.gray
            Rango.D -> TextColors.green
            Rango.C -> TextColors.blue
            Rango.B -> TextColors.cyan
            Rango.A -> TextColors.red
            Rango.S -> TextColors.yellow
        }
    }




    data class Pocion(override val nombre: String, override val rango: Rango, override val precio: Int, override val estadisticas: Estadisticas, val tipo: TipoPociones) : Item() {
        override fun toString() = "$nombre, Rango $rango ,Id $id"
    }
    data class Arma(override val nombre: String, override val rango: Rango, override val precio: Int, override val estadisticas: Estadisticas, val tipo: TipoEquipable) : Item(), Equipable<Jugador> {

        override fun equipar(jugador: Jugador) {
            if (jugador.equipado["arma"] == false) {
                jugador.equipado["arma"] = true
                jugador.equipo.add(this)
                aumentarStastItem(jugador, this) { it, cant -> it + cant }
                Mensaje.mostrar("$this equipado")
            } else Mensaje.mostrar("Ya tienes un arma equipada.")
        }

        override fun desequipar(jugador: Jugador) {
            if (this in jugador.equipo) {
                if (jugador.equipado["arma"] == true) {
                    jugador.equipado["arma"] = false
                    jugador.equipo.remove(this)
                    aumentarStastItem(jugador, this) { it, cant -> it - cant }
                    Mensaje.mostrar("$this desequipado")
                } else Mensaje.mostrar("Ya tienes un arma equipada.")
            }
        }

        override fun toString() = "$nombre, Rango $rango ,Id $id"
    }
    data class Armadura(override val nombre: String, override val rango: Rango, override val precio: Int, override val estadisticas: Estadisticas, val tipo: TipoEquipable) : Item(), Equipable<Jugador> {
        override fun equipar(jugador: Jugador) {
            if (jugador.equipado["armadura"] == false) {
                jugador.equipado["armadura"] = true
                jugador.equipo.add(this)
                aumentarStastItem(jugador, this) { it, cant -> it + cant }
                Mensaje.mostrar("$this equipado")
            } else Mensaje.mostrar("Ya tienes una armadura equipada")
        }

        override fun desequipar(jugador:Jugador) {
            if (this in jugador.equipo) {
                if (jugador.equipado["armadura"] == true) {
                    jugador.equipado["armadura"] = false
                    jugador.equipo.remove(this)
                    aumentarStastItem(jugador, this) { it, cant -> it - cant }
                    Mensaje.mostrar("$this desequipado")
                } else Mensaje.mostrar("Ya tienes una armadura equipada.")
            }
        }

        override fun toString() = "$nombre, Rango $rango ,Id $id"
    }

    data class Material(override val nombre: String, override val precio:Int, override val rango: Rango, override val estadisticas: Estadisticas?) : Item() {
        override fun toString() = "$nombre, Id $id"
    }

}

enum class TipoPociones {
    VIDA, FUERZA, AGILIDAD, RESISTENCIA
}

enum class TipoEquipable {
    PESADA, LIGERA
}