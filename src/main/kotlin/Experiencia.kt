class Experiencia {
    var limitePorNivel = 100
    var experienciaActual = 0


    fun aumentarExperiencia(jugador: Jugador, cant:Int) {
        val experiencia = jugador.experiencia.experienciaActual
        val limite = jugador.experiencia.limitePorNivel

        if (experiencia + cant >= limite) {
            jugador.experiencia.experienciaActual = limite
            jugador.subirNivel()
        }
        else {
            jugador.experiencia.experienciaActual += cant
        }
    }

}