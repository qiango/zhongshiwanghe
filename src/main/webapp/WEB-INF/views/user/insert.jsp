<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


	<div class="order-title stretch">
		<div class="title-text col-xs-3 padding-collapse">新增用户</div>
		<button type="button" class="btn btn-default pull-right mt-10" id="return_index_btn">返回</button>
	</div>

   <form action="" target="_self" method="post" id="submit_form"
        class="edit-form form-horizontal panel-h-align col-xs-11">
        <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>真实姓名</span>
            <div class="col-xs-10 stretch">
	            <input type="text" class="form-control width-200 display-align" name="user_real_name" placeholder="输入真实姓名" required  >
	            
	            <label id="user_real_name-error" class="error" for="user_real_name"></label>
            </div>
        </div>
        <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>登陆名</span>
            <div class="col-xs-10 stretch">
            	<input type="text" class="form-control width-200 display-align" placeholder="输入登陆名" required name="user_login_name">               
                <label id="user_login_name-error" class="error" for="user_login_name"></label>
            </div>
        </div>
        <div class="col-xs-11 stretch form-group float-none collapse">
            <span class="col-xs-2 align-right">密码</span> <div class="col-xs-10 stretch"><input type="hidden"
                class="form-control width-200 display-align" name="user_password"
                placeholder="输入密码" value="111111"></div>
        </div>
        <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>手机</span>
            <div class="col-xs-10 stretch">
	            <input type="text" required isMobile="true" class="form-control width-200 display-align" name="phone" placeholder="输入手机">
	            <label id="phone-error" class="error" for="phone"></label>
            </div>
        </div>
        <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>邮箱</span>
            <div class="col-xs-10 stretch">
	            <input type="text" required email="true" class="form-control width-200 display-align" name="mail_address" placeholder="输入邮箱" >           
	            <label id="mail_address-error" class="error" for="mail_address"></label>
            </div>
        </div>
        <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right">备注</span>
            <div class="col-xs-10 stretch">
            	<textarea name="remark" class="form-control display-align" rows="4" placeholder="请输入备注"></textarea>
            </div>
        </div>
        <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>平台</span> 
            <div class="col-xs-10 stretch">
	            <select type="text" required selectNone class="form-control width-200 select-choose form-control" name="platform_id" id="platform_id" data-placeholder="请选择平台">
	                	<option value=""></option>
	                	<c:forEach items="${platform_info_list}" var="plat"> 
	                	<option value="${plat.platform_id }">${plat.platform_name }</option>
	                	</c:forEach>
	            </select>
	            
	            <label id="platform_id-error" class="error" for="platform_id"></label>
            </div>
        </div>


        <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>用户角色</span>
            <div class="col-xs-10 checkbox stretch user_role" >
            <c:forEach items="${role_list}" var="role" varStatus="status">
                <label><input type="checkbox" name="user_roles" value="${role.role_id }" required>${role.role_name }</label>
				<c:if test="${role.role_id % 5 == 0}">
		            <br>
		        </c:if>	
            </c:forEach> 
            	
            </div>
        </div>
		<div class="col-xs-3 col-xs-offset-1 margin-align">
			<label class="warning-label">*</label>
			密码默认值为“111111”
		</div>
       <div class="col-xs-11 stretch form-group mt-10">
			
			<button class="btn btn-primary btn-theme pull-left col-xs-offset-2" id="submit_form_btn" type="submit" data-role="loading">保存</button>
		</div>
    </form>
    
    <script type="text/javascript">
    cadleo_data('/user/insertLoad.htmls');
    function showData(data){
    	$.when((function(){
    		var df = $.Deferred();
    		load_checkbox(data.data.roles,'user_roles','role_id','role_display_','role_name','.user_role');
    		load_page_data(data.data.platform_list,'code','value','select[name="platform_id"]');
    		df.resolve();
	    	return df.promise();
    	})())
    	.done(function(){
    		$(".select-choose").chosen().next('.chosen-container').css({width:$('.select-choose').css('width')});
    	});
    }

    //表单验证
    ns.formSubmit({
        submitUrl: '/user/insertSave.htmls'
    });

   </script>

