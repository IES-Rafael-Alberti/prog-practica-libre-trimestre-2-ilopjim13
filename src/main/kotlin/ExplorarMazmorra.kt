import com.github.ajalt.mordant.widgets.Panel
import com.github.ajalt.mordant.widgets.Text

object ExplorarMazmorra {

    private var noHuye = false

    fun entrarEnMazmorra(jugador: Jugador) {
        val mazmorra = Mazmorra(nombreRandom(), rangoRandom())
        limpiarPantalla()
        println("Has llegado a una mazmorra diaria.")
        analisis("mazmorra")
        mostrarInfoMazmorra(mazmorra)
        enterContinuar()
        entrarEnSalas(jugador, mazmorra)

    }

    private fun entrarEnSalas(jugador: Jugador, mazmorra: Mazmorra) {
        mazmorra.salas.forEach {
            do {
                noHuye = false
                limpiarPantalla()
                T.println(
                    Panel(
                        content = textoSala(it.value),
                        title = Text("** SALA ${it.key} **")
                    )
                )
                if (it.value.isEmpty()) {
                    salaVacia(jugador)
                }
                else {
                    salaConEnemigos(jugador, it.value)
                }
            } while (noHuye)


        }
    }



    private fun salaConEnemigos(jugador: Jugador, enemigos: MutableMap<Enemigo, Boolean>) {
        println("¿Que quieres hacer?")
        println("1. Atacar a los enemigos.")
        println("2. Tomar una pocion")
        println("3. Huir a la siguiente sala")

        val opcion = Vista.pedirOpcion(3)

        elegirOpcionSala(opcion, jugador, enemigos)
    }


    private fun salaVacia(jugador: Jugador) {
        println("¿Que quieres hacer?")
        println("1. Buscar objeto oculto")
        println("2. Avanzar a la siguiente sala")

        val opcion = Vista.pedirOpcion(2)

        elegirOpcionSalaVacia(opcion, jugador)
    }

    private fun elegirOpcionSala(opcion: Int, jugador: Jugador, enemigos: MutableMap<Enemigo, Boolean>) {
        when (opcion) {
            1-> atacarEnemigos(jugador, enemigos)
            2-> tomarPocion(jugador)
            3-> huirDeSala(jugador)
        }
    }

    private fun elegirOpcionSalaVacia(opcion:Int, jugador: Jugador) {
        when (opcion) {
            1 -> buscarObjeto(jugador)
        }
    }

    private fun tomarPocion(jugador: Jugador) {
        MostrarEstadisticas.mostrarEstadisticas(jugador)
        println()
        print(">> Introduce el Id del consumible que quieres usar: ")
        val id = readln().toInt()
        val pocion = comprobarId(id, jugador)
        if (pocion != null) {
            jugador.usarConsumible(pocion)
        } else println("EL objeto introducido no es una poción")
    }

    private fun comprobarId(id:Int, jugador: Jugador): Item? {
        val objetos = jugador.inventario.inventario.filter { it.key.id == id }
        val objeto = objetos.keys.firstOrNull()
        objeto.let {
            when (it) {
                is Item.Pocion -> return objeto
                else -> return null
            }
        }
    }

    private fun huirDeSala(jugador: Jugador) {
        if (jugador.huir()) println("¡Has conseguido escapar correctamente!")
            else {
            println("¡No has conseguido escapar!")
            noHuye = true
            }
        enterContinuar()
    }

    private fun atacarEnemigos(jugador: Jugador, enemigos: MutableMap<Enemigo, Boolean>) {
        var cont = 1
        enemigos.forEach { enemigo ->
            val estadisticasIniciales:Estadisticas = jugador.estadisticas.copy()
            println("\nEnemigo ${cont++} -> ${enemigo.key.tipoEnemigo}")
            analisis("enemigo")
            println(enemigo.key)
            println("${enemigo.key.estadisticas}\n")
            enterContinuar()
            SimularCombate.simularCombate(jugador, enemigo.key)
            roboDeVida(jugador, estadisticasIniciales)
            enterContinuar()
        }
    }


    private fun roboDeVida(jugador: Jugador, estadisticasIniciales: Estadisticas) {
        T.println("\n** Activando Habilidad Robo de Vida **".colorRojo())
        println(">> Roba las estadisticas de tu enemigo y vuelves a tu estado normal.\n")
        val progreso = barraProgreso("Robo de Vida...")
        progreso.start()
        (1..5).forEach {
            progreso.update(it.toLong()*20, 100)
            tiempoEspera(300)
        }
        progreso.stop()
        jugador.estadisticas = estadisticasIniciales
        T.println("\n\n** ROBO DE VIDA COMPLETADO TUS ESTADISTICAS HAN VUELTO A LA NORMALIDAD **".colorVerde())
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
                val monedas = (20..50).random()
                println("** FELICIDADES HAS ENCONTRADO $monedas MONEDAS **")
                Jugador.cartera.ganarDinero(monedas)
            }
        } else println("No has encontrado nada...")
        enterContinuar()
    }

    private fun textoSala(lista:MutableMap<Enemigo, Boolean>):Text {
        var num = 0
        if (lista.isEmpty()) return Text("Esta sala no tiene enemigos.")
        else lista.forEach { _ -> num++ }
        return if (num == 1) Text("Solo hay un enemigo en la sala")
        else Text("Hay un total de $num enemigos en la sala")
    }

    private fun analisis(desc:String) {
        println("Utilizando habilidad Analisis sobre $desc")
        val progreso = barraProgreso("Analizando...")
        progreso.start()
        (1..5).forEach {
            progreso.update(it.toLong()*20, 100)
            tiempoEspera(300)
        }
        progreso.stop()
        println("\n\n** Analisis completado **")
    }


    private fun mostrarInfoMazmorra(mazmorra: Mazmorra) {
        var numSalas = 0
        mazmorra.salas.forEach { numSalas = it.key }
        println("Nombre: ${mazmorra.nombre} de rango ${mazmorra.rango} y con un total de $numSalas salas")
    }

    private fun nombreRandom() :String {
        return listOf(
            "Caverna de las Sombras Profundas",
            "Cripta del Lamento Eterno",
            "Abismo de los Antiguos",
            "Catacumbas del Olvido",
            "Mazmorra de la Serpiente de Obsidiana",
            "Torre de los Susurros Oscuros",
            "Foso de los Espíritus Perdidos",
            "Pasaje de las Almas Errantes",
            "Gruta de los Espectros",
            "Antro de la Noche Infinita",
            "Cámara de los Enigmas Arcanos",
            "Calabozo de los Espejismos",
            "Sala de los Mil Suspiros"
        ).random()
    }

    private fun rangoRandom() = listOf(Rango.E, Rango.D, Rango.C, Rango.B, Rango.A, Rango.S).random()


}