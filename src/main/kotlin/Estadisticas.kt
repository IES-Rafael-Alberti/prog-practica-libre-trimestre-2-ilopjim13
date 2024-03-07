class Estadisticas(vida:Double, fuerza:Double, agilidad:Double, resistencia:Double) {

    var vida = vida
        set(value) {
            field = value.redondear(2)
        }

    var fuerza = fuerza
        set(value) {
            field = value.redondear(2)
        }
    var agilidad = agilidad
        set(value) {
            field = value.redondear(2)
        }
    var resistencia = resistencia
        set(value) {
            field = value.redondear(2)
        }

    fun aumentarVida(jugador: Jugador,cant:Double) {

    }

    fun aumentarFuerza(jugador: Jugador,cant:Double) {

    }

    fun aumentarAgilidad(jugador: Jugador,cant:Double) {

    }

    fun aumentarResistencia(jugador: Jugador,cant:Double) {

    }


    fun restarVida(jugador: Jugador,cant:Double) {
        if (jugador.estadisticas.vida - cant > 0) jugador.estadisticas.vida -= cant
        else jugador.estadisticas.vida = 0.0
    }
    fun restarVidaEnemigo(jugador: Enemigo,cant:Double) {
        if (jugador.estadisticas.vida - cant > 0) jugador.estadisticas.vida -= cant
        else jugador.estadisticas.vida = 0.0
    }

    fun restarFuerza(jugador: Jugador,cant:Double) {

    }

    fun restarAgilidad(jugador: Jugador,cant:Double) {

    }

    fun restarResistencia(jugador: Jugador,cant:Double) {

    }

}