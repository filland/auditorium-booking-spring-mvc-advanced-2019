<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Upload events from json</title>
    <link href="/resources/css/common.css" rel="stylesheet" type="text/css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<#include "common/header.ftl">
<div id="main">
    <h3>Upload events from json:</h3>
    <p>You can find the json with events in the jsons folder in the root of the project.</p>
    <form method="post" action="/event/upload-from-json" enctype="multipart/form-data">
        <div>
            <label for="events-upload" typeof="">Choose json file</label>
            <input id="events-upload" type="file" accept=".json,.txt" name="events_upload">
        </div>
        <div>
            <button type="submit">Upload json</button>
        </div>
    </form>
    <div style="color:green">${message}</div>
</div>
<#include "common/footer.ftl">
</body>
</html>