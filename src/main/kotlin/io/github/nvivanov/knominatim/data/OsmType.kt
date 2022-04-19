package io.github.nvivanov.knominatim.data

import com.fasterxml.jackson.annotation.JsonValue

enum class OsmType(@JsonValue val value: String) {
    NODE("node"),
    WAY("way"),
    RELATION("relation");

    fun shortTitle(): String {
        return when(value) {
            "node" -> "N"
            "way" -> "W"
            "relation" -> "R"
            else -> throw IllegalStateException()
        }
    }

    companion object {
        fun from(s: String): OsmType = values().find { it.value == s } ?: throw IllegalArgumentException()
    }
}