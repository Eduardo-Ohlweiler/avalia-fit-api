package br.com.avaliafit_api.exceptions

import java.util.Date

data class ErrorResponse(
    var erro:       String? = null,
    var codigo:     Int? = null,
    var timestamp:  Date?   = null,
    var path:       String? = null
)