class Mazmorra(val nomrbe:String, rango: Rango) {

    var salas:MutableMap<Int, List<MutableMap<Enemigo, Boolean>>> = mutableMapOf()

    var completada = false

    fun comprobarMazmorraCompletada() {
        val comprobar = salas.any { true }

        if (comprobar) completada = true
    }

    fun generarSalas() {
        salas = mutableMapOf()
        val numSalas = (2..5).random()
        for(i in 1..numSalas) {
            val numEnemigos = (1..3).random()
            val listaEnemigos = GenerarEnemigos.generarEnemigos(numEnemigos)
            salas[i] = listaEnemigos
        }
    }



}