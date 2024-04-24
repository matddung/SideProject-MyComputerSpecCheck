# SideProject - MyComputerSpecCheck
*****
## 개발 동기
> 내 컴퓨터의 사양을 체크해주는 데스크톱 애플리케이션과 웹 페이지를 연동하여 웹 페이지에서 내 컴퓨터의 사양이 어느 정도의 성능을 수행하는지, 현재 나온 제품들의 성능 중 몇 번째인지를 비교해 주는 사이트를 만들고 싶다. 
*****
## 타임라인
>> ### 2024. 04. 20 (시작)
> java - awt, swing을 이용하여 GUI개발
> 
> Java 표준 API만을 사용해서는 시스템 정보를 직접적으로 확인하는 것이 어렵다. 
> 
> Runtime.exec() 메소드를 사용하여 운영 체제의 명령어를 실행하고, 그 결과를 가져올 수 있다.
> 
>> ### 2024. 04. 24
> 웹 서버와 통신하여 데스크톱 애플리케이션에서 추출한 정보를 서버에 전송하여 DB에서 업데이트
*****
## Version Rules
> java version : 17.0.9 
> mariadb.jdbc.java.client
> springframework.security.spring.core
> io.jsonwebtoken.jjwt.api : 0.11.2
> > io.jsonwebtoken.jjwt.impl : 0.11.2
> > io.jsonwebtoken.jjwt.jackson : 0.11.2