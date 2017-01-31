/**
 * 
 */

function getJiraData(){
	alert('entered');
	try {
		$
				.ajax({
					url : "rest/jirareporter",
					type : 'GET',
					async : false,
					dataType : 'json',
					data : {
						
					},
					contentType : 'application/json',
					mimeType : 'application/json',
					beforeSend : function(xhr) {
						xhr.setRequestHeader("Accept", "application/json");
						xhr
								.setRequestHeader("Content-Type",
										"application/json");
					},
					success : function(data) {
						alert('hello');
					alert(data);
					
					
					$('.jdata tbody').html('');
//					var jiraIssue = 
//						var jiraDesc = 
//							var jiraAssignee = 
					},
					error : function(data, status, er) {
						alertMessage(
								"error",
								"There is a problem fetching data from JIRA");
					}
				});
	} catch (err) {
		throw err;
	}
	
}

/*function createTR_4_jiraData(id, auditid,lastUpdtTime, lastUpdtUser,lastUpdtAction) {

	var lastUpdtActionFlag ="";
	if (lastUpdtAction == "A"){
		lastUpdtActionFlag = "Added";
	}
	else if (lastUpdtAction == "U"){
		lastUpdtActionFlag = "Updated";
	}else if (lastUpdtAction == "D"){
		lastUpdtActionFlag = "Deleted";
	}	
	
	var tr='<tr >'
		+ '<td class="text-center"><span>' +  lastUpdtTime + '</span></td>'
		+ '<td class="text-center"><span>' +  lastUpdtUser + '</span></td>'
		+ '<td class="text-center"><span>' +  lastUpdtActionFlag +'</span></td>'
		+ '<td class="text-center"><span><a href="#" class="viewjob"> View</a></span></td>'
		+ '<td class="text-center">'
		+ '<a href="#" class="rollback" auditid="' + auditid +'" data-toggle="modal" data-target=".bs-example-modal-sm" >Roll back </a></td>'
		
		+ '</tr>';
	return tr;
}*/
 