package ru.mit.itmo.representation.ru.mit.itmo.representation

import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.serialization.Serializable
import java.io.File

//import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

//@JsonClass(generateAdapter = true)
data class FunctionParameters(
    /**
     * Base class for representing different aliases for function
     */
    @Json(name = "aliases") val aliases: List<String>

    )

//@JsonClass(generateAdapter = true)
//data class FunctionRepresentation(
//    val functionName: String,
//    val parameters: FunctionParameters
//)

class Representation {
//    val commandsByName = listOf(::fromYaml, ::fromYaml).associateBy { it.name }
    /**
     * - Name of the function is known before
     * - Given the name we can get all the aliases
     */
    companion object {
        private val moshi: Moshi = Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        private val jsonAdapter: JsonAdapter<Map<String, FunctionParameters>> = moshi.adapter<Map<String, FunctionParameters>>(Map::class.java)
        fun toJson(function: Map<String, FunctionParameters>, path: String) {
            val json = jsonAdapter.toJson(function)
            println(json)
            File(path).printWriter().use { out -> out.println(json) }
        }

        fun fromJson(path: String) {
            val file = File(path).useLines { it.toList() }
            val data = file.joinToString()
            val res = jsonAdapter.fromJson(data)
            println(res)
        }

        fun invokeFunction(functionName: String) {

        }
    }

}