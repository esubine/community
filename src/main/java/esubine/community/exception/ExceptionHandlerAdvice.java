package esubine.community.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler({DuplicatedException.class, AuthException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse aaa(Exception e){
        return new ErrorResponse(e.getMessage());
    }



    @ExceptionHandler({MissingRequestHeaderException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse bbb(Exception e){
        return new ErrorResponse("필요한 입력 값이 없습니다. Cause by: " + e.getMessage());
    }
}
