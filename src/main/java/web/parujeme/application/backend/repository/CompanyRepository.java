package web.parujeme.application.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.parujeme.application.backend.entity.Company;
import web.parujeme.application.backend.entity.Contact;

/**
 * @author jdusil
 * @date 2022-08-05 3:17 PM
 */
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
