package com.aptit.octagnosis.cotroller;

import com.aptit.octagnosis.model.Board;
import com.aptit.octagnosis.repository.BoardRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
class BoardApiController {
    @Autowired
    private  BoardRepository repository;

    /*BoardController(BoardRepository repository) {
        this.repository = repository;
    }*/


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/boards")
    List<Board> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @GetMapping("/boardFind")
    List<Board> boardFind(@RequestParam(required = false, defaultValue = "")String title, @RequestParam(required = false, defaultValue = "")String content) {

        if(StringUtils.isEmpty(title) && StringUtils.isEmpty(content))
        {
            return repository.findAll();
        }
        else
        {
            return repository.findByTitleOrContent(title, content);
        }
    }
    @PostMapping("/boards")
    Board newBoard(@RequestBody Board newBoard) {
        return repository.save(newBoard);
    }

    // Single item

    @GetMapping("/boards/{id}")
    Board one(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/boards/{id}")
    Board replaceBoard(@RequestBody Board newBoard, @PathVariable Long id) {

        return repository.findById(id)
                .map(board -> {
                    board.setTitle(newBoard.getTitle());
                    board.setContent(newBoard.getContent());
                    return repository.save(board);
                })
                .orElseGet(() -> {
                    newBoard.setId(id);
                    return repository.save(newBoard);
                });
    }

    @DeleteMapping("/boards/{id}")
    void deleteBoard(@PathVariable Long id) {
        repository.deleteById(id);
    }
}