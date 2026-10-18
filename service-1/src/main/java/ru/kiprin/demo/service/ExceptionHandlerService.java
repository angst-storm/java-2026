package ru.kiprin.demo.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ru.kiprin.demo.model.Response;

@Service
public interface ExceptionHandlerService {

    ResponseEntity<Response> handle(Response response, Exception e);
}
