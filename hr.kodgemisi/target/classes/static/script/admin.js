if (!jQuery) { throw new Error("This page requires jQuery") }

(function($) {

	function CreateJob(){
		var jobTitle = document.getElementById("jobTitle").value;
		var jobDesc = document.getElementById("jobDesc").value;
		var numOfHired = parseInt(document.getElementById("numOfHired").value);
		var selectMonth = $("#selectMonthDate>option:selected").text();
		var selectYear = $("#selectYearDate>option:selected").text();
		var lastDate = selectMonth.concat(selectYear);
		
		$.ajax({
			type:"POST",
			url: "/admin/create",
			data: {
				"jobTitle": jobTitle,
				"jobDesc": jobDesc,
				"numOfHired": numOfHired,
				"lastDate": lastDate
				
			},
			dataType: "json",
			success: function(json){
				window.alert(json.jobTitle + " created");
				window.location.href = "/admin";
			}
			
		});
	}

	$("#createButton").click(function(e){
		console.log("Im clicked");
		CreateJob();   
	});
	
	
})(jQuery);