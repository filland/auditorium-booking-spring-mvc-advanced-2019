<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <link href="/resources/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="/resources/css/registration.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<#include "common/header.ftl">
<div id="main">
    <h1>${title}</h1>

    <form method="post" action="/registration">

        <div>
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="alexey">
        </div>
        <div>
            <label for="email">Email:</label>
            <input type="text" id="email" name="email" value="fasdfasffd@gmail.com">
        </div>
        <div>
            <label for="birthday">Birthday:</label>
            <input type="text" id="birthday" name="birthday" value="2000-01-01">
        </div>
        <button type="submit">Register</button>
    </form>
</div>
<#include "common/footer.ftl">
</body>
</html>