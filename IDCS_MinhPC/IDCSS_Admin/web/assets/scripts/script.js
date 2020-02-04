

$(document).ready(function(){
    $(".mb-2").click(function() {
        var $row = $(this).closest("tr");    // Find the row
        var $tds = $row.find("td");
        $.each($tds, function (index, value){
            if (index == 1) {
                $("#account").val($(value).text());
                $("#account1").val($(value).text());
            }
            
            if (index == 2) {
                $("#fullname").val($(value).text());
            }
            if (index == 3) {
                $("#email").val($(value).text());
            }
            if (index == 4) {
                $("#phoneNum").val($(value).text());
            }
            if (index == 5) {
                $("#addressDialog").val($(value).text());
            }
            if (index == 6) {
                $("#creditDialog").val($(value).text());
            }
        });
    });


    $("#search").on("keyup", function() {
      var value = $(this).val().toLowerCase();
      $("#mytable tr").filter(function() {
        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
      });
    });
  });