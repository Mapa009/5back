package kr.co.yooooon.base.repository;

import kr.co.yooooon.base.to.BoardTO;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface BoardRepository extends CrudRepository<BoardTO,String> {
    ArrayList<BoardTO> findByOrderByBoardSeqDesc();
    ArrayList<BoardTO> findByBoardSeqAndRefSeq(String boardSeq,String refSeq);
}
