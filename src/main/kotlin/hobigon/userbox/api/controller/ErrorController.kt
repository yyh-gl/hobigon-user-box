package hobigon.userbox.api.controller

import hobigon.userbox.api.view.ErrorView
import hobigon.userbox.domain.entity.shared.InvalidValueException
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

    @ExceptionHandler(Exception::class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    // FIXME: ログ出力でeを使うようになったら消す
    @Suppress("UnusedPrivateMember")
    fun occurOtherException(e: Exception): ErrorView {
        return ErrorView("原因不明のエラーが発生しました")
    }
}
