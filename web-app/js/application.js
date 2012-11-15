

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

        $(document).ready(function() {

            var g = $('.graph').first()

            for (var i=0; i< 60; i++) {
                $('#graphs').append(g.clone());
            }

            var options = {
                xaxis : {
                    // extend graph to fit the last point
                    max : 31
                },
                grid : {
                    show : false
                }
            };

            // main series
            var series = [{
                data : [],
                color : '#000000',
                lines : {
                    lineWidth : 0.8
                },
                shadowSize : 0
            }];

            // color the last point red.
            series.push({
                data : [],
                points : {
                    show : true,
                    radius : 1,
                    fillColor : '#ff0000'
                },
                color : '#ff0000'
            });

            // draw the sparkline
            $('.graph').each(function(i){
                var data = $(this).data("values");
                var newData = [];
                $.each(data, function(index, dataItem){
                  newData.push([index, dataItem]);
                });
                series[0].data = newData;
                var endPoint = newData[newData.length -1];
                series[1].data = [endPoint];
                options.xaxis.max = newData.length + 1;
                plot = $.plot($(this).find('.chart'), series, options);
                var o = plot.pointOffset({x: endPoint[0], y: endPoint[1]});
                $('<div class="data-point-label">' + endPoint[1] + '</div>').css( {
                    position: 'absolute',
                    left: o.left + 5,
                    top: o.top - 7,
                    display: 'none'
                }).appendTo(plot.getPlaceholder()).show();
                var label = $(this).find('.label');
                //label.text("Machine " + i+1)
//                if (endPoint[1] > 75) {
//                    label.addClass("error");
//                }
            });

        });


    })(jQuery);


}

function deleteRow(id, row, message) {
    var r = confirm("Are you sure?")
    if (r == true) {
        $.ajax({
                type:"DELETE",
                url:"delete/" + id
            }

        ).done(function () {
                row.slideUp();
                $(".message").text(Message)
            });
    }
}





