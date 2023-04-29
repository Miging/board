package com.study.board.service;

import com.study.board.dto.BoardDTO;
import com.study.board.entity.BoardEntity;
import com.study.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


//DTO->ENTITY (Entity class)
//Entity->DTO(DTo class)
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    public void save(BoardDTO boardDTO){
        BoardEntity boardEntity=BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);
    }
}
