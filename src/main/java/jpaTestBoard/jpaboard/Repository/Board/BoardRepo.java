package jpaTestBoard.jpaboard.Repository.Board;

import jpaTestBoard.jpaboard.Entity.Board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepo extends JpaRepository<Board,Long> {
    Board getByIdx(Long idx);

    Page<Board> findAllByOrderIdxDesc(Pageable pageable);
}
