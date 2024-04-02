package ru.mit.itmo.representation

//import com.sun.tools.javac.code.TypeAnnotationPosition.instanceOf
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.mit.itmo.representation.ru.mit.itmo.representation.FunctionParameters
//import ru.mit.itmo.representation.ru.mit.itmo.representation.FunctionRepresentation
import ru.mit.itmo.representation.ru.mit.itmo.representation.Representation

class RepresentationTest{
    fun f(a: Int): Int{
        return a + 1
    }

    fun g(a: Int): Int{
        return a + 2
    }

    private val path = "src\\test\\kotlin\\ru\\mit\\itmo\\representation\\test.json"

    private val commandsByName = listOf(::f, ::g).associateBy { it.name }
    @Test
    fun `test toJson`(){


        val aliases = listOf("function1", "function2")
//        val name : (Int) -> Int = f
        val mockParameters = FunctionParameters(aliases);
        val mockRepresentation = mapOf(
            "f" to mockParameters,
            "g" to mockParameters
        )
        Representation.toJson(mockRepresentation, path)
    }

    @Test
    fun `test fromJson`(){
        val res = Representation.fromJson(path)
//        println(res)
        val aliases = listOf("function1", "function2")
        val test = res.get("f")
        print(test)
        assertTrue(FunctionParameters::class.isInstance(test))
        assertEquals(FunctionParameters(aliases), res["f"])
        assertEquals(FunctionParameters(aliases), res["g"])
    }
}