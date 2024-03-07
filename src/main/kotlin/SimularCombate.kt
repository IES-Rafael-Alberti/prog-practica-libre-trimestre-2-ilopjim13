object SimularCombate {

    private var combate = false
    private val historial: MutableList<String> = mutableListOf()
    private var rondas = 1

    fun simularCombate(jugador: Jugador, enemigo: Enemigo) {
        combate = true
        val combatientes = listOf(jugador, enemigo)
        do {
            val atacante = combatientes.random()
            val atacado = combatientes.find { it != atacante }
            val danio = atacante.atacar()

            if(atacado?.recibirDanio(danio) == true) {
                when (atacante) {
                    is Jugador -> registrarAccion("El cazador ${jugador.nombre} le ha hecho un daño de $danio al enemigo, vida restante: ${enemigo.estadisticas.vida}")
                    is Enemigo -> registrarAccion("El enemigo te ha hecho un daño de $danio, vida restante: ${jugador.estadisticas.vida}")
                }
            } else {
                when (atacado) {
                    is Jugador -> registrarAccion("${jugador.nombre} ha esquivado el ataque, vida restante: ${jugador.estadisticas.vida}")
                    is Enemigo -> registrarAccion("El enemigo ha esquivado el ataque, vida restante: ${enemigo.estadisticas.vida}")
                }
            }

            mostrarRondas(combatientes)
            finalizarCombate(jugador, enemigo)

        } while(combate)

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
        println("** Ronda ${rondas++} **\n")
        combatientes.forEach {
            when (it) {
                is Jugador -> println("Cazador: ${it.nombre}, vida restante: ${it.estadisticas.vida}")
                is Enemigo -> println("Enemigo tipo: ${it.tipoEnemigo}, vida restante: ${it.estadisticas.vida}")
            }
        }

    }

    private fun registrarAccion(desc :String) {
        historial.add(desc)
    }
}