if (typeof jQuery !== 'undefined') {
    (function ($) {
        $('#spinner').ajaxStart(function () {
            $(this).fadeIn();
        }).ajaxStop(function () {
                $(this).fadeOut();
            });

        $('.deleteuser').click(function (eventObject) {
            var id = $(this).data("userid");
            var row = $(this).closest("tr");
            var userName = $(this).data("username");
            deleteRow(id,row, "Deleted user " + userName)
        });

        $('.deletemachine').click(function (eventObject) {
            var id = $(this).data("machineid");
            var row = $(this).closest("tr");
            var name = $(this).data("name");
            deleteRow(id, row, "Deleted machine " + name)

        });
        $('.deletestudy').click(function (eventObject) {
            var id = $(this).data("studyid");
            var row = $(this).closest("tr");
            var name = $(this).data("studyname");
            deleteRow(id, row, "Deleted study " + name)

        });


    })(jQuery);



}

function deleteRow(id, row, message) {
     $.ajax({
             type:"DELETE",
             url:"delete/" + id
         }

     ).done(function () {
             row.slideUp();
             $(".message").text(Message)
         });

 }



