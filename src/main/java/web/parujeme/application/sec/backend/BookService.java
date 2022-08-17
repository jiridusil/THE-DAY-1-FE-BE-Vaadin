package web.parujeme.application.sec.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;

/**
 * author: jiri dusil 8/14/2022
 */
@Service
@RequiredArgsConstructor
public class BookService implements CrudListener<Books> {

    private BookRepository bookRepository;

    @Override
    public Collection<Books> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Books add(Books books) {
        return bookRepository.save(books);
    }

    @Override
    public Books update(Books books) {
        return bookRepository.save(books);
    }

    @Override
    public void delete(Books books) {
        bookRepository.delete(books);
    }
}
