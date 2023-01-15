# **chumaengi**
### 추맹이(추수를 향해 달려가는 알맹이)
***
## 추진 배경 및 목적
* 추진 배경
  * 귀농, 귀촌인들을 위한 특화된 기능을 제공해주는 플랫폼의 부재
  * 초보 귀농인들은 벼의 질병이나 해충에 대해 잘 알지 못하여 잘못된 정보로 인한 피해가 생길 수 있음
  * 귀농인들끼리의 온라인 커뮤니티 공간의 수가 매우 적어 농사에서 중요한 노하우, 시행 착오 등의 공유가 어려움
* 목적
  * PC에서 귀농인들간의 정보 공유를 위한 웹 사이트를 제공
  * PC에서 벼 판매를 할 경우를 위한 쌀값 예측 제공
  * 모바일에서 벼의 질병이나 해충을 촬영하여 이를 분석한 결과와 일치하는 질병이나 해충 정보 제공
  * 모바일에서 개인적인 농사 일지를 사용하여 벼의 성장일지를 기록할 수 있도록 한다

## 맡은 부분
  * 추맹이 웹에서 로그인, 회원가입, 공지사항, QnA, 정보공유, 관리자 페이지 구현에 필요한 Domain, Service, Controller, Dto, JS 작성을 담당했습니다.

## 개발 환경
* HTML5, CSS3, JavaScript, Bootstrap, Ajax, JQuery
* Springboot, MySQL, Java 11.0.13
* Streamlit, pandas, plotly, python
* androidstudio, TensorFlowLite

## 팀원 역할
<img src="https://user-images.githubusercontent.com/97610532/212548229-7cf626c7-8164-496d-8c62-35746bb91a4f.png" width="600" height="400"/>


## DB 설계
<img src="https://user-images.githubusercontent.com/97610532/212548663-8c9ff573-3dee-4742-b84d-e07ca6e307a2.png" width="600" height="400"/>

## 동작 과정
* 웹

![image](https://user-images.githubusercontent.com/97610532/211458737-776008c1-0bc9-4111-8385-9e22c7774ece.png)

## 시연
* 메인 페이지
   * 회원일 경우 로그인하게 되면 마이 페이지와 쌀값 그래프, 게시판들을 이용할 수 있으며, 공지사항의 게시글은 보기만 가능하고, 나머지 게시판들은 게시글과 댓글 기능을 사용할 수 있습니다. 회원이 아닐 경우 회원가입을 통해 가입할 수 있습니다.

   * 관리자일 경우 로그인하게 되면 마이 페이지와 회원목록과 쌀값 그래프, 게시판들을 이용할 수 있으며, 공지사항의 글만 작성이 가능하며, 다른 게시판은 보기만 가능했습니다. 회원 목록 페이지를 통해 회원을 강제 삭제할 수 있습니다.

![image](https://user-images.githubusercontent.com/97610532/211460318-0469be8a-851f-4c21-8d8a-1338cc157903.png)
![image](https://user-images.githubusercontent.com/97610532/211460342-84376c74-306a-4595-86e2-2c30fd18f337.png)


* 정보공유게시판
  * 귀농인들이 자신의 노하우 혹은 시행착오를 공유한다. 경험 부족으로 인해 어려움을 겪는 귀농, 귀촌인들이 서로의 경험을 공유하며 벼농사 관련 정보를 얻을 수 있다.
  * 정보 공유 게시판에 들어가면 회원의 경우 글쓰기가 가능하며 관리자의 경우는 글쓰기가 불가능합니다. 게시글마다 댓글을 달 수 있습니다. 검색 기능을 통해 검색창에 입력된 키워드가 들어가는 제목, 내용, 글쓴이가 존재하는 경우 그 리스트가 출력됩니다. 
  
![image](https://user-images.githubusercontent.com/97610532/211460583-7d26ea09-c9c9-445b-b1fa-07f5c5ceda34.png)


* 질문 게시판
  * 벼농사를 하다가 문제가 발생할 경우 빠르게 다른 귀농인들에게 해결 방안에 대한 정보를 얻을 수 있다.
  * QnA 게시판에 들어가면 회원의 경우 글쓰기가 가능하며 관리자의 경우는 글쓰기가 불가능합니다. 게시글마다 댓글을 달 수 있습니다. 검색 기능을 통해 검색창에 입력된 키워드가 들어가는 제목, 내용, 글쓴이가 존재하는 경우 그 리스트가 출력됩니다. 
  
 ![image](https://user-images.githubusercontent.com/97610532/211460753-5fc6344a-4b50-4fcf-9dfd-629a4f2a16cc.png)


* 공지사항 게시판
  * 웹 페이지 관련 주의사항들을 관리자가 올리고 관리한다.
  * 공지사항 게시판에 들어가면 관리자의 경우 글쓰기가 가능하며 회원의 경우는 글쓰기가 불가능합니다. 공지사항의 글은 댓글을 달 수 없습니다. 검색 기능을 통해 검색창에 입력된 키워드가 들어가는 제목, 내용, 글쓴이가 존재하는 경우 그 리스트가 출력됩니다. 
  
 ![image](https://user-images.githubusercontent.com/97610532/211460825-4f1fa2a5-e5f2-4da9-9e59-2ed7eb784a9d.png)
 
 
* 게시글 작성 및 댓글 작성
<img src="https://user-images.githubusercontent.com/97610532/211465409-d22e99a8-07a9-4f72-8b0b-fab56b0dc71b.png" width="500" height="300"/>
<img src="https://user-images.githubusercontent.com/97610532/211466188-a3f65773-8023-49ce-9f5e-a284c5a84a14.png" width="500" height="300"/>
<img src="https://user-images.githubusercontent.com/97610532/211465752-1880a9d4-6a12-4e65-918f-c26859e83583.png" width="500" height="300"/>

 
* 쌀값 예측 페이지
  * 서울, 대전, 대구, 부산, 광주 지역의 쌀값을 예측하는 그래프를 확인할 수 있다. 예측된 쌀값에 대한 정보를 얻어 귀농인들이 추수를 하기 전에 미리 예상 수익을 알 수 있다.
  * 메인 페이지에서 Learn More이라고 적힌 버튼을 클릭하면 위 페이지로 이동하며 지역을 선택하고, 예측하고자 하는 연도를 선택하면, 실제 값과 예측한 그래프가 보이도록 구현했습니다.
  
![image](https://user-images.githubusercontent.com/97610532/211462237-dac556ef-3917-4c88-b75b-7328ace006fc.png)


* 로그인, 회원가입, 마이페이지
  * 회원일 경우 로그인 페이지를 통해 비회원일 경우 회원가입 후 로그인 페이지를 통해 메인 페이지로 이동합니다. 
  * 메인 페이지를 이동 후에 마이 페이지로 접속할 경우 로그인 한 회원의 정보를 볼 수 있으며 수정 버튼을 클릭할 경우 수정과 탈퇴도 가능합니다. 관리자의 경우 수정과 강제 탈퇴가 가능합니다.
  
![image](https://user-images.githubusercontent.com/97610532/211460504-7cd46c05-cd6d-42bf-a20b-543f1698a78d.png)
![image](https://user-images.githubusercontent.com/97610532/211460517-ead4faa8-ad0b-4f96-bcb5-7be795c12c90.png)






