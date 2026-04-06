package br.com.avaliafit_api.config

import br.com.avaliafit_api.exceptions.ErrorResponse
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import java.io.IOException
import java.util.Date

@Component
class CustomAccessDeniedHandler : AccessDeniedHandler {

    private val mapper = ObjectMapper()

    @Throws(IOException::class)
    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        ex: AccessDeniedException
    ) {

        val error = ErrorResponse(
            erro = "Acesso negado",
            codigo = 403,
            timestamp = Date(),
            path = request.requestURI
        )

        response.status = HttpServletResponse.SC_FORBIDDEN
        response.contentType = "application/json"
        response.writer.write(mapper.writeValueAsString(error))
    }
}