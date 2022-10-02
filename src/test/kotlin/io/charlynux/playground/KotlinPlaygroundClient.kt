package io.charlynux.playground

import org.http4k.client.JavaHttpClient
import org.http4k.core.Uri
import org.http4k.core.then
import org.http4k.filter.ClientFilters

fun main() {
    val informationAdapter = InformationAdapter(
        ClientFilters
            .SetBaseUriFrom(Uri.of("https://swapi.dev/api/films"))
            .then(JavaHttpClient())
    )

    println(informationAdapter.get("1"))
    println(informationAdapter.get("NOT_AN_ID"))
}
