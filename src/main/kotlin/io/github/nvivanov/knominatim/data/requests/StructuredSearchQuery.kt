package io.github.nvivanov.knominatim.data.requests

import java.util.*

data class StructuredSearchQuery(
    var street: String? = null,
    var city: String? = null,
    var county: String? = null,
    var state: String? = null,
    var country: String? = null,
    var postalCode: String? = null,
    var addressDetails: Boolean? = false,
    var nameDetails: Boolean? = false,
    var acceptLanguage: List<Locale> = listOf(Locale.ENGLISH),
    var limit: Int? = 10
)
