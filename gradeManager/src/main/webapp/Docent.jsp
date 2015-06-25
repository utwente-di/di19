<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class=" js flexbox flexboxlegacy canvas canvastext webgl no-touch geolocation postmessage no-websqldatabase indexeddb hashchange history draganddrop websockets rgba hsla multiplebgs backgroundsize borderimage borderradius boxshadow textshadow opacity cssanimations csscolumns cssgradients no-cssreflections csstransforms csstransforms3d csstransitions fontface generatedcontent video audio localstorage sessionstorage webworkers applicationcache svg inlinesvg smil svgclippaths" style=""><head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
  
  
  
  
  
  
  
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TOSTi Framework</title>
    <link rel="stylesheet" href="Student_files/foundation.css">
    <script src="js/d3.js"></script>
    <script src="js/d3.min.js"></script>
    <script src="js/Teacher_module_overview.js"></script>
    <script src="js/vendor/modernizr.js"></script>
    <script src="js/vendor/jquery.js"></script>
    <script src="js/vendor/fastclick.js"></script>
    <script src="js/foundation/foundation.js"></script><meta class="foundation-data-attribute-namespace"><meta class="foundation-mq-xxlarge"><meta class="foundation-mq-xlarge-only"><meta class="foundation-mq-xlarge"><meta class="foundation-mq-large-only"><meta class="foundation-mq-large"><meta class="foundation-mq-medium-only"><meta class="foundation-mq-medium"><meta class="foundation-mq-small-only"><meta class="foundation-mq-small"><style></style>
<meta class="foundation-mq-topbar"></head>
<body>
<nav class="top-bar" data-topbar="" role="navigation" data-options="is_hover: false">
  <ul class="title-area">
    <li class="name">
      <h1><a href="#">TOSTi Tim de Klerk</a></h1>
    </li>
     <!-- Remove the class "menu-icon" to get rid of menu icon. Take out "Menu" to just have icon alone -->
    <li class="toggle-topbar menu-icon"><a href="#"><span>Menu</span></a></li>
  </ul>

  
  
<section class="top-bar-section">
    <!-- Right Nav Section -->
    <ul class="left">
    
    <li class="has-dropdown"><a href="#">2015</a><ul class="dropdown"><li class="title back js-generated"><h5><a href="javascript:void(0)">Back</a></h5></li><li class="parent-link hide-for-medium-up"><a class="parent-link js-generated" href="#">2015</a></li>
<li><a href="http://localhost:8080/gradeManager/Resulttabel?moduleid=1234567890&amp;moduleyear=2015">Data &amp; Informatie</a></li>
</ul></li>
<li class="has-dropdown"><a href="#">2014</a><ul class="dropdown"><li class="title back js-generated"><h5><a href="javascript:void(0)">Back</a></h5></li><li class="parent-link hide-for-medium-up"><a class="parent-link js-generated" href="#">2014</a></li>
<li><a href="http://localhost:8080/gradeManager/Resulttabel?moduleid=1234567890&amp;moduleyear=2014">Data &amp; Informatie</a></li>
<li><a href="http://localhost:8080/gradeManager/Resulttabel?moduleid=987654321&amp;moduleyear=2014">Getallenmanagement</a></li>
</ul></li>


  </ul></section><section class="top-bar-section">
  <ul class="right">
  <li><a href="#">My account</a></li>
  <li><a href="#">Settings</a></li>
  </ul></section></nav>




<h1 style="background-color:#EAEAEA; border-style:solid; border-width:2px; border-color:#EAEAEA">Module name</h1>
<ul style="float:left; width: 50%" class="accordion" data-accordion="">
  <li class="accordion-navigation">
    <a aria-expanded="false" href="#Assignment1"> Assignment 1</a>
    <input id="Assignment1-data" type="hidden" data='[1.5,1,5.5,1,4,5]'/>
    <div style="background-color:white" id="Assignment1" class="content">
      <table>
        <thead><tr><th>Name</th><th>ID</th><th>Date</th><th>Grade</th></tr></thead>
        <tbody><tr><td>Mark</td><td>s1380087</td><td class="grade">2015-08-08</td><td>10</td></tr></tbody>
      </table>
    </div>
  </li>
</ul>


<div class="databox">
  <h3>Cijfer verdeling</h3>
  <svg id="graphbox">
  </svg>
</div>



  <script>
    $(document).ready(prepare());
    $(document).foundation();
  </script>
</body></html>