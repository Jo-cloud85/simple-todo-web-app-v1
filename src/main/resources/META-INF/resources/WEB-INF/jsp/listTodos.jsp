<%@ include file="common/header.jspf" %>
	<%@ include file="common/nav.jspf" %>
	<div class="container mt-5">
		<h1>Welcome ${name}</h1>
		<h2>Your To-dos</h2>
		<table class="table">
			<thead>
				<tr>
					<th>Description</th>
					<th>Target Date</th>
					<th>Is Done?</th>
				</tr>
			</thead>
			<tbody>
 				<c:forEach items="${todos}" var="todo">
					<tr>
						<td>${todo.description}</td>
						<td>${todo.targetDate}</td>
						<td>${todo.done}</td>
						<td><a href="update-todo?id=${todo.id}" class="btn btn-success">UPDATE</a></td>
						<td><a href="delete-todo?id=${todo.id}" class="btn btn-warning">DELETE</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="add-todo" class="btn btn-success">Add Todo</a>
	</div>
 		
<%@ include file="common/footer.jspf" %>