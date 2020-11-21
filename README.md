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

서블릿 애플리케이션
