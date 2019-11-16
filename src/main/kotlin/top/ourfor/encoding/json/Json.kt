package top.ourfor.encoding.json

import com.alibaba.fastjson.JSON

/**
 * @param obj the object you want to stringify
 * @return result is the json string of para obj
 */
fun stringify(obj: Any): String {
    return JSON.toJSONString(obj)
}

/**
 * @param string string with type json
 * @param clazz the type of object class
 * @return result is an object
 */

fun <T> parse(string: String,clazz: Class<T>): T {
    return JSON.parseObject(string,clazz)
}