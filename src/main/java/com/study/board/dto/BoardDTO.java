package com.study.board.dto;

import com.study.board.controller.BoardController;
import com.study.board.entity.BoardEntity;
import com.study.board.entity.BoardFileEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//Data Transfer Object
@Getter
@Setter
@ToString
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자
public class BoardDTO {
    private Long id;
    private String boardWriter;
    private String boardPass;
    private String boardTitle;
    private String boardContents;
    private int boardHits;
    private LocalDateTime boardCreatedTime;
    private LocalDateTime boardUpdatedTime;

    private List<MultipartFile> boardFile;//save.html->controller 파일 담는 용도
    private List<String> originalFileName;//원본파일 이름
    private List<String> storedFileName;//서버 저장용 파일 이름
    private int fileAttached;//파일 첨부 여부(첨부 1, 미첨부 0).플래그

    public BoardDTO(Long id, String boardWriter, String boardTitle, int boardHits, LocalDateTime boardCreatedTime) {
        this.id = id;
        this.boardWriter = boardWriter;
        this.boardTitle = boardTitle;
        this.boardHits = boardHits;
        this.boardCreatedTime = boardCreatedTime;
    }

    public static BoardDTO toBoardDTO(BoardEntity boardEntity){
        BoardDTO boardDTO=new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardPass(boardEntity.getBoardPass());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        boardDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDTO.setBoardCreatedTime(boardEntity.getCreatedTime());
        boardDTO.setBoardUpdatedTime(boardEntity.getUpdatedTime());
        if (boardEntity.getFileAttached()==0){
            boardDTO.setFileAttached(boardEntity.getFileAttached());
        }else{
            //파일 이름을 가져가야함
            //originalFileName,storedFileName:board_file_table(BoardFileEntity)
            //join
            //select *from board_table b,board_file_table bf where b.id=bf.board_id
            //and where b.id={원하는 id}
            List<String> originalFileNameList=new ArrayList<>();
            List<String> storedFileNameList=new ArrayList<>();
            boardDTO.setFileAttached(boardEntity.getFileAttached());
            for (BoardFileEntity boardFileEntity:boardEntity.getBoardFileEntityList()){
                originalFileNameList.add(boardFileEntity.getOriginalFileName());
                storedFileNameList.add(boardFileEntity.getStoredFileName());
            }
            boardDTO.setOriginalFileName(originalFileNameList);
            boardDTO.setStoredFileName(storedFileNameList);

        }
        return boardDTO;
    }
}
