function createBarGraph(assignmentID){
	var width = 300;
	var height = 150;
	var barWidth = 30;

	var data_fields = $(".grade");
	var data = [];

	for(i = 0; i < data_fields.length; i++) {
		data.push(parseFloat(data_fields[i].innerHTML));
	}

	var group_data = [0,0,0,0,0,0,0,0,0,0];

	for(i = 0; i < data.length; i++) {
		if(typeof data[i] == "number" && data[i] <= 10 && data[i] >= 1) {
			group_data[Math.round(data[i]) - 1] += 1;
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
	createBarGraph(3);
}