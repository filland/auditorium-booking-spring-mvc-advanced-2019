<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Find ticket price</title>
    <link href="/resources/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="/resources/css/booking-find-ticket-price.css" rel="stylesheet" type="text/css"/>
    <script src="/resources/js/booking-find-ticket-price.js" type="text/javascript"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

</head>
<body onload="updateSeats()">
<#include "common/header.ftl">
<div id="main">
    <h3>Find ticket price:</h3>
    <div>
        <label for="event">Choose an event: </label>
        <select id="event" onchange="updateSeats()">
            <#list events as event>
                <option value="${event.name}">${event.name}</option>
            </#list>
        </select>
    </div>
    <div>
        <label for="common_seats">Choose a common seat: </label>
        <#--multiple-->
        <select id="common_seats" name="common_seats">
            <option value="default">Default option</option>
            <option value="default2">Default option2</option>
            <option value="default3">Default option3</option>
            <option value="default4">Default option4</option>
        </select>
    </div>
    <div>
        <label for="vip_seats">Choose a vip seat: </label>
        <#--multiple-->
        <select id="vip_seats" name="vip_seats">
            <option value="default">Default option</option>
            <option value="default2">Default option2</option>
            <option value="default3">Default option3</option>
            <option value="default4">Default option4</option>
        </select>
    </div>

    <div>Common ticket price, $: <span id="common_price"></span></div>
    <div>Vip ticket price, $: <span id="vip_price"></span></div>
    <button type="button" id="find_ticket_price" onclick="findPrice()">Find price</button>

</div>
<#include "common/footer.ftl">
</body>
</html>