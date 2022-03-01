# 네이버 예약 시스템 클론 프로젝트

네이버 부스트코스 강의 참고해 기술스택 변경으로 재구성한 클론 프로젝트

- 개발 언어, 프레임워크, 라이브러리 : SpringBoot, Spring Security, MyBatis, Spring Data JPA, Thymeleaf, JWT, Querydsl, MySQL, AWS, Javascript, jQuery, Ajax

- 주요 기능
  -> 회원가입/로그인, 카테고리, 메인페이지, 상세페이지, 댓글, 예약하기

-  특이 사항
  -> Spring Security를 활용한 토큰(jwt) 기반 로그인
  -> 기존의 MyBatis로 구현한 코드를 JPA로 재구현 진행
  -> Join, 조건문이 복잡한 쿼리들은 QueryDSL 활용해서 해결
  -> 무분별한 Dom Element Selector 방지를 위한 캐싱 활용

- 참고
  -> MyBatis를 활용한 레거시 프로젝트 저장소 : https://github.com/honinbo6355/naver_boost_course
  -> JPA로 재구성한 프로젝트 저장소 : https://github.com/honinbo6355/naver_boost_course_refactor
  -> 배포 URL : http://ec2-3-37-7-111.ap-northeast-2.compute.amazonaws.com:8083/mainpage
