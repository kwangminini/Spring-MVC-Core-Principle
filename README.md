# Spring-MVC-Core-Principle
## 1. 스프링 웹 MVC 핵심 원리 <br/>
M : Model <br/>
V : View <br/>
C : Controller <br/>

Model : 도메인 객체 또는 DTO로 화면에 전달할 또는 화면에서 전달 받은 데이터를 담고 있는 객체 <br/>
View : 데이터를 보여주는 역할. 다양한 형태로 보여줄 수 있다. ex) HTML, JSON, XML, JSP, Thymeleaf... <br/>
Controller : 사용자 입력을 받아 모델 객체의 데이터를 ㅕㄴ경하거나, 모델 객체를 View에 전달하는 역할 <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; \- 입력값 검증 <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; \- 입력 받은 데이터로 모델 객체 변경 <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; \- 변경된 모델 객체를 뷰에 전달 <br/>

**MVC 패턴의 장점**
- 동시 다발적(Simultaneous) 개발 - 백엔드 개발자와 프론트엔드 개발자가 독립적으로 개발을 진행할 수 있다. 
- 높은 결합도 - 논리적으로 관련있는 기능을 하나의 컨트롤러로 묶거나, 특정 모델과 관련있는 뷰를 그룹화 할 수 잇다.
- 낮은 의존도 - 뷰, 모델, 컨트롤러는 각각 독립적이다.
- 개발 용이성 - 책임이 구분되어 있어 코드 수정하는 것이 편하다.
- 한 모델에 대한 여러 형태의 뷰를 가질 수 있다.

**MVC 패턴의 단점**
- 코드 네비게이션 복잡함
- 코드 일관성 유지에 노력이 필요함
- 높은 학습 곡선

------------------------

### 서블릿 애플리케이션
서블릿 (Servlet)
- 자바 엔터프라이즈 에디션은 웹 애플리케이션 개발용 스팩과 API제공
- 요청 당 쓰레드 사용
- 그 중에 가장 중요한 클래스중 하나가 HttpServlet

서블릿의 장점 
- 빠르다
- 플랫폼 독립적
- 보안
- 이식성

서블릿 엔진 또는 서블릿 컨테이너 (톰캣, 제티, 언더토, ...)
- 세션 관리
- 네트워크 서비스
- MIME 기반 메시지 인코딩 디코딩
- 서블릿 생명주기 관리

서블릿 생명주기
- 서블릿 컨테이너가 서블릿 인스턴스의 init() 메소드를 호출하여 초기화
  - 최초 요청을 받았을 때 한번 초기화하고 나면 그 다음 요청부터는 이 과정을 생략
- 서블릿이 초기화 된 다음부터 클라이언트의 요청을 처리할 수 있다. 각 요청은 별도의 쓰레드로 처리하고 이때 서블릿 인스턴스의 service() 메소드를 호출
  - 이 안에서 HTTP 요청을 받고 클라이언트로 보낼 HTTP 응답을 만든다
  - service()는 보통 HTTP Method에 따라 doGet(), doPost() 등으로 처리를 위임
  - 따라서 보통 doGet() 또는 doPost()를 구현
- 서블릿 컨테이너 판단에 따라 해당 서블릿을 메모리에서 내려야 할 시점에 destroy()를 호출

서블릿 리스너
- 웹 플리케이션에서 발생하는 주요 이벤트를 감지하고 각 이벤트에 특별한 작업이 필요한 경우에 사용할 수 있다
  - 서블릿 Context 수준의 이벤트
    - Context LifeCycle 이벤트
    - Context Attribute 변경 이벤트
  - 세션 수준의 이벤트
    - Session LifeCycle 이벤트
    - Session Attribute 변경 이벤트

서블릿 필터
- 들어온 청을 서블릿으로 보내고, 또 서블릿이 작성한 응답을 클라이언트로 보내기 전에 특별한 처리가 필요한 경우에 사용

------------------------

