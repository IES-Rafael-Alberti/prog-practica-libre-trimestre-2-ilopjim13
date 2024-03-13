package Juego

import com.github.ajalt.mordant.widgets.Text
import T
import tiempoEspera

object Mensaje {

    fun mostrar(desc:String) {
        println(desc)
    }

    fun mostrarConColores(text: Text) {
        T.println(text)
    }

    fun imprimirLento(texto: String) {
        texto.forEach {
            print(it)
            tiempoEspera(50)
        }
    }

}