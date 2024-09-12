<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Table User</title>

                <!-- Latest compiled and minified CSS -->
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

                <!-- Latest compiled JavaScript -->
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

                <!-- Dung Jqery de preview anh upload -->
                <script>
                    $(document).ready(() => {
                        const avatarFile = $("#avatarFile");
                        avatarFile.change(function (e) {
                            const imgURl = URL.createObjectURL(e.target.files[0]);
                            $("#avatarPreview").attr("src", imgURl);
                            $("#avatarPreview").css({ "display": "block" });
                        })
                    })
                </script>
                <link href="/css/styles.css" rel="stylesheet" />
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
            </head>

            <body class="sb-nav-fixed">
                <jsp:include page="../layout/header.jsp" />
                <div id="layoutSidenav">
                    <jsp:include page="../layout/sidebar.jsp" />
                    <div id="layoutSidenav_content">
                        <main>
                            <div class="container-fluid px-4">
                                <h1 class="mt-4">Manage User</h1>
                                <ol class="breadcrumb mb-4">
                                    <a href="/admin">Dashboard</a>
                                    <span class="mx-2">/</span>
                                    <li class="breadcrumb-item active">User</li>
                                </ol>

                                <div class="mt-5">
                                    <div class="row">
                                        <div class="col-md-6 col-12 mx-auto">
                                            <h3 class="mb-3">Create a new User</h3>
                                            <hr>
                                            <form:form action="/admin/user/create" method="post"
                                                modelAttribute="newUser" enctype="multipart/form-data" class="row">
                                                <div class="mb-3 col-12 col-md-6">
                                                    <label for="exampleInputEmail1" class="form-label">Email
                                                        address</label>
                                                    <form:input type="email" class="form-control" path="email" />
                                                </div>
                                                <div class="mb-3 col-12 col-md-6">
                                                    <label for="exampleInputPassword1"
                                                        class="form-label">Password</label>
                                                    <form:input type="password" class="form-control" path="password" />
                                                </div>
                                                <div class="mb-3 col-12 col-md-6">
                                                    <label for="exampleInputEmail1" class="form-label">Phone
                                                        Number</label>
                                                    <form:input type="text" class="form-control" path="phone" />
                                                </div>
                                                <div class="mb-3 col-12 col-md-6">
                                                    <label for="exampleInputEmail1" class="form-label">Full Name</label>
                                                    <form:input type="text" class="form-control" path="fullName" />
                                                </div>
                                                <div class="mb-3 col-12">
                                                    <label for="exampleInputEmail1" class="form-label">Address</label>
                                                    <form:input type="text" class="form-control" path="address" />
                                                </div>
                                                <div class="mb-3 col-12 col-md-6">
                                                    <label class="form-label">Roles</label>
                                                    <form:select class="form-select" aria-label="Default select example"
                                                        path="role.name">
                                                        <form:option value="ADMIN">Admin</form:option>
                                                        <form:option value="USER">User</form:option>
                                                    </form:select>
                                                </div>
                                                <div class="mb-3 col-12 col-md-6">
                                                    <label class="form-label" for="avatarFile">Avatar</label>
                                                    <input type="file" class="form-control" id="avatarFile"
                                                        name="aronFile" accept=".png, .jpg, .jpeg">
                                                </div>
                                                <div class="col-12 mb-3">
                                                    <img style="max-height: 250px; display: none;" alt="avatarPreview"
                                                        id="avatarPreview" />

                                                </div>
                                                <div class="col-12 mb-3">
                                                    <button type="submit" class="btn btn-primary">Create</button>
                                                </div>
                                            </form:form>

                                        </div>

                                    </div>
                                </div>
                            </div>
                        </main>
                        <jsp:include page="../layout/footer.jsp" />
                    </div>
                </div>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                    crossorigin="anonymous"></script>
                <script src="/js/scripts.js"></script>

            </body>

            </html>