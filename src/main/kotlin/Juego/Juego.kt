package Juego

import Item.CargarItem
import Mision.MisionDIaria
import Personaje.Jugador
import Tienda.Tienda
import limpiarPantalla
import personajeIncial

object Juego {

    var dias = 0
    var juego = false



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




    fun reiniciarDia() {
        dias++
        Tienda.actualizarTiendaDiaria()
        MisionDIaria.reiniciarMisiones()
    }

}