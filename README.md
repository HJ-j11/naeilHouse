# naeilHouse

### ERD
<img src="./img/ERD.png" width="600px">

### Flowchart
- 주문 및 배송

<img src="./img/flowchart/OrderAndDelivery.png" width="500px">

- 결제

<img src="./img/flowchart/Pay.png" width="500px">

- 게시물 작성

<img src="./img/flowchart/Post.png" width="500px">

### 개발 진행 및 개발 완료 사항
#### 개발 완료 부분


|Members|개발 부분|
|--------|----------------|
|구혜지| - 커뮤니티 글 생성 </br> - 커뮤니티 글 좋아요/댓글 수 카운트 </br> - 커뮤니티 글에 대한 댓글 생성 </br> - 커뮤니티 글 좋아요 누르기 </br> - 회원 가입 시 장바구니 생성 </br> - 상풍 장바구니 담기 </br> - 장바구니 조회 |
|박성준| - 완료사항작성 </br> - 완료사항작성 </br>|
|이채영| - 로그인 </br>  - 주문취소 </br> - 회원 관리 목록 </br> - 관리자 관리 목록 </br> - 판매자 관리 목록/판매자 승인, 판매자 비승인 </br> - 커뮤니티 관리 목록 </br> - 상품 관리 목록 </br> - 주문 관리 목록 </br> - 마이페이지 </br> - 마이페이지/주문 내역 </br> - 마이페이지/주문 내역에 대한 리뷰 생성 </br> - 마이페이지/리뷰 내역 </br> - 마이페이지/좋아요 내역 </br> - 화면 레이아웃 </br>|

#### 개발 진행 부분

|Members|개발 부분|
|--------|---------|
|구혜지| - 장바구니에 담긴 상품 구매 시, 주문 객체 생성 </br> - 상품 장바구니에 담을 시 희망 구매 갯수 넣기|
|박성준| - 진행사항작성 </br> - 진행사항작성|
|이채영| - 진행사항작성 </br>|

### API 명세서

#### 유저
|Function|URL|Method|Description|
|--------|---|------|-----------|
|[회원가입]|/join|POST|
|[회원 로그인]|/login|POST|소비자/ 판매자/ 관리자로 로그인|

<br>

#### 소비자
|Function|URL|Method|Description|
|--------|---|------|-----------------------|
|[커뮤니티]|/community|GET|소비자가 작성하는 글 게시판 이동|
|[커뮤니티]|/community/{id}|GET|게시글 상세 페이지 이동|
|[커뮤니티]|/community/new|GET|새로운 글 작성 페이지 이동|
|[커뮤니티]|/community/write|GET|새로운 글 작성|
|[커뮤니티]|/community/{id}/likes|POST|좋아요 누르기|
|[커뮤니티]|/community/{id}/comments/write|POST|글에 댓글 달기|
|[커뮤니티]|/comments/{id}/put|PUT|글에 게시한 댓글 수정|
|[커뮤니티]|/comments/{id}/delete|DELETE|글에 게시한 댓글 삭제|
|[주문 취소]|/orderlist/{order_id}|GET|배송 완료가 되지 않은 주문 취소|
|[마이페이지]|/user/{consumer_id}|GET|소비자의 정보(좋아요, 프로필이미지, 주문, 리뷰, 좋아요)를 확인할 수 있는 마이페이지로 이동|
|[마이페이지/주문내역]|/user/{consumer_id}/orders|GET|소비자의 주문내역을 확인할 수 있는 페이지로 이동|
|[마이페이지/리뷰내역]|/user/{consumer_id}/reviews|GET|소비자의 리뷰 내역을 확인할 수 있는 페이지로 이동|
|[마이페이지/좋아요내역]|/user/{consumer_id}/likes|GET|소비자의 좋아요 내역을 확인할 수 있는 페이지로 이동|