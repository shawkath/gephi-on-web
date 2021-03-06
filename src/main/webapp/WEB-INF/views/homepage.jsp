<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Welcome to Gephi On Web Demo</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/images/icons/favicon.ico">
    <link rel="apple-touch-icon" href="<%=request.getContextPath()%>/images/icons/favicon.png">
    <link rel="apple-touch-icon" sizes="72x72" href="<%=request.getContextPath()%>/images/icons/favicon-72x72.png">
    <link rel="apple-touch-icon" sizes="114x114" href="<%=request.getContextPath()%>/images/icons/favicon-114x114.png">
    <!--Loading bootstrap css-->
    <link type="text/css" rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,400,300,700">
    <link type="text/css" rel="stylesheet" href="http://fonts.googleapis.com/css?family=Oswald:400,700,300">
    <link type="text/css" rel="stylesheet" href="styles/jquery-ui-1.10.4.custom.min.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/styles/font-awesome.min.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/styles/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/styles/animate.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/styles/all.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/styles/main.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/styles/style-responsive.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/styles/zabuto_calendar.min.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/styles/pace.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/styles/jquery.news-ticker.css">
    
    <!--  GEPHI RESOURCES-->
         <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/gephi/styles/gexfjs.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/gephi/styles/jquery-ui-1.10.3.custom.min.css" />
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
        <script type="text/javascript">
        // Fallback in case JQuery CDN isn't available
            if (typeof jQuery == 'undefined') {
                document.write(unescape("%3Cscript type='text/javascript' src='js/jquery-2.0.2.min.js'%3E%3C/script%3E"));
            }
        </script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/gephi/js/jquery.mousewheel.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/gephi/js/jquery-ui-1.10.3.custom.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/gephi/js/gexfjs.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/gephi/config.js"></script>
    <!--  GEPHI RESOURCES -->
    
