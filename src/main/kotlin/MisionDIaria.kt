import com.github.ajalt.mordant.terminal.Terminal
import com.github.ajalt.mordant.animation.ProgressAnimation
import com.github.ajalt.mordant.animation.progressAnimation


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

            println("** Mision completada **")
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

            println("\n** Mision completada **")
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

            println("** Mision completada **")
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

            println("** Mision completada **")
        }

        private fun compleatarMision(mision: Mision)  {
            misiones[mision.desc] = true
        }

    }

    enum class Mision(val desc:String) {
        CORRER("Correr"), FLEXION("Flexiones"), ABDOMINAL("Abdominales"), DOMINADA("Dominadas")
    }




}