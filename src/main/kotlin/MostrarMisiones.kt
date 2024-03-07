object MostrarMisiones {

    fun misionesPorHacer() {
        val restantes = MisionDIaria.misiones.filter { !it.value }
        if (restantes.isNotEmpty()) println("Quedan por hacer: ${restantes.keys.joinToString(", ")}")
        else println("Todas las misiones diarias completadas, espere hasta mañana.")
    }

    fun mostrarMisiones() {
        println("1 - Correr 100Km ${if (MisionDIaria.misiones[MisionDIaria.Mision.CORRER.desc] == true) "10/10" else "0/10"}")
        println("2 - Hacer 100 flexiones ${if (MisionDIaria.misiones[MisionDIaria.Mision.FLEXION.desc] == true) "100/100" else "0/100"}")
        println("3 - Hacer 100 abdominales ${if (MisionDIaria.misiones[MisionDIaria.Mision.ABDOMINAL.desc] == true) "100/100" else "0/100"}")
        println("4 - Hacer 100 dominadas ${if (MisionDIaria.misiones[MisionDIaria.Mision.DOMINADA.desc] == true) "100/100" else "0/100"}")
        println("5 - Volver")

        val opcion = Vista.pedirOpcion(4)


    }

}