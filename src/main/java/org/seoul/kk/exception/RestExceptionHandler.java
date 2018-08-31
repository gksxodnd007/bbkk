package org.seoul.kk.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<ErrorModel> handledExceptionHandler(BaseException exception) throws RuntimeException {
        ErrorModel error = exception.error;
        log.error("Rest api error : {}", error.getMsg());

        switch (error.getCode()) {
            case 400:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            default:
                throw new RuntimeException();
        }

    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorModel> unhandledExceptionHandler(RuntimeException exception) {
        log.error("unhandledException occur : {}", exception.getMessage());
        exception.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorModel.builder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .msg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                        .timestamp(LocalDateTime.now())
                        .build());
    }

}
