package com.study.board.service;

import com.study.board.dto.BoardDTO;
import com.study.board.entity.BoardEntity;
import com.study.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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


    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList=boardRepository.findAll();
        List<BoardDTO> boardDTOList=new ArrayList<>();
        for (BoardEntity boardEntity:boardEntityList){
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }

    @Transactional
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    public BoardDTO findById(Long id) {
        Optional<BoardEntity> optionalBoardEntity=boardRepository.findById(id);
        if(optionalBoardEntity.isPresent()){
            BoardEntity boardEntity=optionalBoardEntity.get();
            BoardDTO boardDTO=BoardDTO.toBoardDTO(boardEntity);
            return boardDTO;
        }else {
            return null;
        }
    }

    public BoardDTO update(BoardDTO boardDTO) {
        BoardEntity boardEntity=BoardEntity.toUpdateEntity(boardDTO);
        boardRepository.save(boardEntity);
        return  findById(boardDTO.getId());
    }

    public Page<BoardDTO> paging(Pageable pageable) {
        int page=pageable.getPageNumber()-1;
        int pageLimit=3;
        //한페이지당 3개씩 글을 보여주고 id 기준으로 내림차순 정렬
        //page는 0부터 시작
        boardRepository.findAll(PageRequest.of(page,pageLimit, Sort.by(Sort.Direction.DESC,"id")));
    }
}
