package ru.kiprin.demo.service;

import org.springframework.stereotype.Service;

import ru.kiprin.demo.model.Request;

@Service
public interface ModifyRequestService {

    void modify(Request request);
}
