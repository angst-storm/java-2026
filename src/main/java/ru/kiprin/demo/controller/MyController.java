package ru.kiprin.demo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import ru.kiprin.demo.exception.UnsupportedCodeException;
import ru.kiprin.demo.exception.ValidationFailedException;
import ru.kiprin.demo.model.Codes;
import ru.kiprin.demo.model.ErrorCodes;
import ru.kiprin.demo.model.ErrorMessages;
import ru.kiprin.demo.model.Request;
import ru.kiprin.demo.model.Response;
import ru.kiprin.demo.service.ModifyResponseService;
import ru.kiprin.demo.service.ValidationService;
import ru.kiprin.demo.util.DateTimeUtil;

@RestController
public class MyController {

    private final ValidationService validationService;

    private final ModifyResponseService modifyResponseService;

    @Autowired
    public MyController(ValidationService validationService,
            @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService) {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
            BindingResult bindingResult) {

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();

        try {
            validationService.isValid(bindingResult);

            if ("123".equals(request.getUid())) {
                throw new UnsupportedCodeException("uid равен 123");
            }
        } catch (ValidationFailedException e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (UnsupportedCodeException e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNSUPPORTED_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNSUPPORTED);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        modifyResponseService.modify(response);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
