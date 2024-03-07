
object GenerarEnemigos {

    private var boss = 0

    fun generarEnemigos(cantidad:Int): List<MutableMap<Enemigo, Boolean>> {
        var numRandom:Int
        val listaEnemigos = mutableListOf<MutableMap<Enemigo, Boolean>>()
        val enemigos = mutableMapOf<Enemigo, Boolean>()

        repeat(cantidad){
            do {
                numRandom = (1..5).random()
            } while (comprobarSiHayBoss(numRandom))

            when (numRandom) {
                1 -> {
                    enemigos[generarGoblinAleatorio()] = false
                    listaEnemigos.add(enemigos)
                }

                2 -> {
                    enemigos[generarGoblinAleatorio()] = false
                    listaEnemigos.add(enemigos)
                }

                3 -> {
                    enemigos[generarGoblinAleatorio()] = false
                    listaEnemigos.add(enemigos)
                }

                4 -> {
                    enemigos[generarGoblinAleatorio()] = false
                    listaEnemigos.add(enemigos)
                }

                5 -> {
                    enemigos[generarGoblinAleatorio()] = false
                    listaEnemigos.add(enemigos)
                }
            }
        }
        return listaEnemigos
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
        val estadisticas = Estadisticas((20..40).random().toDouble(), (1..4).random().toDouble(), (4..8).random().toDouble(), (1..5).random().toDouble())
        return Goblin(nivel, estadisticas, Rango.E)
    }

}