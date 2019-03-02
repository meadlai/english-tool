<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mead VOA</title>
</head>
<body>
	VOA Text Extract
	<hr />
	<form action="print" method="get">
		Language: <select name="lang">
			<option value="en">English</option>
			<option value="zh">Chinese</option>
			<option value="bt">Both</option>

		</select> <br /> <br /> Link: <input name="url" /> <br /> <br />
		<button value="submit">submit</button>
	</form>
	<hr />

	Please input the link from below URL:
	<br />
	<a href="http://voa.iyuba.com/voachangsu.html" target="_blank">http://voa.iyuba.com/voachangsu.html</a>
	<p>
		Example URL: <br>
		http://voa.iyuba.com/audioitem_standard_9347.html
	</p>
</body>
</html>