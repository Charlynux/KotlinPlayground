package io.charlynux.playground

import dev.forkhandles.result4k.Success
import dev.forkhandles.result4k.peekFailure
import dev.forkhandles.result4k.recover
import org.http4k.core.*
import org.http4k.core.Method.GET
import org.http4k.core.Status.Companion.OK
import org.http4k.format.Jackson.auto
import org.http4k.lens.asResult

val infoLens = Body.auto<Info>()
    .map(InfoResponse::success)
    .toLens()
    .asResult()

data class MessageContainer(val message: String)

val errorLens = Body.auto<MessageContainer>()
    .map { it.message }
    .map(InfoResponse::failure)
    .toLens()
    .asResult()

val defaultResponse: InfoResponse = InfoResponse.failure("default message")

private fun extractInfoResponse(response: Response): InfoResponse =
    when (response.status) {
        OK -> infoLens.extract(response)
        Status.BAD_REQUEST -> errorLens.extract(response)
        else -> Success(defaultResponse)
    }
    .peekFailure { println(it) }
    .recover { defaultResponse }

class InformationAdapter(private val httpHandler: HttpHandler) : Information {
    override fun get(id: String?): InfoResponse {
        return extractInfoResponse(httpHandler(Request(GET, "/${id}")))
    }
}
