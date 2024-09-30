export default function buildDonutGraph(data, htmlCanvasId) {

	let canvas = document.getElementById(htmlCanvasId);

	return new Chart(canvas, {
		type: 'doughnut',
		data: {
			labels: ['Número de confirmações', 'Presenças não confirmadas'],
			datasets: [{
				label: 'Confirmações',
				data: data,
				backgroundColor: ['#00488E', '#c20000'],
				hoverOffset: 5
			}]
		}
	})
}