open class MisionDIaria {

    companion object {
        val misiones = mutableMapOf(Mision.CORRER.desc to false, Mision.FLEXION.desc to false, Mision.ABDOMINAL.desc to false, Mision.DOMINADA.desc to false)

        fun reiniciarMisiones() {
            misiones[Mision.CORRER.desc] = false
            misiones[Mision.FLEXION.desc] = false
            misiones[Mision.ABDOMINAL.desc] = false
            misiones[Mision.DOMINADA.desc] = false
        }

        fun correr10km(jugador: Jugador) {

            val progreso = barraProgreso("Corriendo...")
            progreso.start()
            for (i in (1..10)) {
                progreso.update(i.toLong(),10)
                Thread.sleep(200)
            }
            progreso.stop()
            compleatarMision(Mision.CORRER)
            modificarTodasEstadisticas(jugador, 0.25) {it,cant -> it + cant}

            Mensaje.mostrar("\n** Mision completada - Estadisticas aumentadas - **")
        }

        fun realizar100Flexiones(jugador: Jugador) {

            val progreso = barraProgreso("Haciendo flexiones...")
            progreso.start()
            for (i in (1..10)) {
                progreso.update((i*10).toLong(),100)
                Thread.sleep(200)
            }
            progreso.stop()
            compleatarMision(Mision.FLEXION)
            modificarTodasEstadisticas(jugador, 0.25) {it,cant -> it + cant}

            Mensaje.mostrar("\n** Mision completada - Estadisticas aumentadas - **")
        }
        fun realizar100Abdominales(jugador: Jugador) {

            val progreso = barraProgreso("Haciendo abdominales...")
            progreso.start()
            for (i in (1..10)) {
                progreso.update((i*10).toLong(),100)
                Thread.sleep(200)
            }
            progreso.stop()
            compleatarMision(Mision.ABDOMINAL)
            modificarTodasEstadisticas(jugador, 0.25) {it,cant -> it + cant}

            Mensaje.mostrar("\n** Mision completada - Estadisticas aumentadas - **")
        }

        fun realizar100Dominadas(jugador: Jugador) {

            val progreso = barraProgreso("Haciendo dominadas...")
            progreso.start()
            for (i in (1..10)) {
                progreso.update((i*10).toLong(),100)
                Thread.sleep(200)
            }
            progreso.stop()
            compleatarMision(Mision.DOMINADA)
            modificarTodasEstadisticas(jugador, 0.25) {it,cant -> it + cant}

            Mensaje.mostrar("\n** Mision completada - Estadisticas aumentadas - **")
        }

        private fun compleatarMision(mision: Mision)  {
            misiones[mision.desc] = true
        }

        fun completas():Boolean {
            val completadas = misiones.all { it.value }
            return completadas
        }

    }

    enum class Mision(val desc:String) {
        CORRER("Correr"), FLEXION("Flexiones"), ABDOMINAL("Abdominales"), DOMINADA("Dominadas")
    }




}