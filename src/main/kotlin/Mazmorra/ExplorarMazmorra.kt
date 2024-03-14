package Mazmorra

import Item.CargarItem
import Enemigo.Enemigo
import EstadisticaYRango.Estadisticas
import Item.Item
import Juego.Mensaje
import Personaje.Jugador
import Personaje.RevisarInventario
import T
import Juego.Vista
import barraProgreso
import colorAzul
import colorRojo
import colorRosa
import colorVerde
import com.github.ajalt.mordant.widgets.Panel
import com.github.ajalt.mordant.widgets.Text
import enterContinuar
import limpiarPantalla
import tiempoEspera

/**
 * Objeto de gestión de la mazmorra
 */
object ExplorarMazmorra {

    private var noHuye = false
    private var tomaPocion = false
    private var salaFinal = false
    private var cont = 1

    fun entrarEnMazmorra(jugador: Jugador, mazmorra: Mazmorra) {
        limpiarPantalla()
        cont = 1
        Mensaje.mostrarConColores("!Has llegado a una mazmorra diaria¡\n".colorRosa())
        jugador.analisis("Mazmorra")
        GestionMazmorra.mostrarInfoMazmorra(mazmorra)
        enterContinuar()
        entrarEnSalas(jugador, mazmorra)
        finalizarMazmorra()

    }


    private fun entrarEnSalas(jugador: Jugador, mazmorra: Mazmorra) {

        mazmorra.salas.forEach {
            cont++
            if (cont <= mazmorra.salas.size) {
                do {
                    noHuye = false
                    tomaPocion = false
                    limpiarPantalla()
                    T.println(
                        Panel(
                            content = GestionMazmorra.textoSala(it.value),
                            title = Text("** SALA ${it.key} **")
                        )
                    )
                    if (it.value.isEmpty()) {
                        salaVacia(jugador)
                    }
                    else {
                        salaConEnemigos(jugador, it.value)
                    }
                } while (noHuye || tomaPocion)
            } else salaFinal(jugador, mazmorra)
        }
    }


    private fun salaFinal(jugador: Jugador, mazmorra: Mazmorra) {
        val salasFinal = mazmorra.salas.filter { it.key == mazmorra.salas.size }
        salasFinal.forEach {
            do {
                noHuye = false
                tomaPocion = false
                salaFinal = true
                limpiarPantalla()
                T.println(
                    Panel(
                        content = GestionMazmorra.textoSalaFinal(it.value),
                        title = "** ENTRANDO EN LA SALA DEL BOSS **".colorRojo()
                    )
                )
                salaConEnemigos(jugador, it.value)
            } while (noHuye || tomaPocion)
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
        RevisarInventario.revisarInventario(jugador)
        tomaPocion = true
        println()
        if (comprobarPociones(jugador)) {
            print(">> Introduce el Id del consumible que quieres usar: ")
            var id = -1
            do{
                try {
                    id = readln().toInt()
                } catch (e :NumberFormatException) {
                    Mensaje.mostrarConColores("El Id introducido debe ser correcto".colorRojo())
                }
            }while(id < 0)
            val pocion = comprobarId(id, jugador)
            if (pocion != null) {
                jugador.usarConsumible(pocion)
            } else Mensaje.mostrarConColores("El objeto introducido no es una poción".colorRojo())
        } else Mensaje.mostrarConColores("No tienes pociones en el inventario...".colorRojo())
        enterContinuar()
    }

    private fun comprobarPociones(jugador: Jugador): Boolean {
        val pociones = jugador.inventario.inventario.filter { it.key is Item.Pocion }
        return pociones.isNotEmpty()
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
        if (!salaFinal) {
            if (jugador.huir()) Mensaje.mostrarConColores("¡Has conseguido escapar correctamente!".colorAzul())
            else {
                Mensaje.mostrarConColores("¡No has conseguido escapar!".colorRojo())
                noHuye = true
            }
        } else {
            Mensaje.mostrarConColores("No puedes huir de la sala del BOSS".colorRojo())
            noHuye = true
        }

        enterContinuar()
    }

    private fun atacarEnemigos(jugador: Jugador, enemigos: MutableMap<Enemigo, Boolean>) {
        var index = 1
        enemigos.forEach { enemigo ->
            val estadisticasIniciales: Estadisticas = jugador.estadisticas.copy()
            println("\nEnemigo ${index++} -> ${enemigo.key.tipoEnemigo}")
            jugador.analisis("Enemigo")
            println(enemigo.key)
            println("${enemigo.key.estadisticas}\n")
            enterContinuar()
            SimularCombate.simularCombate(jugador, enemigo.key)
            roboDeVida(jugador, estadisticasIniciales)
        }
    }


    private fun roboDeVida(jugador: Jugador, estadisticasIniciales: Estadisticas) {
        Mensaje.mostrarConColores("\n** Activando Habilidad Robo de Vida **".colorRojo())
        Mensaje.mostrar(">> Roba las estadisticas de tu enemigo y vuelves a tu estado normal.\n")
        val progreso = barraProgreso("Robo de Vida...")
        progreso.start()
        (1..5).forEach {
            progreso.update(it.toLong()*20, 100)
            tiempoEspera(300)
        }
        progreso.stop()
        jugador.estadisticas = estadisticasIniciales
        Mensaje.mostrarConColores("\n\n** ROBO DE VIDA COMPLETADO TUS ESTADISTICAS HAN VUELTO A LA NORMALIDAD **".colorVerde())
        enterContinuar()
    }


    private fun finalizarMazmorra() {
        Mensaje.mostrarConColores("\n** ENHORABUENA HAS COMPLETADO LA MAZMORRA DIARIA -- VUELVE MAÑANA PARA COMPLETAR OTRA **".colorAzul())
    }



    private fun buscarObjeto(jugador: Jugador) {
        val probabilidad = (1..100).random()
        if (probabilidad > 70) {
            if ((1..10).random() > 5) {
                Mensaje.mostrar("** FELICIDADES HAS ENCONTRADO UN OBJETO **")
                val item = CargarItem.itemAleatorio()
                Mensaje.mostrar("Has obtenido un $item")
                jugador.inventario.agregarItem(item)
            }
            else {
                val monedas = (20..50).random()
                Mensaje.mostrar("** FELICIDADES HAS ENCONTRADO $monedas MONEDAS **")
                Jugador.cartera.ganarDinero(monedas)
            }
        } else Mensaje.mostrar("- No has encontrado nada...")
        enterContinuar()
    }











}