<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Create Product</title>
                <!-- Latest compiled and minified CSS -->
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

                <!-- Latest compiled JavaScript -->
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

                <link href="/css/styles.css" rel="stylesheet" />
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>

                <!-- Dung Jqery de preview anh upload -->
                <script>
                    $(document).ready(() => {

                        const avatarFile = $("#productFile");
                        const orgImg = "${productUpdate.image}";
                        if (orgImg) {
                            const urlImg = "/img/imgProduct/" + orgImg
                            $("#avatarPreview").attr("src", urlImg);
                            $("#avatarPreview").css({ "display": "block" });
                        }

                        avatarFile.change(function (e) {
                            const imgURl = URL.createObjectURL(e.target.files[0]);
                            $("#avatarPreview").attr("src", imgURl);
                            $("#avatarPreview").css({ "display": "block" });
                        })
                    });
                </script>
            </head>

            <body>

                <body class="sb-nav-fixed">
                    <jsp:include page="../layout/header.jsp" />
                    <div id="layoutSidenav">
                        <jsp:include page="../layout/sidebar.jsp" />
                        <div id="layoutSidenav_content">
                            <main>
                                <div class="container-fluid px-4">
                                    <h1 class="mt-4">Manage Product</h1>
                                    <ol class="breadcrumb mb-4">
                                        <a href="/admin">Dashboard</a>
                                        <span class="mx-2">/</span>
                                        <li class="breadcrumb-item active">Product</li>
                                    </ol>

                                    <div class="mt-5">
                                        <div class="row">
                                            <div class="col-md-6 col-12 mx-auto">
                                                <h3 class="mb-3">Update Product</h3>
                                                <hr>
                                                <form:form class="row g-3" action="/admin/product/update" method="post"
                                                    modelAttribute="productUpdate" enctype="multipart/form-data">
                                                    <div class="col-md-6">
                                                        <label for="inputEmail4" class="form-label">Name</label>
                                                        <c:set var="nameError">
                                                            <form:errors path="name" cssClass="invalid-feedback" />
                                                        </c:set>
                                                        <form:input type="text"
                                                            class="form-control ${not empty nameError ? 'is-invalid': '' }"
                                                            path="name" />
                                                        ${nameError}
                                                    </div>
                                                    <div class="col-md-6">
                                                        <label for="inputPassword4" class="form-label">Price</label>
                                                        <c:set var="priceError">
                                                            <form:errors path="price" cssClass="invalid-feedback" />
                                                        </c:set>
                                                        <form:input type="number"
                                                            class="form-control ${not empty priceError ? 'is-invalid': '' }"
                                                            path="price" />
                                                        ${priceError}
                                                    </div>
                                                    <div class="col-12">
                                                        <label for="inputAddress" class="form-label">Detail
                                                            description</label>
                                                        <c:set var="detailDesError">
                                                            <form:errors path="detailDesc"
                                                                cssClass="invalid-feedback" />
                                                        </c:set>
                                                        <form:textarea type="text"
                                                            class="form-control ${not empty detailDesError ? 'is-invalid': '' }"
                                                            path="detailDesc"></form:textarea>
                                                        ${detailDesError}
                                                    </div>
                                                    <div class="col-md-6">
                                                        <label for="inputAddress2" class="form-label">Short
                                                            Description</label>
                                                        <c:set var="shortDesError">

                                                            <form:errors path="shortDesc" cssClass="invalid-feedback" />
                                                        </c:set>

                                                        <form:input type="text"
                                                            class="form-control ${not empty shortDesError ? 'is-invalid': '' }"
                                                            path="shortDesc" />
                                                        ${shortDesError}
                                                    </div>
                                                    <div class="col-md-6">
                                                        <label for="inputAddress2" class="form-label">Quantity</label>
                                                        <c:set var="quantityError">
                                                            <form:errors path="quantity" cssClass="invalid-feedback" />
                                                        </c:set>

                                                        <form:input type="number"
                                                            class="form-control ${not empty quantityError ? 'is-invalid': '' }"
                                                            path="quantity" />
                                                        ${quantityError}
                                                    </div>
                                                    <div class="col-md-6">
                                                        <label for="inputState" class="form-label">Factory</label>
                                                        <form:select path="factory" class="form-select">
                                                            <form:option value="Asus">Asus</form:option>
                                                            <form:option value="Dell">Dell</form:option>
                                                            <form:option value="Mac">Mac</form:option>
                                                            <form:option value="ThinkPad">ThinkPad</form:option>
                                                        </form:select>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <label for="inputState" class="form-label">Target</label>
                                                        <form:select path="target" class="form-select">
                                                            <form:option value="Student">Student</form:option>
                                                            <form:option value="Business">Business</form:option>
                                                        </form:select>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <label for="formFile" class="form-label">Image</label>
                                                        <input class="form-control" type="file" id="productFile"
                                                            name="imgProduct" accept=".png, .jpg, .jpeg" path="image">
                                                    </div>
                                                    <div class="col-12 mb-3">
                                                        <img style="max-height: 250px; display: none;"
                                                            alt="avatarPreview" id="avatarPreview" />

                                                    </div>
                                                    <div class="col-12">
                                                        <button type="submit" class="btn btn-primary">Update</button>
                                                    </div>
                                                </form:form>
                                            </div>


                                        </div>
                                    </div>
                                </div>

                                <jsp:include page="../layout/footer.jsp" />
                        </div>
                        </main>

                    </div>
                    </div>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                        crossorigin="anonymous"></script>
                    <script src="/js/scripts.js"></script>

                </body>

            </body>

            </html>