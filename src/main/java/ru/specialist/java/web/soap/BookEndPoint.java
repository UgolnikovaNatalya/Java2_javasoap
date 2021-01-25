package ru.specialist.java.web.soap;


import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.sql.SQLException;

@Endpoint
public class BookEndPoint {
    private static final String NAMESPACE_URI = "http://specialist.ru/schemas";

    @Autowired
    private BookService bookService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookRequest")
    @ResponsePayload
    public GetBookResponse getBook(@RequestPayload GetBookRequest request)
            throws JDOMException, SQLException, ClassNotFoundException {

        Book book = bookService.getBook(request.getBookId());
        GetBookResponse response = new GetBookResponse();
        response.setBook(book);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBooksRequest")
    @ResponsePayload
    public GetBooksResponse getBooks(@RequestPayload GetBooksRequest request)
            throws SQLException, ClassNotFoundException {
        GetBooksResponse response = new GetBooksResponse();
        response.getBooks().addAll(bookService.getBooks());
        return response;
    }


}