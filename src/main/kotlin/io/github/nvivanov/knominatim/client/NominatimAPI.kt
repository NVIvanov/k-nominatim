package io.github.nvivanov.knominatim.client

import io.github.nvivanov.knominatim.data.requests.LookupAddressQuery
import io.github.nvivanov.knominatim.data.requests.SimpleSearchQuery
import io.github.nvivanov.knominatim.data.requests.StructuredSearchQuery
import io.github.nvivanov.knominatim.data.responses.SearchQueryResponse

interface NominatimAPI {

    suspend fun search(
        simpleSearchQuery: SimpleSearchQuery
    ): List<SearchQueryResponse>

    suspend fun search(
        structuredSearchQuery: StructuredSearchQuery
    ): List<SearchQueryResponse>

    suspend fun lookUpAddress(
        lookupAddressQuery: LookupAddressQuery
    ): List<SearchQueryResponse>
}