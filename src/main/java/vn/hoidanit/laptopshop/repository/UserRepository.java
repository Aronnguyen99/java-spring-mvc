package vn.hoidanit.laptopshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vn.hoidanit.laptopshop.domain.User;

@Repository // ==> tao repo de thao tac tren co so du lieu
public interface UserRepository extends CrudRepository<User, Long> {
    User save(User user);
}
