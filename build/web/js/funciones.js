//AQUI ABRIMOS EL MODAL //
function nVentana(hoja, tit, ancho) {
    $('.modal-content').css('width', ancho);
    $("#myModalLabel").html(tit);
    var url = 'includes/' + hoja;
    $("#modal-body-gen").html('<iframe width="100%" height="450px" frameborder="0" scrolling="yes" allowtransparency="true" src="' + url + '"></iframe>');
    $('#myModal22').modal({
        keyboard: true,
        backdrop: 'static',
        show: true
    });
};

function cierra() {
    $('#myModal22').modal('hide');

}
