<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
</main>
<footer class="pie_pagina" style="display:flex">
  <div class="copyright">
    <p>&copy 2020 - TinyBoardGames</p>
  </div>
  <div class="social">
    <a href="#" class="support">Contactanos</a>
    <a href="#" class="face">f</a>
    <a href="#" class="tweet">t</a>
    <a href="#" class="linked">in</a>
  </div>
</footer>

<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/bootstrap.bundle.js"></script>
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