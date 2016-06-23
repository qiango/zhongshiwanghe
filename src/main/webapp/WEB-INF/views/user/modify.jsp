<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


	<div class="order-title stretch">
		<div class="title-text col-xs-3 padding-collapse">修改用户</div>
		<button type="button" class="btn btn-default pull-right mt-10" id="return_index_btn">返回</button>
	</div>

   <form action="" target="_self" method="post" id="submit_form"
        class="edit-form form-horizontal panel-h-align col-xs-11">
        <input type="hidden" name="user_id" value="${user_info.user_id }">
        <input type="hidden" name="user_login_name" value="${user_info.user_login_name }">
        <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>真实姓名</span>
            <div class="col-xs-10 stretch">
            	<input type="text"  class="form-control width-200 display-align" name="user_real_name"
                placeholder="输入真实姓名"  value="${user_info.user_real_name }" required>
            </div>
        </div>
        <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>登陆名</span>
            <div class="col-xs-10 stretch">
            	<input type="text" class="form-control width-200 display-align" readonly="readonly" name="user_login_name" value="" required>
          	</div> 
        </div>
        <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>手机</span>
            <div class="col-xs-10 stretch">
            	<input type="text" class="form-control width-200 display-align" name="phone" id="phone" required isMobile="true" 
                placeholder="输入手机号" value="${user_info.phone }">
            	<label id="phone-error" class="error" for="phone"></label>
            </div>
        </div>
        <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>邮箱</span>
            <div class="col-xs-10 stretch">
            	<input type="text" class="form-control width-200 display-align" name="mail_address" required email="true" placeholder="输入邮箱" value="${user_info.mail_address}">
            	<label id="mail_address-error" class="error" for="mail_address"></label>
            </div>
        </div>
        <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right">备注</span>
            <div class="col-xs-10 stretch">
            	<textarea class="form-control display-align" rows="5" name="remark"
                placeholder="备注">${user_info.remark}</textarea>
            </div>
        </div>
        <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>平台</span> 
            <div class="col-xs-10 stretch">
	            <select type="text" required selectNone class="form-control width-200 select-choose form-control" name="platform_id" id="platform_id" data-placeholder="请选择平台">
	                	<option value=""></option>
	                	<c:forEach items="${platform_info_list}" var="plat"> 
	                	<c:choose>
							<c:when test="${plat.platform_id == user_info.platform_id }">
								<option value="${plat.platform_id }" selected>${plat.platform_name }</option>
							</c:when>
							<c:otherwise>
								<option value="${plat.platform_id }">${plat.platform_name }</option>
							</c:otherwise>
						</c:choose>
	                	</c:forEach>
	            </select>

            </div>
        </div>

<!--         <div class="col-xs-11 stretch form-group float-none"> -->
<!--             <span class="col-xs-2 align-right">是否删除</span> -->
<!--             <div class="col-xs-10 checkbox stretch"> -->
<%-- 				<label><input data-checked="${user_info.is_delete }" type="checkbox" name="is_delete" placeholder="" value="1"> 删除</label> --%>
<!-- 			</div> -->
<!--         </div> -->

       
         <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>角色</span> 
            <div class="col-xs-10 checkbox stretch user_role" >
            <c:forEach items="${role_list_all}" var="role">
               <label><input type="checkbox"  name="user_roles"  id="role_display_${role.role_id }" value="${role.role_id }" > ${role.role_name }</label>
               <c:if test="${role.role_id % 5 == 0}">
		            <br>
		        </c:if>
            </c:forEach>
            </div>
        </div>
        
        <div class="col-xs-11 stretch form-group mt-10">
			
			<button class="btn btn-primary btn-theme pull-left col-xs-offset-2" id="submit_form_btn" type="submit">保存</button>
		</div>
    </form>
    
<%--     <c:forEach items="${users_roles}" var="users_roles">z</c:forEach> --%>
    
    <script type="text/javascript">
    cadleo_data('/user/modifyLoad.htmls?user_id='+sessionStorage.getItem('edit_id'));
    function showData(data){
    	$.when((function(){
    		var df = $.Deferred();
    		load_checkbox(data.data.roles,'user_roles','role_id','role_display_','role_name','.user_role');
        	load_page_data(data.data.platform_list,'code','value','select[name="platform_id"]');
        	
        	df.resolve();
        	return df.promise();
	    })())
	    .done(function(){
	    	$('select option').each(function(){
	    		data.data.user_info[$(this).parent().attr('name')]==$(this).val() && $(this).prop('selected',true);
	    	});
	    })
	    .done(function(){
	    	$(".select-choose").chosen().next('.chosen-container').css({width:$('.select-choose').css('width')})
		    $(':input').not('select,input[type="checkbox"]').each(function(){
	    		$(this).val(data.data.user_info[$(this).attr('name')]);
	    	});
	        for(var i=0;i< data.data.user_roles.length;i++){
	     	  $("#role_display_"+data.data.user_roles[i]).prop('checked',true);
	        }
	    });
		
//     	if( $("[name='is_delete']").data("checked") == 1 ){
//     		$("[name='is_delete']").prop("checked",true); 
//     	}
    }
    
    $("#submit_form").validate({
        submitHandler : function(){
            if(confirm("确定要提交数据吗？")) {
                $.ajax({
                     type: "POST",
                     url: url +"/user/modifySave.htmls",
                     data: $("#submit_form").serialize(),
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

