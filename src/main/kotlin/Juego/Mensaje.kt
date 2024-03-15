package Juego

import com.github.ajalt.mordant.widgets.Text
import T
import tiempoEspera

/**
 * Objeto Mensaje que contiene funciones para mostrar mensajes en la consola.
 */
object Mensaje {

    /**
     * Muestra un mensaje en la consola.
     *
     * @param desc El mensaje a mostrar.
     */
    fun mostrar(desc:String) {
        println(desc)
    }

    /**
     * Muestra un mensaje en la consola dejando en la misma linea la siguiente interacción.
     *
     * @param desc El mensaje a mostrar.
     */
    fun mostrarEnLinea(desc:String) {
        print(desc)
    }

    /**
     * Muestra un mensaje con colores en la consola.
     *
     * @param text El texto con formato de colores.
     */
    fun mostrarConColores(text: Text) {
        T.println(text)
    }

    /**
     * Imprime lentamente un texto en la consola.
     *
     * @param texto El texto a imprimir.
     */
    fun imprimirLento(texto: String) {
        texto.forEach {
            print(it)
            tiempoEspera(20)
        }
    }

}