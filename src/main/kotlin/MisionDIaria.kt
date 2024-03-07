object MisionDIaria {

    val misiones = mutableMapOf(Mision.CORRER.desc to false, Mision.FLEXION.desc to false, Mision.ABDOMINAL.desc to false, Mision.DOMINADA.desc to false)

    fun reiniciarMisiones() {
        misiones[Mision.CORRER.desc] = false
        misiones[Mision.FLEXION.desc] = false
        misiones[Mision.ABDOMINAL.desc] = false
        misiones[Mision.DOMINADA.desc] = false
    }

    fun Correr10km(jugador: Jugador) {

    }

    fun realizar100Flexiones() {

    }
    fun realizar100Abdominales() {

    }

    fun realizar100Dominadas() {

    }

    fun compleatarMision(mision: Mision)  {
        misiones[mision.desc] = true
    }



    enum class Mision(val desc:String) {
        CORRER("Correr"), FLEXION("Flexiones"), ABDOMINAL("Abdominales"), DOMINADA("Dominadas")
    }

}