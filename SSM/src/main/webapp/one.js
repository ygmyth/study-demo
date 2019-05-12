$(function(){
   $.ajax({
            url: "/exception1",
            success: function (result) {
                console.log(result)
            }
       }
   )
});