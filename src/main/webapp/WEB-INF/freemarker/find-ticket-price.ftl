<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Find ticket price</title>
    <link href="/resources/css/common.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<#include "common/header.ftl">
<div id="main">
    <h3>Find ticket price:</h3>
    <label for="event">Choose an event</label>
    <select id="event">
        <#list events as event>
            <option value="${event.id}">${event.name}</option>
        </#list>
    </select>
    <#--<label for="seat">Choose a seat</label>-->
    <#--<select id="seat">-->
        <#--<#list ${eventIdAndSeats.get()} as seat>-->
           <#--<option value="${seat}">${seat}</option>-->
        <#--</#list>-->
    <#--</select>-->
</div>
<#include "common/footer.ftl">
</body>
</html>