</head>
<body>
<div>
        <!--BEGIN THEME SETTING-->
      <!--   <div id="theme-setting">
            <a href="#" data-toggle="dropdown" data-step="1" data-intro="&lt;b&gt;Many styles&lt;/b&gt; and &lt;b&gt;colors&lt;/b&gt; be created for you. Let choose one and enjoy it!"
                data-position="left" class="btn-theme-setting"><i class="fa fa-cog"></i></a>
            <div class="content-theme-setting">
                <select id="list-style" class="form-control">
                    <option value="style1">Flat Squared style</option>
                    <option value="style2">Flat Rounded style</option>
                    <option value="style3" selected="selected">Flat Border style</option>
                </select>
            </div>
        </div> -->
        <!--END THEME SETTING-->
        <!--BEGIN BACK TO TOP-->
        <a id="totop" href="#"><i class="fa fa-angle-up"></i></a>
        <!--END BACK TO TOP-->
        <!--BEGIN TOPBAR-->
        <div id="header-topbar-option-demo" class="page-header-topbar">
            <nav id="topbar" role="navigation" style="margin-bottom: 0;" data-step="3" class="navbar navbar-default navbar-static-top">
            <div class="navbar-header">
                <button type="button" data-toggle="collapse" data-target=".sidebar-collapse" class="navbar-toggle"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
                <a id="logo" href="index.html" class="navbar-brand"><span class="fa fa-rocket"></span><span class="logo-text">Sample App</span><span style="display: none" class="logo-text-icon">�</span></a></div>
            <div class="topbar-main"><a id="menu-toggle" href="#" class="hidden-xs"><i class="fa fa-bars"></i></a>
                
                <form id="topbar-search" action="" method="" class="hidden-sm hidden-xs">
                    <div class="input-icon right text-white"><a href="#"><i class="fa fa-search"></i></a><input type="text" placeholder="Search here..." class="form-control text-white"/></div>
                </form>
                <div class="news-update-box hidden-xs"><span class="text-uppercase mrm pull-left text-white">News:</span>
             
                </div>
                <ul class="nav navbar navbar-top-links navbar-right mbn">
                    <li class="dropdown"><a data-hover="dropdown" href="#" class="dropdown-toggle"><i class="fa fa-bell fa-fw"></i><span class="badge badge-green">3</span></a>
                        
                    </li>
                    <li class="dropdown"><a data-hover="dropdown" href="#" class="dropdown-toggle"><i class="fa fa-envelope fa-fw"></i><span class="badge badge-orange">7</span></a>
                        
                    </li>
                    <li class="dropdown"><a data-hover="dropdown" href="#" class="dropdown-toggle"><i class="fa fa-tasks fa-fw"></i><span class="badge badge-yellow">8</span></a>
                        
                    </li>
                    <li class="dropdown topbar-user"><a data-hover="dropdown" href="#" class="dropdown-toggle"><img src="<%=request.getContextPath()%>/images/avatar/48.jpg" alt="" class="img-responsive img-circle"/>&nbsp;<span class="hidden-xs">Robert John</span>&nbsp;<span class="caret"></span></a>
                         <ul class="dropdown-menu dropdown-user pull-right">
                            <li><a href="#"><i class="fa fa-user"></i>My Profile</a></li>
                            <li><a href="#"><i class="fa fa-calendar"></i>My Calendar</a></li>
                            <li><a href="#"><i class="fa fa-envelope"></i>My Inbox<span class="badge badge-danger">3</span></a></li>
                            <li><a href="#"><i class="fa fa-tasks"></i>My Tasks<span class="badge badge-success">7</span></a></li>
                            <li class="divider"></li>
                            <li><a href="#"><i class="fa fa-lock"></i>Lock Screen</a></li>
                            <li><a href="Login.html"><i class="fa fa-key"></i>Log Out</a></li>
                        </ul>
                    </li>
                    <li id="topbar-chat" class="hidden-xs"><a href="javascript:void(0)" data-step="4" data-intro="&lt;b&gt;Form chat&lt;/b&gt; keep you connecting with other coworker" data-position="left" class="btn-chat"><i class="fa fa-comments"></i><span class="badge badge-info">3</span></a></li>
                </ul>
            </div>
        </nav>
            <!--BEGIN MODAL CONFIG PORTLET-->
            <div id="modal-config" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" data-dismiss="modal" aria-hidden="true" class="close">
                                &times;</button>
                            <h4 class="modal-title">
                                Modal title</h4>
                        </div>
                        <div class="modal-body">
                            <p>
                                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed eleifend et nisl eget
                                porta. Curabitur elementum sem molestie nisl varius, eget tempus odio molestie.
                                Nunc vehicula sem arcu, eu pulvinar neque cursus ac. Aliquam ultricies lobortis
                                magna et aliquam. Vestibulum egestas eu urna sed ultricies. Nullam pulvinar dolor
                                vitae quam dictum condimentum. Integer a sodales elit, eu pulvinar leo. Nunc nec
                                aliquam nisi, a mollis neque. Ut vel felis quis tellus hendrerit placerat. Vivamus
                                vel nisl non magna feugiat dignissim sed ut nibh. Nulla elementum, est a pretium
                                hendrerit, arcu risus luctus augue, mattis aliquet orci ligula eget massa. Sed ut
                                ultricies felis.</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" data-dismiss="modal" class="btn btn-default">
                                Close</button> 
                            <button type="button" class="btn btn-primary">
                                Save changes</button>
                        </div>
                    </div>
                </div>
            </div>
            <!--END MODAL CONFIG PORTLET-->
        </div>
        <!--END TOPBAR-->
        <div id="wrapper">
            <!--BEGIN SIDEBAR MENU-->
            <nav id="sidebar" role="navigation" data-step="2" data-intro="Template has &lt;b&gt;many navigation styles&lt;/b&gt;"
                data-position="right" class="navbar-default navbar-static-side">
            <div class="sidebar-collapse menu-scroll">
                <ul id="side-menu" class="nav">
                    
                     <div class="clearfix"></div>
               
                    <li class="active"><a href="Pages.html"><i class="fa fa-file-o fa-fw">
                        <div class="icon-bg bg-yellow"></div>
                    </i><span class="menu-title">GEPHI ON IFRAME</span></a>
                       
                    </li>
                    
                        <li class="active"><a href="Pages.html"><i class="fa fa-file-o fa-fw">
                        <div class="icon-bg bg-yellow"></div>
                    </i><span class="menu-title">APO LIST</span></a>
                       
                    </li>
                    
                </ul>
            </div>
        </nav>
          
          
            <div id="page-wrapper">
                <!--BEGIN TITLE & BREADCRUMB PAGE-->
                <div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
                    <div class="page-header pull-left">
                        <div class="page-title">
                            GEPHI DEMO</div>
                    </div>
                   <%--  <ol class="breadcrumb page-breadcrumb pull-right">
                        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=request.getContextPath()%>/">Home</a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
                        <li class="hidden"><a href="#">My Meetings</a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
                        <li class="active">My Meetings</li>
                    </ol> --%>
                    <div class="clearfix">
                    </div>
                </div>
                <!--END TITLE & BREADCRUMB PAGE-->
                <!--BEGIN CONTENT-->
                <div class="page-content">
                    <div id="tab-general">
                        <div class="row mbl">
                            <div class="col-lg-12">
                                
                                            <div class="col-md-12">
                                                <div id="area-chart-spline" style="width: 100%; height: 300px; display: none;">
                                                </div>
                                                
                                           <div class="container">
	<div class="row">
		<div class="span5">
          <!--   <table class="table table-striped table-condensed  jdata">
                  <thead>
                  <tr>
                <td>EMBED HERE</td>                           
                  </tr>
              </thead>   
              <tbody>
                <tr >
                    <td><span >Active</span> 
                                     
                </tr>
                                     
              </tbody>
            </table>	 -->
            
            
            
            <!-- GEPHI DEMO STARTS HERE -->
            
            <!--    <div id="zonecentre" class="gradient">
            <canvas id="carte" width="0" height="0"></canvas>
            <ul id="ctlzoom">
                <li>
                    <a href="#" id="zoomPlusButton" title="S'approcher"> </a>
                </li>
                <li id="zoomSliderzone">
                    <div id="zoomSlider"></div>
                </li>
                <li>
                    <a href="#" id="zoomMinusButton" title="S'�loigner"> </a>
                </li>
                <li>
                    <a href="#" id="lensButton"> </a>
                </li>
                <li>
                    <a href="#" id="edgesButton"> </a>
                </li>
            </ul>
        </div>
        <div id="overviewzone" class="gradient">
            <canvas id="overview" width="0" height="0"></canvas>
        </div>
        <div id="leftcolumn">
            <div id="unfold">
                <a href="#" id="aUnfold" class="rightarrow"> </a>
            </div>
            <div id="leftcontent"></div>
        </div>
        <div id="titlebar">
            <div id="maintitle">
                <h1><a href="http://gephi.org/" target="_blank" title="Made with Gephi">Gephi: JavaScript GEXF Viewer</a></h1>
            </div>
            <form id="recherche">
                <input id="searchinput" class="grey" autocomplete="off" />
                <input id="searchsubmit" type="submit" />
            </form>
        </div>
        <ul id="autocomplete">
        </ul> -->
        
           <!--  GEPHI DEMO STARTS HERE-->
        <iframe src="<%=request.getContextPath()%>/resources/gephi/index.html"  allowfullscreen scrolling="off" width="1000px" height="800px">
