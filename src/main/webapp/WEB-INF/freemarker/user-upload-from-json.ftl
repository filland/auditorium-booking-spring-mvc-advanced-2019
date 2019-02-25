<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Upload users from json</title>
    <link href="/resources/css/common.css" rel="stylesheet" type="text/css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<#include "common/header.ftl">
<div id="main">
    <h3>Upload users from json:</h3>
    <p>You can find the json with users in the jsons folder in the root of the project.</p>
    <form method="post" action="/user/upload-from-json" enctype="multipart/form-data">
        <div>
            <div>
                <label for="users-upload" typeof="">Choose json file</label>
                <input id="users-upload" type="file" accept=".json,.txt" name="users_upload">
            </div>
            <div>
                <button type="submit">Upload json</button>
            </div>
        </div>
    </form>
    <div style="color:green">${message}</div>
</div>
<#include "common/footer.ftl">
</body>
</html>