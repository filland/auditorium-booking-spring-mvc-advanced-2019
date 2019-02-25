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

 $.get(
        '/booking/search-booked-tickets',
        {
            event_name: $("#event").val(),
            auditorium_name: $("#auditorium_name").val(),
            date_time: $("#date_time").val()
        },
        function (data) {

            var doc = new jsPDF()

            var arrayLength = data.tickets.length;

            doc.text('Event | Username | User email | Seat | Price', 10, 10);

            var current_y_coord = 20
            for (var i = 0; i < arrayLength; i++) {

                var ticket = data.tickets[i];

                if(i === 27){

                    doc.addPage();
                    current_y_coord = 20;
                }

                var row = ticket.event.name+ " | "+ticket.user.name+" | "+ticket.user.email+ " | "+ticket.seats+" | "+ticket.price;

                doc.text(row, 10, current_y_coord);
                current_y_coord+=10;
            }

            doc.save('a4.pdf')

            console.log("booked ticked search went with no errors.")
        }
    ).fail(function () {

        console.log("not managed to find booked ticked for the event named = " + $("#event").val());
    })

}


function openPdf(){

    $.get(
            '/booking/search-booked-tickets',
            {
                event_name: $("#event").val(),
                auditorium_name: $("#auditorium_name").val(),
                date_time: $("#date_time").val()
            },
            function (data) {

                console.log("booked ticked search went with no errors.")
            }
        ).fail(function () {

            console.log("not managed to find booked ticked for the event named = " + $("#event").val());
        })

}