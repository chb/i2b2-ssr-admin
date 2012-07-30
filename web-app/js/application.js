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
            deleteRow(id, row, "Deleted user " + userName)
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

//show the modal overlay and popup window
function showPopUpMessage(msg) {
    overlayElement = document.createElement("div");
    overlayElement.className = 'modalOverlay';
    modalWindowElement = document.createElement("div");
    modalWindowElement.className = 'modalWindow';
    modalWindowElement.innerHTML = msg;
    modalWindowElement.style.left = (window.innerWidth - 200) / 2 + "px";
    document.body.appendChild(overlayElement);
    document.body.appendChild(modalWindowElement);
    setTimeout(function () {
        modalWindowElement.style.opacity = 1;
        overlayElement.style.opacity = 0.4;
        overlayElement.addEventListener("click", hidePopUpMessage, false);
    }, 300);
}
//hide the modal overlay and popup window
function hidePopUpMessage() {
    modalWindowElement.style.opacity = 0;
    overlayElement.style.opacity = 0;
    overlayElement.removeEventListener("click", hidePopUpMessage, false);
    setTimeout(function () {
        document.body.removeChild(overlayElement);
        document.body.removeChild(modalWindowElement);
    }, 400);
}



