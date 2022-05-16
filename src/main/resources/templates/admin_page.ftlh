<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="css/account.css/">
    <link rel="stylesheet" type="text/css" href="css/header.css"/>
    <title>Admin Page</title>

    <script>
        function banUser(id) {
            let request = new XMLHttpRequest()

            request.open("PUT", "/api/users/ban/" + id, false)
            request.send()

            let response = JSON.parse(request.response)

            let html = ""

            for (let i = 0; i < response.length; i++) {
                html += "<tr>"
                html += "<td><p class='fw-bold'>" + response[i]['email'] + "</p></td>"
                html += "<td><p class='fw-bold'>" + response[i]['state'] + "</p></td>"
                html += "<td> <p class='fw-bold'>" + response[i]['role'] + "</p></td>"
                if (response[i]['state'] === 'ACTIVE') {
                    html += "<td>" +
                        "<input type='submit' class='btn btn-danger' value='Заблокировать' onclick='banUser("+ response[i]['id'] +")'>" +
                        "</td>"
                } else {
                    html += "<td>" +
                        "<input type='submit' class='btn btn-danger' value='Разблокировать' onclick='unBanUser("+ response[i]['id'] +")'>" +
                        "</td>"
                }
                html += "</tr>"
            }

            document.getElementById('content').innerHTML = html
        }

        function unBanUser(id) {
            let request = new XMLHttpRequest()

            request.open("PUT", "/api/users/unban/" + id, false)
            request.send()

            let response = JSON.parse(request.response)

            let html = ""

            for (let i = 0; i < response.length; i++) {
                html += "<tr>"
                html += "<td><p class='fw-bold'>" + response[i]['email'] + "</p></td>"
                html += "<td><p class='fw-bold'>" + response[i]['state'] + "</p></td>"
                html += "<td><p class='fw-bold'>" + response[i]['role'] + "</p></td>"
                if (response[i]['state'] === 'ACTIVE') {
                    html += "<td>" +
                        "<input type='submit' class='btn btn-danger' value='Заблокировать' onclick='banUser("+ response[i]['id'] +")'>" +
                        "</td>"
                } else {
                    html += "<td>" +
                        "<input type='submit' class='btn btn-danger' value='Разблокировать' onclick='unBanUser("+ response[i]['id'] +")'>" +
                        "</td>"
                }
                html += "</tr>"
            }

            document.getElementById('content').innerHTML = html
        }
    </script>

</head>
<body>
<div class="header">
    <a class="header_link" href="/account">Профиль</a>
    <a class="header_link" href="/tours">Обзор туров</a>
    <a class="header_link" href="/create-tour">Создать тур</a>
    <a class="header_link" href="/admin">Управление</a>
    <a class="header_link" href="/logout">Выход</a>
</div>
<table class="table" style="text-align: center">
    <thead class="table-dark">
    <tr>
        <td><p class="fw-bold">Email</p></td>
        <td><p class="fw-bold">Статус</p></td>
        <td><p class="fw-bold">Роль</p></td>
        <td></td>
    </tr>
    </thead>
    <tbody id="content">
    <#list users as user>
        <tr>
            <td><p class="fw-bold">${user.email}</p></td>
            <td>
                <p class="fw-bold">${user.state}</p>
            </td>
            <td>
                <p class="fw-bold">${user.role}</p>
            </td>
            <td>
                <#if user.isActive()>
                    <input type="submit" class="btn btn-danger" value="Заблокировать" onclick="banUser(${user.id})">
                <#else>
                    <input type="submit" class="btn btn-success" value="Разблокировать"
                           onclick="unBanUser(${user.id})">
                </#if>
            </td>
        </tr>
    </#list>
    </tbody>
</table>

</body>
</html>