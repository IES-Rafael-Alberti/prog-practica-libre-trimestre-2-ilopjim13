package Mision

import EstadisticaYRango.modificarTodasEstadisticas
import Juego.Mensaje
import Personaje.Jugador
import barraProgreso

/**
 * Clase que representa las misiones diarias del jugador.
 */
open class MisionDIaria {

    companion object {
        val misiones = mutableMapOf(Mision.CORRER.desc to false, Mision.FLEXION.desc to false, Mision.ABDOMINAL.desc to false, Mision.DOMINADA.desc to false)

        /**
         * Reinicia el estado de todas las misiones a "no completada".
         */
        fun reiniciarMisiones() {
            misiones[Mision.CORRER.desc] = false
            misiones[Mision.FLEXION.desc] = false
            misiones[Mision.ABDOMINAL.desc] = false
            misiones[Mision.DOMINADA.desc] = false
        }

        /**
         * Simula correr 10 km y actualiza el progreso de la misión "Correr".
         *
         * @param jugador El jugador que realiza la misión.
         */
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

        /**
         * Simula hacer 100 flexiones y actualiza el progreso de la misión "Flexiones".
         *
         * @param jugador El jugador que realiza la misión.
         */
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

        /**
         * Simula hacer 100 abdominales y actualiza el progreso de la misión "Abdominales".
         *
         * @param jugador El jugador que realiza la misión.
         */
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

        /**
         * Simula hacer 100 dominadas y actualiza el progreso de la misión "Dominadas".
         *
         * @param jugador El jugador que realiza la misión.
         */
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

        /**
         * Marca una misión como completada.
         *
         * @param mision La misión que se ha completado.
         */
        private fun compleatarMision(mision: Mision)  {
            misiones[mision.desc] = true
        }

        /**
         * Verifica si todas las misiones están completadas.
         *
         * @return `true` si todas las misiones están completadas, de lo contrario, `false`.
         */
        fun completas():Boolean {
            val completadas = misiones.all { it.value }
            return completadas
        }

    }

    /**
     * Define diferentes tipos de misiones con sus respectivas descripciones
     */
    enum class Mision(val desc:String) {
        CORRER("Correr"), FLEXION("Flexiones"), ABDOMINAL("Abdominales"), DOMINADA("Dominadas")
    }


}