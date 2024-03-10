class Mazmorra(val nombre:String,val rango: Rango) {

    var salas:MutableMap<Int, MutableMap<Enemigo, Boolean>> = generarSalas()

    private var completada = false

    fun comprobarMazmorraCompletada() {
        val comprobar = salas.any { true }
        if (comprobar) completada = true
    }
    companion object {
        private var salas:MutableMap<Int, MutableMap<Enemigo, Boolean>> = mutableMapOf()
        fun generarSalas() :MutableMap<Int, MutableMap<Enemigo, Boolean>> {
            salas = mutableMapOf()
            val numSalas = (2..5).random()
            for(i in 1..numSalas) {
                val numEnemigos = (0..3).random()
                val listaEnemigos = GenerarEnemigos.generarEnemigos(numEnemigos)
                salas[i] = listaEnemigos
            }
            return salas
        }
    }

}



