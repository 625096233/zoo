package zoo.zuul;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Waldemar Rittscher
 */
@Slf4j
@RestController
public class ZuulErrorController implements ErrorController {

    private final String errorPath;

    @Autowired
    public ZuulErrorController(@Value("${error.path:/error}") String errorPath) {
        this.errorPath = errorPath;
    }

    @Override
    public String getErrorPath() {
        return errorPath;
    }

    @RequestMapping(value = "${error.path:/error}", produces = "application/vnd.error+json")
    public ResponseEntity error(HttpServletRequest request) {
        int status = getErrorStatus(request);
        String errorMessage = getErrorMessage(request);
        return ResponseEntity.status(status).body(errorMessage);
    }

    private int getErrorStatus(HttpServletRequest request) {
        Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
        return statusCode != null ? statusCode : HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    private String getErrorMessage(HttpServletRequest request) {
        final Throwable exc = (Throwable) request.getAttribute("javax.servlet.error.exception");
        return exc != null ? exc.getMessage() : "Unexpected error occurred";
    }
}
