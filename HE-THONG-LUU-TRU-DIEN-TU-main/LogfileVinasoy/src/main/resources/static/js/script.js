
$(document).ready(function(){
  $("#myInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#myTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});

$(document).ready(function() {
    $("table.table.table-hover.mb-3.rounded-bottom").tablesorter({
    });

});

function getListSend() {
        var list = $(".processcheckbox");
        var touser = document.getElementById("touser").value;
        var listSend = [];
        for (let i = 0; i < list.length; i++) {
            if (list[i].checked ) {
                listSend.push(list[i].getAttribute("id"));
            }
        }
        window.location.href = 'http://localhost:8080/web/attachment/shareprocess?id=' + listSend +'&u='+ touser;
    }
    function starProcess() {
            var list = $(".processcheckbox");
            var touser = document.getElementById("loginuser").value;
            var listSend = [];
            for (let i = 0; i < list.length; i++) {
                if (list[i].checked ) {
                    listSend.push(list[i].getAttribute("id"));
                }
            }
            window.location.href = 'http://localhost:8080/web/attachment/starprocess?id=' + listSend +'&u='+ touser;
        }
    function shareticket() {
            var list = $(".ticketcheckbox");
            var touser = document.getElementById("touser").value;
            var idprocess = document.getElementById("idProcess").value;
            var listSend = [];
            for (let i = 0; i < list.length; i++) {
                if (list[i].checked ) {
                    listSend.push(list[i].getAttribute("id"));
                }
            }
            window.location.href = 'http://localhost:8080/web/attachment/shareticket?idpro='+idprocess+'&id=' + listSend +'&u='+ touser;
        }
        function starticket() {
                    var list = $(".ticketcheckbox");
                    var touser = document.getElementById("loginuser").value;
                    var idprocess = document.getElementById("idProcess").value;
                    var listSend = [];
                    for (let i = 0; i < list.length; i++) {
                        if (list[i].checked ) {
                            listSend.push(list[i].getAttribute("id"));
                        }
                    }
                    window.location.href = 'http://localhost:8080/web/attachment/starticket?idpro='+idprocess+'&id=' + listSend +'&u='+ touser;
                }
    function shareattachment() {
                var list = $(".attachmentcheckbox");
                var touser = document.getElementById("touser").value;
                var idprocess = document.getElementById("idProcess").value;
                var idticket = document.getElementById("idTicket").value;
                var listSend = [];
                for (let i = 0; i < list.length; i++) {
                    if (list[i].checked ) {
                        listSend.push(list[i].getAttribute("id"));
                    }
                }
                window.location.href = 'http://localhost:8080/web/attachment/shareattachment?idpro='+idprocess+'&idticket='+idticket+'&id=' + listSend +'&u='+ touser;
            }
    function starattachment() {
        var list = $(".attachmentcheckbox");
        var idprocess = document.getElementById("idProcess").value;
        var idticket = document.getElementById("idTicket").value;
        var listSend = [];
                for (let i = 0; i < list.length; i++) {
                    if (list[i].checked ) {
                        listSend.push(list[i].getAttribute("id"));
                    }
                }
        window.location.href = 'http://localhost:8080/web/attachment/starattachment?idpro='+idprocess+'&idticket='+idticket+'&id=' + listSend ;
    }
//choose or drop a file here
    dropContainer.ondrop = function(evt) {
      // pretty simple -- but not for IE :(
      fileInput.files = evt.dataTransfer.files;
      // If you want to use some of the dropped files
      const dT = new DataTransfer();
      dT.items.add(evt.dataTransfer.files[0]);
      dT.items.add(evt.dataTransfer.files[3]);
      fileInput.files = dT.files;
      evt.preventDefault();
    };

    // ajax

$('#formcheck').submit(function(event){
event.preventDefault();


var formData = {
                file1: $('#file1')[0].files,
                file2: $('#file2')[0].files,
            };
    $.ajax({
        url: "/api/attachment/check",
        type: "GET",
        data: formData,
        dataType: "json",
              encode: true,
        }).done(function (data) {
                    console.log(data);
            });

});

