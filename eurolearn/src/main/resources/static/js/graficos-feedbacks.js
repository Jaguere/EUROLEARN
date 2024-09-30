export default async function getFeedbackData(filter){
	
    let url = '';

	if(filter == '6'){
		url = 'http://localhost:8080/restfeedbacks/groupbylast6months';
	} else {
		url = 'http://localhost:8080/restfeedbacks/groupbylast12months'
	}

    const getFeedback = new Request(url);
    const response = await fetch(getFeedback);
    return await response.json();
}

