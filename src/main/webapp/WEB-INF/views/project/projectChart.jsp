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
<link rel="stylesheet" href="/css/project/projectBase.css" type="text/css"/>
<link rel="stylesheet" href="/css/project/chart.css" type="text/css"/>
<link rel="stylesheet" href="/css/font-awesome/css/font-awesome.css" type="text/css"/>
<script>
   $(function(){
      $("#proNavi").attr('class','nav-item nav-link active');
   });
</script>
<style>
	#pageTitle{margin-bottom:40px;}
	#pageTitle h1{display:inline;margin-right:10px;font-weight:bold;}
	#pageTitle .btn{margin-left:5px;}
	.projectList{margin-bottom:10px;}	
	.pTextInfo label.N{background-color:limegreen;}
	.pTextInfo label.Y{background-color:red;}
	.pTextInfo label{color:white;}
	.pTextInfo{padding:15px;background-color:#ffffff;border-radius:10px 0px 0px 10px;}
	.graphNaviItem:last-of-type{border-radius:0px 10px 10px 0px;}
	.graphNaviItem:not(:first-of-type){margin-left:2px;}
	.pGraphBar{position:relative;}
    .fa-share-alt,.fa-bookmark{margin-left:15px;}
    .pInfoBox span{font-size:15px;display:inline-block;}
	.pInfoBox span:first-of-type{width:180px;}
	.pInfoBox span:nth-of-type(2){width:90px;}
    .pTitleBox{font-weight:bold;}     
    .pTextInfo{z-index:1;}
/*     .graphNaviItemBlock{ */
/*     	z-index:2; */
/*     	background-color:#f2f2f2; */
/*     	width:300px; */
/*     	height:150px; */
/*     	display:inline-block; */
/*     } */
	
/* 	.graphNaviItem{background-color:#e0e0e0;color:#ffe88790;width:53px;line-height:120px;} */	
	.graphNaviItem{background-color:#e0e0e0;color:#ffe88790;width:103px;line-height:150px;}
	.progressBar{
		height:50px;
		width:623px;
		background-color:#ffc107;
		position:absolute;
		top:50%; 
 		left:50%; 
     	transform: translate(-330px, -50%);/*마이너스숫자클수록 왼쪽으로 이동함. 105p이동이 한칸.*/
/*      	transform: translate(-225px, -50%); */
/*      	transform: translate(-120px, -50%); */
		transform: translate(-270px, -50%);
     	width:413px;
    }
    .progressBar:hover{cursor:pointer;}
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
            <section class="projectContainer" style="min-height: 100px;">        
               
	            <div class=container id="projectPage">
					<div id="pageTitle" class="row">
						<div class="col-12 col-lg-4"><h1>프로젝트 모집</h1></div>
						<div class="col-12 col-lg-8 pt-2">
							<a class="btn btn-secondary" href="/project/list" role="button">게시판</a>
							<a class="btn btn-danger" href="/project/chart" role="button">그래픽</a>
							<a class="btn btn-secondary" href="/project/map" role="button">지도</a>
						</div>
					</div>	
					
								
					<div class="choice">
<!-- 					정렬선택 -->
					</div>
					<div class="projectContainer">
						<c:choose>
							<c:when test="${projectList.size()==0 }">
					  			<div class="row"><div class="col-12">모집중인 프로젝트가 없습니다.</div></div>
					  		</c:when>
					  		<c:otherwise>
					  			<c:forEach items="${projectList }" var="p">
									<div class="row projectList">
										<div class="col-xl-5 d-none d-xl-block pTextInfo">
											<label class="${p.state } badge badge-pill ml-0" id="stateLabel">${p.stateInKor }</label>
											<i class="fa fa-share-alt"></i><i class="fa fa-bookmark"></i>
											<span class="float-right"><i class="fa fa-calendar-check-o"></i> ${p.formedAllDate }</span>
											<div class="pTitleBox mt-1 mb-1" style="max-width:430px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;">${p.title }</div>
											<div class="pInfoBox mt-1" style="height:50px;">
												<div>
													<span><i class="fa fa-map-marker"></i> ${p.location1 } ${p.location2 }</span>
													<span><i class="fa fa-user"></i> ${p.capacity }명</span>
												</div>
												<div>
													<div style="max-width:430px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;display:inline-block"><i class="fa fa-check"></i> ${p.languages }</div>
												</div>
											</div>
										</div>
										
										<div class="col-xl-7 d-none d-xl-block pGraphBar p-0">
											<ul class="nav graphNavi">
											  <li class="nav-item graphNaviItem">.</li>
											  <li class="nav-item graphNaviItem"> </li>
											  <li class="nav-item graphNaviItem"> </li>
											  <li class="nav-item graphNaviItem"> </li>
											  <li class="nav-item graphNaviItem"> </li>
											  <li class="nav-item graphNaviItem"> </li>											  
<!-- 											  <li class="nav-item graphNaviItem"> </li> -->
<!-- 											  <li class="nav-item graphNaviItem"> </li> -->
<!-- 											  <li class="nav-item graphNaviItem"> </li> -->
<!-- 											  <li class="nav-item graphNaviItem"> </li> -->
<!-- 											  <li class="nav-item graphNaviItem"> </li> -->
<!-- 											  <li class="nav-item graphNaviItem"> </li>											  											   -->
											</ul>												
											<div class="progressBar"></div>
<!-- 											<div class="nav-item graphNaviItemBlock"></div>										 -->
										</div>
										
<!-- 										<div class="progressBarBrick"></div> -->
									</div>					
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</div>
	            </div>
            
            </section>           
            <!--       몸통 끝!!!   -->
            
            <div class=container>
                <div class=row>
                    <div class="col-12" id=aroundContent>
                    </div>
                </div>
            </div>
        </div>
        
       
        
        <jsp:include page="/WEB-INF/views/standard/footer.jsp"/>
        
<script>
	

</script>
	
</body>
</html>
