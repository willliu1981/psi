<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>flash card v2</title>
<script
	src="<%=request.getContextPath()%>/webjars/jquery/3.4.1/jquery.min.js"></script>

<script type="text/javascript">
  $(document)
	  .ready(
		  function() {
			var $model;
			var $flipIndex;
			var $flip = $("#flip");

			$("#draw").click(function() {
			  var $msg = $("#msg");
			  $.ajax({
				type : "post",
				url : "${pageContext.request.contextPath}/process/exam/draw",
				dataType : "json",
				success : function(data) {
				  $model = data;
				  $flip.val($model.vocabulary);
				  $flipIndex = 0;
				  if (data.isLast) {
					$msg.val("最後一筆了");
				  }
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
				  //alert("error:" + textStatus);
				}
			  });

			});

			$flip.click(function() {
			  $flipIndex = 1 - $flipIndex;
			  if ($flipIndex == 0) {
				$(this).val($model.vocabulary);
			  } else {
				$(this).val($model.translation);
			  }

			});

			$("#move-right")
				.click(
					function() {
					  $
						  .ajax({
							type : "post",
							url : "${pageContext.request.contextPath}/process/exam/move-right/"
								+ $model.cid,
							success : function(data) {
							  //alert(data.name);
							},
							error : function(XMLHttpRequest, textStatus,
								errorThrown) {
							  //alert("error:" + textStatus);
							}
						  });

					});
			$("#move-left")
				.click(
					function() {
					  $
						  .ajax({
							type : "post",
							url : "${pageContext.request.contextPath}/process/exam/move-left/"
								+ $model.cid,
							success : function(data) {
							  //alert(data.name);
							},
							error : function(XMLHttpRequest, textStatus,
								errorThrown) {
							  //alert("error:" + textStatus);
							}
						  });

					});

		  });
</script>

<style type="text/css">
.table_draw {
	border: 3px #0000FF solid;
	margin-left: auto;
	margin-right: auto;
	padding: 12px;
}

.table_draw input {
	font-size: 36px;
	color: blue;
	width: 100%;
}
</style>

</head>
<body>
	<h1 style="text-align: center">card draw</h1>
	<table class="table_draw">
		<tr>
			<td colspan="2" align="right"><input type="button" id="draw"
					value="draw" /></td>
		</tr>
		<tr>
			<td colspan="2"><input type="text" id="flip"
					style="height: 240px; text-align: center" readonly /></td>
		</tr>
		<tr>
			<td><input type="button" id="move-left" value="✘"
					style="color: red" /></td>
			<td><input type="button" id="move-right" value="✔"
					style="color: green" /></td>
		</tr>
		<tr>
			<td colspan="2"><input type="text" id="msg" style="color: red" /></td>
		</tr>
	</table>
</body>
</html>