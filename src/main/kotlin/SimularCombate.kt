import com.github.ajalt.mordant.rendering.Whitespace
import com.github.ajalt.mordant.terminal.Terminal
import com.github.ajalt.mordant.widgets.Panel
import com.github.ajalt.mordant.widgets.Text

object SimularCombate {

    private var combate = false
    private val historial: MutableList<String> = mutableListOf()
    private var rondas = 1

    fun simularCombate(jugador: Jugador, enemigo: Enemigo) {
        combate = true

        val combatientes = listOf(jugador, enemigo)
        do {
            Thread.sleep(200)
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
                is Jugador -> registrarAccion("El cazador ${jugador.nombre} le ha hecho un daño de ${danio.redondear(2)} al enemigo, vida restante: ${enemigo.estadisticas.vida}")
                is Enemigo -> registrarAccion("El enemigo te ha hecho un daño de ${danio.redondear(2)}, vida restante: ${jugador.estadisticas.vida}")
            }
        } else {
            when (atacado) {
                is Jugador -> registrarAccion("${jugador.nombre} ha esquivado el ataque, vida restante: ${jugador.estadisticas.vida}")
                is Enemigo -> registrarAccion("El enemigo ha esquivado el ataque, vida restante: ${enemigo.estadisticas.vida}")
            }
        }
    }

    private fun finalizarCombate(jugador: Jugador, enemigo: Enemigo) {
        if (enemigo.comprobarVida()) {
            registrarAccion("** Has acabado con el enemigo enorabuena **")
            jugador.experiencia.aumentarExperiencia(jugador, experienciaASumar(enemigo))
            combate = false
        } else if (jugador.comprobarVida()) {
            Fin.gameOver()
        }
    }


    private fun experienciaASumar(enemigo: Enemigo) :Int {
        return when (enemigo.tipoEnemigo) {
            TipoEnemigo.GOBLIN -> 25
            TipoEnemigo.OGRO -> 50
            TipoEnemigo.ORCO -> 75
            TipoEnemigo.CAZADOR -> 100
            TipoEnemigo.BOSS -> 150
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
                is Jugador -> texto += "-- Cazador: ${it.nombre}, vida restante: ${it.estadisticas.vida}\n"
                is Enemigo -> texto += "-- Enemigo tipo: ${it.tipoEnemigo}, vida restante: ${it.estadisticas.vida}\n\n"
            }
        }
        texto += ">> " + historial.last()
        return Text(texto, whitespace = Whitespace.PRE)
    }

    private fun registrarAccion(desc :String) {
        historial.add(desc)
    }
}