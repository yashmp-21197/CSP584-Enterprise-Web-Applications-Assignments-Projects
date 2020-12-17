var req;
var is_IE;

var search_id;
var complete_table;
var auto_row;

function init() {
    search_id = document.getElementById("search_id");
    complete_table = document.getElementById("complete-table");
    auto_row = document.getElementById("auto-row");
}

function doCompletion() {
    var url = "autocomplete?action=complete&search_id=" + escape(search_id.value);
    req = initRequest();
    req.open("GET", url, true);
    req.onreadystatechange = callback;
    req.send(null);
}

function initRequest() {
    if (window.XMLHttpRequest) {
        if (navigator.userAgent.indexOf('MSIE') != -1) {
            is_IE = true;
        }
        return new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        is_IE = true;
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
}

function callback() {
    clearTable();
    if (req.readyState == 4) {
        if (req.status == 200) {
            parseMessages(req.responseXML);
        }
    }
}

function clearTable() {
    if (complete_table.getElementsByTagName("tr").length > 0) {
        complete_table.style.display = 'none';
        for (loop = complete_table.childNodes.length -1; loop >= 0 ; loop--) {
            complete_table.removeChild(complete_table.childNodes[loop]);
        }
    }
}

function parseMessages(responseXML) {
    
    if (responseXML == null) {
        return false;
    } else {

        var products = responseXML.getElementsByTagName("products")[0];

        if (products.childNodes.length > 0) {
            complete_table.setAttribute("bordercolor", "white");
            complete_table.setAttribute("border", "1");
    
            for (loop = 0; loop < products.childNodes.length; loop++) {
                var product = products.childNodes[loop];
                var product_name = product.getElementsByTagName("product_name")[0];
                var product_id = product.getElementsByTagName("product_id")[0];
                appendProduct(product_name.childNodes[0].nodeValue, product_id.childNodes[0].nodeValue);
            }
        }
    }
}

function appendProduct(product_name,product_id) {

    var row;
    var cell;
    var link_element;
    
    if (is_IE) {
        complete_table.style.display = 'block';
        row = complete_table.insertRow(complete_table.rows.length);
        cell = row.insertCell(0);
    } else {
        complete_table.style.display = 'table';
        row = document.createElement("tr");
        cell = document.createElement("td");
        row.appendChild(cell);
        complete_table.appendChild(row);
    }

    cell.className = "popupCell";

    link_element = document.createElement("a");
    link_element.className = "popupItem";
    link_element.setAttribute("href", "autocomplete?action=lookup&search_id=" + product_id);
    link_element.appendChild(document.createTextNode(product_name));
    cell.appendChild(link_element);
}