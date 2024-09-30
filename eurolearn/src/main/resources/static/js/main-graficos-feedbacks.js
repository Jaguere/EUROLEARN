import buildBarGraph from './buildBarGraph.js'
import getFeedbackData from './graficos-feedbacks.js'
import anime from '../animejs/anime.es.js';

function generateGraph(data, htmlCanvasId, graphType, backgroundColor, borderColor){

    if (Chart.getChart(htmlCanvasId) != undefined){
        Chart.getChart(htmlCanvasId).destroy();
    }

    switch (graphType) {
        case 'bar':
           return buildBarGraph(data, htmlCanvasId, backgroundColor, borderColor);
        default:
            break;
    }
}

function generateMeanValue(data){
    return math.mean(data[1].filter((value) => value !== 0)).toFixed(2);
}

function hideElement(elementId){
    document.getElementById(elementId).classList.add('display-none');
}

function showElement(elementId){
    document.getElementById(elementId).classList.remove('display-none');
}


// inserir aqui a lógica async (pronto => exibir gráfico, esperando => loading, erro => erro na tela)
const buttonGenerateGraph = document.getElementById('generate-mean-graph');
hideElement('barChartPerMonth');
hideElement('barChartInfo');
hideElement('filterInfo');


buttonGenerateGraph.addEventListener('click', async () => {
    let filter = document.getElementById('monthsFilter').value.toString();
    
    console.log(`O valor do filtro é ${filter} do tipo ${typeof filter}`)

    let data = [];  

	if(filter == '6'){
		data = await getFeedbackData('6');
	} else if(filter == '12') {
		data = await getFeedbackData('12');
	}

	
	console.log(data);

    showElement('barChartPerMonth');
    showElement('barChartInfo');
    showElement('filterInfo');
    
    
    
    generateGraph(data, 'barChartPerMonth', 'bar', '#3B4DD6', '#3B4DD6');

    const filterHeader = document.getElementById('filterNumber');
    
    const meanValueIndicator = document.getElementById('meanValue');
    
    const meanValueObject = {
		meanValue: 0,
	}
    
    
    anime({
 	 targets: meanValueObject,
 	 meanValue: generateMeanValue(data),
 	 round: 100,
 	 easing: 'linear',
 	 update: function() {
		  meanValueIndicator.textContent = meanValueObject.meanValue;
	  }
	});
    
    filterHeader.innerText = filter;
})

//async function showData(data){
//	console.log(await data);
//}
//
//showData(getFeedbackData('6'));