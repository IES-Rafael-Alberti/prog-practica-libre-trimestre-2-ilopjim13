class Mazmorra(val nombre:String,val rango: Rango) {

    var salas:MutableMap<Int, MutableMap<Enemigo, Boolean>> = generarSalas()

    var completada = false

    fun comprobarMazmorraCompletada() :Boolean {
        val comprobar = salas.all { it -> it.value.all { it.value } }
        if (comprobar) {
            completada = true
            return true
        }
        else return false
    }

    fun salasTerminadas() {
        salas.forEach { (key, value) ->
            value.forEach {
                value[it.key] = true
            }
        }
    }

    companion object {
        private var salas:MutableMap<Int, MutableMap<Enemigo, Boolean>> = mutableMapOf()
        fun generarSalas() :MutableMap<Int, MutableMap<Enemigo, Boolean>> {
            var sala = 1
            salas = mutableMapOf()
            val numSalas = (2..5).random()
            for(i in 1..numSalas) {
                val numEnemigos = if(numSalas != sala) (0..3).random()
                                  else (1..3).random()
                val listaEnemigos = GenerarEnemigos.generarEnemigos(numEnemigos, numSalas, sala)
                sala++
                salas[i] = listaEnemigos
            }
            return salas
        }
    }

}



