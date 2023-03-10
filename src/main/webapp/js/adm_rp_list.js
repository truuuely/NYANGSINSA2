//전역변수
let dataPerPage=5; // 한 페이지에 나타낼 글 수
let pageCount=5; // 페이징에 나타낼 페이지 수
let currentPage=1; // 현재 표시중인 페이지
let first=1; // 페이징 첫 페이지
let last; // 페이징 마지막 페이지
let totalData; // 총 데이터 수
let dataList; // 표시하려하는 데이터 리스트
let totalPage; //총 페이지 수 결정
let selectPage; // 보고싶은 페이지(실제로 사용자가 보고 싶은 페이지)
let part; // 목록 요청 매개변수(카테고리)
let values; // 매개변수로 사용할 임시 객체 
let encodedValues; // values를 json 타입으로 인코딩
let url;


function list(selectPage,step) {
	pageCount=5;
	console.log(totalPage);
	// 페이징 시작번호와 끝번호 세팅하기
	last=first+pageCount-1;
	if(selectPage < first) { // 보고싶은 페이지가 현재 페이징 첫 페이지보다 작을 때(즉, ◀ 버튼을 눌렀을 때)
		first-=pageCount; // 페이징 번호를 앞으로
		last-=pageCount;
	}
	else if(last < selectPage){
		first+=pageCount; // 페이징 번호를 뒤로
		last+=pageCount;
	}

	part="report";

	console.log("dmddmd")
	console.log("part: "+part);
	console.log("selectPage: "+selectPage);
	console.log("step "+step)


	$.ajax({ // ajax로 데이터 가져오기
		type: 'POST',
		url: 'getAdminList.do',
		data: {part:part}, // category, sort 담아서 ListController Servlet에 걸리게!
		dataType: 'json',
		traditional: 'true',
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		success: function(data) {

			// 기본 세팅
			dataList=data;
			totalData = dataList.length;
			selectPage=selectPage; // 보고싶은 페이지(실제로 사용자가 보고 싶은 페이지) : 사실은 전역변수일 필요가 없으나 가독성 때문에...
			totalPage = Math.ceil(totalData / dataPerPage); //총 페이지 수 결정

			// 글 목록 표시 호출 (테이블 생성)
			console.log(dataList,step);
			displayData(selectPage,step); // 현재페이지인데 바뀌어서 나옴

			// 페이징 표시 호출
			paging(selectPage,step); // 그래서 저장해논 값을 넣어줌

		}
	});
};


//데이터 출력 부분: 현재 페이지(currentPage)와 페이지당 글 개수(dataPerPage) 반영
function displayData(selectPage,step) {
	let listhtml = "";
	let chartHtml = "";

//	Number로 변환하지 않으면 아래에서 +를 할 경우 스트링 결합이 되어버림.. 
//	currentPage = Number(currentPage);
//	dataPerPage = Number(dataPerPage);

	console.log(dataList);
	if(totalData!=0){
		for (var i = (selectPage - 1) * dataPerPage ; i < (totalData < (selectPage * dataPerPage) ? totalData : (selectPage * dataPerPage)) ; i++) {
			if(step==1 && dataList[i].reportStat==1){
				// 신고된 글 
				
				values = { targetNum: dataList[i].targetNum ,
						   reportNum: dataList[i].reportNum ,
						   userNum : dataList[i].userNum ,
						   reporterNum: dataList[i].reporterNum ,
						   reportStep: dataList[i].reoprtStep };
				encodedValues = encodeURIComponent(JSON.stringify(values));
				
				url = `init(encodedValues);`;

				
				
				console.log(dataList[i]);
				chartHtml+="<tr><td><i class='fab fa-angular fa-lg text-danger me-3'></i> <strong>"+dataList[i].reportNum+"</strong></td>"
				+"<td>"+dataList[i].userId+"</td>"
				+"<td><a href='#' onclick='newOpen("+encodedValues+");'> "+(dataList[i].content.length > 25 ? dataList[i].content.substring(0, 25) + "..." : dataList[i].content)+"</a></td>"
				+"<td>"+dataList[i].reporterId+"</td>"
				+"<td>"+dataList[i].reportContent+"</td>"
					
				+"<td>"
				+"<div class='dropdown'>"
				+"<button type='button' class='btn p-0 dropdown-toggle hide-arrow' data-bs-toggle='dropdown'>"
				+"<i class='bx bx-dots-vertical-rounded'></i>"
				+"</button>"
				+"<div class='dropdown-menu'>"
				+"<a class='dropdown-item ' href='javascript:proc("+encodedValues+");'  ><i class='bx bx-edit-alt me-1'></i>신고 처리</a>"
				+"</div>"
				+"</div>"
				+"</td>"
				+"</tr>";
				
				listhtml="	<tr> 	<th>no.</th> 	<th>글 작성자</th> 	<th>게시글 내용</th> 	<th>신고자</th> 	<th>신고 내용</th> </tr>";
			}
			if(step==2 && dataList[i].reportStat==1){
				// 신고된 댓글
				chartHtml+="<tr><td><i class='fab fa-angular fa-lg text-danger me-3'></i> <strong>"+dataList[i].reportNum+"</strong></td>"
				+"<td>"+dataList[i].userId+"</td>"
				+"<td><a href='#' onclick='newOpen("+encodedValues+");'> "+(dataList[i].content.length > 25 ? dataList[i].content.substring(0, 25) + "..." : dataList[i].content)+"</a></td>"
				+"<td>"+dataList[i].reportId+"</td>"
				+"<td>"+dataList[i].reportContent+"</td>"

				+"<td>"
				+"<div class='dropdown'>"
				+"<button type='button' class='btn p-0 dropdown-toggle hide-arrow' data-bs-toggle='dropdown'>"
				+"<i class='bx bx-dots-vertical-rounded'></i>"
				+"</button>"
				+"<div class='dropdown-menu'>"
				+"<a class='dropdown-item ' href='javascript:proc("+encodedValues+");'  ><i class='bx bx-edit-alt me-1'></i>신고 처리</a>"
				+"</div>"
				+"</div>"
				+"</td>"
				+"</tr>";
				
				
				
				listhtml="	<tr> 	<th>no.</th> 	<th>댓글 작성자</th> 	<th>댓글 내용</th> 	<th>신고자</th> 	<th>신고 내용</th> </tr>";

			}


		}
		$("#dataTableBody").html(chartHtml);
		$("#dataTableList").html(listhtml);
	}
}




