package io.github.nvivanov.knominatim.data.requests

import java.util.*

data class SimpleSearchQuery(
    var queryString: String?,
    var addressDetails: Boolean? = false,
    var nameDetails: Boolean? = false,
    var acceptLanguage: List<Locale> = listOf(Locale.ENGLISH),
    var limit: Int? = 10
)