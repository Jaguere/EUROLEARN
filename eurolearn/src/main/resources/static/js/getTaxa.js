export default async function getTaxa(){
	let url = 'http://localhost:8080/REST/monitorar/taxaPresenca';
	
	const getTaxa = new Request(url);
	const response = await fetch(getTaxa);
	return await response.json();
}