function createBarGraph(assignmentID){
	var width = 300;
	var height = 150;
	var barWidth = 30;

	var data = $("#Assignment"+assignmentID+"-data")[0].getAttribute("data");
	var json = JSON.parse(data);
	var group_data = [0,0,0,0,0,0,0,0,0,0];
	json = [];
	for(i = 0; i < 100; i++){
		json.push(Math.random() * 9 + 1);
	}
	for(i = 0; i < json.length; i++) {
		if(typeof json[i] == "number" && json[i] <= 10 && json[i] >= 1) {
			group_data[Math.round(json[i]) - 1] += 1;
		}
	}

	var x = d3.scale.linear()
		.domain([0, d3.max(group_data)])
		.range([0, height]);

	var svg = d3.select("#graphbox");
	svg.attr("width", width).attr("height", height);

	var bar = svg.selectAll("g")
		.data(group_data)
		.enter()
		.append("g")
		.attr("transform", function(d, i) {
			return "translate(" + i * barWidth + ", " + (height - x(d)) + ")";
		});

	bar.append("rect")
		.attr("width", barWidth - 1)
		.attr("height", x);

	bar.append("text")
    	.attr("y", function(d) { return x(d) - 5; })
    	.attr("x", barWidth / 2)
    	.attr("dy", ".35em")
    	.text(function(d, i) { return i + 1; });
}

function prepare(){
	createBarGraph(1);
}