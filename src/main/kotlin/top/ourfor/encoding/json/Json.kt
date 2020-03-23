package top.ourfor.encoding.json

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

object Json {
    val mapper = jacksonObjectMapper()
}

/**
 * @param obj the object you want to stringify
 * @return result is the json string of para obj
 */
fun stringify(obj: Any): String = Json.mapper.writeValueAsString(obj)

/**
 * @param string string with type json
 * @param clazz the type of object class
 * @return result is an object
 */

fun <T> parse(string: String,clazz: Class<T>): T = Json.mapper.convertValue(string,clazz)
