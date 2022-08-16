package web.parujeme.db.backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * author: jiri dusil 8/14/2022
 */
@Repository
public interface BookRepository extends JpaRepository<Books, Long> {
}
