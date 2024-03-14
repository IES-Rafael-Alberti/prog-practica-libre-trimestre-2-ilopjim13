package Mazmorra

import Item.CargarItem
import Enemigo.Enemigo
import Juego.Fin
import Interfaces.Combates
import Juego.Mensaje
import Personaje.Jugador
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

object SimularCombate {

    private var combate = false
    private val historial: MutableList<String> = mutableListOf()
    private var rondas = 0

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

    private fun combatientesRandom(combatientes: List<Combates<*>>): Combates<*> {
        val jugador = combatientes.filterIsInstance<Jugador>().first()
        val enemigo = combatientes.filterIsInstance<Enemigo>().first()
        val probabilidadJugador = jugador.estadisticas.agilidad / (jugador.estadisticas.agilidad + enemigo.estadisticas.agilidad)
        val suerte = Random.nextDouble()

        return if (suerte < probabilidadJugador) jugador
        else enemigo

    }

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


    private fun mostrarRondas(combatientes: List<Combates<*>>){
        val t = Terminal()
        t.println(
            Panel(
                content = textoBatalla(combatientes) ,
                title = Text("** RONDA ${rondas++} **")
            )
        )
    }

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

    private fun registrarAccion(desc :String) {
        historial.add(desc)
    }
}