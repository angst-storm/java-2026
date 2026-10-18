package ru.kiprin.demo.service;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import lombok.extern.slf4j.Slf4j;
import ru.kiprin.demo.exception.UnsupportedCodeException;
import ru.kiprin.demo.exception.ValidationFailedException;
import ru.kiprin.demo.model.Request;

@Slf4j
@Service
public class RequestValidationService implements ValidationService {

    @Override
    public void isValid(BindingResult bindingResult, Request request)
            throws ValidationFailedException, UnsupportedCodeException {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            log.error("validation failed: {}", errorMessage);
            throw new ValidationFailedException(errorMessage);
        }

        if ("123".equals(request.getUid())) {
            log.error("unsupported uid [{}]: uid=123", request.getOperationUid());
            throw new UnsupportedCodeException("uid равен 123");
        }
    }
}
