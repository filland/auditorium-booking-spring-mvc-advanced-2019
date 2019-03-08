<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="../../resources/css/booking.css"/>
    <meta charset="UTF-8">
    <title>Book a ticket</title>
    <link href="/resources/css/common.css" rel="stylesheet" type="text/css"/>
    <script src="/resources/js/booking.js" type="text/javascript"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body onload="updateAllSeats()">
<#include "common/header.ftl">
<div id="main">

    <div id="preparation">
        <p>
            Click on the below buttons before booking anything and then refresh the page.
        </p>
        <div>
            <span id="user_created"></span>
            <button type="button" onclick="createNewUsers()">Create new users with id 1 and 2</button>
        </div>
        <div>
            <span id="events_created"></span>
            <button type="button" onclick="createNewEvents()">Create some events</button>
        </div>
    </div>

    <h3>Book a ticket:</h3>

    <div>
        <label for="event">Choose an event: </label>
        <select id="event" onchange="updateAllSeats()">
            <#list events as event>
                <option value="${event.name}">${event.name}</option>
            </#list>
        </select>
    </div>
    <div>
        <label for="seats">Choose a seat: </label>
        <select id="seats">
            <option value="default">Default option</option>
            <option value="default2">Default option 2</option>
            <option value="default3">Default option 3</option>
        </select>
    </div>
    <button type="button" onclick="bookTicket()">Book a ticket</button>
    <div id="message"></div>
    <div id="error"></div>
</div>
<#include "common/footer.ftl">
</body>
</html>