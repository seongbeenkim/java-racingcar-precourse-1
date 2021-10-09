# 자동차 경주 게임
## 진행 방법
* 자동차 경주 게임 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 과제를 제출한다.

## 과제 제출 과정
* [과제 제출 방법](https://github.com/next-step/nextstep-docs/tree/master/precourse)

## 구현해야할 기능 목록

- [] 입력
  - [] 자동차 이름 입력
    - [] [ERROR] : `,`이 포함되어 있지 않을 경우
  - [] 시도할 회수 입력
    - [] [ERROR] : 숫자가 아닐 경우
  - [] [ERROR] : 아무것도 입력하지 않을 경우    

- [] 출력
  - [] n대의 자동차 이름, 전진 거리 출력   
  - [] 우승자 출력
    - [] 한 명 이상일 경우, `,`로 구분하여 출력
 
- [] 도메인
  - [x] 이름과 위치를 가진 자동차 객체 생성
    - [x] [ERROR] : 이름이 5자 초과일 경우
    - [x] [ERROR] : 이름이 null 또는 빈 문자열인 경우
    - [x] [ERROR] : 자동차의 위치가 0 미만일 경우
  - [] 0 ~ 9 숫자 중 랜덤으로 전진 조건 1개의 숫자 선택
  - [] 4 이상일 경우 전진, 3 이하일 경우 멈춤
  - [] n대의 자동차 전진
  - [] n대 중 가장 많이 전진한 자동차의 위치 찾기
  - [] 가장 많이 전진한 자동차들 반환
