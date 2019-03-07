<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Transfer money to your account</title>
    <link href="/resources/css/common.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<#include "common/header.ftl">
<div id="main">
    <h3>Transfer money to your account:</h3>

    <form action="/account/add" method="post">
        <label>Amount:
            <input type="number" name="amount">
        </label>
        <button type="submit">Put money on your account</button>
    </form>
</div>
<#include "common/footer.ftl">
</body>
</html>