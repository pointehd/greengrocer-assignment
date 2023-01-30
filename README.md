## 요구사항 
- 청과물 가격을 조회할 수 있는 API중계 웹 어플리케이션 서버 구현

## 가정사항 
- API 중계는 실시간으로 조회한다. 
- 외부 API에 의존적이기 때문에 외부 API 오류 발생시 적절한 에러로 대응한다.

## Version
- java : 17
- spring boot : 2.7.8

## Style Guide
- [java-google-style](https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml)
 
## 구현 수정 필요 사항 
> 토큰 재발급 요청시 webclient retry 사용하고 싶으나 비동기 실행으로 인한 오류 수정필요
  - 참고사항
  - [stack overflow](https://stackoverflow.com/questions/64355088/spring-webclient-call-method-in-retry)
  - [medium blog](https://geraldnguyen.medium.com/retry-webclient-request-f058fa4c337f)
  - [stack overflow - 같은오류 답변 없음.](https://stackoverflow.com/questions/75111657/throws-java-lang-illegalstateexception-block-blockfirst-blocklast-when-re)