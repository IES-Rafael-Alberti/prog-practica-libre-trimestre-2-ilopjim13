object Juego {

    var dias = 0
    var juego = false



    fun iniciarJuego() {
        limpiarPantalla()
        Tienda.agregarItemsATienda(CargarItem.todosLosItems())
        reiniciarDia()
        Vista.introduccion()


        val nombre = Vista.pedirNombre()

        val jugador:Jugador = personajeIncial(nombre)
        juego = true
        do {
            Vista.menu(jugador)
        } while (juego)

    }




    fun reiniciarDia() {
        dias++
        Tienda.actualizarTiendaDiaria()
        Mazmorra.generarSalas()
        MisionDIaria.reiniciarMisiones()
    }

}