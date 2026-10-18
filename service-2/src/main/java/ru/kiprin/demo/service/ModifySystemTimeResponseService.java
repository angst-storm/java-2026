package ru.kiprin.demo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ru.kiprin.demo.model.Response;
import ru.kiprin.demo.util.DateTimeUtil;

@Slf4j
@Service
@Qualifier("ModifySystemTimeResponseService")
public class ModifySystemTimeResponseService implements ModifyResponseService {

    @Override
    public Response modify(Response response) {
        response.setSystemTime(DateTimeUtil.getCustomFormat()
                .format(new Date()));
        log.info("response modified [{}]: systemTime={}", response.getOperationUid(),
                response.getSystemTime());
        return response;
    }
}
