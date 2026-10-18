package ru.kiprin.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import ru.kiprin.demo.exception.UnsupportedCodeException;
import ru.kiprin.demo.exception.ValidationFailedException;
import ru.kiprin.demo.model.Request;

@Service
public interface ValidationService {

    void isValid(BindingResult bindingResult, Request request)
            throws ValidationFailedException, UnsupportedCodeException;
}
