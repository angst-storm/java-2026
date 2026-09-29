package ru.kiprin.demo.service;

import org.springframework.stereotype.Service;

import ru.kiprin.demo.model.Response;

@Service
public interface ModifyResponseService {

    Response modify(Response response);
}
