<div id="header">
    <a href="/">Back to main page</a>
    <form style="float:right" action="/logout" method="post">
        <button type="submit">Logout</button>
    </form>
    <div>Username: <span style="font-weight: bold" id="username"></span></div>
    <div>Account balance, $: <span style="font-weight: bold" id="account_balance"></span></div>
    <script>

        var HttpClient = function () {
            this.get = function (aUrl, aCallback) {
                var anHttpRequest = new XMLHttpRequest();
                anHttpRequest.onreadystatechange = function () {
                    if (anHttpRequest.readyState === 4 && anHttpRequest.status === 200)
                        aCallback(anHttpRequest.responseText);
                };

                anHttpRequest.open("GET", aUrl, true);
                anHttpRequest.send(null);
            }
        };

        var client = new HttpClient();
        client.get('/menu/data', function (response) {

            let jsons = JSON.parse(response);
            document.getElementById("username").innerText = jsons.username;
            document.getElementById("account_balance").innerText = jsons.account_balance;
        });



        // $.get(
        //     "/menu/data",
        //     function (data) {
        //
        //         $("#username").text(data.username);
        //         $("#account_balance").text(data.account_balance);
        //
        //     })
        //     .fail(function () {
        //
        //         console.log("not managed to fetch seats numbers")
        //         $("#username").text("User name not found");
        //         $("#account_balance").text("-1");
        //
        //     });
    </script>
</div>