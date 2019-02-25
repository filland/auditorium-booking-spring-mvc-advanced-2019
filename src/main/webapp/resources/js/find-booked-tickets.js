function findBookedTickets() {

    $.get(
        '/booking/search-booked-tickets',
        {
            event_name: $("#event").val(),
            auditorium_name: $("#auditorium_name").val(),
            date_time: $("#date_time").val()
        },
        function (data) {

            $("#tickets_table tbody").empty();

            var arrayLength = data.tickets.length;

            if (arrayLength === 0){
                $("#tickets_table").hide();
            } else {
                $("#tickets_table").show();
            }

            for (var i = 0; i < arrayLength; i++) {

                var ticket = data.tickets[i];

                $("#tickets_table tbody")
                    .append(" <tr><td>" + ticket.user.name + "</td>\n" +
                        "            <td>" + ticket.user.email + "</td>\n" +
                        "            <td>" + ticket.seats + "</td>\n" +
                        "            <td>" + ticket.price + "</td></tr>");
            }

            console.log("booked ticked search went with no errors.")
        }
    ).fail(function () {

        console.log("not managed to find booked ticked for the event named = " + $("#event").val());
    })

}


function findBookedTicketsAndGeneratePdf() {

    $.ajax({
        url: "/find-booked-tickets-pdf",
        data:  {
            "event_name": $("#event").val(),
            "auditorium_name": $("#auditorium_name").val(),
            "date_time": $("#date_time").val()
        },
        type: "GET",
        headers:{'Accept': 'application/pdf'},
        success: function(data) {

            console.log(data);

            var a = document.createElement("aaaa");
            var file = new Blob([data], {type: "application/pdf;base64"});
            a.href = URL.createObjectURL(file);
            a.download = "booked_tickets.pdf";
            a.click();

            console.log("pdf created.")
        }
    }).fail(function () {
        console.log("pdf creation failed")
    });

//     $.ajax({
//         url: '/find-booked-tickets-pdf',
//         {
//             "event_name": $("#event").val(),
//             "auditorium_name": $("#auditorium_name").val(),
//             "date_time": $("#date_time").val()
//         },
//         type: "GET",
//         beforeSend: function(xhr){xhr.setRequestHeader('X-Test-Header', 'test-value');},
//         success: function (data) {
//
//             $("#tickets_table tbody").empty();
//
//
//
//             console.log("booked ticked search went with no errors.")
//         }
// });

}