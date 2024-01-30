<%@ include file="common/header.jspf" %>
	<%@ include file="common/nav.jspf" %>
	<div class="container">
		<h1>Enter Todo Details</h1>
		<!-- modelAttribute tells this form that it needs to map to the todo bean -->
		<form:form method="post" modelAttribute="todo">
		
			<fieldset class="mb-3">
				<form:label path="description">Description</form:label>
				<form:input type="text" path="description" required="required"/>
				<form:errors path="description" cssClass="text-warning"/>
			</fieldset>
			
			<fieldset class="mb-3">
				<form:label path="targetDate">Target Date</form:label>
				<form:input type="text" path="targetDate" required="required"/>
				<form:errors path="targetDate" cssClass="text-warning"/>
			</fieldset>
			
			<!-- Since id and done are fields of todo bean, BUT you don't need to show
			in this form, you have to include these 2 as form inputs but then you can
			hide them. -->
			<form:input type="hidden" path="id"/>
			<form:input type="hidden" path="done"/>
			<input type="submit" class="btn btn-success"/>
			
		</form:form>
	</div>
<%@ include file="common/footer.jspf" %>
<script type="text/javascript">
	$('#targetDate').datepicker({
	    format: 'yyyy-mm-dd'
	});
</script>
