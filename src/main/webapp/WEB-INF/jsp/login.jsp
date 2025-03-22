<%--
  Created by IntelliJ IDEA.
  User: miarantsoa
  Date: 22/03/2025
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login NewApp</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" />
    <style>
        .divider:after,
        .divider:before {
            content: "";
            flex: 1;
            height: 1px;
            background: #eee;
        }
        .h-custom {
            height: calc(100% - 73px);
        }
        @media (max-width: 450px) {
            .h-custom {
                height: 100%;
            }
        }
    </style>
</head>
<body>

<section class="vh-100">
    <div class="container-fluid h-custom">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-md-9 col-lg-6 col-xl-5">
                <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp"
                     class="img-fluid" alt="Sample image">
            </div>
            <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
            <form method="POST" action="${pageContext.request.contextPath}/login">
                <div>
                    <h1 class="text-center">DayByDay NewApp</h1>
                </div>
                <div class="divider d-flex align-items-center my-4"></div>

                    <!-- Email input -->
                    <div data-mdb-input-init class="form-outline mb-4">
                        <input type="email" id="form3Example3" class="form-control form-control-lg"
                               placeholder="Entrer votre email" name="email"/>
                        <label class="form-label" for="form3Example3">Email:</label>
                    </div>

                    <!-- Password input -->
                    <div data-mdb-input-init class="form-outline mb-3">
                        <input type="password" id="form3Example4" class="form-control form-control-lg"
                               placeholder="Entrer votre mot de passe" name="mdp"/>
                        <label class="form-label" for="form3Example4">Mot de passe:</label>
                    </div>

                    <div class="text-center text-lg-start mt-4 pt-2">
                        <button  type="button" data-mdb-button-init data-mdb-ripple-init class="btn btn-lg"
                                 style="padding-left: 2.5rem; padding-right: 2.5rem; background-color: #1a2035; color: #FFF">Login</button>
                    </div>

                </form>
            </div>
        </div>
    </div>
</section>

</body>
</html>
