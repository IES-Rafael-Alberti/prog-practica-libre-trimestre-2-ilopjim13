object Juego {

    var dias = 1
    var juego = false



    fun iniciarJuego() {
        limpiarPantalla()
        reiniciarDia()
        Vista.introduccion()
        val nombre = Vista.pedirNombre()
        val enemigo = GenerarEnemigos.generarEnemigos(1).random()
        val jugador:Jugador = personajeIncial(nombre)
        juego = true
        do {
            Vista.menu(jugador)
        } while (juego)


        println()

        enemigo.forEach {
            SimularCombate.simularCombate(jugador, it.key)
        }

        println()

        TiendaVista.mostrarTienda()

    }




    private fun reiniciarDia() {
        if (dias != 1) dias++
        Tienda.actualizarTiendaDiaria()
        Mazmorra.generarSalas()

    }

}