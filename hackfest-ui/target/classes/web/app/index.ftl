
<!DOCTYPE html>
<#-- @ftlvariable name="" type="com.flipkart.ekl.hackfest.views.DashboardView" -->
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Gameo</title>

    <!-- Bootstrap core CSS -->
    <!--<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">-->
    <link href="web/app/assets/css/bootstrap.css" rel="stylesheet">

    <!--PAGE LEVEL STYLES-->
    <link href="web/app/assets/css/pricing.css" rel="stylesheet" />
    <!-- Custom styles for this template -->
    <link href="web/app/assets/css/dashboard.css" rel="stylesheet">
    <!-- FONTAWESOME STYLES-->
    <link href="web/app/assets/css/font-awesome.css" rel="stylesheet"/>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.0.1/jquery.rateyo.min.css">


    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"><div><img src="web/app/assets/img/ekart.jpeg  "  style="max-width: 500px; max-height: 30px"/></div></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#" onclick="signOut();">Sign out</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="main">
            <h4 class="page-header">Hello <span class='emp_name'></span>, Your overall score is ${employee.score} and percentile is ${employee.percentile}</h4>


            <div class="row">
                <div class="col-md-8">
                    <div class="row">
                        <!--Productivity-->
                        <div class="col-md-6">
                            <div class="panel-danger simple-table">
                                <div class="panel-heading">
                                    <h4>Productivity</h4>
                                </div>
                                <div class="alert alert-danger">
                                    <canvas id="myChart" width="320" height="250"></canvas>
                                </div>

                            </div>
                        </div>
                        <!--Stretch assignment-->
                        <div class="col-md-6">
                            <div class="panel-info simple-table">
                                <div class="panel-heading">
                                    <h4>Stretch Assignment</h4>
                                </div>
                                <div class=" alert alert-info">
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <b>Assignments to choose from</b>
                                        </div>
                                        <div class="panel-body">
                                            <div class="table-responsive">
                                                <table class="table table-striped table-bordered table-hover">
                                                    <thead>
                                                    <tr>
                                                        <th>#</th>
                                                        <th>Assignment</th>
                                                        <th>Skill set</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <#list stretchAssignmentList as strechAssignment>
                                                        <tr>
                                                            <td>${strechAssignment?index + 1}</td>
                                                            <td>${strechAssignment.assignmentName}</td>
                                                            <td>${strechAssignment.skillSet}</td>
                                                        </tr>
                                                    </#list>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <a href="http://ekl-lm-app-001-stage.ch.flipkart.com/index.php/unanswered/tagged/stretchassignment/" class="btn btn-success ">View More...</a>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <!--Tech forum-->
                        <div class="col-md-6">
                            <div class="panel-warning simple-table">
                                <div class="panel-heading">
                                    <h4>Tech Forum</h4>
                                </div>
                                <div class="alert alert-warning">
                                    <h4>Top 3 questions</h4>
                                    <ul>
                                        <#list questions as question>
                                            <li><a href="http://ekl-lm-app-001-stage.ch.flipkart.com/index.php/unanswered/">${question}</a></li>
                                        </#list>
                                    </ul>
                                    <hr/>
                                    <a href="http://ekl-lm-app-001-stage.ch.flipkart.com/index.php/unanswered/" class="btn btn-warning ">View more...</a>
                                </div>

                            </div>
                        </div>
                        <!--Skill Management-->
                        <div class="col-md-6">
                            <div class="panel-success simple-table">
                                <div class="panel-heading">
                                    <h4>Skill Management</h4>
                                </div>
                                <div class="alert alert-success">

                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <b>Top 3 Skills</b>
                                        </div>
                                        <div class="panel-body">
                                            <div class="table-responsive">
                                                <table class="table table-striped table-bordered table-hover">
                                                    <thead>
                                                    <tr>
                                                        <th>#</th>
                                                        <th>Skill</th>
                                                        <th>Level</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <#list employee.skills as skill>
                                                        <tr>
                                                            <td>${skill?index + 1}</td>
                                                            <td>${skill.name}</td>
                                                            <td>${skill.level}</td>
                                                        </tr>
                                                    </#list>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <a href="http://ekl-lm-app-001-stage.ch.flipkart.com/moodle/" class="btn btn-success ">View More...</a>
                                        </div>
                                    </div>
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <b>Recommended Skills</b>
                                        </div>
                                        <div class="panel-body">
                                            <div class="table-responsive">
                                                <table class="table table-striped table-bordered table-hover">
                                                    <thead>
                                                    <tr>
                                                        <th>#</th>
                                                        <th>Skill</th>
                                                        <th>Level</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <#list employee.recommendedSkills as skill>
                                                        <tr>
                                                            <td>${skill?index + 1}</td>
                                                            <td>${skill.name}</td>
                                                            <td>${skill.level}</td>
                                                        </tr>
                                                    </#list>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <a href="http://ekl-lm-app-001-stage.ch.flipkart.com/moodle/" class="btn btn-success ">View More...</a>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">

                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <i class="fa fa-star fa-fw glyphicon-star"></i>Leadership Board
                        </div>

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                Top leaders this week
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-hover">
                                        <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Name</th>

                                        </tr>
                                        </thead>
                                        <tbody>
                                        <#list leaders as leader>
                                            <tr>
                                                <td>${leader?index + 1}</td>
                                                <td>${leader.name}</td>

                                            </tr>
                                        </#list>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0-beta1/jquery.min.js"><\/script>')</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha/js/bootstrap.min.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/Chart.js/1.0.2/Chart.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.0.1/jquery.rateyo.min.js"></script>
