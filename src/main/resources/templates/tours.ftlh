<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="css/tours.css"/>
    <title>Login</title>
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
        <h2 class="main_title">Туры</h2>
        <div class="main_tours tours">
            <#list tours as tour>
                <div class="tours_tour tour">
                    <p class="tour_name">${tour.name}</p>
                    <p class="tour_location">
                        <#assign i = 0>
                        <#list tour.getCountries() as country>
                            <#if country_has_next>
                                ${country.name + ", "}
                            <#else>
                                ${country.name}
                            </#if>
                        </#list>
                    </p>
                    <p class="tour_location">
                        <#list cities as city>
                            <#if city_has_next>
                                ${city.name + ", "}
                            <#else>
                                ${city.name}
                            </#if>
                        </#list>
                    </p>
                    <p class="tour_date">${tour.days}</p>
                    <p class="tour_price">${tour.price}</p>
                    <a href="/tours/add/${tour.id}/${user.id}"><p class="tours_title">Забронировать</p></a>
                </div>
            </#list>
        </div>
    </div>
</div>


</body>
</html>