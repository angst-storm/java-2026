package ru.kiprin.demo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ru.kiprin.demo.model.Response;
import ru.kiprin.demo.util.DateTimeUtil;

@Service
@Qualifier("ModifySystemTimeResponseService")
public class ModifySystemTimeResponseService implements ModifyResponseService {

    @Override
    public Response modify(Response response) {
        response.setSystemTime(DateTimeUtil.getCustomFormat()
                .format(new Date()));
        return response;
    }
}
