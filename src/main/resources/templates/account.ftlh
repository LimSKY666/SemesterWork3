<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="css/account.css"/>
    <link rel="stylesheet" type="text/css" href="css/header.css"/>
    <title>Account</title>
</head>
<body>
<div class="body-wrapper">
    <div class="header">
        <a class="header_link" href="/account">Профиль</a>
        <a class="header_link" href="/tours">Обзор туров</a>
        <a class="header_link" href="/create-tour">Создать тур</a>
        <a class="header_link" href="/admin">Управление</a>
        <a class="header_link" href="/logout">Выход</a>
    </div>
    <div class="main">
        <h2 class="main_title">Аккаунт</h2>
        <div class="main_user user">
            <p class="user_fio">${user.firstName} ${user.lastName}</p>
            <p class="user_email">${user.email}</p>
        </div>
        <a href="/create-tour" class="go-to-tours">Перейти к турам</a>
        <div class="main_tours tours">
            <p class="tours_title">Мои туры</p>
            <div class="tours_tours-list">
                <#list tours as tour>
                    <div class="tours_tour">${tour.name}</div>
                </#list>
            </div>
        </div>
    </div>
</div>


</body>
</html>