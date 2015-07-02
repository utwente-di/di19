<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>TOSTi inlog</title>
    <link rel="stylesheet" href="css/foundation.css"/>
    <script src="js/vendor/modernizr.js"></script>
</head>
<body>
<div style="text-align: right; ">
    <button type="button" style="background-color:transparent; border-color:transparent;"
            onclick="alert('Hier komt hulp voor het inloggen te staan')">
        <img src="images/help.png"/>
    </button>
</div>

<div class="loginbox">
    <form name="login" action="login" method="post" accept-charset="utf-8">
		    <label for="username"><h3>Student of medewerkernummer</h3></label>
            <input id="username" type="text" name="username" size="15" placeholder="s0000000" required autofocus>
		    <label for="password"><h3>Wachtwoord</h3></label>
		    <input id="password" type="password" name="password" placeholder="Wachtwoord" required>
        	<div class="row">
      			<h3>Inloggen als</h3>
       				<select name="loginals">
         				<option value="Student">Student</option>
          				<option value="Docent">Docent</option>
          				<option value="Manager">Manager</option>
        			</select>
      		</div>
            <input type="submit" class="button expand" value="Login">
        </form>
    </div>

<div class="links onder">
<img src="images/UT_Logo.png" width="40%" height="40%"/>
</div>

<div class="rechts onder">
    Gemaakt door groep 19
</div>

<script src="js/vendor/jquery.js"></script>
<script src="js/foundation.min.js"></script>
<script>
    $(document).foundation();
</script>
</body>
</html>