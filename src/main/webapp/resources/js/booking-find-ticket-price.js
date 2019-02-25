function findPrice() {

    var eventName = $("#event").val();
    var vipSeatNumber = $("#vip_seats").val();
    var commonSeatNumber = $("#common_seats").val();

    console.log("vip = "+vipSeatNumber);
    console.log("common = "+commonSeatNumber);

    $.get(
        '/booking/calc-ticket-price',
        {
            event_name: eventName,
            vip_seat : vipSeatNumber,
            common_seat : commonSeatNumber
        },
        function (data) {

            var v_ticket_price = data.vip_ticket_price;
            var c_ticket_price = data.common_ticket_price;

            console.log("vip price = "+v_ticket_price);
            console.log("common price = "+c_ticket_price);

            $("#vip_price").text(v_ticket_price);
            $("#common_price").text(c_ticket_price);

        });
}

function updateSeats() {

    console.log("clean seats");
    $('#vip_seats').empty();
    $('#common_seats').empty();

    var eventName = $("#event").val();

    console.log("event name [" + eventName+"]");

    $.get(
        "/auditorium/seats",
        {
            event_name: eventName
        },
        function (data) {

            var vip_seats = data.vip_seats.split(",");
            var common_seats = data.common_seats.split(",");

            console.log("vip seats = "+vip_seats);
            console.log("common seats = "+common_seats);

            var arrayLength = common_seats.length;
            for (var i = 0; i < arrayLength; i++) {
                // var opt = document.createElement('option');
                // opt.value = common_seats[i];
                // opt.text = common_seats[i];
                // opt.innerText = common_seats[i];

                $("#common_seats")
                    .append("<option value=" + common_seats[i] + ">" + common_seats[i] + "</option>");
                // .appendChild(opt);
            }

            var arrayLength2 = vip_seats.length;
            for (var j = 0; j < arrayLength2; j++) {

                $("#vip_seats")
                    .append("<option value=" + vip_seats[j] + ">" + vip_seats[j] + "</option>");
            }
        })
        .fail(function () {

            console.log("not managed to fetch seats numbers")
        });
}
