package ru.kiprin.demo.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ru.kiprin.demo.exception.UnsupportedCodeException;
import ru.kiprin.demo.exception.ValidationFailedException;
import ru.kiprin.demo.model.Codes;
import ru.kiprin.demo.model.ErrorCodes;
import ru.kiprin.demo.model.ErrorMessages;
import ru.kiprin.demo.model.Response;

@Slf4j
@Service
public class ExceptionHandlerServiceImpl implements ExceptionHandlerService {

    @Override
    public ResponseEntity<Response> handle(Response response, Exception e) {
        response.setCode(Codes.FAILED);

        if (e instanceof ValidationFailedException) {
            log.error("validation exception [{}]: {}", response.getOperationUid(), e.getMessage());
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (e instanceof UnsupportedCodeException) {
            log.error("unsupported exception [{}]: {}", response.getOperationUid(), e.getMessage());
            response.setErrorCode(ErrorCodes.UNSUPPORTED_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNSUPPORTED);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        log.error("unknown exception [{}]: {}", response.getOperationUid(), e.getMessage());
        response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
        response.setErrorMessage(ErrorMessages.UNKNOWN);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
