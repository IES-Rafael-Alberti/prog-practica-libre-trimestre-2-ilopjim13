[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/NBVXLiSy)
# Actividad: Desarrollo de Proyecto Software en Kotlin

**ID actividad:** 2324_PRO_u4u5u6_libre

**Agrupamiento de la actividad**: Individual 

---

### Descripción:

La actividad consiste en el desarrollo de un proyecto software en Kotlin, permitiendo al estudiante elegir la temática. Este proyecto debe demostrar la comprensión y aplicación de los conceptos de programación orientada a objetos (POO), incluyendo la definición y uso de clases, herencia, interfaces, genericos, principios SOLID y el uso de librerías externas.

**Objetivo:**

- Demostrar comprensión de los fundamentos de POO mediante la instanciación y uso de objetos.
- Aplicar conceptos avanzados de POO como herencia, clases abstractas, e interfaces.
- Crear y usar clases que hagan uso de genéricos. 
- Aplicar principios SOLID.
- Integrar y utilizar librerías de clases externas para extender la funcionalidad del proyecto.
- Documentar y presentar el código de manera clara y comprensible.

**Trabajo a realizar:**

1. **Selección de la Temática:** Elige un tema de tu interés que pueda ser abordado mediante una aplicación software. Esto podría ser desde una aplicación de gestión para una pequeña empresa, una herramienta para ayudar en la educación, hasta un juego simple. Define claramente el problema que tu aplicación pretende resolver.

2. **Planificación:** Documenta brevemente cómo tu aplicación solucionará el problema seleccionado, incluyendo las funcionalidades principales que desarrollarás.

3. **Desarrollo:**
   - **Instancia de Objetos:** Tu aplicación debe crear y utilizar objetos, demostrando tu comprensión de la instanciación y el uso de constructores, métodos, y propiedades.
   - **Métodos Estáticos:** Define y utiliza al menos un método estático, explicando por qué es necesario en tu aplicación.
   - **Uso de IDE:** Desarrolla tu proyecto utilizando un IDE, aprovechando sus herramientas para escribir, compilar, y probar tu código.
   - **Definición de Clases:** Crea clases personalizadas con sus respectivas propiedades, métodos, y constructores.
   - **Clases con genéricos:** Define y utiliza al menos una clase que haga uso de genéricos en tu aplicación.
   - **Herencia y Polimorfismo:** Implementa herencia y/o interfaces en tu proyecto para demostrar la reutilización de código y la flexibilidad de tu diseño.  **Usa los principios SOLID:** Ten presente durante el desarrollo los principios SOLID y úsalos durante el diseño para mejorar tu aplicación.
   - **Librerías de Clases:** Integra y utiliza una o más librerías externas que enriquezcan la funcionalidad de tu aplicación.
   - **Documentación:** Comenta tu código de manera efectiva, facilitando su comprensión y mantenimiento.

4. **Prueba y Depuración:** Realiza pruebas para asegurarte de que tu aplicación funciona como se espera y depura cualquier error encontrado.
5. **Contesta a las preguntas** ver el punto **Preguntas para la Evaluación**

### Recursos

- Apuntes dados en clase sobre programación orientada a objetos, Kotlin, uso de IDEs, y manejo de librerías.
- Recursos vistos en clase, incluyendo ejemplos de código, documentación de Kotlin, y guías de uso de librerías.

### Evaluación y calificación

**RA y CE evaluados**: Resultados de Aprendizaje 2, 4, 6, 7 y Criterios de Evaluación asociados.

**Conlleva presentación**: SI

**Rubrica**: Mas adelante se mostrará la rubrica.

### Entrega

> **La entrega tiene que cumplir las condiciones de entrega para poder ser calificada. En caso de no cumplirlas podría calificarse como no entregada.**
>
- **Conlleva la entrega de URL a repositorio:** El contenido se entregará en un repositorio GitHub. 
- **Respuestas a las preguntas:** Deben contestarse en este fichero, README.md


# Preguntas para la Evaluación

Este conjunto de preguntas está diseñado para ayudarte a reflexionar sobre cómo has aplicado los criterios de evaluación en tu proyecto. Al responderlas, **asegúrate de hacer referencia y enlazar al código relevante** en tu `README.md`, facilitando así la evaluación de tu trabajo.

#### **Criterio global 1: Instancia objetos y hacer uso de ellos**
- **(2.a, 2.b, 2.c, 2.d, 2.f, 2.h, 4.f, 4.a)**: Describe cómo has instanciado y utilizado objetos en tu proyecto. ¿Cómo has aplicado los constructores y pasado parámetros a los métodos? Proporciona ejemplos específicos de tu código.


