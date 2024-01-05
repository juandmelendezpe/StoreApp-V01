<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE xsl:stylesheet [
<!ENTITY nbsp "&#160;">
<!ENTITY copy "&#169;">
<!ENTITY reg "&#174;">
<!ENTITY trade "&#8482;">
<!ENTITY mdash "&#8212;">
<!ENTITY ldquo "&#8220;">
<!ENTITY rdquo "&#8221;">
<!ENTITY pound "&#163;">
<!ENTITY yen "&#165;">
<!ENTITY euro "&#8364;">
]>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" encoding="utf-8"
		doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" />
	<xsl:template match="/">
		
		
		<html lang="es">
			<head>
				<meta charset="UTF-8" />
				<meta http-equiv="X-UA-Compatible" content="IE=edge" />
				<meta name="viewport" content="width=device-width, initial-scale=1.0" />
				<title>Inicio</title>
				<link rel="stylesheet" href="css/style.css" type="text/css" />
				<link
					href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
					rel="stylesheet"
					integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
					crossorigin="anonymous"
					/>
			</head>
			<body>
				<header>
					<!-- Encabezado -->
					<div class="encabezado">
						<div class="banner-principal">
							<img src="img/baner pricipal.jpg" alt="banner pricipal" />
						</div>
					</div>
				</header>
				<!-- Navbar -->
				<div class="menu">
					<nav class="navbar navbar-expand-lg bg-body-tertiary" id="menu">
						<div class="container-fluid container">
							<button
								class="navbar-toggler"
								type="button"
								data-bs-toggle="collapse"
								data-bs-target="#navbarSupportedContent"
								aria-controls="navbarSupportedContent"
								aria-expanded="false"
								aria-label="Toggle navigation"
								>
								<span class="navbar-toggler-icon"></span>
							</button>
							<div class="collapse navbar-collapse" id="navbarSupportedContent">
								<ul class="navbar-nav me-auto mb-2 mb-lg-0">
									<li class="nav-item">
										<a
											class="nav-link active"
											aria-current="page"
											href="principal.html"
											id="menu-a"
											>Inicio</a>
									</li>
									<li class="nav-item dropdown">
										<a
											class="nav-link dropdown-toggle"
											href="#"
											role="button"
											data-bs-toggle="dropdown"
											aria-expanded="false"
											>
											Productos
										</a>
										<ul class="dropdown-menu">
											<li>
												<a class="dropdown-item" href="#">Tecnología</a>
											</li>
											<li>
												<a class="dropdown-item" href="#">Ocio</a>
											</li>
											<li>
												<a class="dropdown-item" href="#">Decoración</a>
											</li>
										</ul>
									</li>
									<li class="nav-item dropdown">
										<a
											class="nav-link dropdown-toggle"
											href="#"
											role="button"
											data-bs-toggle="dropdown"
											aria-expanded="false"
											>
											Gestión productos
										</a>
										<ul class="dropdown-menu">
											<li>
												<a class="dropdown-item" href="crearProducto.html"
													>Crear
													producto</a>
											</li>
											<li>
												<a class="dropdown-item" href="tablaProductos.html"
													>Tabla
													productos</a>
											</li>
										</ul>
									</li>
									<li class="nav-item dropdown">
										<a
											class="nav-link dropdown-toggle"
											href="#"
											role="button"
											data-bs-toggle="dropdown"
											aria-expanded="false"
											>
											Gestión ventas
										</a>
										<ul class="dropdown-menu">
											<li>
												<a class="dropdown-item" href="tablaVentas.html"
													>Ver
													ventas</a>
											</li>
											<li>
												<a class="dropdown-item" href="valoraciones.html"
													>Ver
													valoraciones</a>
											</li>
										</ul>
									</li>
								</ul>
							</div>
						</div>
					</nav>
				</div>
				
				<main class="container">
					<section class="productos">
						
						<xsl:for-each select="comercio/producto">
							
							<div class="card-producto">
								<div class="card-producto__img">
									<img src="img/{ruta_imagen}" alt="Imagen producto" />
								</div>
								<div class="card-producto__info">
									<h3><xsl:value-of select="nombre"/></h3>
									<h4><xsl:value-of select="precio"/> €</h4>
									<a
										href="producto.html"
										role="button"
										class="btn btn-outline-light btn-detalles"
										>Detalles</a>
								</div>
							</div>
							
						</xsl:for-each>
					</section>
				</main>
				
				<footer>
					<div class="row justify-content-center">
						<div class="col-2">
							<h5>Conocenos</h5>
							<ul class="nav flex-column">
								<li class="nav-item mb-2">
									<a href="#" class="nav-link p-0 text-muted">Trabaja en Star x</a>
								</li>
								<li class="nav-item mb-2">
									<a href="#" class="nav-link p-0 text-muted">Sobre StarX.es</a>
								</li>
								<li class="nav-item mb-2">
									<a href="#" class="nav-link p-0 text-muted">StarX Science</a>
								</li>
							</ul>
						</div>
						
						<div class="col-2">
							<h5>Metodos de Pago</h5>
							<ul class="nav flex-column">
								<li class="nav-item mb-2">
									<a href="#" class="nav-link p-0 text-muted"
										>Coversor de divisas</a>
								</li>
								<li class="nav-item mb-2">
									<a href="#" class="nav-link p-0 text-muted">Cheques de Regalo</a>
								</li>
								<li class="nav-item mb-2">
									<a href="#" class="nav-link p-0 text-muted">Recarga Online</a>
								</li>
							</ul>
						</div>
						<div class="col-2">
							<h5>¿ Necesitas Ayuda ?</h5>
							<ul class="nav flex-column">
								<li class="nav-item mb-2">
									<a href="#" class="nav-link p-0 text-muted"
										>Localizar y gestionar
										compras</a>
								</li>
								<li class="nav-item mb-2">
									<a href="#" class="nav-link p-0 text-muted"
										>Tarifas u politicas
										de envío</a>
								</li>
								<li class="nav-item mb-2">
									<a href="#" class="nav-link p-0 text-muted"
										>Atencion al cliente</a>
								</li>
							</ul>
						</div>
					</div>
					
					<div class="d-flex justify-content-between py-4 my-4 border-top">
						<p>© 2023 Company, Inc. All rights reserved.</p>
						<div class="redes-sociales">
							<a src="https://es-es.facebook.com/" target="_blank">
								<img src="img/facebook.png" alt="Logo Facebook" />
							</a>
							<a src="https://es-es.facebook.com/" target="_blank">
								<img src="img/twitter.png" alt="Logo Twitter" />
							</a>
							<a src="https://es-es.facebook.com/" target="_blank">
								<img src="img/instagram.png" alt="Logo Instagram" />
							</a>
						</div>
						<div class="footer-logo">
							<img src="img/logo.jpg" alt="logo" />
						</div>
					</div>
				</footer>
				<script
					src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
					integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
					crossorigin="anonymous"
					></script>
			</body>
		</html>
		
		
	</xsl:template>
</xsl:stylesheet>