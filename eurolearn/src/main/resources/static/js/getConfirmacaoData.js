export default async function getData(){
	let url = 'http://localhost:8080/REST/monitorar/data';
	
	const getData = new Request(url);
	const response = await fetch(getData);
	return await response.json();
}