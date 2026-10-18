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
import lombok.extern.slf4j.Slf4j;
import ru.kiprin.demo.exception.UnsupportedCodeException;
import ru.kiprin.demo.model.Codes;
import ru.kiprin.demo.model.ErrorCodes;
import ru.kiprin.demo.model.ErrorMessages;
import ru.kiprin.demo.model.Request;
import ru.kiprin.demo.model.Response;
import ru.kiprin.demo.service.AnnualBonusService;
import ru.kiprin.demo.service.ExceptionHandlerService;
import ru.kiprin.demo.service.ModifyRequestService;
import ru.kiprin.demo.service.ModifyResponseService;
import ru.kiprin.demo.service.ValidationService;
import ru.kiprin.demo.util.DateTimeUtil;

@Slf4j
@RestController
public class MyController {

    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;
    private final ModifyRequestService modifyRequestService;
    private final AnnualBonusService annualBonusService;
    private final ExceptionHandlerService exceptionHandlerService;

    @Autowired
    public MyController(ValidationService validationService,
            @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService,
            @Qualifier("ModifySourceRequestService") ModifyRequestService modifyRequestService,
            AnnualBonusService annualBonusService,
            ExceptionHandlerService exceptionHandlerService) {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.modifyRequestService = modifyRequestService;
        this.annualBonusService = annualBonusService;
        this.exceptionHandlerService = exceptionHandlerService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
            BindingResult bindingResult) {

        request.setSystemTime(DateTimeUtil.getCustomFormat().format(new Date()));
        log.info("request [{}]: {}", request.getOperationUid(), request);

        Response response = buildResponse(request);
        log.info("response created [{}]: {}", response.getOperationUid(), response);

        try {
            validationService.isValid(bindingResult);
            checkUid(request);
        } catch (Exception e) {
            return exceptionHandlerService.handle(response, e);
        }

        response.setAnnualBonus(annualBonusService.calculate(
                request.getPosition(),
                request.getSalary(),
                request.getBonus(),
                request.getWorkDays()));
        log.info("response enriched [{}]: annualBonus={}", response.getOperationUid(),
                response.getAnnualBonus());

        modifyResponseService.modify(response);
        modifyRequestService.modify(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private Response buildResponse(Request request) {
        return Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();
    }

    private void checkUid(Request request) throws UnsupportedCodeException {
        if ("123".equals(request.getUid())) {
            log.error("unsupported uid [{}]: uid=123", request.getOperationUid());
            throw new UnsupportedCodeException("uid равен 123");
        }
    }
}
