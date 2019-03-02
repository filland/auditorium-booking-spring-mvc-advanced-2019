<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Main page</title>
    <link href="/resources/css/common.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<#include "common/header.ftl">
<div id="main">
    <p style="font-weight:bold">First of all open 'Booking page'</p>
    <ul>
        <li><a href="/registration">Registration page</a></li>
        <li><a href="/event/create">Create new event</a></li>
        <li><a href="/booking/book">Booking page</a></li>
        <li><a href="/booking/find-ticket-price">Find ticket price</a></li>
        <li><a href="/user/upload-from-json">Upload users from json</a></li>
        <li><a href="/event/upload-from-json">Upload events from json</a></li>

        <br>
        Pages available only for managers:

        <li><a href="/booking/find-booked-tickets">Find booked tickets for an event</a></li>
    </ul>
</div>
</body>
</html>