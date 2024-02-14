package com.aptit.octagnosis.repository;

import com.aptit.octagnosis.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
