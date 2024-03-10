
object GenerarEnemigos {

    private var boss = 0

    fun generarEnemigos(cantidad:Int): MutableMap<Enemigo, Boolean> {
        var numRandom:Int
        val enemigos = mutableMapOf<Enemigo, Boolean>()

        repeat(cantidad){

            do {
                numRandom = (1..5).random()
            } while (comprobarSiHayBoss(numRandom))

            when (numRandom) {
                1 -> {
                    enemigos[generarGoblinAleatorio()] = false
                    //listaEnemigos.add(enemigos)
                }
                2 -> {
                    enemigos[generarOgroAleatorio()] = false
                    //listaEnemigos.add(enemigos)
                }
                3 -> {
                    enemigos[generarOrcoAleatorio()] = false
                    //listaEnemigos.add(enemigos)
                }
                4 -> {
                    enemigos[generarCazadorAleatorio()] = false
                    //listaEnemigos.add(enemigos)
                    }
                5 -> {
                    enemigos[generarBossAleatorio()] = false
                    //listaEnemigos.add(enemigos)
                }
            }
        }
        return enemigos
    }


    private fun comprobarSiHayBoss(num :Int) :Boolean {
        if (num == 5 && boss == 5) return true
        else {
            boss = 5
            return false
        }

    }


    private fun generarGoblinAleatorio() :Enemigo {
        val nivel = (1..5).random()
        val estadisticas = comprobarRango(Rango.E)
        return Goblin(nivel, estadisticas, Rango.E)
    }
    private fun generarOgroAleatorio() :Enemigo {
        val nivel = (1..5).random()
        val rango = listOf(Rango.E,Rango.D, Rango.C).random()
        val estadisticas = comprobarRango(rango)
        return Ogro(nivel, estadisticas, rango)
    }
    private fun generarOrcoAleatorio() :Enemigo {
        val nivel = (1..5).random()
        val rango = listOf(Rango.D,Rango.C, Rango.B).random()
        val estadisticas = comprobarRango(rango)
        return Orco(nivel, estadisticas, rango)
    }

    private fun generarCazadorAleatorio() :Enemigo {
        val nivel = (1..5).random()
        val rango = listOf(Rango.C, Rango.E, Rango.D, Rango.B,Rango.A).random()
        val estadisticas = comprobarRango(rango)
        return Cazador(nivel, estadisticas, rango)
    }

    private fun generarBossAleatorio() :Enemigo {
        val nivel = (1..5).random()
        val estadisticas = comprobarRango(Rango.S)
        return Boss(nivel, estadisticas, Rango.S)
    }

    private fun comprobarRango(rango: Rango):Estadisticas {
        return when (rango) {
            Rango.E -> Estadisticas((15..30).random().toDouble(), (1..4).random().toDouble(), (3..6).random().toDouble(), (1..5).random().toDouble())
            Rango.D -> Estadisticas((15..40).random().toDouble(), (2..5).random().toDouble(), (3..7).random().toDouble(), (2..7).random().toDouble())
            Rango.C -> Estadisticas((20..50).random().toDouble(), (3..6).random().toDouble(), (3..8).random().toDouble(), (3..9).random().toDouble())
            Rango.B -> Estadisticas((30..60).random().toDouble(), (4..7).random().toDouble(), (4..9).random().toDouble(), (4..10).random().toDouble())
            Rango.A -> Estadisticas((40..70).random().toDouble(), (5..8).random().toDouble(), (4..10).random().toDouble(), (5..11).random().toDouble())
            Rango.S -> Estadisticas((50..80).random().toDouble(), (6..9).random().toDouble(), (5..11).random().toDouble(), (6..12).random().toDouble())
        }
    }

}