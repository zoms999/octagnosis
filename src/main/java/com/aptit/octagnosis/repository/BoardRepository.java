package com.aptit.octagnosis.repository;

import com.aptit.octagnosis.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByTitleOrContent(String title,String Content);
}