- Para este proyecto he instanaciado las clases de jugador que es el que se va a mover entre todas las demás clases, la clase de mazmorras para que se generen de manera aleatoria diferentes mazmorras cada vez que se pase de dia, la clase enemigos la instancio de forma aleatoria para sacara enemigos de diferentes tipos dentro de la mazmorras, y los items los instancio de manera manual pasandole sus propiedades necesarias y las guardo en una lista en un objeto desde el cual puede mandar diferentes objetos o todos.


        val jugador: Jugador = personajeIncial(nombre)

        private var mazmorra = GestionMazmorra.generarMazmorraRandom()


#### **Criterio global 2: Crear y llamar métodos estáticos**
- **(4.i)**: ¿Has definido algún método/propiedad estático en tu proyecto? ¿Cuál era el objetivo y por qué consideraste que debía ser estático en lugar de un método/propiedad de instancia?
- **(2.e)**: ¿En qué parte del código se llama a un método estático o se utiliza la propiedad estática?


- Las funciones del objeto Mensaje para mostrar los mensajes que aparezeran por pantalla desde esas funciones, debe ser algo estático ya que se deberían poder llamar desde cualquier lugar sin tener que estar instanciando Mensaje en todos lados por eso lo he creado como objeto para que no tenga ni que instancairse. Las llamó cada vez que tengo que imprimir algo por pantalla

object Mensaje {

    /**
     * Muestra un mensaje en la consola.
     *
     * @param desc El mensaje a mostrar.
     */
    fun mostrar(desc:String) {
        println(desc)
    }

    /**
     * Muestra un mensaje en la consola dejando en la misma linea la siguiente interacción.
     *
     * @param desc El mensaje a mostrar.
     */
    fun mostrarEnLinea(desc:String) {
        print(desc)
    }

#### **Criterio global 3: Uso de entornos**
- **(2.i)**: ¿Cómo utilizaste el IDE para el desarrollo de tu proyecto? Describe el proceso de creación, compilación, y prueba de tu programa.


- Para este proyecto he utilizado IntelliJ creando el proyecto utilizando gradle, con este IDE he podido ver errores o mejoras más eficientes en mi código de manera clara, también utilizando su sistema de Debug he podido ver errores en las variables y detectar fallos inesperados en el código de sintaxis. Además gracias a este IDE he podido realizar los commits de manera más fácil sin tener que utilizar la línea de comandos de git.

#### **Criterio global 4: Definir clases y su contenido**
- **(4.b, 4.c, 4.d, 4.g)**: Explica sobre un ejemplo de tu código, cómo definiste las clases en tu proyecto, es decir como identificaste las de propiedades, métodos y constructores y modificadores del control de acceso a métodos y propiedades, para representar al objeto del mundo real. ¿Cómo contribuyen estas clases a la solución del problema que tu aplicación aborda?

