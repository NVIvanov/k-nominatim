package io.github.nvivanov.knominatim.client.impl

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import io.github.nvivanov.knominatim.client.NominatimAPI
import io.github.nvivanov.knominatim.data.ClientInformationData
import io.github.nvivanov.knominatim.data.requests.LookupAddressQuery
import io.github.nvivanov.knominatim.data.requests.SimpleSearchQuery
import io.github.nvivanov.knominatim.data.requests.StructuredSearchQuery
import io.github.nvivanov.knominatim.data.responses.SearchQueryResponse
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.http.*

class KtorNominatimClient(
    httpClient: HttpClient,
    block: ClientInformationData.() -> Unit
): NominatimAPI {

    private val httpClient: HttpClient = httpClient.config {
        install(JsonFeature) {
            serializer = JacksonSerializer {
                propertyNamingStrategy = PropertyNamingStrategies.SNAKE_CASE
                configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            }
        }
    }

    private val clientInformationData = ClientInformationData().apply(block)

    override suspend fun search(simpleSearchQuery: SimpleSearchQuery): List<SearchQueryResponse> {
        return httpClient.get("${clientInformationData.baseUrl}/search") {
            parameter("format", "jsonv2")
            with(simpleSearchQuery) {
                queryString?.let { parameter("q", it) }
                addressDetails?.let { parameter("addressdetails", addressDetails) }
                nameDetails?.let { parameter("namedetails", nameDetails) }
                limit?.let { parameter("limit", limit) }
                headers {
                    append(HttpHeaders.AcceptLanguage, acceptLanguage.joinToString(","))
                }
            }
            setUpClientInformation()
        }
    }

    override suspend fun search(structuredSearchQuery: StructuredSearchQuery): List<SearchQueryResponse> {
        return httpClient.get("${clientInformationData.baseUrl}/search") {
            parameter("format", "jsonv2")
            with(structuredSearchQuery) {
                street?.let { parameter("street", it) }
                city?.let { parameter("city", it) }
                county?.let { parameter("county", it) }
                state?.let { parameter("state", it) }
                country?.let { parameter("country", it) }
                postalCode?.let { parameter("postalCode", it) }
                addressDetails?.let { parameter("addressdetails", addressDetails) }
                nameDetails?.let { parameter("namedetails", nameDetails) }
                limit?.let { parameter("limit", limit) }
                headers {
                    append(HttpHeaders.AcceptLanguage, acceptLanguage.joinToString(","))
                }
            }
            setUpClientInformation()
        }
    }

    override suspend fun lookUpAddress(lookupAddressQuery: LookupAddressQuery): List<SearchQueryResponse> {
        return httpClient.get("${clientInformationData.baseUrl}/lookup") {
            parameter("format", "jsonv2")
            val paramValue = lookupAddressQuery
                .osmIds
                .joinToString(",")
            parameter("osm_ids", paramValue)
            lookupAddressQuery.addressDetails?.let { parameter("addressdetails", lookupAddressQuery.addressDetails) }
            lookupAddressQuery.nameDetails?.let { parameter("namedetails", lookupAddressQuery.nameDetails) }
            setUpClientInformation()
        }
    }

    private fun HttpRequestBuilder.setUpClientInformation() {
        clientInformationData.email?.let { parameter("email", it) }
        headers {
            with(clientInformationData) {
                userAgent?.let {
                    append(HttpHeaders.UserAgent, it)
                }
            }
        }
    }

}