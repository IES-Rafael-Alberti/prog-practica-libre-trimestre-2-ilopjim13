package Mision

import Personaje.Jugador
import Juego.Vista
import enterContinuar

object MostrarMisiones: MisionDIaria() {


    fun mostrarMisiones(jugador: Jugador) {
        var opcion: Int
        do {
            println("\n** MISIONES **\n")
            println("1 - Correr 100Km ${if (misiones[Mision.CORRER.desc] == true) "10/10" else "0/10"}")
            println("2 - Hacer 100 flexiones ${if (misiones[Mision.FLEXION.desc] == true) "100/100" else "0/100"}")
            println("3 - Hacer 100 abdominales ${if (misiones[Mision.ABDOMINAL.desc] == true) "100/100" else "0/100"}")
            println("4 - Hacer 100 dominadas ${if (misiones[Mision.DOMINADA.desc] == true) "100/100" else "0/100"}")
            println("5 - Volver")

            opcion = Vista.pedirOpcion(5)

            elegirOpcionMision(opcion, jugador)

        }while (opcion != 5)
    }

    private fun elegirOpcionMision(opcion: Int, jugador: Jugador) {
        when (opcion) {
            1 ->  {
                if(misiones[Mision.CORRER.desc] == false) correr10km(jugador)
                else println("Esta misión ya ha sido completada, vuleve mañana.")
                enterContinuar()
            }
            2 ->  {
                if(misiones[Mision.FLEXION.desc] == false) realizar100Flexiones(jugador)
                else println("Esta misión ya ha sido completada, vuleve mañana.")
                enterContinuar()
            }
            3 ->  {
                if(misiones[Mision.ABDOMINAL.desc] == false) realizar100Abdominales(jugador)
                else println("Esta misión ya ha sido completada, vuleve mañana.")
                enterContinuar()
            }
            4 ->  {
                if(misiones[Mision.DOMINADA.desc] == false) realizar100Dominadas(jugador)
                else println("Esta misión ya ha sido completada, vuleve mañana.")
                enterContinuar()
            }
        }
    }

}