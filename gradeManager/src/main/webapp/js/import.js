var rawData = {};
var globalData = {};
var mappingTable = {
    'Persons': {
        'Last Name': {
            'sql': 'surname',
            'display': 'Achternaam'
        },
        'First Name': {
            'sql': 'firstname',
            'display': 'Voornaam'
        },
        'Username': {
            'sql': 'personID',
            'display': 'Inlog'
        },
        'Password': {
            'sql': 'password',
            'display': 'Wachtwoord'
        },
        'Administator': {
            'sql': 'administator',
            'display': 'Administator?' 
        },
        'defaults': {
            'First Name': 'voornaam',
            'Last Name': 'achternaam',
            'Password': 'wachtwoord',
            'Administator': 'false'
        }

    },
    'Grades': {
        'defaults': {
        }
    },
    'Modules': {
        'defaults': {
        }
    }
}

// Method that checks that the browser supports the HTML5 File API
function browserSupportFileUpload() {
    return window.File && window.FileReader && window.FileList && window.Blob;
}

function upload(evt) {
    if (!browserSupportFileUpload()) {
        alert('The File APIs are not fully supported in this browser!');
    } 
    else {
        var file = evt.target.files[0];
        var type = file.type;
        var reader = new FileReader();
        reader.readAsText(file);
        reader.onload = function(event) {
            var data = event.target.result;
            var parsedData;
            if(type == "text/csv"){
                parsedData = parseCSV(data);
            }
            else if(type == "text/xml"){
                alert('XML is helaas niet geimplementeerd. Excuses.');
            }
            else if(type == "application/vnd.ms-excel"){
                parsedData = parseXLS(data);
            }
            if (parsedData && parsedData.length > 0) {
                rawData = parsedData;
                updatePage();
            } 
            else {
                alert('No data to import!');
            }
        };
        reader.onerror = function() {
            alert('Unable to read ' + file.fileName);
        };
    }
}

function addDefaults(){
    globalData = deepCopy(rawData);
    var type = document.getElementById('tableSelector').value;
    for(var e = 0; e < globalData.length; e++){
        var keys = Object.keys(globalData[e]);
        var defaultKeys = Object.keys(mappingTable[type]['defaults']);
        for(var i = 0; i < keys.length; i++){
            for(var j = 0; j < defaultKeys.length; j++){
                if(keys[i] == defaultKeys[j]){
                    var tmp = defaultKeys[defaultKeys.length - 1];
                    defaultKeys.pop();
                    if(defaultKeys.length > j){
                        defaultKeys[j] = tmp;
                    }
                }
            }
        }
        for(var i = 0; i < defaultKeys.length; i++){
            globalData[e][defaultKeys[i]] = mappingTable[type]['defaults'][defaultKeys[i]];
        }
    }
}

function typeChange(evt){
    updatePage();
}

function deepCopy(arrayOfHashes){
    var returnVal = new Array(arrayOfHashes.length);
    for(var e = 0; e < returnVal.length; e++){
        var _tmp = {};
        Object.keys(arrayOfHashes[e]).forEach(function(key) {
             _tmp[key] = arrayOfHashes[e][key];
        }); 
        returnVal[e] = _tmp;
    }
    return returnVal;
}

function postData(){
    addDefaults();
    var type = document.getElementById('tableSelector').value;
    var postArray = new Array(globalData.length);
    var keys = Object.keys(globalData[0]);
    for(var i = 0; i < postArray.length; i++){
        postArray[i] = {};
        for(var j = 0; j < keys.length; j++){
            var key = sqlmapping(type, keys[j]);
            if(key != null){
                postArray[i][key] = globalData[i][keys[j]];
            }
        }
    }
    return postArray;
}

function updatePage(){
    addDefaults();
    var type = $('#tableSelector')[0].value;
    var title = "<h5>"+type+"</h5>";
    var table = "<table><thead><tr>";
    var keys = Object.keys(globalData[0]);
    for(var i = 0; i < keys.length; i++){
        table += "<td>" + displaymapping(type, keys[i]) + "</td>";
    }
    table += "</thead><tbody>";
    for(var i = 0; i < globalData.length; i++){
        table += "<tr>";
        for(var j = 0; j < keys.length; j++){
            table += "<td>" + globalData[i][keys[j]] + "</td>";
        }
        table += "</tr>";
    }
    table += "</tbody></table>";
    $("#showHash")[0].innerHTML = title + table;

    var importDataHTML = '';
    var importData = postData();
    for(var i = 0; i < importData.length; i++){
        var keys = Object.keys(importData[i]);
        for(var j = 0; j < keys.length; j++){
            importDataHTML += '<input id="' + i + '-' + keys[j] + '" type="hidden" value="' + importData[i][keys[j]] +'"/>';
        }
    }
    var warningMsg = 
    importDataHTML += '<input type="submit" value="Voeg deze items aan de tabel ' + type + ' toe" method="POST" onClick="return confirm(\'Weet je zeker dat je deze waardes toe wilt voegen?\');">';
    $("#importData")[0].innerHTML = importDataHTML;
}


function addListeners(){
    $('#txtFileUpload')[0].addEventListener('change', upload, false);
    $('#tableSelector')[0].addEventListener('change', typeChange, false);
}

// function parseXML(xml){

//     var query = '//cell'
//     var result = document.evaluate(query, xml, null, XPathResult.ANY_TYPE, null);
//     return null;
// }

function parseXLS(xls){
    var data;    
    var headers = [];
    data = xls.split('\n');


    var array = [data.length - 1];

    headers = parseXLSRow(data[0]);
    for(var i = 1; i < data.length; i++){
        var _obj = {};
        var _row = parseXLSRow(data[i]);
        if(_row.length >= headers.length){
            for(j = 0; j < headers.length; j++){
                _obj[headers[j]] = _row[j];
            }
            array[i-1] = _obj;
        }
    }
    
    return array;
}

function parseXLSRow(string){
    return string.split('\t');
}

function parseCSV(csv){
    var data;    
    var headers = [];
    data = csv.split('\n');


    var array = [data.length - 1];

    headers = parseCSVRow(data[0]);
    for(var i = 1; i < data.length; i++){
        var _obj = {};
        var _row = parseCSVRow(data[i]);
        if(_row.length >= headers.length){
            for(j = 0; j < headers.length; j++){
                _obj[headers[j]] = _row[j];
            }
            array[i-1] = _obj;
        }
    }
    
    return array;
}

function parseCSVRow(string){
    var result = [""];
    var inString = false;
    var lastSplit = 0;
    var indexCounter = 0;
    
    for(var i = 0; i < string.length; i++){
        if(string.charAt(i) == '"'){
            inString = !inString;
        }
        else if(string.charAt(i) == ',' && !inString){
            result.push("");
            indexCounter++;
        }
        else{
            result[indexCounter] += string.charAt(i);
        }
    }
    return result;
}

function displaymapping(type, column){
    var returnVal;
    if(mappingTable[type] && mappingTable[type][column]){
        returnVal = mappingTable[type][column]['display'];
    }
    else{
        returnVal = 'Niet gebruikte data';
    }
    return returnVal;
}

function sqlmapping(type, column){
    var returnVal;
    if(mappingTable[type] && mappingTable[type][column]){
        returnVal = mappingTable[type][column]['sql'];
    }
    else{
        returnVal = null;
    }
    return returnVal;
}
