<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
</main>
<footer>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-bottom">
		<a class="navbar-brand" href="#">&copy; 2020 Tiny Boardgames</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

	</nav>
</footer>

<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
<script src="js/jquery.dataTables.min.js"></script>
<script src="js/dataTables.bootstrap4.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('table').dataTable({
			"language" : {
				"url" : "js/Spanish.json"
			}
		});
	});
</script>
</body>
</html>