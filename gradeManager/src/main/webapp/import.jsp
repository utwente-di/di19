<html>
	<head>
		<link rel="stylesheet" href="css/foundation.css">
		<script src="js/d3.js"></script>
		<script src="js/d3.min.js"></script>
		<script src="js/import.js"></script>
		<script src="js/vendor/modernizr.js"></script>
		<script src="js/vendor/jquery.js"></script>
		<script src="js/vendor/fastclick.js"></script>
		<script src="js/foundation/foundation.js"></script>
	</head>
	<body>
		<div id="dvImportSegments" class="fileupload ">
			<fieldset>
				<legend>Importeer uw bestand</legend>
				<input type="file" name="File Upload" id="txtFileUpload" accept=".csv, .xls" />
				<select id="tableSelector">
					<option value="Persons">Personen</option>
					<option value="Grades">Cijfers</option>
					<option value="Modules">Modules</option>
				</select> 
			</fieldset>
		</div>

		<div id="showHash">
		</div>

		<div id="importData">
			
		</div>

		<script>
			$(document).ready(addListeners());
		</script>

	</body>
</html>