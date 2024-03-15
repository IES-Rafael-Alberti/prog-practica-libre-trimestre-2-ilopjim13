package Juego

import Item.CargarItem
import Mision.MisionDIaria
import Personaje.Jugador
import Tienda.Tienda
import limpiarPantalla
import personajeIncial

/**
 * Clase Juego que contiene funciones relacionadas con la gestión del juego.
 *
 * @property dias El número de días transcurridos en el juego.
 * @property juego Indica si el juego está en curso o no.
 */
object Juego {

    var dias = 0
    var juego = false



    /**
     * Inicia el juego realizando las siguientes acciones:
     * - Limpia la pantalla.
     * - Agrega todos los ítems a la tienda.
     * - Muestra la introducción del juego.
     * - Reinicia el día.
     * - Solicita al [Jugador] que ingrese su nombre.
     * - Crea un objeto [Jugador] con el nombre proporcionado.
     * - Muestra el menú del juego mientras el juego esté en curso.
     */
    fun iniciarJuego() {
        limpiarPantalla()
        Tienda.agregarItemsATienda(CargarItem.todosLosItems())
        Vista.introduccion()
        reiniciarDia()

        val nombre = Vista.pedirNombre()

        val jugador: Jugador = personajeIncial(nombre)

        juego = true
        do {
            Vista.menu(jugador)
        } while (juego)

    }

    /**
     * Reinicia el día en el juego:
     * - Incrementa el contador de días.
     * - Actualiza la tienda con los ítems diarios.
     * - Reinicia las misiones diarias.
     */
    fun reiniciarDia() {
        dias++
        Tienda.actualizarTiendaDiaria()
        MisionDIaria.reiniciarMisiones()
    }

}