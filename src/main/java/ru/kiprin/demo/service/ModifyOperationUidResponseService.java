package ru.kiprin.demo.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ru.kiprin.demo.model.Response;

@Slf4j
@Service
@Qualifier("ModifyOperationUidResponseService")
public class ModifyOperationUidResponseService implements ModifyResponseService {

    @Override
    public Response modify(Response response) {
        String previousOperationUid = response.getOperationUid();
        UUID uuid = UUID.randomUUID();
        response.setOperationUid(uuid.toString());
        log.info("response modified [{}]: operationUid={}", previousOperationUid,
                response.getOperationUid());
        return response;
    }
}
