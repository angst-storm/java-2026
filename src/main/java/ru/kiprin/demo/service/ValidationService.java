package ru.kiprin.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import ru.kiprin.demo.exception.ValidationFailedException;

@Service
public interface ValidationService {

    void isValid(BindingResult bindingResult) throws ValidationFailedException;
}
