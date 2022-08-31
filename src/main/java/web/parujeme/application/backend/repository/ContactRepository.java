package web.parujeme.application.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import web.parujeme.application.backend.entity.Contact;

import java.util.List;

/**
 * @author jdusil
 * @date 2022-08-05 3:17 PM
 */
public interface ContactRepository extends JpaRepository<Contact, Long> {
    @Query("select c from Contact c " +
            "where lower(c.firstName) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(c.lastName) like lower(concat('%', :searchTerm, '%'))")
    List<Contact> search(@Param("searchTerm") String searchTerm);

    @Query("select c.email from Contact c where lower(c.email) = lower(:email)")
    String searchEmail(@Param("email") String searchTerm);

    @Query("select c from Contact c where lower(c.email) = lower(:email)")
    Contact searchContactByEmail(@Param("email") String searchTerm);
}
