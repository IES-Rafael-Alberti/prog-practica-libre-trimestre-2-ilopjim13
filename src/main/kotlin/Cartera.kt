class Cartera {

    var dinero = 100

    fun ganarDinero(cant:Int) {
        dinero += cant
    }

    fun restarDinero(cant: Int) {
        if (dinero - cant > 0) dinero -= cant
        else dinero = 0
    }

}