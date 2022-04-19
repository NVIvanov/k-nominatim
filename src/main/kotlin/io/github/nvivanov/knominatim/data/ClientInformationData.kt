package io.github.nvivanov.knominatim.data

data class ClientInformationData(
    var baseUrl: String = "https://nominatim.openstreetmap.org/",
    var email: String? = null,
    var userAgent: String? = null,
)