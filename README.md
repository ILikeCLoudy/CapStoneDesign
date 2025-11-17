# 을지대학교 캡스톤 디자인 - 학생 진로 추천 시스템

## 프로젝트 소개

학생들의 설문조사 데이터를 수집하고 머신러닝 기반 분석을 통해 진로 및 직업 경로를 추천하는 시스템입니다. Spring Boot 기반 RESTful API와 Flask ML 서비스를 결합한 3-tier 아키텍처로 구성되어 있습니다.

## 기술 스택

### Backend
- **Framework**: Spring Boot 3.2.4
- **Language**: Java 17
- **Build Tool**: Gradle 8.7
- **Database**: MySQL 8
- **ORM**: Spring Data JPA (Hibernate)

### Dependencies
- Spring Boot Starter Web (REST API)
- Spring Boot Starter Data JPA (데이터 영속성)
- Spring Boot Starter Validation (입력 검증)
- Spring Boot Starter Thymeleaf (템플릿 엔진)
- MySQL Connector/J
- Lombok (코드 간소화)
- Spring Boot DevTools (개발 도구)

### ML Integration
- **Flask** (Python 기반 ML 서비스)
- RestTemplate (HTTP 통신)

## 시스템 아키텍처

```
[Frontend Client]
      ↓ HTTP/REST
[Spring Boot Backend]
      ↓ JPA/Hibernate
[MySQL Database]

[Spring Boot Backend]
      ↓ HTTP/JSON
[Flask ML Service (Python)]
```

### 디자인 패턴
- **Repository Pattern**: 데이터 접근 추상화
- **Service Layer Pattern**: 비즈니스 로직 분리
- **DTO Pattern**: 계층 간 데이터 전송
- **Builder Pattern**: Lombok을 통한 엔티티 생성

## 프로젝트 구조

```
CapStoneDesign/
├── build.gradle                    # 빌드 설정
├── settings.gradle                 # 프로젝트 설정
├── .gitignore                      # Git 제외 파일
├── gradlew & gradlew.bat          # Gradle 래퍼 스크립트
├── gradle/wrapper/                 # Gradle 래퍼 파일
└── src/
    ├── main/
    │   ├── java/com/EuljiUniv/CapstoneDesign/
    │   │   ├── CapstoneDesignApplication.java    # 메인 애플리케이션
    │   │   ├── Config/                           # 설정 클래스
    │   │   │   ├── Appconfig.java               # RestTemplate 설정
    │   │   │   ├── WebConfig.java               # 정적 리소스 설정
    │   │   │   └── CorsConfig.java              # CORS 설정
    │   │   ├── Controller/                       # REST 컨트롤러
    │   │   │   └── MainController.java          # 메인 API 컨트롤러
    │   │   ├── DTO/                             # 데이터 전송 객체
    │   │   │   ├── AddPersonalRequest.java      # 설문 제출 요청
    │   │   │   ├── AnswerRequest.java           # 결과 응답
    │   │   │   └── ResultRequest.java           # 결과 업데이트 요청
    │   │   ├── Entity/                          # JPA 엔티티
    │   │   │   ├── PersonalEntity.java          # 학생 개인정보
    │   │   │   └── SurveyEntity.java            # 설문 응답
    │   │   ├── Repository/                      # 데이터 접근 계층
    │   │   │   ├── PersonalRepository.java      # 개인정보 레포지토리
    │   │   │   └── SurveyRepository.java        # 설문 레포지토리
    │   │   └── Service/                         # 비즈니스 로직
    │   │       ├── PersonalService.java         # 개인정보 서비스
    │   │       └── SurveyService.java           # 설문 서비스
    │   └── resources/
    │       ├── application.yml                   # 애플리케이션 설정
    │       ├── templates/index.html             # Thymeleaf 템플릿
    │       └── static/index.html                # 정적 HTML
    └── test/
        └── java/com/EuljiUniv/CapstoneDesign/
            └── CapstoneDesignApplicationTests.java
```

## 주요 기능

### 1. 학생 정보 관리
- 학생 개인정보 저장 (이름, 학번, 전공, 성별, MBTI)
- 학번 기반 정보 조회

### 2. 설문조사 시스템
총 15개 문항으로 구성:
- **흥미도 관련 질문** (5개): interest1~5
- **전공 만족도 질문** (5개): majorlike1~5
- **취업 선호도 질문** (5개): jobemploy1~5

