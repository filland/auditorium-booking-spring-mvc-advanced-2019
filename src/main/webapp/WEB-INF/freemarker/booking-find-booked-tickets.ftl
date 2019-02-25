<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Find booked tickets</title>
    <link href="/resources/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="/resources/css/find-booked-tickets.css" rel="stylesheet" type="text/css"/>
    <script src="/resources/js/find-booked-tickets.js" type="text/javascript"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<#include "common/header.ftl">
<div id="main">
    <h3>Find booked tickets:</h3>
    <div>
        <label for="event">Choose an event: </label>
        <select id="event">
            <#list events as event>
                <option value="${event.name}">${event.name}</option>
            </#list>
        </select>
    </div>
    <div>
        <label>
            Auditorium:
            <select id="auditorium_name">
                <#list auditoriums as audt>
                    <option value="${audt.name}" selected>${audt.name}</option>
                </#list>
            </select>
        </label>
    </div>
    <div>
        <label>
            Date and time:
            <input type="datetime-local" id="date_time" value="2020-02-05T21:18">
        </label>
    </div>
    <button type="button" onclick="findBookedTickets()">Find booked tickets</button>
    <#--<a href="/find-booked-tickets-pdf" download="booked_tickets.pdf">Download a pdf file</a>-->
    <button type="button" onclick="findBookedTicketsAndGeneratePdf()">Find booked tickets and create pdf</button>
    <p>Result:</p>
    <table id="tickets_table" style="display: none;">
        <thead>
        <tr>
            <th>User name</th>
            <th>User email</th>
            <th>Seat(s)</th>
            <th>Price,$</th>
        </tr>
        </thead>
        <tbody>

        </tbody>
    </table>
</div>
<#include "common/footer.ftl">
</body>
</html>