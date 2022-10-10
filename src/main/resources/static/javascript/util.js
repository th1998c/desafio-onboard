function validaData(){
    const dateControl = document.getElementById('payment');
    const dateRetorno = document.getElementById('paymentDate');
    var dia = dateControl.value.toString().slice(8,10);
    var ano = dateControl.value.toString().slice(0,4);
    var mes = dateControl.value.toString().slice(5,7);
    var hora = dateControl.value.toString().slice(11,16);
    var data = dia+'/'+ mes + '/' + ano+ ' '+ hora;
    dateRetorno.value = data.toString();
}