google.charts.load('current', {packages: ['corechart', 'bar', 'table']});


$("#btnTableProductInventory").click(function () {
	var x = document.getElementById("table_product_inventory");
	if(x.style.display == "none") {
		x.style.display = "block";
		document.getElementById("btnTableProductInventory").innerHTML = "Close Table of Product Inventory";
		$.ajax({
			url: "DataVisualization",
			type: "POST",
			data: "action=table_product_inventory",
			success: function (msg) {
				createData("table_product_inventory", msg)
			},
			error: function(){
				console.log("error occurred while making ajax call")
			}
		});
	}else if(x.style.display == "block"){
		x.style.display = "none";
		document.getElementById("btnTableProductInventory").innerHTML = "View Table of Product Inventory";
	}    
});

$("#btnChartProductInventory").click(function () {
	var x = document.getElementById("chart_product_inventory");
	if(x.style.display == "none") {
		x.style.display = "block";
		document.getElementById("btnChartProductInventory").innerHTML = "Close Chart of Product Inventory";
		$.ajax({
			url: "DataVisualization",
			type: "POST",
			data: "action=chart_product_inventory",
			success: function (msg) {
				createData("chart_product_inventory", msg)          
			},
			error: function(){
				console.log("error occurred while making ajax call")
			}
		});
	}else if(x.style.display == "block"){
		x.style.display = "none";
		document.getElementById("btnChartProductInventory").innerHTML = "View Chart of Product Inventory";
	}    
});

$("#btnTableProductOnSale").click(function () {
	var x = document.getElementById("table_product_on_sale");
	if(x.style.display == "none") {
		x.style.display = "block";
		document.getElementById("btnTableProductOnSale").innerHTML = "Close Table of Product on Sale";
		$.ajax({
			url: "DataVisualization",
			type: "POST",
			data: "action=table_product_on_sale",
			success: function (msg) {
				createData("table_product_on_sale", msg)          
			},
			error: function(){
				console.log("error occurred while making ajax call")
			}
		});
	}else if(x.style.display == "block"){
		x.style.display = "none";
		document.getElementById("btnTableProductOnSale").innerHTML = "View Table of Product on Sale";
	}    
});

$("#btnTableProductHavingRebate").click(function () {
	var x = document.getElementById("table_product_having_rebate");
	if(x.style.display == "none") {
		x.style.display = "block";
		document.getElementById("btnTableProductHavingRebate").innerHTML = "Close Table of Product having Rebate";
		$.ajax({
			url: "DataVisualization",
			type: "POST",
			data: "action=table_product_having_rebate",
			success: function (msg) {
				createData("table_product_having_rebate", msg)          
			},
			error: function(){
				console.log("error occurred while making ajax call")
			}
		});
	}else if(x.style.display == "block"){
		x.style.display = "none";
		document.getElementById("btnTableProductHavingRebate").innerHTML = "View Table of Product having Rebate";
	}    
});


$("#btnTableProductSold").click(function () {
	var x = document.getElementById("table_product_sold");
	if(x.style.display == "none") {
		x.style.display = "block";
		document.getElementById("btnTableProductSold").innerHTML = "Close Table of Product Sold";
		$.ajax({
			url: "DataVisualization",
			type: "POST",
			data: "action=table_product_sold",
			success: function (msg) {
				createData("table_product_sold", msg)          
			},
			error: function(){
				console.log("error occurred while making ajax call")
			}
		});
	}else if(x.style.display == "block"){
		x.style.display = "none";
		document.getElementById("btnTableProductSold").innerHTML = "View Table of Product Sold";
	}    
});

$("#btnChartProductSold").click(function () {
	var x = document.getElementById("chart_product_Sold");
	if(x.style.display == "none") {
		x.style.display = "block";
		document.getElementById("btnChartProductSold").innerHTML = "Close Chart of Product Sold";
		$.ajax({
			url: "DataVisualization",
			type: "POST",
			data: "action=chart_product_Sold",
			success: function (msg) {
				createData("chart_product_Sold", msg)          
			},
			error: function(){
				console.log("error occurred while making ajax call")
			}
		});
	}else if(x.style.display == "block"){
		x.style.display = "none";
		document.getElementById("btnChartProductSold").innerHTML = "View Chart of Product Sold";
	}    
});

$("#btnTableDailySalesTransaction").click(function () {
	var x = document.getElementById("table_daily_sales_transaction");
	if(x.style.display == "none") {
		x.style.display = "block";
		document.getElementById("btnTableDailySalesTransaction").innerHTML = "Close Table of Daily Dales Transaction";
		$.ajax({
			url: "DataVisualization",
			type: "POST",
			data: "action=table_daily_sales_transaction",
			success: function (msg) {
				createData("table_daily_sales_transaction", msg)          
			},
			error: function(){
				console.log("error occurred while making ajax call")
			}
		});
	}else if(x.style.display == "block"){
		x.style.display = "none";
		document.getElementById("btnTableDailySalesTransaction").innerHTML = "View Table of Daily Dales Transaction";
	}    
});



