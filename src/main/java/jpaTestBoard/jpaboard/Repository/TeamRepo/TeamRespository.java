package jpaTestBoard.jpaboard.Repository.TeamRepo;

import jpaTestBoard.jpaboard.Entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRespository extends JpaRepository<Team, Long> {

    Team findByName(String userName);
}
