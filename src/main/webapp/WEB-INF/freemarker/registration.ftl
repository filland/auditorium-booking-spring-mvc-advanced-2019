<html lang="en">
<head>
    <title>${title}</title>
</head>
<body>
<h1>${title}</h1>

<p>${message}</p>


<form method="post" action="/registration" >

    <label for="name">Name:</label>
    <input type="text" id="name" name="name">

    <label for="email">Email:</label>
    <input type="text" id="email" name="email">

   <!--<label for="birthday">Birthday:</label>-->
   <input type="hidden" id="birthday" name="birthday" value="2000-01-01">

    <button type="submit">Register</button>
</form>

</body>
</html>