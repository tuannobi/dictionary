$(document).ready(function (){
    $.ajax({
        url: 'rest/admin/vocabularies',
        type: 'GET',
        // data: 'twitterUsername=jquery4u',
        success: function(data) {
            //called when successful
            // $('#ajaxphp-results').html(data);
            // alert(log(data))
            console.log(data[0].englishWord)
            for(i=0;i<data.length;i++){
                console.log(data[i].englishWord)
                console.log(data[i].pronunciation)
                console.log(data[i].vietnameseMeaning)
            }
        },
        contentType: "application/json",
        dataType: 'json',
        error: function(e) {
            //called when there is an error
            // console.log(e.message);
            alert(e.message)
        }
    });
})