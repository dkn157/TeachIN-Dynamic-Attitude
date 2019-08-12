window.onload = function(){
    var url_string = window.location.href;
var url = new URL(url_string);
var id = url.searchParams.get("id");
console.log(id);
    $.ajax({
        url:'http://localhost:8080/teachin/api/issues/',
        type:'GET',
        contentType: 'application/json',
        async: true,
        success: populateDropDown,
        error: errorCallbackdrop
    });

    $.ajax({
        url:'http://localhost:8080/teachin/api/dynamics/'+id,
        type:'GET',
        contentType: 'application/json',
        async: true,
        success: populate,
        error: errorCallbackdrop
    });
    $.ajax({
        url:'http://localhost:8080/teachin/api/search/'+id,
        type:'GET',
        contentType: 'application/json',
        async: true,
        success: searchresult,
        error: errorCallbacksearch
    });
    
};

var  searchresult = function(resultSet){
    $.each(resultSet, function(i, item) {
        var $tr = $('<tr>').append(
            $('<td  class="tableId" style="display:none">').text(item.id),
            $('<td>').text(item.name),
            $('<td>').text(item.maxAge),
            $('<td>').text(item.minAge),
            $('<td>').append('<input class="btn btn-primary clickView" type="button" value="view" />'),
        ).appendTo('#tbody-users');
    });
    $(".clickView").click(function(){
        var $row = $(this).closest("tr");
        var $id = $row.find(".tableId").text();
            $('<td>').append('<input class="btn btn-danger clickDelete" type="button" value="delete" />')
            window.open("result.html?id="+$id,"_self");
});
}

var errorCallbacksearch = function(){
    
}
var errorCallback = function(){
    
}
var errorCallbackdrop = function(){
    
}
var  populate = function(elements){
$('#name').append(elements.name).innerHTML;
$('#issue').append(elements.issue).innerHTML;
$('#method').append(elements.method).innerHTML;
$('#age').append(elements.minAge + ' - ' + elements.maxAge).innerHTML;
$('#desc').append(elements.description).innerHTML;
    };


function populateDropDown(data){
    $.each(data, function (index, value) {
        // APPEND OR INSERT DATA TO SELECT ELEMENT.
        $('#sel').append('<option value="search.html?id=' + value.id + '">' + value.tittle + '</option>');
    });
}