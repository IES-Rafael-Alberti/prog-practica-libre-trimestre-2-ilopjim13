package Interfaces

/**
 * Interfaz que define las acciones relacionadas con objetos equipables.
 * @param T Tipo genérico.
 */
interface Equipable<T> {
    fun equipar(jugador: T)
    fun desequipar(jugador: T)
}