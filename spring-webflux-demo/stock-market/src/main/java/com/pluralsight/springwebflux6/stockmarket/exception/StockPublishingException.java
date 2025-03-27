package com.pluralsight.springwebflux6.stockmarket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class StockPublishingException extends RuntimeException {

    public StockPublishingException(String message) {
        super(message);
    }

    public ProblemDetail asProblemDetail() {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, getMessage());
    }
}
