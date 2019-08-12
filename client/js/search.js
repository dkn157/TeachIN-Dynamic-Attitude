$(document).ready(function(){
    $.ajax({
        url:'http://localhost:8080/teachin/api/search/{{url}}/{id}',
        type:'GET',
        contentType: 'application/json',
        async: true,
        success: populate,
        error: errorCallback
    });
});
var errorCallback = function(){
    alert("error");
}

var  populate = function(){
    var elements = JSON.parse(teststring);
    $('#name').append(elements.nome).innerHTML;
    $('#problem').append(elements.problem).innerHTML;
    $('#method').append(elements.problem).innerHTML;
    $('#age').append(elements.maxAge).innerHTML;
    $('#minAge').append(elements.minAge+ ' - '+ elements.maxAge).innerHTML;
    $('#desc').append(elements.desc).innerHTML;
};
