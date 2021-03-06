<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
    <title>Hello Javascript</title>

    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>
    <script src="http://code.jquery.com/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>

<body>
<div class="container">
    <!-- 결과가 출력될 div -->
    <div id="result"></div>
</div>	

<script type="text/javascript">
    $(function() {
            $.ajax( {
                /** ajax 기본 옵션 */
                url: 'WeatherTest.do',// 읽어들일 파일의 경로
                method: 'get',           // 통신방법 (get(기본값), post)
                /* data: {},                // 접속대상에게 전달할 파라미터 */
                dataType: 'json',        // 읽어올 내용 형식 (html,xml,json)
                timeout: 30000,          // 타임아웃 (30초)

                // 통신 시작전 실행할 기능 (ex: 로딩바 표시)
                beforeSend: function() {
                    // 현재 통신중인 대상 페이지를 로그로 출력함
                    console.log(">> Ajax 통신 시작 >> " + this.url);
                },
                // 통신 성공시 호출될 함수 (파라미터는 읽어온 내용)
                success: function(category) {
                    console.log(">> 성공!!!! >> " + category);
                    // 준비된 요소에게 읽어온 내용을 출력한다.
                    $("#result").append(category);
                },
                // 통신 실패시 호출될 함수 (파라미터는 에러내용)
                error: function(error) {
                    // 404 -> Page Not Found
                    // 50x -> Server Error(웹 프로그램 에러)
                    // 200, 0 -> 내용의 형식 에러(JSON,XML)
                    console.log(">> 에러!!!! >> " + error.status);
                },
                // 성공,실패에 상관 없이 맨 마지막에 무조건 호출됨 ex) 로딩바 닫기
                complete: function() {
                    console.log(">> Ajax 통신 종료!!!!");
                }
            } );
    });
</script>
</body>
</html>