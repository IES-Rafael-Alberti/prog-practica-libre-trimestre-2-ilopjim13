open class Mazmorra(protected val nombre:String, protected val rango: Rango) {

    companion object {
         var salas:MutableMap<Int, List<MutableMap<Enemigo, Boolean>>> = mutableMapOf()

        private var completada = false

        fun comprobarMazmorraCompletada() {
            val comprobar = salas.any { true }

            if (comprobar) completada = true
        }

        fun generarSalas() {
            salas = mutableMapOf()
            val numSalas = (2..5).random()
            for(i in 1..numSalas) {
                val numEnemigos = (0..3).random()
                val listaEnemigos = GenerarEnemigos.generarEnemigos(numEnemigos)
                salas[i] = listaEnemigos
            }
        }
    }



}