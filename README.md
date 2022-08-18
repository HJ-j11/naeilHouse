# 내일의 집(naeilHouse)

### 설명

오늘의 집(<a href="http://ohou.se">ohou.se</a>)을 차용한 쇼핑몰 만들기 

### 개발 환경

- 개발 언어 및 프레임워크 : Java 8,  Spring Boot 
- 기타 개발 툴 : Thymeleaf, IntelliJ
- 배포 : AWS EC2 
- 데이터베이스 : H2, MariaDB (배포용)

### 기간
2022.03.14 ~ 진행 중 

### 인원
3명 (백엔드 3명) 

### [화면 정의](https://github.com/HJ-j11/naeilHouse/wiki/%ED%8E%98%EC%9D%B4%EC%A7%80-%EC%A0%95%EB%A6%AC)

<br/>

### ERD
<img src="./img/HouseERD.png" width="600px">

### Flowchart
- 주문 및 배송

<img src="./img/flowchart/OrderAndDelivery.png" width="500px">

- 결제

<img src="./img/flowchart/Pay.png" width="500px">

- 게시물 작성

<img src="./img/flowchart/Post.png" width="500px">

#### 권한 설명

|ROLE|접근 가능 URL|역할 설명|
|--------|---------|-------------|
|Consumer| | |
|Seller| | |
|Admin|admin/*|- 전체적인 소비자, 판매자, 커뮤니티, 댓글, 상품에 대한 전체 데이터 확인 </br> - 판매자의 승인을 관리하여 승인 취소 및 승인 수락 설정|

### 개발 진행 및 개발 완료 사항
#### 개발 완료 부분


|Members|개발 부분|
|--------|----------------|
|구혜지| - 커뮤니티 글 생성 </br> - 커뮤니티 글 좋아요/댓글 수 카운트 </br> - 커뮤니티 글에 대한 댓글 생성 </br> - 커뮤니티 글 좋아요 누르기 </br> - 커뮤니티 댓글 삭제 </br> - 회원 가입 시 장바구니 생성 </br> - 상품 장바구니 담기 </br> - 장바구니 조회 </br> - 장바구니 상품 주문하기 </br> - 배송 완료 Status 변경 </br> - 커뮤니티 글 조회수 카운트 </br>|
|박성준| - 완료사항작성 </br> - 완료사항작성 </br>|
|이채영| - 로그인(Session 이용) </br>  - 주문취소 </br> - 회원 관리 목록 </br> - 관리자 관리 목록 </br> - 판매자 관리 목록/판매자 승인, 판매자 비승인 </br> - 커뮤니티 관리 목록 </br> - 상품 관리 목록 </br> - 주문 관리 목록 </br> - 마이페이지 </br> - 마이페이지/주문 내역 </br> - 마이페이지/주문 내역에 대한 리뷰 생성 </br> - 마이페이지/리뷰 내역 </br> - 마이페이지/좋아요 내역 </br> - 화면 레이아웃 </br>|

#### 개발 진행 부분

|Members|개발 부분|
|--------|---------|
|구혜지| - 통합 검색 기능|
|박성준| - 진행사항작성 </br> - 진행사항작성|
|이채영| - 페이징 처리  </br> - jenkins 연동 </br>|

### [API 명세서](./doc/api_table.md)
