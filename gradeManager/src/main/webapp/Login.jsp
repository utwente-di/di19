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
        <img src="help.png"/>
    </button>
</div>

<div style="text-align: center; ">
    <div style="box-sizing: border-box; max-width: 480px; border: 5px solid #6249BB; border-radius: 10px; margin: 200px auto auto; padding: 20px; background-color: #E2C800;">
        <form name="login" action="login" method="post" accept-charset="utf-8">
            <div class="row">
                <div class="large-12 columns">
		    <label for="username"><h5>Student of medewerkernummer</h5></label>
                    <input id="username" type="text" name="username" size="15" placeholder="s0000000" required>
		    <label for="password"><h5>Wachtwoord</h5></label>
		    <input id="password" type="password" name="password" placeholder="Wachtwoord" required>
                    <input type="submit" class="button expand" value="Login">
		 </div>	
	     </div>
        </form>
    </div>
</div>

<br><br><br><br>
<img src="images/UT_Logo.png" width="40%" height="40%"/>

<div class="NaamOnderaan">
    Gemaakt door groep 19
</div>

<script src="js/vendor/jquery.js"></script>
<script src="js/foundation.min.js"></script>
<script>
    $(document).foundation();
</script>
</body>
</html>