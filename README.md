#  📅 일정 관리 API

이 프로젝트는 일정 관리 기능을 제공하는 API로, `Postman`을 사용하여 테스트할 수 있습니다. 

일정, 회원, 댓글의 CRUD 기능과 로그인 기능을 제공하며, 3계층 구조(`3 Layer Architecture`)를 적용하여 유지보수성을 향상시켰습니다. 

`JPA`를 통해 MySQL 데이터베이스와 연동됩니다.

## 💡 주요 기능
- 일정 CRUD
- 회원 CRUD
- 댓글 CRUD
- 로그인 기능
- 비밀번호 검증
- 페이징 처리
- 유효성 검증 및 예외 처리

## 🛠️ 개발 환경
![Java](https://img.shields.io/badge/Java-007396?style=flat&logo=java&logoColor=white)  
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat&logo=springboot&logoColor=white)  
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=mysql&logoColor=white)  
![JPA](https://img.shields.io/badge/JPA-6E4C13?style=flat&logo=jpa&logoColor=white)  
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ%20IDEA-000000?style=flat&logo=intellij-idea&logoColor=white)  
![Git](https://img.shields.io/badge/Git-F05032?style=flat&logo=git&logoColor=white)  
![GitHub](https://img.shields.io/badge/GitHub-181717?style=flat&logo=github&logoColor=white)  
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=flat&logo=postman&logoColor=white)  

## 📝 일정 관리 API 명세서
**[![Postman](https://img.shields.io/badge/Postman-FF6C37?style=flat&logo=postman&logoColor=white)](https://documenter.getpostman.com/view/43185835/2sB2cSfhku) Click Here**

## 📊 ERD
![image](https://github.com/user-attachments/assets/52e68231-d9db-4763-b1d6-4d1c6579a3b1)

## 📦 패키지 구조
```
📁 src
 └── 📁 main
     └── 📁 java
         └── 📁 com.thezz9.schedulerjpa
             ├── 📁 api
             │   ├── 📁 comment
             │   │   ├── 📁 controller
             │   │   ├── 📁 dto
             │   │   ├── 📁 entity
             │   │   ├── 📁 repository
             │   │   └── 📁 service
             │   ├── 📁 schedule
             │   │   ├── 📁 controller
             │   │   ├── 📁 dto
             │   │   ├── 📁 entity
             │   │   ├── 📁 repository
             │   │   └── 📁 service
             │   ├── 📁 user
             │   │   ├── 📁 controller
             │   │   ├── 📁 dto
             │   │   ├── 📁 entity
             │   │   ├── 📁 repository
             │   │   └── 📁 service
             │   └── 📁 login
             │       ├── 📁 controller
             │       ├── 📁 dto
             │       └── 📁 service
             └── 📁 common
                 ├── 📁 config
                 ├── 📁 filter
                 ├── 📁 handler
                 └── 📁 logger
```

## 트러블슈팅
**[velog](https://velog.io/@harvard--/Spring-%EC%9D%BC%EC%A0%95-%EA%B4%80%EB%A6%AC-API-with-JPA)**
