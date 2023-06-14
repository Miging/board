package com.study.board.entity;
//DB테이블 역할을 하는 ㅡㄹ래스

import com.study.board.dto.BoardDTO;
import com.study.board.repository.BoardRepository;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="board_table")
public class BoardEntity extends BaseEntity {
    @Id//pk 컬럼 지정.필수
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto_increment
    private Long id;

    @Column(length = 20,nullable = false)//크기 20, notnull
    private String boardWriter;

    @Column//크기 255, null 가능
    private String boardPass;

    @Column//크기 255, null 가능
    private String boardTitle;

    @Column(length = 500)//크기 255, null 가능
    private String boardContents;

    @Column//크기 255, null 가능
    private int boardHits;

    @Column
    private int fileAttached;//0 or 1


    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardFileEntity> boardFileEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList=new ArrayList<>();

    public static BoardEntity toSaveEntity(BoardDTO boardDTO){
        BoardEntity boardEntity=new BoardEntity();
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(0);
        boardEntity.setFileAttached(0);//파일 없음
        return boardEntity;
    }

    public static BoardEntity toUpdateEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity=new BoardEntity();
        boardEntity.setId(boardDTO.getId());
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(boardDTO.getBoardHits());
        return boardEntity;
    }

    public static BoardEntity toSaveFileEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity=new BoardEntity();
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(0);
        boardEntity.setFileAttached(1);
        return boardEntity;
    }
}
