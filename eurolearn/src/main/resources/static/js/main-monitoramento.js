import buildDonutGraph from "./buildDonutGraph.js";
import getData from "./getConfirmacaoData.js";
import getTaxa from "./getTaxa.js";
import anime from "../animejs/anime.es.js";

function generateGraph(data, htmlCanvasId){
	
	if (Chart.getChart(htmlCanvasId) != undefined){
        Chart.getChart(htmlCanvasId).destroy();
    }
    
    return buildDonutGraph(data, htmlCanvasId);
}

async function main(){
	let data = [];
	data = await getData();
	
	
	const taxaValueField = document.getElementById('taxaValue');
	
	
	generateGraph(data, 'presencaDonutGraph');
	
	const taxaObject = {
		taxa: 0
	}
	
	anime({
 	 targets: taxaObject,
 	 taxa: await getTaxa() * 100,
 	 round: 1000,
 	 easing: 'linear',
 	 update: function() {
		  taxaValueField.textContent = taxaObject.taxa;
	  }
	});
	
	if(await getTaxa() < 0.7){
		taxaValueField.classList.add('graph-animated-value-danger');
	} else {
		taxaValueField.classList.add('graph-animated-value');
	}
	
}

main();