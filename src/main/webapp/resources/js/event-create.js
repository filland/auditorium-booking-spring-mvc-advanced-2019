function createEvnt() {

    var eventName = $("#name").val();

    $.post(
        "/event/create",
        {
            name: eventName,
            rate: $("#rate").val(),
            base_price: $("#base_price").val(),
            date_time: $("#date_time").val(),
            auditorium_name: $("#auditorium_name").val()
        },
        function () {

            console.log("event creation finished with success.");
            $('#result').text("Event with name [" + eventName + "] created.");

        })
        .fail(function () {
            console.log("error while creating new event.");
            $('#result').text("Error while creating event with name [" + eventName + "].");
        });

}