function createData(id, jsonData) {
		
	if(id == "table_product_inventory"){
		var parsed_data = $.parseJSON(jsonData);
		var data_arr = new Array();
		for(var i=0; i<parsed_data.length; i++) {
			var product_name = parsed_data[i]["name"];
			var product_price = parsed_data[i]["price"];
			var inventory_count = parsed_data[i]["count"];
			data_arr.push( [ product_name , product_price , inventory_count ] ); 
		}
		drawTable(id, data_arr);
	}
	
	if(id == "chart_product_inventory"){
		var parsed_data = $.parseJSON(jsonData);
		var data_arr = new Array();
		for(var i=0; i<parsed_data.length; i++) {
			var product_name = parsed_data[i]["name"];
			var inventory_count = parsed_data[i]["count"];
			data_arr.push( [ product_name , inventory_count ] ); 
		}
		drawChart(id, data_arr);
	}
	
	if(id == "table_product_on_sale"){
		var parsed_data = $.parseJSON(jsonData);
		var data_arr = new Array();
		for(var i=0; i<parsed_data.length; i++) {
			var product_name = parsed_data[i]["name"];
			var product_price = parsed_data[i]["price"];
			data_arr.push( [ product_name , product_price ] ); 
		}
		drawTable(id, data_arr);
	}
	
	if(id == "table_product_having_rebate"){
		var parsed_data = $.parseJSON(jsonData);
		var data_arr = new Array();
		for(var i=0; i<parsed_data.length; i++) {
			var product_name = parsed_data[i]["name"];
			var product_price = parsed_data[i]["price"];
			var product_discount = parsed_data[i]["discount"];
			data_arr.push( [ product_name , product_price , product_discount ] ); 
		}
		drawTable(id, data_arr);
	}
	
	if(id == "table_product_sold"){
		var parsed_data = $.parseJSON(jsonData);
		var data_arr = new Array();
		for(var i=0; i<parsed_data.length; i++) {
			var product_name = parsed_data[i]["name"];
			var product_price = parsed_data[i]["price"];
			var product_quentity = parsed_data[i]["quentity"];
			var product_sales = parsed_data[i]["sales"];
			data_arr.push( [ product_name , product_price , product_quentity, product_sales ] ); 
		}
		drawTable(id, data_arr);
	}
	
	if(id == "chart_product_Sold"){
		var parsed_data = $.parseJSON(jsonData);
		var data_arr = new Array();
		for(var i=0; i<parsed_data.length; i++) {
			var product_name = parsed_data[i]["name"];
			var product_sales = parsed_data[i]["sales"];
			data_arr.push( [ product_name , product_sales ] ); 
		}
		drawChart(id, data_arr);
	}
	
	if(id == "table_daily_sales_transaction"){
		var parsed_data = $.parseJSON(jsonData);
		var data_arr = new Array();
		for(var i=0; i<parsed_data.length; i++) {
			var date = parsed_data[i]["date"];
			var sales = parsed_data[i]["sales"];
			data_arr.push( [ date , sales ] ); 
		}
		drawTable(id, data_arr);
	}
	
}

function drawTable(id, data){
	
	if(id == "table_product_inventory"){
		var table_data = new google.visualization.DataTable();
		table_data.addColumn('string', 'Product Name');
        table_data.addColumn('number', 'Product Price');
        table_data.addColumn('number', 'Inventory Count');
		table_data.addRows(data);
		var options = {
			'width': 600,
			'height': 650,
			'showRowNumber': true,
		};
		var table = new google.visualization.Table(document.getElementById(id));
		table.draw(table_data, options);
	}
	
	if(id == "table_product_on_sale"){
		var table_data = new google.visualization.DataTable();
		table_data.addColumn('string', 'Product Name');
        table_data.addColumn('number', 'Product Price');
		table_data.addRows(data);
		var options = {
			'width': 600,
			'height': 650,
			'showRowNumber': true,
		};
		var table = new google.visualization.Table(document.getElementById(id));
		table.draw(table_data, options);
	}
	
	if(id == "table_product_having_rebate"){
		var table_data = new google.visualization.DataTable();
		table_data.addColumn('string', 'Product Name');
        table_data.addColumn('number', 'Product Price');
		table_data.addColumn('number', 'Product Discount');
		table_data.addRows(data);
		var options = {
			'width': 600,
			'height': 650,
			'showRowNumber': true,
		};
		var table = new google.visualization.Table(document.getElementById(id));
		table.draw(table_data, options);
	}
	
	if(id == "table_product_sold"){
		var table_data = new google.visualization.DataTable();
		table_data.addColumn('string', 'Product Name');
        table_data.addColumn('number', 'Product Price');
		table_data.addColumn('number', 'Product Quentity');
		table_data.addColumn('number', 'Product Sales');
		table_data.addRows(data);
		var options = {
			'width': 600,
			'height': 650,
			'showRowNumber': true,
		};
		var table = new google.visualization.Table(document.getElementById(id));
		table.draw(table_data, options);
	}
	
	if(id == "table_daily_sales_transaction"){
		var table_data = new google.visualization.DataTable();
		table_data.addColumn('string', 'Date');
        table_data.addColumn('number', 'Sales');
		table_data.addRows(data);
		var options = {
			'width': 600,
			'height': 650,
			'showRowNumber': true,
		};
		var table = new google.visualization.Table(document.getElementById(id));
		table.draw(table_data, options);
	}
	
}

function drawChart(id , data) {
	
	if(id == "chart_product_inventory"){
		data.unshift(["Product Name", "Inventory Count"]);
		var chart_data = google.visualization.arrayToDataTable(data);
		var options = {
			'width':800,
			'height':800,
			title: "Product Inventory Chart",
			bars: 'horizontal'
		};
		var chart = new google.visualization.BarChart(document.getElementById(id));
		chart.draw(chart_data, options);
	}
	
	if(id == "chart_product_Sold"){
		data.unshift(["Product Name", "Product Sales"]);
		var chart_data = google.visualization.arrayToDataTable(data);
		var options = {
			'width':800,
			'height':800,
			title: "Product Sales Chart",
			bars: 'horizontal'
		};
		var chart = new google.visualization.BarChart(document.getElementById(id));
		chart.draw(chart_data, options);
	}
    
}




