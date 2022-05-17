# naeilHouse

### ERD
![HOUSE (1)](https://user-images.githubusercontent.com/66746270/168729993-3b27f8d7-2693-4e8a-a152-17d19e0eb745.jpg)
### Flowchart
![flowchart (1)](https://user-images.githubusercontent.com/66746270/168730228-1906091d-ccaa-46ad-b260-1c47dc61f1f6.jpg)
### 개발 진행 및 개발 완료 사항
#### 개발 완료 부분


|Members|개발 부분|
|--------|---------|
|구혜지| - 커뮤니티 글 생성 </br> - 커뮤니티 글 좋아요/댓글 수 카운트 </br> - 커뮤니티 글에 대한 댓글 생성 </br> - 커뮤니티 글 좋아요 누르기 </br> - 회원 가입 시 장바구니 생성 </br> - 상풍 장바구니 담기 </br> - 장바구니 조회 |
|박성준| - 완료사항작성 </br> - 완료사항작성|
|이채영| - 완료사항작성 </br> - 완료사항작성|

#### 개발 진행 부분

|Members|개발 부분|
|--------|---------|
|구혜지| - 장바구니에 담긴 상품 구매 시, 주문 객체 생성 </br> - 상품 장바구니에 담을 시 희망 구매 갯수 넣기|
|박성준| - 진행사항작성 </br> - 진행사항작성|
|이채영| - 진행사항작성 </br> - 진행사항작성|

### API 명세서

#### 유저
|Function|URL|Method|
|--------|---|------|
|[회원가입](API_Doc/Join.md)|/join|POST|
|[회원 로그인](API_Doc/Login.md)|/login|POST|

<br>

#### 소비자
|Function|URL|Method|Description|
|--------|---|------|-----------|
|[커뮤니티](API_Doc/Post.md)|/community|GET|소비자가 작성하는 글 게시판 이동|
|[커뮤니티](API_Doc/Post.md)|/community/{id}|GET|게시글 상세 페이지 이동|
|[커뮤니티](API_Doc/Post.md)|/community/new|GET|새로운 글 작성 페이지 이동|
|[커뮤니티](API_Doc/Post.md)|/community/write|GET|새로운 글 작성|
|[커뮤니티](API_Doc/Post.md)|/community/{id}/likes|POST|좋아요 누르기|
|[커뮤니티](API_Doc/Post.md)|/community/{id}/comments/write|POST|글에 댓글 달기|
|[커뮤니티](API_Doc/Post.md)|/comments/{id}/put|PUT|글에 게시한 댓글 수정|
|[커뮤니티](API_Doc/Post.md)|/comments/{id}/delete|DELETE|글에 게시한 댓글 삭제|
