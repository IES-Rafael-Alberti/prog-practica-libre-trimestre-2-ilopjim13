import org.junit.jupiter.api.Test


class MainKtTest {

    @Test
    fun redondear() {
        if (5.554.redondear(2) == 5.55) assert(true)
        else assert(false)

        if (435.559365.redondear(2) == 435.56) assert(true)
        else assert(false)
    }

    @Test
    fun espacios() {
        if("Hola Mundo" == "    hola    mundo  ".espacios()) assert(true)
        else assert(false)

        if("Hola Mundo Que Tal" == "    hola    mundo  qUe   tal ".espacios()) assert(true)
        else assert(false)
    }

    @Test
    fun personajeIncial() {
        assert(true)
    }
}