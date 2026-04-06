package br.com.avaliafit_api.config

import br.com.avaliafit_api.exceptions.ErrorResponse
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.io.IOException
import java.util.Date

@Component
class CustomAuthenticationEntryPoint : AuthenticationEntryPoint {

    private val mapper = ObjectMapper()

    @Throws(IOException::class)
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        ex: AuthenticationException
    ) {

        val error = ErrorResponse(
            erro = "Não autenticado",
            codigo = 401,
            timestamp = Date(),
            path = request.requestURI
        )

        response.status = HttpServletResponse.SC_UNAUTHORIZED
        response.contentType = "application/json"
        response.writer.write(mapper.writeValueAsString(error))
    }
}