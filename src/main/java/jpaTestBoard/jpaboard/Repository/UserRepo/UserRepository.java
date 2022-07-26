package jpaTestBoard.jpaboard.Repository.UserRepo;

import jpaTestBoard.jpaboard.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUserId(String userId);
}
