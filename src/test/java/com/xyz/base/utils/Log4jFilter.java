package com.xyz.base.utils;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class Log4jFilter implements Filter {
    private final Logger logger = LogManager.getLogger();

    @Override
    public Response filter(FilterableRequestSpecification reqSpec,
                           FilterableResponseSpecification resSpec,
                           FilterContext filterContext) {

        Response response = filterContext.next(reqSpec, resSpec);

        logger.info("Method: {}", reqSpec.getMethod());
        Report.infoStep("Method: " + reqSpec.getMethod());
        logger.info("URI: {}", reqSpec.getURI());
        Report.infoStep("URI:  " + reqSpec.getURI());

        logger.info("Request Headers:");
        logger.info(reqSpec.getHeaders());
        Report.infoStep("Request Headers: " + reqSpec.getHeaders().toString());

        if (!reqSpec.getPathParams().isEmpty()) {
            logger.info("Request Path Parameters:");
            Report.infoStep("Request Path Parameters:");
            logMap(reqSpec.getPathParams());
        }

        if (!reqSpec.getQueryParams().isEmpty()) {
            logger.info("Request Query Parameters:");
            Report.infoStep("Request Query Parameters:");
            logMap(reqSpec.getQueryParams());
        }

        if (!reqSpec.getFormParams().isEmpty()) {
            logger.info("Request Form Parameters:");
            Report.infoStep("Request Form Parameters:");
            logMap(reqSpec.getFormParams());
        }

        if (reqSpec.getBody() != null) {
            logger.info("Request Body:");
            logger.info(reqSpec.getBody().toString());
            Report.infoStep("Request Body: " + reqSpec.getBody().toString());
        }

        logger.info("Response Status Code: {}", response.statusCode());
        Report.infoStep("Response Status Code: " + response.statusCode());

        logger.info("Response Headers:");
        logger.info(response.getHeaders());
        Report.infoStep("Response Headers: " + response.getHeaders());

        logger.info("Response Body:");
        logger.info(response.getBody().asPrettyString());
        Report.infoStep("Response Body: " + response.getBody().asPrettyString());

        return response;
    }

    private void logMap(Map<String, String> map) {
        map.forEach((key, value) -> {
        	logger.info("{} : {}", key, value);
        	Report.infoStep(key + "-" + value);
        });
    }
}
