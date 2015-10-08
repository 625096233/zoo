package zoo.panda.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import zoo.panda.entity.Panda;

/**
 * @author Waldemar Rittscher
 */
@Repository
public interface PandaRepository extends PagingAndSortingRepository<Panda, Long> {
}
