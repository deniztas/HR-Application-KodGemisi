if (!jQuery) { throw new Error("This page requires jQuery") }


(function($) {

    ///READ INPUT FROM /LOGIN PAGE
    function readInput() {
        var username = document.getElementById('username').value;
        var password = document.getElementById('password').value;
        $.ajax({
            type: "POST",
            url: "/login/continue",
            data: {       
                "username": username,
                "password": password
            },
            dataType: 'json',
            success : function(json) {
                if(json.isLogged == true){
                    document.cookie = "username="+username+";";
                    window.location.replace("/homepage");
                }
                else{
                    window.alert("Wrong password or username")
                }
            },
        }); 
    }

    $("#login").click(function(e){
        e.preventDefault();
        readInput();
    });

     $("#loginArea").click(function(e){
        e.preventDefault();
        document.location.href = "/login";
    });

    //INPUT ENDS
})(jQuery);