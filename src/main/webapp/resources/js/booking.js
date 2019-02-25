function updateAllSeats() {

    console.log("clean seats");
    $("#seats").empty();

    var eventName = $("#event").val();

    console.log("event name [" + eventName + "]");

    $.get(
        "/auditorium/seats-all",
        {
            event_name: eventName
        },
        function (data) {

            var seats = data.seats.split(",");

            console.log("received seats = " + seats);

            for (var i = 0; i < seats.length; i++) {
                // var opt = document.createElement('option');
                // opt.value = common_seats[i];
                // opt.text = common_seats[i];
                // opt.innerText = common_seats[i];

                $("#seats")
                    .append("<option value=" + seats[i] + ">" + seats[i] + "</option>");
                // .appendChild(opt);
            }

        })
        .fail(function () {

            console.log("not managed to fetch seats numbers")
        });
}


function bookTicket() {

    var event_name = $("#event").val();
    var user_id = $("#user_id").val();
    var seat_number = $("#seats").val();

    $.post(
        '/booking/book',
        {
            event_name: event_name,
            user_id: user_id,
            seat_number: seat_number
        },
        function (data) {

            console.log("success");
            console.log(data);
        }
    ).fail(function () {

        console.log("fail");
    })

}


function createNewUsers() {

    $.post(
        '/registration',
        {
            name: "alexey",
            email: "fasdfasffd@gmail.com",
            birthday: "2000-01-01"
        },
        function (data) {
            console.log("created a new user");
            $("#user_created").text("A new user created.");
        }
    ).fail(function () {
        console.log("failed when creating a new user")
    });

    $.post(
        '/registration',
        {
            name: "bob",
            email: "123@gmail.com",
            birthday: "2001-01-01"
        },
        function (data) {
            console.log("created a new user");
            $("#user_created").text("A new user created.");
        }
    ).fail(function () {
        console.log("failed when creating a new user")
    });
}

function createNewEvents() {

    $.post(
        '/event/create-events',
        {},
        function (data) {
            console.log(">>> events created.")
            $("#events_created").text("A few events created.");
        }
    ).fail(function () {
        console.log(">>> failed to create events.")
    });
}