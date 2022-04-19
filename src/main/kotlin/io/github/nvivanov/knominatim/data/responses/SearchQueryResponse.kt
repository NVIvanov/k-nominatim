package io.github.nvivanov.knominatim.data.responses

import com.fasterxml.jackson.annotation.JsonAlias
import io.github.nvivanov.knominatim.data.OsmType


data class SearchQueryResponse(
    val placeId: Long,
    val osmType: OsmType,
    val osmId: Long,
    val displayName: String,
    val placeRank: Long,
    val category: String,
    val type: String,
    val importance: Double,
    val address: Map<String, String>?,
    @JsonAlias("extratags")
    val extraTags: Map<String, String>?,
    @JsonAlias("namedetails")
    val nameDetails: Map<String, String>?
)