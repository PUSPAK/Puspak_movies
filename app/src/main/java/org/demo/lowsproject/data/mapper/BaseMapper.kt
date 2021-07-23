package org.demo.lowsproject.data.mapper

abstract class BaseMapper<IN, OUT> {

    abstract fun mapFrom(from: IN): OUT

    fun mapFrom(from: List<IN>?): List<OUT> {
        return from?.let { list ->
            list.map(::mapFrom)
        } ?: emptyList()
    }
}