### 3. 머신러닝 기반 진로 추천
- Flask ML 서비스 연동
- 설문 데이터 기반 진로 예측
- 예측 결과 저장 및 조회

## API 엔드포인트

| 엔드포인트 | 메서드 | 설명 | 요청 본문 | 응답 |
|----------|--------|------|----------|------|
| `/` | GET | 인덱스 페이지 | - | String |
| `/survey` | POST | 설문 제출 (기본) | AddPersonalRequest | boolean |
| `/survey/update` | PUT | 설문 결과 업데이트 | ResultRequest | SurveyEntity |
| `/survey/ver2` | POST | 설문 제출 + ML 예측 (Flask) | AddPersonalRequest | AnswerRequest |
| `/survey/ver3` | POST | 설문 제출 + ML 예측 (Python 스크립트) | AddPersonalRequest | AnswerRequest |
| `/answer` | POST | 학생 결과 조회 | ResultRequest | AnswerRequest |

### 주요 API 설명

#### `/survey/ver2` (권장)
Flask REST API를 통한 ML 예측
```
1. 설문 데이터 저장
2. Flask 서버로 JSON 전송 (http://localhost:5000/test/pyflask)
3. ML 예측 결과 수신
4. 결과 DB 업데이트
5. AnswerRequest 응답
```

#### `/survey/ver3` (대체 방법)
Python 스크립트 직접 실행
```
1. 설문 데이터 저장
2. ProcessBuilder로 Python 스크립트 실행 (C:\cappython\testRunCode.py)
3. stdout에서 예측 결과 읽기
4. 결과 DB 업데이트
```

## 데이터베이스 스키마

### personal_entity 테이블
| 필드 | 타입 | 제약조건 | 설명 |
|------|------|---------|------|
| id | BIGINT | PK, AUTO_INCREMENT | 고유 ID |
| name | VARCHAR | - | 학생 이름 |
| studentid | VARCHAR | UNIQUE | 학번 |
| major | VARCHAR | - | 전공 |
| sex | VARCHAR | - | 성별 |
| mbti | VARCHAR | - | MBTI 유형 |

### survey_entity 테이블
| 필드 | 타입 | 제약조건 | 설명 |
|------|------|---------|------|
| id | BIGINT | PK, AUTO_INCREMENT | 고유 ID |
| studentid | VARCHAR | - | 학번 (참조) |
| mbti | VARCHAR | - | MBTI 유형 |
| interest1~5 | INT | NOT NULL | 흥미도 문항 |
| majorlike1~5 | INT | NOT NULL | 전공 만족도 문항 |
| jobemploy1~5 | INT | NOT NULL | 취업 선호도 문항 |
| result | INT | NULLABLE | ML 예측 결과 |

## 설치 및 실행

### 1. 사전 요구사항
- Java 17 이상
- MySQL 8
- Gradle 8.7 이상
- Flask ML 서버 (선택사항)

### 2. 데이터베이스 설정
```sql
CREATE DATABASE capstone;
```

`application.yml` 수정:
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3302/capstone
    username: root
    password: [your-password]
```

### 3. 프로젝트 빌드
```bash
./gradlew build
```

### 4. 애플리케이션 실행
```bash
./gradlew bootRun
```

서버는 `http://localhost:8080`에서 실행됩니다.

### 5. Flask ML 서버 실행 (선택)
```bash
# Flask 서버는 포트 5000에서 실행되어야 함
python flask_server.py
```

## 설정 정보

### 애플리케이션 설정 (application.yml)
- **서버 포트**: 8080
- **데이터베이스**: MySQL (localhost:3302)
- **JPA DDL**: create (개발용)
- **SQL 로깅**: 활성화
- **Thymeleaf 캐시**: 비활성화 (개발용)

### CORS 설정
- 모든 오리진 허용 (개발용)
- 모든 메서드 허용
- Credentials 허용

## 개발 히스토리

- 15차 변경: API 전체 완료, FE 연동 대기
- 16차 변경: FE 통신 구현, ML 통합, Flask 통신 추가
- survey/ver2 설문 응답 API 리팩토링 완료
- 개발 환경 배포 환경 분리(ForServerCode branch)

## 라이선스

Eulji University Capstone Design Project

## 기여자

을지대학교 캡스톤 디자인 팀
