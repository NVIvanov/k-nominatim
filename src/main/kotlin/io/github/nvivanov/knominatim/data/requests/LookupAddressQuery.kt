package io.github.nvivanov.knominatim.data.requests

import java.util.*

data class LookupAddressQuery(
    val osmIds: List<String>,
    var addressDetails: Boolean? = false,
    var nameDetails: Boolean? = false,
    var acceptLanguage: List<Locale> = listOf(Locale.ENGLISH)
)