//페이지네이션 표시 함수
function paging(currentPage,step) {
	console.log('페이징 함수 실행5');
	if(totalData!=0){ // dataList에 데이터(상품||리뷰)가 있을 때 페이징 띄우기
		console.log('paging 함수 '+part);
		// 지정한 페이징 숫자보다 실제 페이지가 적을 경우 
		if(totalPage<pageCount){
			pageCount=totalPage; // 페이징 숫자를 줄여줌
		}

		// (페이징의 끝에서) 남은 페이징 숫자가 전체페이지보다 크다 
		if (last > totalPage) {
			last = totalPage; // 페이징 끝을 줄여줌
		}

//		first = last - (pageCount - 1); //화면에 보여질 첫번째 페이지 번호

		let pageHtml = "";

		if (first > 1) {
			pageHtml += "<a href='javascript:list("+(first-1)+","+step+")' id='prev'><li> ◀ </li></a>";
		}

		//페이징 번호 표시 
		for (var i = first; i <= last; i++) {
			if (currentPage == i) {
				pageHtml +=
					"<a href='javascript:list("+i+","+step+")' id='" + i + "'><li class='on'>" + i + "</li></a>";
			} else {
				pageHtml += "<a href='javascript:list("+i+","+step+")' id='" + i + "'><li>" + i + "</li></a>";
			}
		}

		if (last < totalPage) {
			pageHtml += "<a href='javascript:list("+(last+1)+","+step+")' id='next'><li> ▶ </li></a>";
		}

		pageHtml+="<br><br>";
		console.log('총 페이징 수'+pageCount);
		$("#pagingul").html(pageHtml);

		/*	  let displayCount = "";
	  displayCount = "현재 1 - " + totalPage + " 페이지 / " + totalData + "건";
	  $("#displayCount").text(displayCount);*/

	}
	else{ // 목록에 dataList(상품||리뷰)가 없을 때 이미지 띄우기
		let pageHtml = "<br><br>데이터가 없습니다.<br><br><br>";
		$("#pagingul").html(pageHtml);
	}
}

function proc(data) {
	  console.log(typeof data.targetNum);
	  console.log(typeof data.reportInput);
	  console.log(typeof data.userNumInput);
	  console.log( data.reporterInput);
	  console.log( parseInt(data.boardNum));
	  console.log(data);
	  
	  
	  var targetNumInput = document.getElementById("targetNum"); // id가 boardNum 인 태그 가져와서 매개변수로 받은 boardNum 대입
	  targetNumInput.value =parseInt(data.targetNum);
		
		var reportInput = document.getElementById("reportNum");  // id가 reportNum 인 태그 가져와서 매개변수로 받은 reportNum 대입
		reportInput.value =parseInt(data.reportInput);
		
		var userNumInput = document.getElementById("userNum");  // id가 userNum 인 태그 가져와서 매개변수로 받은 userNum 대입
		userNumInput.value =parseInt(data.userNumInput);
		
	    var reporterInput = document.getElementById("reporterNum");  // id가 reporterNum 인 태그 가져와서 매개변수로 받은 reporterNum 대입
		   reporterInput.value =parseInt(data.reporterInput);
		
		var reportStepInput=document.getElementById("reportStep");
			reportStepInput.value=parseInt(data.reportStep);
		   
		var reportForm = document.getElementById("reportForm");
		reportForm.action = "updateReport.do";

			document.querySelector(".report-modal").classList.remove("report-modal-hidden");
	  
	};
function newOpen(data){
		window.open("boardPostView.do?targetNum="+data.targetNum+"&reportStep="+data.reportStep);
		
	
}
