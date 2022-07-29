package jpaTestBoard.jpaboard.Repository.UserRepo;

import jpaTestBoard.jpaboard.Entity.User.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserId(String tjfgusdlf12);
}
