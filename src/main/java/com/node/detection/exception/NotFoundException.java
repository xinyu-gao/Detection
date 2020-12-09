package com.node.detection.exception;

import com.node.detection.entity.util.HttpResult;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xinyu
 */
@RestController
public class NotFoundException implements ErrorController {

    private static final String ERROR_PATH = "/error";


    @RequestMapping("/error")
    public HttpResult error(){
        return HttpResult.failed("404 not found");
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}