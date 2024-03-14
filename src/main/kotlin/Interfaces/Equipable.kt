package Interfaces

interface Equipable<T> {
    fun equipar(jugador: T)
    fun desequipar(jugador: T)
}