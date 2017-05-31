
if (!jQuery) { throw new Error("This page requires jQuery") }

(function($) {
	
	function getCookie(cname) {
	    var name = cname + "=";
	    var decodedCookie = decodeURIComponent(document.cookie);
	    var ca = decodedCookie.split(';');
	    for(var i = 0; i <ca.length; i++) {
	        var c = ca[i];
	        while (c.charAt(0) == ' ') {
	            c = c.substring(1);
	        }
	        if (c.indexOf(name) == 0) {
	        	var foundUsername = c.substring(name.length, c.length);
	        	if(foundUsername.length == 0){
	    	    	return null;
	    	    }else{
	    	    	return foundUsername;
	    	    }
	        }
	    }
	    return null;
	}
	
	function changeUsername(){
		var username = getCookie('username');
		if(username != null){
			$("#username").text("Hello " + username);
			$("#adminPage").css('display', 'inherit');    
			$("#signOut").css('display', 'inherit');
			$("#loginArea").css('display', 'none');
		}
		else{
			$("#username").text("Hello User");
			$("#adminPage").css('display', 'none');       
			$("#signOut").css('display', 'none');
			$("#loginArea").css('display', 'inherit');
		}	
	};
	changeUsername();
    
    function logout() {
        document.cookie = "username=;";      
    	changeUsername();
    	
    	var username = document.getElementById('username').value;
        $.ajax({
            type: "POST",
            url: "/login/logout",
            data: {            
                "username": username,
            },
            dataType: 'json',
            success : function(json) {
                if(json.isLogged === true){             
                    window.location.replace("/");             
                }
            },
        }); 
        
    }

    $("#signOut").click(function(e){
        e.preventDefault();
        
        document.location.href = "/homepage";
        logout();
    });

    $("#loginArea").click(function(e){
        e.preventDefault();
        document.location.href = "/login";
    });
    
	$("#adminPage").click(function(e){
		e.preventDefault();
		document.location.href = "/admin";
	});
	
})(jQuery);
