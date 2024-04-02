package ru.mit.itmo.representation.ru.mit.itmo.representation

import com.squareup.moshi.*
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
//import kotlinx.serialization.Serializable
import java.io.File

//import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

@JsonClass(generateAdapter = true)
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

        val type = Types.newParameterizedType(Map::class.java, String::class.java, FunctionParameters::class.java);
        private val jsonAdapter: JsonAdapter<Map<String, FunctionParameters>> =
            moshi.adapter<Map<String, FunctionParameters>>(type)

        fun toJson(function: Map<String, FunctionParameters>, path: String) {
            val json = jsonAdapter.toJson(function)
            println(json)
            File(path).printWriter().use { out -> out.println(json) }
        }

        fun fromJson(path: String): Map<String, FunctionParameters> {
            val file: List<String>
            try {
                file = File(path).useLines { it.toList() }
            } catch (e: Exception) {
                throw Exception("Error while opening file: ${e.message}")
            }

            val data = file.joinToString()
            val res = jsonAdapter.fromJson(data)

            if (res != null) {
                return res
            }
            // Add default configuration
            throw Exception("Empty file")
        }

        fun invokeFunction(functionName: String) {

        }
    }

}