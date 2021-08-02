택시 배차 요청과 수락을 사용자 타입에 따라 할 수 있도록 함.

사용자는 토큰을 통해 API에 접근할 수 있도록 하였음
(단순한 인코딩으로 작성된 토큰.)

MVC stack 으로 구현됨.

**API endpoints**

* Taxi-Requests-API 
  
    GET http://localhost:8080/taxi-requests
  
    POST http://localhost:8080/taxi-requests

    POST http://localhost:8080/taxi-requests/{taxiRequestId}/accept


  
* Users-API

  POST http://localhost:8080/users/sign-in
  
  POST http://localhost:8080/users/sign-up

**실행**
1. mvn clean install
2. docker build -t taxi-allocation .
3. docker run -p 8080:8080 --name taxi-allocation -d taxi-allocation


**Database Objects**
``` sql
create sequence taxi_seq start with 1 increment by 1
create sequence user_seq start with 1 increment by 1
create table taxi_allocation (id integer not null, accepted_at timestamp, address varchar(255), created_at timestamp, driver_id integer not null, passenger_id integer not null, status varchar(255), updated_at timestamp, version bigint not null, primary key (id))
create table user (id integer not null, created_at timestamp, email varchar(255), password varchar(255), updated_at timestamp, user_type varchar(255), primary key (id))
```


