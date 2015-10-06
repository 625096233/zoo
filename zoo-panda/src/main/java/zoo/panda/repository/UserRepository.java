package zoo.panda.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import zoo.panda.entity.User;

/**
 * @author Waldemar Rittscher
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
}
