fun <T> modificarEstadisticas(jugador: T, cant: Double, estadistica:String, operacion: (Double,Double) -> Double) {
    when (jugador) {
        is Jugador -> {
            when (estadistica) {
                "vida" -> jugador.estadisticas.vida = maxOf(operacion(jugador.estadisticas.vida, cant), 0.0)
                "fuerza" -> jugador.estadisticas.fuerza = maxOf(operacion(jugador.estadisticas.fuerza, cant), 0.0)
                "agilidad" -> jugador.estadisticas.agilidad = maxOf(operacion(jugador.estadisticas.agilidad, cant), 0.0)
                "resistencia" -> jugador.estadisticas.resistencia = maxOf(operacion(jugador.estadisticas.resistencia, cant), 0.0)
            }
        }
        is Enemigo -> {
            when (estadistica) {
                "vida" -> jugador.estadisticas.vida = maxOf(operacion(jugador.estadisticas.vida, cant), 0.0)
                "fuerza" -> jugador.estadisticas.fuerza = maxOf(operacion(jugador.estadisticas.fuerza, cant), 0.0)
                "agilidad" -> jugador.estadisticas.agilidad = maxOf(operacion(jugador.estadisticas.agilidad, cant), 0.0)
                "resistencia" -> jugador.estadisticas.resistencia = maxOf(operacion(jugador.estadisticas.resistencia, cant), 0.0)
            }
        }
    }
}

fun <T> modificarTodasEstadisticas(jugador: T, cant: Double, operacion: (Double,Double) -> Double) {
    when (jugador) {
        is Jugador -> {
            jugador.estadisticas.vida = maxOf(operacion(jugador.estadisticas.vida, cant), 0.0)
            jugador.estadisticas.fuerza = maxOf(operacion(jugador.estadisticas.fuerza, cant), 0.0)
            jugador.estadisticas.agilidad = maxOf(operacion(jugador.estadisticas.agilidad, cant), 0.0)
            jugador.estadisticas.resistencia = maxOf(operacion(jugador.estadisticas.resistencia, cant), 0.0)

        }
        is Enemigo -> {
            jugador.estadisticas.vida = maxOf(operacion(jugador.estadisticas.vida, cant), 0.0)
            jugador.estadisticas.fuerza = maxOf(operacion(jugador.estadisticas.fuerza, cant), 0.0)
            jugador.estadisticas.agilidad = maxOf(operacion(jugador.estadisticas.agilidad, cant), 0.0)
            jugador.estadisticas.resistencia = maxOf(operacion(jugador.estadisticas.resistencia, cant), 0.0)
        }
    }
}
