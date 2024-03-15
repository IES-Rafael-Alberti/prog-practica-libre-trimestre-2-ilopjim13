package Item

import EstadisticaYRango.Estadisticas
import EstadisticaYRango.Rango
import EstadisticaYRango.aumentarStastItem
import Interfaces.Equipable
import Juego.Mensaje
import Personaje.Jugador
import com.github.ajalt.mordant.rendering.TextColors

/**
 * Clase sellada que representa un ítem en el juego.
 *
 * @property nombre Nombre del ítem.
 * @property rango Rango del ítem.
 * @property precio Precio del ítem.
 * @property estadisticas Estadísticas asociadas al ítem.
 */
sealed class Item {

    abstract val nombre:String
    abstract val rango: Rango
    abstract val precio:Int
    abstract val estadisticas: Estadisticas?

    val listaDeItems = mutableListOf<Item>()
    val id: Int = ++ident

    companion object {
        var ident = 0
    }

    /**
     * Comprueba el rango del ítem y devuelve el color de texto correspondiente.
     *
     * @return Color de texto según el rango del ítem.
     */
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


    /**
     * Clase que define una poción en el juego.
     *
     * @property nombre Nombre de la poción.
     * @property rango Rango de la poción.
     * @property precio Precio de la poción.
     * @property estadisticas Estadísticas asociadas a la poción.
     * @property tipo Tipo específico de la poción.
     */
    data class Pocion(override val nombre: String, override val rango: Rango, override val precio: Int, override val estadisticas: Estadisticas, val tipo: TipoPociones) : Item() {
        override fun toString() = "$nombre, Rango $rango ,Id $id"
    }

    /**
     * Clase que define un arma en el juego.
     *
     * @property nombre Nombre del arma.
     * @property rango Rango del arma.
     * @property precio Precio del arma.
     * @property estadisticas Estadísticas asociadas al arma.
     * @property tipo Tipo específico del arma (por ejemplo, espada, arco, etc.).
     */
    data class Arma(override val nombre: String, override val rango: Rango, override val precio: Int, override val estadisticas: Estadisticas, val tipo: TipoEquipable) : Item(), Equipable<Jugador> {

        /**
         * Equipa el arma al jugador poniendo a true el map equipados y añadiendo el arma al equipo
         * y aumenta sus stats dependiendo de las estadisticas del arma.
         *
         * @param jugador Jugador al que se le equipará el arma.
         */
        override fun equipar(jugador: Jugador) {
            if (jugador.equipado["arma"] == false) {
                jugador.equipado["arma"] = true
                jugador.equipo.add(this)
                aumentarStastItem(jugador, this) { it, cant -> it + cant }
                Mensaje.mostrar("$this equipado")
            } else Mensaje.mostrar("Ya tienes un arma equipada.")
        }

        /**
         * Desequipa el arma al jugador poniendo a false arma en el map equipados y eliminando el arma al equipo
         * y quitandole sus stats dependiendo de las estadisticas del arma.
         *
         * @param jugador Jugador al que se le equipará el arma.
         */
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

    /**
     * Clase que representa una armadura en el juego.
     *
     * @property nombre Nombre del material.
     * @property precio Precio del material.
     * @property rango Rango del material.
     * @property estadisticas Estadísticas asociadas al material.
     */
    data class Armadura(override val nombre: String, override val rango: Rango, override val precio: Int, override val estadisticas: Estadisticas, val tipo: TipoEquipable) : Item(), Equipable<Jugador> {

        /**
         * Equipa la armadura al jugador poniendo a true el map equipados y añadiendo la armadura al equipo
         * y aumenta sus stats dependiendo de las estadisticas de la armadura.
         *
         * @param jugador Jugador al que se le equipará el arma.
         */
        override fun equipar(jugador: Jugador) {
            if (jugador.equipado["armadura"] == false) {
                jugador.equipado["armadura"] = true
                jugador.equipo.add(this)
                aumentarStastItem(jugador, this) { it, cant -> it + cant }
                Mensaje.mostrar("$this equipado")
            } else Mensaje.mostrar("Ya tienes una armadura equipada")
        }

        /**
         * Desequipa la armadura al jugador poniendo a false armadura en el map equipados y quitando la armadura al equipo
         * y disminuyendo sus stats dependiendo de las estadisticas de la armadura.
         *
         * @param jugador Jugador al que se le equipará el arma.
         */
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

    /**
     * Clase que representa un material en el juego.
     *
     * @property nombre Nombre del material.
     * @property precio Precio del material.
     * @property rango Rango del material.
     * @property estadisticas Estadísticas asociadas al material.
     */
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