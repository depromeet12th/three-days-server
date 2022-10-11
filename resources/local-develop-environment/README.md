# docker-compose 를 통한 local 개발 환경 구축
## docker-compose 띄우기
```
cd local-develop-environment
docker-compose up -d

# 완전 종료 : 모든 데이터 사라짐
docker-compose down

# 일시 정지 : 데이터 유지
docker-compose stop
```

## 초기화 파일
* `mysql-init.d` : `*.sql` 파일들로 database 생성 등을 수행한다.

* 초기화 파일 수정시에는 `docker-compose down` 으로 완전 초기화를 해야한다.

## mysql client
* 서비스용 계정 : `three-days-local`
* 서비스용 비밀번호 : `three-days-local`
* [docker localhost adminer](http://localhost:18080) 로 접속하면 [adminer](https://www.adminer.org) Web DB Client 로 DB조회 조작 가능.

```shell
docker exec -it three-days-mysql8 mysql -uroot -p
# 비밀번호 root 로 접속

mysql> show databases;
mysql> use master;
mysql> show tables;
```