<script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.js"  type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.1/jquery.js"></script>

<script>
    function onLoad() {
        gapi.load('auth2', function() {
            console.log("Gapi loading..");
            gapi.auth2.init({
            client_id: '1039857374119-7cvdetqsa9mfb7lo4ken8va0umkeupls.apps.googleusercontent.com'
        });
            console.log("Gapi loaded..");
        })}
//    window.onLoadCallback = function(){
//        gapi.auth2.init({
//            client_id: 'filler_text_for_client_id.apps.googleusercontent.com'
//        });
//    }
</script>
<script>
    function signOut() {
        var auth2 = gapi.auth2.getAuthInstance();
        auth2.signOut().then(function () {
            console.log('User signed out.');
            window.location.replace("http://localhost:8080/web/app/login.html");
        });
    }

</script>
<script>
    $(document).ready(function () {

//        gapi.load('auth2', function() {
//            console.log("Gapi loading..");
//            gapi.auth2.init({
//                client_id: '1039857374119-7cvdetqsa9mfb7lo4ken8va0umkeupls.apps.googleusercontent.com'
//            });
//            console.log("Gapi loaded..")});
        function getCookie(cname) {
            var name = cname + "=";
            var ca = document.cookie.split(';');
            for(var i=0; i<ca.length; i++) {
                var c = ca[i];
                while (c.charAt(0)==' ') c = c.substring(1);
                if (c.indexOf(name) == 0) return c.substring(name.length,c.length);
            }
            return "";
        }

        console.log(getCookie('name'));
        $('.emp_name').html(getCookie('name'));
        var data = {
            labels: ["January", "February", "March", "April", "May", "June", "July"],
            datasets: [
                {
                    label: "My First dataset",
                    fillColor: "rgba(220,220,220,0.2)",
                    strokeColor: "rgba(220,220,220,1)",
                    pointColor: "rgba(220,220,220,1)",
                    pointStrokeColor: "#fff",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "rgba(220,220,220,1)",
                    data: [65, 59, 80, 81, 56, 55, 40]
                },
                {
                    label: "My Second dataset",
                    fillColor: "rgba(151,187,205,0.2)",
                    strokeColor: "rgba(151,187,205,1)",
                    pointColor: "rgba(151,187,205,1)",
                    pointStrokeColor: "#fff",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "rgba(151,187,205,1)",
                    data: [28, 48, 40, 19, 86, 27, 90]
                }
            ]
        };
        var ctx = document.getElementById("myChart").getContext("2d");
        var myLineChart = new Chart(ctx).Line(data);
        $.each($('.rateYo'), function() {
            $(this).rateYo({
                rating: $(this).children().val(),
                readOnly: true,
                starWidth: "20px"
            });
        });

    });

</script>
</body>
</html>
