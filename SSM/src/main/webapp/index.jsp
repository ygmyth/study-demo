<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>
<h2>Hello World!!!!!</h2>
</body>
<script>
        $.ajax({
                url: "http://www.test.com:6080/test",
            type: 'post',
            data: {
                    id: '',

            },
                success: function (result) {
                    console.log(result)
                }
            }
        )
</script>
</html>
