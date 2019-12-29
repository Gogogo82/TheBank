package bankApp.aspect;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionAspect {

    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public String handleException(Exception exc, Model model){
        model.addAttribute("exception", exc);
        return "errorPage";
    }
}
