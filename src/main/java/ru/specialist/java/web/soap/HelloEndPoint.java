package ru.specialist.java.web.soap;


import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Endpoint
public class HelloEndPoint {
    private static final String NAMESPACE_URI = "http://specialist.ru/schemas";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "helloRequest")
    @ResponsePayload
    public HelloResponse getBook(@RequestPayload HelloRequest request) {
        HelloResponse response = new HelloResponse();
        response.setGreeting("Hello!!!");
        return response;
    }

}