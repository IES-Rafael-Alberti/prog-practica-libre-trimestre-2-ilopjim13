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
 * Clase que representa la exploración de una mazmorra.
 *
 * @property noHuye indica si el jugador ha intentado huir de la mazmorra.
 * @property tomaPocion indica si el jugador ha tomado una poción durante la exploración.
 * @property salaFinal indica si la sala actual es la sala final de la mazmorra.
 * @property cont contador para llevar el registro de las salas exploradas.
 */
object ExplorarMazmorra {

    private var noHuye = false
    private var tomaPocion = false
    private var salaFinal = false
    private var cont = 1

    /**
     * Método para que el jugador entre en la mazmorra y comience la exploración.
     *
     * @param jugador el jugador que entra en la mazmorra.
     * @param mazmorra la mazmorra a explorar.
     */
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


    /**
     * Ejecuta la exploración en las salas de la mazmorra.
     *
     * @param jugador el jugador que entra en la mazmorra.
     * @param mazmorra la mazmorra a explorar.
     */
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
                    } else {
                        salaConEnemigos(jugador, it.value)
                    }
                } while (noHuye || tomaPocion)
            } else salaFinal(jugador, mazmorra)
        }
    }

    /**
     * Cuando llega a la ultima sala no se puede huir y cambia el texto de las salas y la sala no puede estar vacía.
     *
     * @param jugador el jugador que está explorando la mazmorra.
     * @param mazmorra la mazmorra actual.
     */
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

    /**
     * Maneja la interacción del jugador con una sala que contiene enemigos.
     *
     * @param jugador el jugador que está explorando la mazmorra.
     * @param enemigos el mapa de enemigos presentes en la sala y su estado.
     */
    private fun salaConEnemigos(jugador: Jugador, enemigos: MutableMap<Enemigo, Boolean>) {
        println("¿Que quieres hacer?")
        println("1. Atacar a los enemigos.")
        println("2. Tomar una pocion")
        println("3. Huir a la siguiente sala")

        val opcion = Vista.pedirOpcion(3)

        elegirOpcionSala(opcion, jugador, enemigos)
    }

    /**
     * Maneja la interacción del jugador con una sala vacía en la mazmorra.
     *
     * @param jugador el jugador que está explorando la mazmorra.
     */
    private fun salaVacia(jugador: Jugador) {
        println("¿Que quieres hacer?")
        println("1. Buscar objeto oculto")
        println("2. Avanzar a la siguiente sala")

        val opcion = Vista.pedirOpcion(2)

        elegirOpcionSalaVacia(opcion, jugador)
    }

    /**
     * Ejecuta la elección de opciones por parte del jugador en una sala con enemigos.
     *
     * @param opcion la opción seleccionada por el jugador.
     * @param jugador el jugador que está explorando la mazmorra.
     * @param enemigos el mapa de enemigos presentes en la sala y su estado.
     */
    private fun elegirOpcionSala(opcion: Int, jugador: Jugador, enemigos: MutableMap<Enemigo, Boolean>) {
        when (opcion) {
            1-> atacarEnemigos(jugador, enemigos)
            2-> tomarPocion(jugador)
            3-> huirDeSala(jugador)
        }
    }

    /**
     * Ejecuta la elección de opciones por parte del jugador en una sala vacía.
     *
     * @param opcion la opción seleccionada por el jugador.
     * @param jugador el jugador que está explorando la mazmorra.
     */
    private fun elegirOpcionSalaVacia(opcion:Int, jugador: Jugador) {
        when (opcion) {
            1 -> buscarObjeto(jugador)
        }
    }


    /**
     * Ejecuta la acción de tomar una poción durante la exploración de una sala.
     *
     * @param jugador el jugador que está explorando la mazmorra.
     */
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

    /**
     * Comprueba si el jugador tiene pociones en su inventario.
     *
     * @param jugador el jugador cuyo inventario se verificará.
     * @return Boolean retorna true si el jugador tiene pociones, false en caso contrario.
     */
    private fun comprobarPociones(jugador: Jugador): Boolean {
        val pociones = jugador.inventario.inventario.filter { it.key is Item.Pocion }
        return pociones.isNotEmpty()
    }

    /**
     * Comprueba si el jugador tiene un item con la id introducida.
     *
     * @param id el ID del objeto a comprobar.
     * @param jugador el jugador cuyo inventario se verificará.
     * @return El objeto correspondiente al ID si existe en el inventario, o null si no se encuentra.
     */
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

    /**
     * Ejecuta la acción de huir de una sala durante la exploración.
     *
     * @param jugador el jugador que está explorando la mazmorra.
     */
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

    /**
     * Ejecuta la acción de atacar a los enemigos presentes en una sala.
     *
     * @param jugador el jugador que está explorando la mazmorra.
     * @param enemigos el mapa de enemigos presentes en la sala y su estado.
     */
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
            jugador.roboDeVida(estadisticasIniciales)
        }
    }

    /**
     * Finaliza la exploración de la mazmorra diaria.
     */
    private fun finalizarMazmorra() {
        Mensaje.mostrarConColores("\n** ENHORABUENA HAS COMPLETADO LA MAZMORRA DIARIA -- VUELVE MAÑANA PARA COMPLETAR OTRA **".colorAzul())
    }


    /**
     * Ejecuta la acción de buscar un objeto durante la exploración de una sala.
     *
     * @param jugador el jugador que está explorando la mazmorra.
     */
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