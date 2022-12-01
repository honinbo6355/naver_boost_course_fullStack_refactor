# 네이버 예약 시스템 클론 프로젝트

기술스택 변경으로 재구성한 네이버 예약 시스템

- 개발 언어, 프레임워크, 라이브러리 : SpringBoot, Spring Security, MyBatis, JPA, Thymeleaf, Querydsl, MySQL, Javascript, jQuery, Redis, Docker, AWS

- 주요 기능
  - 회원가입/로그인, 카테고리, 메인페이지, 상세페이지, 댓글, 결제

- 상세 내용
  - Spring Security를 활용한 토큰(jwt) 기반 로그인
  - redis를 활용한 재발급 토큰 관리
  - 기존 MyBatis 코드를 JPA로 100% 전환 달성
  - 아임포트 API를 활용한 카카오페이 결제 연동
  - 결제 위변조 검증 로직 학습 및 적용

- 참고
  - MyBatis를 활용한 레거시 프로젝트 저장소 : https://github.com/honinbo6355/naver_boost_course