</iframe>

            <!--  GEPHI DEMO ENDS HERE-->
            </div>
	</div>
</div>
                                            </div>
                                
                            </div>
                            
                            </div>
                            
                        </div>
                    </div>
                </div>
                <!--END CONTENT-->
                <!--BEGIN FOOTER-->
                <div id="footer">
                    <div class="copyright">
                        <a href="http://shawkath.com target="_new">2014 � Meeting Notes | Developed & Mainted by Shawkath Khan</a></div>
                </div>
                <!--END FOOTER-->
            </div>
            <!--END PAGE WRAPPER-->
        </div>
    </div>
    <script src="<%=request.getContextPath()%>/script/jquery-1.10.2.min.js"></script>
    <script src="<%=request.getContextPath()%>/script/jquery-migrate-1.2.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/script/jquery-ui.js"></script>
    <script src="<%=request.getContextPath()%>/script/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/script/bootstrap-hover-dropdown.js"></script>
    <script src="<%=request.getContextPath()%>/script/html5shiv.js"></script>
    <script src="<%=request.getContextPath()%>/script/respond.min.js"></script>
    <script src="<%=request.getContextPath()%>/script/jquery.metisMenu.js"></script>
    <script src="<%=request.getContextPath()%>/script/jquery.slimscroll.js"></script>
    <script src="<%=request.getContextPath()%>/script/jquery.cookie.js"></script>
    <script src="<%=request.getContextPath()%>/script/icheck.min.js"></script>
    <script src="<%=request.getContextPath()%>/script/custom.min.js"></script>
    <script src="<%=request.getContextPath()%>/script/jquery.news-ticker.js"></script>
    <script src="<%=request.getContextPath()%>/script/jquery.menu.js"></script>
    <script src="<%=request.getContextPath()%>/script/pace.min.js"></script>
    <script src="<%=request.getContextPath()%>/script/holder.js"></script>
    <script src="<%=request.getContextPath()%>/script/responsive-tabs.js"></script>
    <script src="<%=request.getContextPath()%>/script/jquery.flot.js"></script>
    <script src="<%=request.getContextPath()%>/script/jquery.flot.categories.js"></script>
    <script src="<%=request.getContextPath()%>/script/jquery.flot.pie.js"></script>
    <script src="<%=request.getContextPath()%>/script/jquery.flot.tooltip.js"></script>
    <script src="<%=request.getContextPath()%>/script/jquery.flot.resize.js"></script>
    <script src="<%=request.getContextPath()%>/script/jquery.flot.fillbetween.js"></script>
    <script src="<%=request.getContextPath()%>/script/jquery.flot.stack.js"></script>
    <script src="<%=request.getContextPath()%>/script/jquery.flot.spline.js"></script>
    <script src="<%=request.getContextPath()%>/script/zabuto_calendar.min.js"></script>
    <script src="<%=request.getContextPath()%>/script/index.js"></script>
    <!--LOADING SCRIPTS FOR CHARTS-->
    <script src="<%=request.getContextPath()%>/script/highcharts.js"></script>
    <script src="<%=request.getContextPath()%>/script/data.js"></script>
    <script src="<%=request.getContextPath()%>/script/drilldown.js"></script>
    <script src="<%=request.getContextPath()%>/script/exporting.js"></script>
    <script src="<%=request.getContextPath()%>/script/highcharts-more.js"></script>
    <script src="<%=request.getContextPath()%>/script/charts-highchart-pie.js"></script>
    <script src="<%=request.getContextPath()%>/script/charts-highchart-more.js"></script>
    
       <script src="<%=request.getContextPath()%>/resources/js/jira.js"></script>  
    <!--CORE JAVASCRIPT-->
    <script src="<%=request.getContextPath()%>/resources/script/main.js"></script>
   
   <script type="text/javascript">
   $(document).ready(function(){
	   getJiraData();
	});
   </script>

</body>
</html>