
if (!jQuery) { throw new Error("This page requires jQuery") }


(function($) {

	function createApply() {
		var name = document.getElementById('name').value;
		var surname = document.getElementById('surname').value;
		var email = document.getElementById('email').value;
		var phone = document.getElementById('phone').value;
		var address = document.getElementById('address').value;  
		var thoughts = document.getElementById('thoughts').value;  
		var jobID = $("#jobID").text(); 
		
		$.ajax({
			type:"POST",
			url: "/homepage/add",
			data: {           
				"name": name,
				"surname": surname,
				"email" : email,
				"phone" : phone,
				"address" : address,
				"thoughts" : thoughts,
				"jobID" : jobID
			},
			dataType: 'json',
			success : function(json) {  
				window.alert("Apply success to " + json.jobTitle);	 
				window.location.href = "/homepage";		
			}
		}); 
	}

	$("#applyButton").click(function(e){	
		e.preventDefault();
		$("#applyDiv").css('display', 'inherit');
		
	});
	
	$("#applyJob").click(function(e){
		e.preventDefault();
		createApply();
		$("#applyDiv").css('display', 'none');
	});

})(jQuery);