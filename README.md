# 내일의 집(naeilHouse)

### 설명

오늘의 집(<a href="http://ohou.se">ohou.se</a>)을 차용한 쇼핑몰 웹페이지 

### 개발 환경

- 개발 언어 및 프레임워크 : Java 8,  Spring Boot 
- 기타 개발 툴 : Thymeleaf, IntelliJ
- 배포 : AWS EC2, Docker
- CICD: Jenkins
- 데이터베이스 : H2, MariaDB (배포용)

### 기간
2022.03.14 ~ 진행 중 

### 인원
| 구혜지  | 박성준 | 이채영 |
| :----: | :----: | :----: | 
| [@HJ-j11](https://github.com/HJ-j11) | [@SJP03](https://github.com/SJP03) | [@chea-young](https://github.com/chea-young) |

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

### [화면 설명](https://github.com/HJ-j11/naeilHouse/wiki/%ED%8E%98%EC%9D%B4%EC%A7%80-%EC%A0%95%EB%A6%AC)

### [개발 진행 및 개발 완료 사항](./doc/progress.md)

### [API 명세서](./doc/api_table.md)
