# BE-Board Project

Spring Boot 기반으로 구현한 간단한 게시판 서비스입니다.  
JWT 인증을 활용한 로그인/회원가입, 게시글 및 댓글 작성 기능을 포함합니다.

> 📌 학습 목적의 중급 프로젝트입니다.  
> 🎯 목적: Spring Boot 전체 흐름 이해 및 RESTful API 설계 경험

## 기술 스택

### 백엔드

- Java 17
- Spring Boot 3.x
- Spring Security + JWT
- JPA (Hibernate)
- MySQL or H2 Database
- Gradle
- Lombok
- RESTful API 설계

### 개발 도구

- IntelliJ IDEA
- Git

## 주요 기능

### 사용자 관리

- 사용자 회원가입 및 로그인 (JWT 인증)
- 로그인 사용자만 작성/수정/삭제 가능
- 역할 기반 권한 관리 시스템
    - 기본 사용자 역할: `ROLE_USER`
    - 역할 업데이트 기능

### 게시글 관리

- 게시글 CRUD 작업
    - 생성
    - 조회
    - 수정
    - 삭제
- 댓글 CRUD 작업
    - 생성
    - 조회
    - 수정
    - 삭제

## 프로젝트 구조

```text
src/main/java/com/crazyatom/beboard/
├── BeBoardApplication.java        # 메인 애플리케이션 클래스
├── entity/                       # 도메인 엔티티
│   ├── Comment.java                # 댓글 엔티티
│   ├── User.java                # 사용자 엔티티
│   └── Post.java                # 게시글 엔티티
├── repository/                   # JPA 리포지토리
├── service/                      # 비즈니스 로직
├── controller/                   # REST 컨트롤러
└── dto/                         # 데이터 전송 객체
```

---

## ▶️ 실행 방법

1. 프로젝트 클론

```bash
git clone https://github.com/CrazyAtom/be-board.git
cd be-board
````

2. Gradle 빌드

```bash
./gradlew build
```

3. 애플리케이션 실행

```bash
./gradlew bootRun
```

4. 웹 브라우저에서 `http://localhost:8080`에 접속하여 애플리케이션을 확인합니다.
5. API 테스트는 Postman 또는 curl을 사용하여 수행할 수 있습니다.
6. API 문서는 Swagger를 통해 확인할 수 있습니다. `http://localhost:8080/swagger-ui/index.html`
