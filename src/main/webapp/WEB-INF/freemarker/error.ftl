<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <link href="/resources/css/common.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<#include "common/header.ftl">
<div id="main">

    <h1>Error</h1>

    <p>Oops, seems like something went wrong...</p>
   <#if error_message??> Error message: ${error_message}</#if>
</div>
<#include "common/footer.ftl">
</body>
</html>