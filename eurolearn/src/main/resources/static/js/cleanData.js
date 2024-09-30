export default function cleanData(data){
    let cleanDates = [];
    
    for(let i = 0; i<data[0].length; i++){
        cleanDates.push(data[0][i].slice(0, 7).replace("-", "/"));
    }
    
    data[0] = cleanDates;
}