<div align="center">

## 🙋Backend Developers

|                           Backend                            |                           Backend                            |                           Backend                            |
| :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
| ![img](https://avatars.githubusercontent.com/u/64529208?v=4) | ![img](https://avatars.githubusercontent.com/u/45715824?v=4) | ![img](https://avatars.githubusercontent.com/u/78407939?v=4) |
|             [김주현](https://github.com/KJH-Sun)             |             [정구아](https://github.com/gojung)              |            [채상엽](https://github.com/saint6839)            |



## 🛠️ Tech Stack

**Communication**

<img src="https://img.shields.io/badge/GitHub-181717?style=flat-square&logo=GitHub&logoColor=white"/> <img src="https://img.shields.io/badge/Jira-0052CC?style=flat-square&logo=Jira&logoColor=white"/> <img src="https://img.shields.io/badge/Slack-4A154B?style=flat-square&logo=Slack&logoColor=white"/> <img src="https://img.shields.io/badge/Notion-000000?style=flat-square&logo=Notion&logoColor=white"/>

**Server**

<img src="https://img.shields.io/badge/Java-FF9E0F?style=flat-square&logo=&logoColor=white"/> <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat-square&logo=springboot&logoColor=white"/> <img src="https://img.shields.io/badge/Spock-85EA2D?style=flat-square&logo=Spock&logoColor=white"/>

**Api Spec**

<img src="https://img.shields.io/badge/Swagger-85EA2D?style=flat-square&logo=Swagger&logoColor=white"/> <img src="https://img.shields.io/badge/Rest Docs-85EA2D?style=flat-square&logo=Rest Docs&logoColor=white"/>

**Database**

<img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white"/> <img src="https://img.shields.io/badge/Adminer-34567C?style=flat-square&logo=Adminer&logoColor=white"/> <img src="https://img.shields.io/badge/Flyway-CC0200?style=flat-square&logo=Flyway&logoColor=white"/>

**Infrastructure**

<img src="https://img.shields.io/badge/AWS EC2-FF9900?style=flat-square&logo=Amazon-EC2&logoColor=white"/> <img src="https://img.shields.io/badge/Docker-2496ED?style=flat-square&logo=Docker&logoColor=white"/> <img src="https://img.shields.io/badge/Terraform-7B42BC?style=flat-square&logo=Terraform&logoColor=white"/> <img src="https://img.shields.io/badge/AWS RDS-527FFF?style=flat-square&logo=Amazon-RDS&logoColor=white"/> <img src="https://img.shields.io/badge/AWS ECS-FF9900?style=flat-square&logo=Amazon-ECS&logoColor=white"/> <img src="https://img.shields.io/badge/AWS Event Bridge-FF9900?style=flat-square&logo=Amazon-EventBridge&logoColor=white"/>

## 🗒️Entity Relationship Diagram(ERD)

<img width="1017" alt="image" src="https://user-images.githubusercontent.com/78407939/209675671-0bd70e93-a773-4005-9000-5ed8a78755a8.png">

## 🗺️Information Architecture(IA)

![I A](https://user-images.githubusercontent.com/78407939/209675815-7e1e6669-16b2-4944-aaae-baaf5cf8f485.png)

## 🏢Service Structure
짝심삼일의 서비스 구성도입니다.
비용 절감을 위해 AWS 프리티어를 한정된 자원 속에서 가장 효율적으로 이용할 수 있는 구조를 채택하였습니다. <br>
외부 서비스는 로그인시 Kakao API를 이용하고, 푸시 알림 전달시 FCM을 이용합니다. <br>
배치성 기능 구현을 위해 EventBridge를 사용하였습니다.
<img width="1495" alt="image" src="https://user-images.githubusercontent.com/78407939/211797940-50db3e8e-af23-4497-812f-8602eb9341e5.png">

</div>



### local 환경 서버 구동 방법
1. 로컬 DB 구동
- `/resources/local-develop-environment/README.md` 참고
2. 환경변수 추가
- `${TOKEN_SECRETKEY}`
- `${FIREBASE_PRIVATE_KEY_ID}`
- `${FIREBASE_PRIVATE_KEY}`
- `${KAKAO_ADMINKEY}`
- `${DB_HOSTNAME}`
- `${DB_USERNAME}`
- `${DB_PASSWORD}`
3. 프로필 설정
- `Edit Configurations...` -> `Add VM Options` -> `-Dspring.profiles.active=local` 추가. 또는
- `Edit Configurations...` -> `Active profiles` -> `local` 추가
4. 애플리케이션 실행
