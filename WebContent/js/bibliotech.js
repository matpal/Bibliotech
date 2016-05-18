$(document).ready(function(){
           
    $('#login-anchor').click(function(){
        $('#box-login').slideToggle('slow');
        $('#username').focus();
        
    });

    $("#wrapper").fadeIn(2000, function(){
        $("#poesia").slideDown(2000);
    });

});