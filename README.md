# 게시판 주요기능

1. 글쓰기(/board/save)
2. 글목록(/board)
3. 글조회(/board/{id})
4. 글수정(/board/update/{id})
-상세화면에서 수정버튼 클릭
-서버에서 게시글의 정보를 가지고 수정화면 클릭
-제목,내용 수정 입력 받아서 서버로 요철
-수정 처리
5. 글삭제(/board/delete/{id})
6. 페이징처리(/board/paging)
   - /board/paging?page=2
   - /board/paging/2
7. 파일(이미지)첨부
   - 단일 파일 첨부
   - 다중 파일 첨부
   - 파일 첨부와 관련하여 추가될 부분들
     - save.html
     - BoardDto
     - BoardService.save()
     - BoardEntity
     - BoardFileEntity, BoardFileRepository 추가
     - detail.html
   - github에서의 코드의 변경점을 잘 살펴보기.