      class Jugador(
      val nombre: String,
      var nivel: Int
      ) : Combates<Jugador>, Comprar, Consumible, VenderJugador {
      
          var rango = Rango.E  // El rango no lo eliges tu, se determina por tus estadisticas
          val experiencia: Experiencia = Experiencia()
          var estadisticas: Estadisticas = Estadisticas(100.0, 10.0, 8.0, 12.0)
          val inventario: Inventario = Inventario()
          var nivelExperiencia = 0
          val equipado = mutableMapOf("arma" to false, "armadura" to false)
          val equipo = mutableListOf<Equipable<*>>()
          private val pociones = mutableListOf<Item.Pocion>()

- Mi idea principal era la de realizar un RPG en el que subes de nivel a tu personaje mientras vas realizando misiones diarias y completas mazmorras por lo que he tenido que definir una estructura de clases donde tenemos la clase Jugador que va a ser la clase del jugador principal que contiene todo lo necesario para crear un jugador con inventario, estadisticas y una cartera, una clase mazmorras que se van a explorar donde se generan los enemigos y una clase misiones donde se ejecutan las misiones todo conectado por una gestion de vista donde se muestra el menu principal y llama a las funciones necesarias.

#### **Criterio global 5: Herencia y uso de clases abstractas e interfaces**
- **(4.h, 4.j, 7.a, 7.b, 7.c)**: Describe sobre tu código cómo has implementado la herencia o utilizado interfaces en tu proyecto. ¿Por qué elegiste este enfoque y cómo beneficia a la estructura de tu aplicación? ¿De qué manera has utilizado los principios SOLID para mejorar el diseño de tu proyecto? ¿Mostrando tu código, contesta a qué principios has utilizado y qué beneficio has obtenido?


- Las jerarquías de clases que he implementado han sido una abstract class de enemigo en la que los enemigos tienen que implementar las propiedades de enemigo y pueden acceder a las funciones de enemigo, también tengo una sealed class item para los items ya que dentro de estos items solo van a estar esos objetos, permitiendo así que puedan heredar los parametros de item, y tambien tengo una jerarquía con Combates ya que permite que si hay más personajes que pueden combatir en un furuo puedan heredar de esa interfaz y tener los metodos de combate.
- Los principios SOLID: El Principio de la Responsabnilidad Única lo he intentado cumplir haciendo que cada clase esté destinada a lo que debe hacer sin meter cosas que no debería hacer dentro de ellas, como por ejemplo he creado una clase Mensaje desde donde se imprime todo lo que aparece por pantalla en lugar de poner println por medio de las funciones, El principio de abierto y cerrado he creado una interfaz Compra que solo la tiene el jugador pero en el caso de querer añadirle otra clase que pueda comprar se podría añadir sin tener que modificar el codigo. Para el principio de Liskob creo que no lo he cumplido para ello los enemigos deberían tener que sobreescribir alguna función de Enemigo para o tener que depender del padre. Principio de Segregación de la Interfaz para cumplir este SOLID he creado una interfaz ventaTienda y otra VentaJugador ambos venden pero la tienda puede vender todo tipo de objetos mientras que el jugador solo piedras. Principio de Inversión de Dependencias para ello he creado varias interfaces y clases padres para que dependan de una superior como por ejemplo la interfaz Combates o la Interfaz Equipable.


      interface VentaTienda {
         fun venta(jugador: Jugador, item: Item)
      }
      interface Equipable<T> {
         fun equipar(jugador: T)
         fun desequipar(jugador: T)
      }
      interface Comprar {
         fun comprarObjeto(item: Item)
      }

#### **Criterio global 6: Diseño de jerarquía de clases**
- **(7.d, 7.e, 7.f, 7.g)**: Presenta la jerarquía de clases que diseñaste. ¿Cómo probaste y depuraste esta jerarquía para asegurar su correcto funcionamiento? ¿Qué tipo de herencia has utilizado: Especificación, Especialización, Extensión, Construcción?

      abstract class Enemigo(open val tipoEnemigo: TipoEnemigo, private val nivel:Int, val estadisticas: Estadisticas, val rango: Rango) : Combates<Enemigo> {
      
          /**
           * Comprueba si el enemigo está sin vida.
           *
           * @return Boolean true si el enemigo está sin vida y false en caso contrario.
           */
          override fun comprobarVida():Boolean {
              return estadisticas.vida == 0.0
          }
        .........
      }

      class Cazador(nivel :Int, estadisticas: Estadisticas, rango: Rango) : Enemigo(TipoEnemigo.CAZADOR, nivel, estadisticas, rango) {
      override val tipoEnemigo = TipoEnemigo.CAZADOR
      }
      .....

- Con el modo debug y haciendo pruebas para ver como el comportamiento de la varible iba a adaptarse según sea un enemigo o otro, manteniendo sus valores sin cambiarse con las del padre y ejecutando la funcion según sus estadísticas.

#### **Criterio global 7: Librerías de clases**
- **(2.g, 4.k)**: Describe cualquier librería externa que hayas incorporado en tu proyecto. Explica cómo y por qué las elegiste, y cómo las incorporaste en tu proyecto. ¿Cómo extendió la funcionalidad de tu aplicación? Proporciona ejemplos específicos de su uso en tu proyecto.

      T.println(table {
      borderType = BorderType.ROUNDED
      borderStyle = TextStyle(color = cyan, bold = true)
      header { row(Text((black on TextColors.rgb("D5E3FF"))(
      "  __                            \n" +
      " (_  _ | _  |  _    _ |o._  _   \n" +
      " __)(_)|(_) |_(/_\\/(/_||| |(_|  \n" +
      "                            _|  "))) }
      })

- Para mi proyecto he utilizado la libreria Mordant para darle una estética más llamativa al programa utilizando tablas, paneles y colores, solo extiende mi programa de manera estética ya que no le da funcionalidades como tal

#### **Criterio global 8: Documentado**
- **(7.h)**: Muestra ejemplos de cómo has documentado y comentado tu código. ¿Que herramientas has utilizado? ¿Cómo aseguras que tu documentación aporte valor para la comprensión, mantenimiento y depuración del código?

      /**
      * Realiza un ataque y retorna el daño y dependiendo de la fuerza y la agilidad del jugador crea una probabilidad para
      * hacer un ataque critico
      *
      * @return El daño infligido por el ataque.
      */
      override fun atacar() :Double {
           val critico = (estadisticas.fuerza * estadisticas.agilidad) / 100
           val suerte = (0..1000).random()/100
           return if (critico >= suerte) estadisticas.fuerza + critico
           else estadisticas.fuerza * 0.85
      }

- Para documentar mi codigo he utilizado la documentación KDoc de kotlin, con descripciones para las funciones y clases de forma que se pueda entender que hacen

#### **Criterio global 9: Genéricos**
- **(6.f)**: Muestra ejemplos de tu código sobre cómo has implementado una clase con genéricos. ¿Qué beneficio has obtenido?

      interface Combates<T> {
          fun comprobarVida() :Boolean
          fun atacar() :Double
          fun recibirDanio(danio:Double) :Boolean
          fun esquivar() :Boolean
      }

- Con los genéricos me han permitido poder tener una lista exclusiva de combatientes gracias a la interfaz Combates para poder usarla en la simulación de la batalla y así poder realizar las acciones del combate

