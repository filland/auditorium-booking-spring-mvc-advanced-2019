<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create event</title>
    <link href="/resources/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="/resources/css/event-create.css" rel="stylesheet" type="text/css"/>
    <script src="/resources/js/event-create.js" type="text/javascript"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<#include "common/header.ftl">
<div id="main">
    <h3>Create event:</h3>
    <form method="post" action="/event/create-events">
        <button type="submit">Create some events</button>
    </form>
    <div>
        <label>Name:
            <input type="text" id="name" value="Main evening of the year">
        </label>
    </div>
    <div>
        <label>
            Rate:
            <select id="rate">
                <option value="HIGH">HIGH</option>
                <option value="MID">MID</option>
                <option value="LOW">LOW</option>
            </select>
        </label>
    </div>
    <div>
        <label>
            Rate:
            <input type="text" id="base_price" value="29.99">
        </label>
    </div>
   <div>
       <label>
           Date and time:
           <input type="datetime-local" id="date_time">
       </label>
   </div>

   <div>
       <label>
           Auditorium:
           <select id="auditorium_name">
               <option value="Audit1" selected>auditorium 1</option>
           </select>
       </label>
   </div>
    <button type="submit" onclick="createEvnt()">Create event</button>

    <div id="result"></div>
</div>
<#include "common/footer.ftl">

</body>
</html>