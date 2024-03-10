import java.io.File

object CargarItem {

    lateinit var items:List<Item>

    fun itemAleatorio():Item {
        return items.random()
    }

    fun itemAleatorioPorRango(enemigo: Enemigo):Item {
        return when (enemigo.rango) {
            Rango.E -> items.filter { it.rango == Rango.E }.random()
            Rango.D -> items.filter { it.rango == listOf(Rango.E,Rango.D).random() }.random()
            Rango.C -> items.filter { it.rango == listOf(Rango.E,Rango.D, Rango.C).random() }.random()
            Rango.B -> items.filter { it.rango == listOf(Rango.E,Rango.D, Rango.C, Rango.B).random() }.random()
            Rango.A -> items.filter { it.rango == listOf(Rango.E,Rango.D, Rango.C, Rango.B, Rango.A).random() }.random()
            Rango.S -> items.filter { it.rango == listOf(Rango.E,Rango.D, Rango.C, Rango.B, Rango.A, Rango.S).random() }.random()
        }
    }



    fun todosLosItems():List<Item> {
        items = mutableListOf(Item.Pocion("Pocion de curación", Rango.E, 50, Estadisticas(25.0, 0.0,0.0,0.0), TipoPociones.VIDA),
            Item.Pocion("Poción de salud menor", Rango.E, 10, Estadisticas(15.0, 0.0, 0.0,0.0), TipoPociones.VIDA),
            Item.Pocion("Poción de salud mayor", Rango.D, 20, Estadisticas(30.0, 0.0,0.0,0.0), TipoPociones.VIDA),
            Item.Pocion("Poción de fuerza", Rango.E, 15, Estadisticas(0.0, 1.5,0.0,0.0), TipoPociones.FUERZA),
            Item.Arma("Espada de acero", Rango.C, 100, Estadisticas(0.0, 2.2,-1.0,0.0), TipoEquipable.PESADA),
            Item.Arma("Daga de plata", Rango.E, 50, Estadisticas(0.0, 1.1,0.5,0.0), TipoEquipable.LIGERA),
            Item.Armadura("Armadura de placas", Rango.B, 100, Estadisticas(20.0, 0.0, -2.2, 1.5), TipoEquipable.PESADA),
            Item.Armadura("Armadura de cuero", Rango.D, 80, Estadisticas(10.0, 0.0, -0.7, 0.6), TipoEquipable.LIGERA),
            Item.Pocion("Poción de resistencia", Rango.E, 25, Estadisticas(0.0, 0.0, 0.0, 0.9), TipoPociones.RESISTENCIA),
            Item.Arma("Hacha de guerra", Rango.C, 120, Estadisticas(0.0, 1.6, -0.3, 0.0 ), TipoEquipable.PESADA),
            Item.Arma("Arco largo", Rango.D, 70, Estadisticas(0.0, 0.8, 0.0, 0.0 ), TipoEquipable.LIGERA),
            Item.Armadura("Casco de hierro", Rango.D, 60, Estadisticas(15.0, 0.0, -0.8, 0.9), TipoEquipable.PESADA),
            Item.Armadura("Botas de cuero", Rango.E, 30, Estadisticas(5.0, 0.0, 0.0, 0.2), TipoEquipable.LIGERA),
            Item.Pocion("Poción de velocidad", Rango.E, 20, Estadisticas(0.0, 0.0, 0.5, 0.0), TipoPociones.AGILIDAD),
            Item.Arma("Espada corta", Rango.D, 70, Estadisticas(.0, 0.9,0.0,0.0), TipoEquipable.LIGERA),
            Item.Armadura("Escudo de madera", Rango.E, 40, Estadisticas(0.0, 0.0, 0.0, 0.6), TipoEquipable.PESADA),
            Item.Arma("Espada de fuego", Rango.C, 120, Estadisticas(0.0, 1.8,0.4, 0.0), TipoEquipable.PESADA),
            Item.Armadura("Armadura de escamas", Rango.A, 220, Estadisticas(40.0, 0.0, -1.0, 2.3), TipoEquipable.PESADA),
            Item.Pocion("Poción de invisibilidad", Rango.E, 40, Estadisticas(0.0, 0.0, 0.7,0.0), TipoPociones.AGILIDAD),
            Item.Arma("Hacha de doble filo", Rango.B, 150, Estadisticas(0.0, 2.6, -1.8,0.0), TipoEquipable.PESADA),
            Item.Armadura("Armadura de seda", Rango.D, 100, Estadisticas(30.0, 0.0,1.7, 1.5), TipoEquipable.LIGERA),
            Item.Pocion("Poción de veneno", Rango.E, 15, Estadisticas(-20.0, 0.0,0.0,0.0), TipoPociones.VIDA),
            Item.Arma("Ballesta de repetición", Rango.C, 180, Estadisticas(0.0, 1.3,0.0,0.0), TipoEquipable.LIGERA),
            Item.Armadura("Casco encantado", Rango.A, 80, Estadisticas(20.0, 0.0,0.0, 0.9), TipoEquipable.PESADA),
            Item.Armadura("Botas de velocidad", Rango.B, 50, Estadisticas(15.0, 0.0, 2.7,0.3), TipoEquipable.LIGERA),
            Item.Pocion("Poción de sabiduría", Rango.E, 30, Estadisticas(0.0, 0.0, 0.2, 0.0), TipoPociones.AGILIDAD),
            Item.Arma("Espada de luz", Rango.S, 200, Estadisticas(0.0, 3.0, 0.0, 0.0), TipoEquipable.PESADA),
            Item.Armadura("Armadura de cuero reforzado", Rango.C, 120, Estadisticas(35.0, 0.0, -1.2, 1.7), TipoEquipable.LIGERA),
            Item.Pocion("Poción de agilidad", Rango.E, 20, Estadisticas(0.0, 0.0, 1.0, 0.0), TipoPociones.AGILIDAD),
        )
        return items
    }



//    Elixir de Vida
//    Brebaje de Fuerza
//    Poción de Invisibilidad
//    Tónico de Velocidad
//    Mezcla de Sabiduría
//    Armas:
//
//    Espada del Amanecer
//    Arco de la Luna Creciente
//    Martillo de los Titanes
//    Lanza de la Tempestad
//    Daga del Viento Silencioso
//    Armaduras:
//
//    Coraza del Dragón
//    Escudo de la Montaña
//    Casco del Guerrero Eterno
//    Guanteletes de la Fuerza Titánica
//    Botas de la Sombra Veloz


}