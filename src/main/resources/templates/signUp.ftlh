<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="css/sign-up.css"/>
    <title>Login</title>

    <script>
        function isUserExists(email) {
            let request = new XMLHttpRequest()

            request.open("GET", "/api/users?email=" + email, false)
            request.send()

            let response = request.response

            if (response.length !== 0) {
                document.getElementById("error").innerText = "Пользователь уже существует"
                return false
            }

            return true
        }
    </script>

</head>
<body>
<div class="body-wrapper">
    <div class="main">
        <h1 class="error"></h1>
        <h2 class="main_title">Регистрация</h2>
        <p class="lead" id="error" style="height: 10px;display: flex;align-items: center;justify-content: center;"></p>
        <div class="main_form-wrapper">
            <form class="form" method="post" action="/signUp"
                  onsubmit="return isUserExists(document.getElementById('email').value);">
                <input id="email" name="email" type="email" placeholder="Email"/>
                <input name="password" type="password" placeholder="Password"/>
                <input name="firstName" type="text" placeholder="Name"/>
                <input name="lastName" type="text" placeholder="Surname"/>
                <input type="submit" value="Sign up"/>
                <a href="/signIn">У меня уже есть аккаунт</a>
            </form>
        </div>
    </div>
</div>
</body>
</html>