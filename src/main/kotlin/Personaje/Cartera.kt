package Personaje

/**
 * Clase que representa la cartera del jugador.
 */
class Cartera {

    var dinero = 100

    /**
     * Aumenta la cantidad de dinero en la cartera.
     *
     * @param cant La cantidad de dinero a ganar.
     */
    fun ganarDinero(cant:Int) {
        dinero += cant
    }

    /**
     * Reduce la cantidad de dinero en la cartera.
     *
     * @param cant La cantidad de dinero a restar.
     */
    fun restarDinero(cant: Int) {
        if (dinero - cant > 0) dinero -= cant
        else dinero = 0
    }

}