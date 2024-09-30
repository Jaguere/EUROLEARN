import cleanData from "./cleanData.js";

export default function buildBarGraph(data, htmlCanvasId, backgroundColor, borderColor){  
	
	cleanData(data);

    let canvas = document.getElementById(htmlCanvasId);

    return new Chart (canvas, {
        type: 'line',
        data: {
            labels: data[0],
            datasets: [{
                label: 'Índice de satisfação médio',
                data: data[1],
                borderWidth: 2,
                backgroundColor: backgroundColor,
                borderColor: borderColor,
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true,
                    max: 5,
                }
            }
        }
    });
}