/*$(document).ready(function () {
    $("#lstlayouts").change(function () {
        updateGraph();
    });


    $("#lststatistics").change(function () {
        updateGraph()
    });
});

function updateGraph()
{
    var statistic = $("#lststatistics").val();
    var layout = $("#lstlayouts").val();
    var inputdata = {"filename": GexfJS.params.graphFile, "layout": layout, "statistic": statistic}
    $.ajax({url: "/generategraph",
        data: inputdata,
        dateType: "json",
        contentType: "application/json",
        success: function (result) {
            $("#div1").html(result);
        }});
}*/