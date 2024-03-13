package Interfaces

interface Equipable<T> {
    fun equipar(item: T)
    fun desequipar(item: T)
}