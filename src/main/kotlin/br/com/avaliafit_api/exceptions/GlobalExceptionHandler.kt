package br.com.avaliafit_api.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.util.Date

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException::class)
    fun applicationException(ex: ApplicationException, request: WebRequest): ResponseEntity<ErrorResponse> {

        val errorResponse = ErrorResponse(
            erro        = ex.message,
            codigo      = HttpStatus.BAD_REQUEST.value(),
            timestamp   = Date(),
            path        = request.getDescription(false)
        )
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(ex: MethodArgumentNotValidException, request: WebRequest): ResponseEntity<ErrorResponse> {

        val erros = ex.fieldErrors.joinToString ("; "){ "${it.field} ${it.defaultMessage}" };
        val errorResponse = ErrorResponse(
            erro        = erros,
            codigo      = HttpStatus.BAD_REQUEST.value(),
            timestamp   = Date(),
            path        = request.getDescription(false)
        )
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException::class)
    fun notFoundException(ex: NotFoundException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            erro        = ex.message,
            codigo      = HttpStatus.NOT_FOUND.value(),
            timestamp   = Date(),
            path        = request.getDescription(false)
        )
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(ConflictException::class)
    fun conflictException(ex: ConflictException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            erro        = ex.message,
            codigo      = HttpStatus.CONFLICT.value(),
            timestamp   = Date(),
            path        = request.getDescription(false)
        )
        return ResponseEntity(errorResponse, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(UnauthorizedException::class)
    fun unauthorizedException(ex: UnauthorizedException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            erro        = ex.message,
            codigo      = HttpStatus.UNAUTHORIZED.value(),
            timestamp   = Date(),
            path        = request.getDescription(false)
        )
        return ResponseEntity(errorResponse, HttpStatus.UNAUTHORIZED)
    }
}