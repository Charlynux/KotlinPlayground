package io.charlynux.playground

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.http4k.core.Response
import org.http4k.core.Status.Companion.BAD_REQUEST
import org.http4k.core.Status.Companion.OK
import org.junit.jupiter.api.Test

class KotlinPlaygroundTest {
    @Test
    fun `Success mapping`() {
        val informationAdapter = InformationAdapter { _ -> Response(OK).body("{ \"title\" : \"hello, world\" }") }

        assertThat(informationAdapter.get("1"), equalTo(InfoResponse.success(Info("hello, world"))))
    }

    @Test
    fun `Error mapping`() {
        val informationAdapter = InformationAdapter { _ -> Response(BAD_REQUEST).body("{ \"message\" : \"the error message\" }") }

        assertThat(informationAdapter.get("azertyuiop"), equalTo(InfoResponse.failure("the error message")))
    }

    @Test
    fun `Error mapping with empty body`() {
        val informationAdapter = InformationAdapter { _ -> Response(BAD_REQUEST) }

        assertThat(informationAdapter.get("azertyuiop"), equalTo(InfoResponse.failure("default message")))
    }
}


