import com.github.ajalt.mordant.rendering.Whitespace
import com.github.ajalt.mordant.terminal.Terminal
import com.github.ajalt.mordant.widgets.Panel
import com.github.ajalt.mordant.widgets.Text

object SimularCombate {

    private var combate = false
    private val historial: MutableList<String> = mutableListOf()
    private var rondas = 0

    fun simularCombate(jugador: Jugador, enemigo: Enemigo) {
        combate = true
        rondas = 1

        val combatientes = listOf(jugador, enemigo)
        do {
            tiempoEspera(400)
            val atacante = combatientes.random()
            val atacado = combatientes.find { it != atacante }
            val danio = atacante.atacar()

            if (atacado != null) {
                registrarCombate(atacado,danio,atacante, jugador, enemigo)
            }

            mostrarRondas(combatientes)
            finalizarCombate(jugador, enemigo)

        } while(combate)

    }


    private fun registrarCombate(atacado:Combates<*>, danio:Double, atacante:Combates<*>, jugador: Jugador, enemigo:Enemigo) {
        if(atacado.recibirDanio(danio)) {
            when (atacante) {
                is Jugador -> registrarAccion("El cazador ${jugador.nombre} le ha hecho un daño de ${danio.redondear(2)} al enemigo, vida restante: ${enemigo.estadisticas.vida.redondear(2)}")
                is Enemigo -> registrarAccion("El enemigo te ha hecho un daño de ${danio.redondear(2)}, vida restante: ${jugador.estadisticas.vida.redondear(2)}")
            }
        } else {
            when (atacado) {
                is Jugador -> registrarAccion("${jugador.nombre} ha esquivado el ataque, vida restante: ${jugador.estadisticas.vida.redondear(2)}")
                is Enemigo -> registrarAccion("El enemigo ha esquivado el ataque, vida restante: ${enemigo.estadisticas.vida.redondear(2)}")
            }
        }
    }

    private fun finalizarCombate(jugador: Jugador, enemigo: Enemigo) {
        if (enemigo.comprobarVida()) {
            val experiencia = experienciaASumar(enemigo)
            println("\n** Has acabado con el enemigo enorabuena **")
            println(">> Experiencia ganada $experiencia PX")
            jugador.experiencia.aumentarExperiencia(jugador, experiencia)
            darBotin(jugador, enemigo)
            combate = false
        } else if (jugador.comprobarVida()) {
            Fin.gameOver()
        }
    }

    private fun darBotin(jugador: Jugador, enemigo: Enemigo) {
        val probabilidad = (1..100).random()
        if (probabilidad > (50..70).random()) {
            if ((1..10).random() > 5) {
                println("** Este enemigo ha soltado un objeto **")
                val botin = CargarItem.itemAleatorioPorRango(enemigo)
                println("Has obtenido un $botin")
                jugador.inventario.agregarItem(botin)
            }
            else {
                val monedas = (40..80).random()
                println("** Este enemigo ha soltado un par de modenas **")
                Jugador.cartera.ganarDinero(monedas)
            }
        } else println("Este enemigo no ha soltado nada...")
        enterContinuar()



    }


    private fun experienciaASumar(enemigo: Enemigo) :Int {
        return when (enemigo.tipoEnemigo) {
            TipoEnemigo.GOBLIN -> 25
            TipoEnemigo.OGRO -> (25..50).random()
            TipoEnemigo.ORCO -> (50..80).random()
            TipoEnemigo.CAZADOR -> (80..100).random()
            TipoEnemigo.BOSS -> (100..150).random()
        }
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