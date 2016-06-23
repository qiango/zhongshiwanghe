<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form id="submit_form">
	<input type="hidden" name="role_id" value="${role_id}">
	<div class="order-title stretch">
		<div class="title-text col-xs-3 padding-collapse">菜单列表</div>
		<button type="button" class="btn btn-default pull-right mt-10" id="return_index_btn">返回</button>
	</div>
	<dl class="col-xs-3 title-text bg-primary menu-list col-xs-offset-1">
		<dt class="checkbox menu-title">
			<input type="checkbox"> <span data-toggle="collapse"
				data-target="#first-level-1">菜单</span>
		</dt>
		<dd class="collapse in checkbox menu-content" id="first-level-1">
			<dl id="dl-container">
				<c:forEach items="${menu_tree}" var="menu" varStatus="status">

					<dt class="checkbox menu-title">
						<c:choose>
							<c:when test="${menu_ids.contains( menu.menu_id ) }">
								<input type="checkbox" checked="checked" name="menus_ids"
									value="${menu.menu_id}">
							</c:when>
							<c:otherwise>
								<input type="checkbox">
							</c:otherwise>
						</c:choose>
						<span data-toggle="collapse"
							data-target="#second-level-1-${status.index+1}">${menu.menu_name}</span>
					</dt>
					<!--                    <dd> -->
					<%--                        <c:forEach items="${menu.menu_branch }" var="branch"> --%>
					<%--                         <li style="color: orange">  ${branch.menu_name}</li> --%>
					<%--                        </c:forEach> --%>
					<!--                    </dd>  -->
					<dd class="collapse in checkbox menu-content"
						id="second-level-1-${status.index+1}">
						<dl>
							<c:forEach items="${menu.menu_leaf }" var="leaf"
								varStatus="status">
								<dt class="checkbox block menu-title">
									<c:choose>
										<c:when test="${menu_ids.contains( leaf.menu_id ) }">
											<input type="checkbox" checked="checked" name="menus_ids"
												value="${leaf.menu_id}">
										</c:when>
										<c:otherwise>
											<input type="checkbox" name="menus_ids"
												value="${leaf.menu_id}">
										</c:otherwise>
									</c:choose>
									<span data-toggle="collapse"
										data-target="#third-level-1-1-${status.index+1}">${leaf.menu_name}</span>
								</dt>
							</c:forEach>
						</dl>
					</dd>
				</c:forEach>
			</dl>
		</dd>
	</dl>



	<div class="col-xs-11 stretch form-group mt-10">
			<button class="btn btn-primary btn-theme pull-left col-xs-offset-2" id="submit_form_btn" type="submit">保存</button>
	</div>
</form>


<script>
	cadleo_data('/role/gear.htmls?role_id='+sessionStorage.getItem('edit_id'));
	function showData(data){
		$('[name="role_id"]').val(data.data.role_id);
		var str = '';
		var dat = data.data.menu_tree;
		for(var i = 0,le = dat.length; i < le; i++){
			str += '<dt class="checkbox menu-title">\
						<input type="checkbox" name="menus_ids" value="'+ dat[i].menu_id +'">\
						<span data-toggle="collapse" data-target="#second-level-1-'+ (i + 1) +'">' + dat[i].menu_name + '</span>\
					</dt>' + 
					'<dd class="collapse in checkbox menu-content" id="second-level-1-'+ (i + 1) + '">' + 
						'<dl>';
			for (var j = 0; j < dat[i].children.length; j++) {
				str += '<dt class="checkbox block menu-title">\
							<input type="checkbox" name="menus_ids" value="'+  dat[i].children[j].menu_id  +'">\
							<span data-toggle="collapse" data-target="#third-level-1-1-'+ (j + 1) +'">' + dat[i].children[j].menu_name + '</span>\
						</dt>';
			}
			str += '</dl>' + 
				'</dd>'
		}
		$('#dl-container').html(str);
		$('input[type="checkbox"]').each(function(){
    		 $.inArray($(this).val()-0,data.data.menu_ids)!==-1 && $(this).prop('checked',true).parents('.menu-content').prev().find('input[type="checkbox"]').prop("checked",true);
    	});
	}

	$('#submit_form').on('change','input[type="checkbox"]',function(){
		if(this.checked){
			$(this).parent().next('.menu-content').find('input[type="checkbox"]').prop("checked",true);
			$(this).parents('.menu-content').prev().find('input[type="checkbox"]').prop("checked",true);
		}
		else{
			$(this).parent().next('.menu-content').find('input[type="checkbox"]').prop("checked",false);
			if(!$(this).parent().parent().parent().find(':checked').length){
				$(this).parent().parent().parent().prev().find('input[type="checkbox"]').click();
			}
		}
	});
	
	
	
	$("#submit_form").validate({
		submitHandler : function() {
			if (confirm("确定要提交数据吗？")) {
				$.ajax({
					type : "POST",
					url : url + "/role/gearSave.htmls",
					data : $("#submit_form").serialize(),
				})
                .done(function(data){
               	 	ns.site_back(data);
                })
                .fail(internal_error);
			}
			return false;//阻止表单提交
		}
	});
</script>
