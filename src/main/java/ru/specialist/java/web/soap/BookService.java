package ru.specialist.java.web.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
public class BookService {

    @Autowired
    JdbcTemplate jdbcTemplate;


    private static final String GET_BOOK = "select b.book_id, b.title, a.author_id, a.author_name, a.last_name " +
            "from books b join authors a " +
            "on b.author_id = a.author_id " +
            "where b.book_id = ? ";

    private static final String GET_BOOKS = "select b.book_id, b.title, a.author_id, a.author_name, a.last_name " +
            "from books b join authors a " +
            "on b.author_id = a.author_id ";

    public Book getBook(int bookId) throws ClassNotFoundException, SQLException {


        Book book = new Book();
        Author author = new Author();

        jdbcTemplate.query(GET_BOOK, ps -> ps.setInt(1, bookId), set -> {
            book.setId(set.getInt(1));
            book.setTitle(set.getString(2));
            author.setId(set.getInt(3));
            author.setName(set.getString(4));
            author.setLastName(set.getString(5));
        });

        book.setAuthor(author);
        return book;
    }

    public List<Book> getBooks() {
        return jdbcTemplate.query(GET_BOOKS, (set, rowNum) -> {
            Book book = new Book();
            Author author = new Author();
            book.setId(set.getInt(1));
            book.setTitle(set.getString(2));
            author.setId(set.getInt(3));
            author.setName(set.getString(4));
            author.setLastName(set.getString(5));
            return book;
        });
    }
}
