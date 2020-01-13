<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello World!</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/css/mainBase.css">
<link rel="stylesheet" href="/css/project/projectBase.css" type="text/css"/>
<style>
	#pageTitle{margin-bottom:20px;}
	#pageTitle h1{display:inline;margin-right:10px;font-weight:bold;}
	#pageTitle .btn{margin-left:5px;}
	.table{background-color:white;padding:0;text-align:center;}	
	.table tbody *{font-weight:normal;}
	.table tbody td:nth-child(2){font-weight:bold;}
	.table tbody td:nth-child(3){text-align:center;cursor:pointer;}
	.table tbody .N{color:limegreen;}
	.table tbody .Y{color:red;}
	.pComment{margin-left:3px;font-size:13px;color:orange;}
	a:hover{text-decoration:none;}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/standard/header.jsp"/>
	
 		<div id=baseBackgroundColor>
            <div class=container>
                <div class=row>
                    <div class="col-12" id=aroundContent>
                    </div>
                </div>
            </div>
            
            <!--      몸통 시작!!!   -->
            <div class=container id="projectPage" style="background-color:white">
				<div id="pageTitle">
					<table>
							<tr>
								<td colspan="3" style="font-size: 60px; font-weight: 100;">업계현황</td>
								<td></td>
								<td style="font-size: 15px; color: gray;">     업계현황에 대한 정보를 나누는 게시판입니다.</td>
								<td></td>
							</tr>
						</table>
				</div>				
				<table class="table table-hover">
				  <thead class="thead-light">
				    <tr>
				      <th scope="col">글번호</th>
				      <th scope="col">분야</th>
				      <th scope="col">직무</th>
				      <th scope="col">제목</th>
				      <th scope="col">작성자</th>
				      <th scope="col">작성일</th>
				      <th scope="col">조회수</th>
				    </tr>
				  </thead>
				  <tbody>
				  	<c:choose>
				  		<c:when test="${industryStatusList.size()==0 }">
				  		<tr><td colspan="8">작성된 글이 없습니다.</td></tr>
				  		</c:when>
				  		<c:otherwise>
				  			<c:forEach items="${industryStatusList }" var="i">
				  				<tr>
				  					<th scope="row">${i.seq }</th>
				  					<td><span class="badge badge-pill badge-success"
											style="margin: 10; width: 60px;">${i.field}</span></td>
				  					<td><span class="badge badge-pill badge-primary">${i.duty }</span></td>
				  					<td><a href="/industry/industryStatusDetailView.do?seq=${i.seq }">${i.title } 
				  						<c:if test="${i.commentCount>0 }">
				  							<span class="pComment font-weight-bold">${i.commentCount }</span>
				  						</c:if>
				  						</a>
				  					</td>
				  					<td>${i.writer }</td>
				  					<td>${i.formedWriteDate }</td>
				  					<td>${i.viewCount }</td>
				  				</tr>
				  			</c:forEach>
				  		</c:otherwise>
				  	</c:choose>				    
				  </tbody>
				</table>
				<div class="text-left">
					<form action="/industry/industrySearch.do" method="post">
					<select name=value>
						<option value="all">전체(제목+내용)</option>
						<option value="field">분야</option>
						<option value="duty">직무</option>
						<option value="writer">작성자</option>
						<option value="title">제목</option>
					</select>
					<input type="text" name=search>
					<input type="button" id=search value="검색">
					</form>
				</div>
				<div class="text-right">
					<button type="button" class="btn btn-primary" id="write">글쓰기</button>				
				</div>
				 <div class="card">
                            <div class="card-body">
                                <nav aria-label="Page navigation example">
                                    <ul class="pagination justify-content-center">
                                    	<c:choose>
                                    		<c:when test="${pageNavi.size() > 0}">
												<c:forEach items="${pageNavi}" var="navi">									
													<li class="page-item pageNavi">${navi}</li>
												</c:forEach>                                    		
                                    		</c:when>
                                    	</c:choose> 
                                    </ul>
                                </nav>
                            </div>
                        </div>
				
            </div>
            <!--       몸통 끝!!!   -->
            
            <div class=container>
                <div class=row>
                    <div class="col-12" id=aroundContent>
                    </div>
                </div>
            </div>
        </div>
        
        <jsp:include page="/WEB-INF/views/standard/footer.jsp"/>
</body>
		<script>
		$("#search").on("click",function(){
			$("form").submit();
		})
		$("#write").on("click",function(){
			if("${sessionScope.loginInfo.id}" == ""){
        		alert("로그인을 해주세요.");
        		return false;
        	}
			
			$.ajax({
				url : "/industry/memLevel.do",
				type : "post",
				dataType : "json",
				data : {
					id : "${sessionScope.loginInfo.id}"
				}
			}).done(function(resp){
				if(resp > 2){
					location.href="/industry/industryStatusWrite.do";	
				}else{
					alert("실무자만 입력가능합니다.")
					return false;
				}
				
			}).fail(function(resp){
				console.log("실패");
			})
		});
		if("${pageNavi.size() > 0}"){
		var element = $(".pageNavi");
		var page = "${page}";
		if(page > 0 && page <= 10){
			element[page-1].classList.add('active');
		}else if(page % 10 == 0){
			element[10].classList.add('active');
		}else{
			element[page % 10].classList.add('active');
		}			
	}
		</script>
</html>