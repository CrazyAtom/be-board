# BE-Board Project

스프링 부트를 이용한 게시판 백엔드 프로젝트입니다.

## 기술 스택

### 백엔드

- Java
- Spring Boot
- Spring Data JPA
- H2 Database (인메모리)
- Gradle

### 개발 도구

- IntelliJ IDEA
- Git

## 주요 기능

### 사용자 관리

- 회원가입/로그인
- 역할 기반 권한 관리 시스템
    - 기본 사용자 역할: `ROLE_USER`
    - 역할 업데이트 기능

### 게시글 관리

- 게시글 CRUD 작업
    - 생성
    - 조회
    - 수정
    - 삭제

## 프로젝트 구조

```text
src/main/java/com/crazyatom/beboard/
├── BeBoardApplication.java        # 메인 애플리케이션 클래스
├── entity/                       # 도메인 엔티티
│   ├── User.java                # 사용자 엔티티
│   └── Post.java                # 게시글 엔티티
├── repository/                   # JPA 리포지토리
├── service/                      # 비즈니스 로직
├── controller/                   # REST 컨트롤러
└── dto/                         # 데이터 전송 객체

