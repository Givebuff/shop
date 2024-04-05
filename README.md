반응형 웹 포스기
============
반응형 웹 디자인으로 모바일로도 사용 가능하고<br> 
간단한 ui/ux 디자인으로 별도의 교육 없이 사용 가능한 포스기

기술 스택
----------------------------
<img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"><img src="https://img.shields.io/badge/tailwindCSS-06B6D4?style=for-the-badge&logo=tailwindCSS&logoColor=white"><img src="https://img.shields.io/badge/thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white"><img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=white">
<img src="https://img.shields.io/badge/intellij-000000?style=for-the-badge&logo=intellij idea&logoColor=white">
<img src="https://img.shields.io/badge/sublime text-FF9800?style=for-the-badge&logo=sublimetext&logoColor=white">
<img src="https://img.shields.io/badge/spring boot 3.2.1-6DB33F?style=for-the-badge&logo=spring boot&logoColor=white">
<img src="https://img.shields.io/badge/querydsl-06B6F4?style=for-the-badge&logo=querydsl&logoColor=white">
<img src="https://img.shields.io/badge/jpa-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
<img src="https://img.shields.io/badge/jpql-06B6F4?style=for-the-badge&logo=jpql&logoColor=white">
<img src="https://img.shields.io/badge/jquery-0769AD?style=for-the-badge&logo=jquery&logoColor=white">
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
<img src="https://img.shields.io/badge/source tree-0052CC?style=for-the-badge&logo=sourcetree&logoColor=white">
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
<img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">

- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)


주요기능(구현중)
--------------
- 홀, 배달, 예약 주문 관리
- 고객 주문내역 관리
- 장부 관리
- 카카오 맵api를 활용한 위치 확인
- 지출 관리

사용방법
-----------------------------------------------
1. 자바를 다운받고 환경변수에 등록합니다.
2. src/main/resources 에서 properties폴더를 생성 후 properties_example에 있는 파일을 전부 옮겨준 뒤 db.properties를 자신의 db에 맞게 설정해준다.
3. ```shell
   // linux java build
   ./gradlew build
   // build된 jar파일 경로로 이동
   cd ./build/libs
   // jar 실행
   java -jar 생성된jar.jar
   ```

실행 이미지
--------------------------
- 홀 관리
<img src="/img/hole.png"  width="400px"/>
