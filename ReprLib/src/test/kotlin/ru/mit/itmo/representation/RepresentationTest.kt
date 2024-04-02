package ru.mit.itmo.representation

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
        Representation.toJson(mockRepresentation, "C:\\Coding\\Hack\\IntelliJControlVoice\\ReprLib\\src\\test\\kotlin\\ru\\mit\\itmo\\representation\\test.json")
    }

    @Test
    fun `test fromJson`(){
        val res = Representation.fromJson("C:\\Coding\\Hack\\IntelliJControlVoice\\ReprLib\\src\\test\\kotlin\\ru\\mit\\itmo\\representation\\test.json")
//        println(res)

    }
}