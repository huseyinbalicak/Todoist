package com.appcent.todoist;

import com.appcent.todoist.response.RestResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;

public class BaseTest {

    protected ObjectMapper objectMapper;

    protected boolean isSuccess(MvcResult result) throws Exception {

        RestResponse restResponse = getRestResponse(result);

        return restResponse.isSuccess();
    }

    private RestResponse getRestResponse(MvcResult result) throws JsonProcessingException, UnsupportedEncodingException {
        return objectMapper.readValue(result.getResponse().getContentAsString(), RestResponse.class);
    }
}
