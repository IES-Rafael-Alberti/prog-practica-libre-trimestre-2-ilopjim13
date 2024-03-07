object Juego {

    var dias = 1



    fun iniciarJuego() {
        limpiarPantalla()
        Vista.introduccion()
        val nombre = Vista.pedirNombre()
        val jugador:Jugador = personajeIncial(nombre)

        Vista.menu(jugador)
    }




    fun reiniciarDia() {

        dias++
        Tienda.actualizarTiendaDiaria()

    }

}