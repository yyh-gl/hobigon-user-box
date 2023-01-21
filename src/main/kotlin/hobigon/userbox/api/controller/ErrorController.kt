package hobigon.userbox.api.controller

import hobigon.userbox.api.view.ErrorView
import hobigon.userbox.domain.entity.user.FailedAuthenticationException
import hobigon.userbox.domain.entity.user.InvalidValueException
import hobigon.userbox.usecase.user.DuplicatedEmailException
import hobigon.userbox.usecase.user.DuplicatedUserNameException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@ControllerAdvice
class ErrorController {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    fun badRequestError(e: MethodArgumentNotValidException): ErrorView {
        return ErrorView(e.fieldErrors[0].defaultMessage.toString())
    }

    @ExceptionHandler(InvalidValueException::class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    fun badRequestError(e: InvalidValueException): ErrorView {
        return ErrorView(e.message.toString())
    }

    @ExceptionHandler(DuplicatedEmailException::class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    fun duplicatedEmailError(e: DuplicatedEmailException): ErrorView {
        return ErrorView(e.message.toString())
    }

    @ExceptionHandler(DuplicatedUserNameException::class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    fun duplicatedUserNameError(e: DuplicatedUserNameException): ErrorView {
        return ErrorView(e.message.toString())
    }

    @ExceptionHandler(FailedAuthenticationException::class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    fun unauthorizedError(e: FailedAuthenticationException): ErrorView {
        return ErrorView(e.message.toString())
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    // FIXME: ログ出力でeを使うようになったら消す
    @Suppress("UnusedPrivateMember")
    fun occurOtherException(e: Exception): ErrorView {
        println("===================")
        println(e.message)
        println("===================")
        return ErrorView("原因不明のエラーが発生しました")
    }
}
