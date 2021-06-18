package pl.kebab.Repository;

import pl.kebab.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends
        CrudRepository<User, Long> {}