### DispatcherServlet 동작 원리
DispatcherServlet 초기화
- 다음의 특별한 타입의 빈들을 찾거나, 없으면 기본 전략(DispatcherServlet.properties)에 해당하는 빈들을 등록한다
- HandlerMapping : 핸들러를 찾아주는 인터페이스
- HandlerAdapter : 핸들러를 실행하는 인터페이스
- HandlerExceptionResolver
- ViewResolver

DispatcherServlet 동작 순서 <br/>
&nbsp;&nbsp;1. 요청을 분석한다. <br/>
&nbsp;&nbsp;2. (핸들러 맵핑에게 위임하여) 요청을 처리할 핸들러를 찾는다. <br/>
&nbsp;&nbsp;3. (등록되어 있는 핸들러 어댑터 중에) 해당 핸들러를 실행할 수 있는 "핸들러 어댑터"를 찾는다 <br/>
&nbsp;&nbsp;4. 찾아낸 "핸들러 어댑터"를 사용해서 핸들러의 응답을 처리한다 <br/>
&nbsp;&nbsp;5. (부가적으로) 예외가 발생했다면, 예외 처리 핸들러에 요청 처리를 위임한다 <br/>
&nbsp;&nbsp;6. 핸들러의 리턴값을 보고 어떻게 처리할지 판단한다. <br/>
 - 뷰 름에 해당하는 뷰를 찾아서 모델 데이터를 렌더링
 - @ResponseEntity가 있다면 Converter를 사용해서 응답 본문을 만든다 <br/>
7. 최종적으로 응답을 보낸다<br/>

DispatcherServlet의 기본 전략
- DispatcherServlet.properties

MultipartResolver
- 파일 업로드 요청 처리에 필요한 인터페이스
- HttpServletRequest를 MultipartHttpServletRequest로 변환해주어 요청이 담고 있는 File을 꺼낼 수 있는 API 제공

LocaleResolver
- 클라이언트의 치(Locale) 정보를 파악하는 인터페이스
- 기본 전략은 요청의 accept-language를 보고 판단

ThemeResolver
- 애플리케이션에 설정된 테마를 파악하고 변경할 수 있는 인터페이스

HandlerMapping
- 요청을 처리할 핸들러를 찾는 인터페이스

HandlerAdapter
- HandlerMapping이 찾아낸 '핸들러'를 처리하는 인터페이스 **(RequestMappingHandlerMapping을 가장 많이 사용)**
- 스프링 MVC 확장력의 핵심

HandlerExceptionResolver
- 요청 처리 중에 발생한 에러 처리하는 인터페이스  **(RequestMappingHandlerAdapter를 가장 많이 사용)**

RequestToViewNameTranslator
- 핸들러에서 뷰 이름을 명시적으로 리턴하지 않는 경우, 요청을 기반으로 뷰 이름을 판한하는 인터페이스

ViewResolver
- 뷰 이름(String)에 해당하는 뷰를 찾아내는 인터페이스

FlashMapManager
- FlashMap 인스턴스를 가져오고 저장하는 인터페이스
- FlashMap은 주로 리다이렉션을 사용할 때 요청 매개변수를 사용하지 않고 데이터를 전달하고 정리할 때 사용

스프링 부트 사용하지 않는 스프링 MVC
- 서블릿 컨테이너(ex - 톰캣)에 등록한 웹 애플리케이션(WAR)에 DispatcherServlet을 등록
  - web.xml에 서블릿 등록
  - 또는 WebApplicationInitializer에 자바코드로 서블릿 등록 (스프링 3.1+, 서블릿 3.0+)
- 세부 구성 요소는 Bean 설정하기 나름

스프링 부트로 사용하는 스프링 MVC
- 자바 애플리케이션에 내장 톰캣을 만들고 그 안에 DispatcherServlet을 등록
  - 스프링 부트 자동 설정이 자동으로 해줌
- 스프링 부트의 주관에 따라 여러 인터페이스 구현체를 Bean으로 등록

**결론 : 굉장히 복잡한 Servlet == DispatcherServlet **

