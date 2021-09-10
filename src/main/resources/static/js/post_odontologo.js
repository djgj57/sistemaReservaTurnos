$(document).ready(function() {
    $("#add_new_odontologo").submit(function(evt){
        evt.preventDefault();

        let formaData = {
            nombre: $("#nombre").val(),
            apellido: $("#apellido").val(),
            matricula: $("#matricula").val(),
        }
        $.ajax({
            url: '/odontologos/registrar',
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(formaData),
            dataType: 'json',
            async: false,
            cache: false,
            success: function(response) {
                let odontologo = response
                console.log(response)
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                '<strong></strong> odontologo agregado </div>'
                $('#response').append(successAlert);
                $('#response').css({"display":"block"});
                resetUploadForm();
            },
                error: function(response) {
                    let successAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong> Error intente nuevamente</strong> </div>'
                resetUploadForm();
                }
        });

    });
    function resetUploadForm(){
        $("#nombre").val("");
        $("#apellido").val("");
        $("#matricula").val("");
    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            $(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/odontologos.html"){
             $(".nav .nav-item a:last").addClass("active");
        }
    });

})




