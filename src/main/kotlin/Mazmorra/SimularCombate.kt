package Mazmorra

import Item.CargarItem
import Enemigo.Enemigo
import Juego.Fin
import Interfaces.Combates
import Juego.Mensaje
import Personaje.Jugador
import T
import colorAzul
import colorRojo
import colorVerde
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.Whitespace
import com.github.ajalt.mordant.terminal.Terminal
import com.github.ajalt.mordant.widgets.Panel
import com.github.ajalt.mordant.widgets.Text
import enterContinuar
import redondear
import tiempoEspera
import kotlin.random.Random

/**
 * Objeto que simula un combate entre un jugador y un enemigo.
 */
object SimularCombate {

    private var combate = false
    private val historial: MutableList<String> = mutableListOf()
    private var rondas = 0

    /**
     * Simula un combate entre un jugador y un enemigo.
     *
     * @param jugador El jugador que participa en el combate.
     * @param enemigo El enemigo que participa en el combate.
     */
    fun simularCombate(jugador: Jugador, enemigo: Enemigo) {
        combate = true
        rondas = 1

        val combatientes = listOf(jugador, enemigo)
        do {
            tiempoEspera(800)
            val atacante = combatientesRandom(combatientes)
            val atacado = combatientes.find { it != atacante }

            val danio = atacante.atacar()
            if (atacado != null) registrarCombate(atacado,danio,atacante, jugador, enemigo)

            mostrarRondas(combatientes)
            finalizarCombate(jugador, enemigo)

        } while(combate)

    }

    /**
     * Selecciona aleatoriamente uno de los combatientes para el próximo ataque dependiendo de la agilidad atacará antes uno que otro.
     *
     * @param combatientes Lista de combatientes que participan en el combate.
     * @return El combatiente seleccionado para el próximo ataque.
     */
    private fun combatientesRandom(combatientes: List<Combates<*>>): Combates<*> {
        val jugador = combatientes.filterIsInstance<Jugador>().first()
        val enemigo = combatientes.filterIsInstance<Enemigo>().first()
        val probabilidadJugador = jugador.estadisticas.agilidad / (jugador.estadisticas.agilidad + enemigo.estadisticas.agilidad) // Calcula la probabilidad de que el jugador sea el atacante.
        val suerte = Random.nextDouble()

        return if (suerte < probabilidadJugador) jugador // Si la suerte es menor que la probabilidad del jugador, elige al jugador como atacante.
        else enemigo // De lo contrario, elige al enemigo.

    }

    /**
     * Registra el resultado de un combate entre el atacante y el atacado.
     *
     * @param atacado El combatiente que recibe el daño.
     * @param danio El daño infligido por el atacante.
     * @param atacante El combatiente que realiza el ataque.
     * @param jugador El jugador involucrado en el combate.
     * @param enemigo El enemigo involucrado en el combate.
     */
    private fun registrarCombate(atacado: Combates<*>, danio:Double, atacante: Combates<*>, jugador: Jugador, enemigo: Enemigo) {
        if(atacado.recibirDanio(danio)) {
            when (atacante) {
                is Jugador -> registrarAccion(TextColors.green("El cazador ${jugador.nombre} le ha hecho un daño de ${danio.redondear(2)} al enemigo, vida restante: ${enemigo.estadisticas.vida.redondear(2)}"))
                is Enemigo -> registrarAccion(TextColors.red("El enemigo te ha hecho un daño de ${danio.redondear(2)}, vida restante: ${jugador.estadisticas.vida.redondear(2)}"))
            }
        } else {
            when (atacado) {
                is Jugador -> registrarAccion(TextColors.cyan("${jugador.nombre} ha esquivado el ataque, vida restante: ${jugador.estadisticas.vida.redondear(2)}"))
                is Enemigo -> registrarAccion(TextColors.white("El enemigo ha esquivado el ataque, vida restante: ${enemigo.estadisticas.vida.redondear(2)}"))
            }
        }
    }

    /**
     * Finaliza el combate entre el jugador y el enemigo.
     *
     * @param jugador El jugador involucrado en el combate.
     * @param enemigo El enemigo involucrado en el combate.
     */
    private fun finalizarCombate(jugador: Jugador, enemigo: Enemigo) {
        if (enemigo.comprobarVida()) {
            val experiencia = enemigo.experienciaASumar()
            Mensaje.mostrar("\n** Has acabado con el enemigo enhorabuena **")
            Mensaje.mostrar(">> Experiencia ganada $experiencia PX")
            jugador.experiencia.aumentarExperiencia(jugador, experiencia)
            darBotin(jugador, enemigo)
            combate = false
        } else if (jugador.comprobarVida()) {
            Fin.gameOver()
        }
    }

    /**
     * Otorga botín al jugador después de un combate con un enemigo.
     *
     * @param jugador El jugador involucrado en el combate.
     * @param enemigo El enemigo derrotado.
     */
    private fun darBotin(jugador: Jugador, enemigo: Enemigo) {
        val material = enemigo.soltarMaterial()
        Mensaje.mostrar(">> Has obtenido una $material")
        jugador.inventario.agregarItem(material)
        val probabilidad = (1..100).random()
        if (probabilidad > (50..70).random()) {
            if ((1..10).random() > 5) {
                Mensaje.mostrar("\n** Este enemigo ha soltado un objeto **")
                val botin = CargarItem.itemAleatorioPorRango(enemigo)
                Mensaje.mostrar("Has obtenido un $botin")
                jugador.inventario.agregarItem(botin)
            }
            else {
                val monedas = (40..80).random()
                Mensaje.mostrar("\n** Este enemigo ha soltado $monedas modenas **")
                Jugador.cartera.ganarDinero(monedas)
            }
        } else Mensaje.mostrar("\nEste enemigo no ha soltado ningun objeto...")
        enterContinuar()

    }

    /**
     * Muestra la información de la ronda actual del combate.
     *
     * @param combatientes Lista de combatientes involucrados en el combate.
     */
    private fun mostrarRondas(combatientes: List<Combates<*>>){
        T.println(
            Panel(
                content = textoBatalla(combatientes) ,
                title = Text("** RONDA ${rondas++} **")
            )
        )
    }

    /**
     * Genera un texto descriptivo del estado de los combatientes en la batalla.
     *
     * @param combatientes Lista de combatientes involucrados en el combate.
     * @return Texto con información sobre los combatientes y su vida restante.
     */
    private fun textoBatalla(combatientes:List<Combates<*>>) :Text {
        var texto = ""
        combatientes.forEach {
            when (it) {
                is Jugador -> texto += "-- Cazador: ${it.nombre}, vida restante: ${it.estadisticas.vida.redondear(2)}\n"
                is Enemigo -> texto += "-- Enemigo tipo: ${it.tipoEnemigo}, vida restante: ${it.estadisticas.vida.redondear(2)}\n\n"
            }
        }
        texto += ">> " + historial.last()
        return Text(texto, whitespace = Whitespace.PRE)
    }

    /**
     * Registra una acción en el historial de combate.
     *
     * @param desc Descripción de la acción realizada.
     */
    private fun registrarAccion(desc :String) {
        historial.add(desc)
    }
}