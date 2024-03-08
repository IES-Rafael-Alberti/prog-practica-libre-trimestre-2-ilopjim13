import com.github.ajalt.mordant.rendering.TextAlign
import com.github.ajalt.mordant.widgets.Panel
import com.github.ajalt.mordant.widgets.Text

object ExplorarMazmorra:Mazmorra("", Rango.E) {

    fun entrarEnMazmorra(jugador: Jugador) {
        println("Has llegado a una mazmorra diaria.")
        utilizarHabilidad()
        mostrarInfoMazmorra()
        entrarEnSalas(jugador)

    }

    private fun entrarEnSalas(jugador: Jugador) {
        salas.forEach {
            T.println(
                Panel(
                    content = textoSala(it.value),
                    title = Text("** SALA ${it.key} **")
                )
            )
            if (it.value.isEmpty()) {
                println("¿Que quieres hacer?")
                println("1. Buscar objeto oculto")
                println("2. Avanzar a la siguiente sala")

                val opcion = Vista.pedirOpcion(2)

                elegirOpcionSalaVacia(opcion, jugador)

            }
            else {
                println("¿Que quieres hacer?")
                println("1. Atacar a los enemigos.")
                println("2. Tomar pocion de curación")
                println("3. Huir a la siguiente sala")

                val opcion = Vista.pedirOpcion(2)

                //elegirOpcionSala(opcion)

            }


        }
    }

//    private fun elegirOpcionSala(opcion: Int) {
//        when (opcion) {
//            1-> atacarEnemigos()
//            2-> tomarPocion()
//            3-> huirDeSala()
//        }
//    }

    private fun elegirOpcionSalaVacia(opcion:Int, jugador: Jugador) {
        when (opcion) {
            1 -> buscarObjeto(jugador)
        }
    }

    private fun buscarObjeto(jugador: Jugador) {
        val probabilidad = (1..100).random()
        if (probabilidad > 70) {
            if ((1..10).random() > 5) {
                println("** FELICIDADES HAS ENCONTRADO UN OBJETO **")
                val item = CargarItem.itemAleatorio()
                println("Has obtenido un $item")
                jugador.inventario.agregarItem(item)
            }
            else {
                println("** FELICIDADES HAS ENCONTRADO MONEDAS **")
                val monedas = (20..50).random()
                Jugador.cartera.ganarDinero(monedas)
            }

        }
    }

    private fun textoSala(lista:List<MutableMap<Enemigo, Boolean>>):Text {
        var num = 0
        if (lista.isEmpty()) return Text("Esta sala no tiene enemigos.")
        else lista.forEach { _ -> num++ }
        return if (num == 1) Text("Solo hay un enemigo en la sala")
        else Text("Hay un total de $num enemigos en la sala")
    }

    private fun utilizarHabilidad() {
        println("Utilizando habilidad analisis sobre la mazmorra")
        val progreso = barraProgreso("Analizando...")
        progreso.start()
        (1..2).forEach {
            progreso.update(it.toLong(), 2)
            tiempoEspera(200)
        }
        progreso.stop()
        println("** Analisis completado **")
    }

    private fun mostrarInfoMazmorra() {
        var numSalas = 0
        salas.forEach { numSalas = it.key }
        println("Nombre: $nombre de rango $rango y con un total de $numSalas salas")
    }


}