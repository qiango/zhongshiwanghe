<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


	<div class="order-title stretch">
		<div class="title-text col-xs-3 padding-collapse">角色新增</div>
		<button type="button" class="btn btn-default pull-right mt-10" id="return_index_btn">返回</button>
	</div>
	<form action="" target="_self" method="post" id="submit_form"
        class="edit-form form-horizontal panel-h-align col-xs-11">
       <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>角色名称</span>
            <div class="col-xs-10 stretch">
            	<input type="text" class="form-control width-200 display-align" name="role_name" placeholder="输入角色名称" required>
            	
            	<label id="role_name-error" class="error" for="role_name"></label>
            </div>
        </div>

		<div class="col-xs-11 stretch form-group float-none">
			<span class="col-xs-2 align-right">备注</span>
			<div class="col-xs-10 stretch">
				<textarea name="remark" class="form-control display-align" rows="4" placeholder="备注"></textarea>
			</div>
		</div>
		<div class="col-xs-11 stretch form-group mt-10">
			
			<button class="btn btn-primary btn-theme pull-left col-xs-offset-2" id="submit_form_btn" type="submit" data-role="loading">保存</button>
		</div>
    </form>
    
    
    <script type="text/javascript">
//     cadleo_data('/role/insertLoad.htmls');
//     function showData(data){
    	
//     }

	//表单验证
	ns.formSubmit({
		submitUrl: '/role/insertSave.htmls'
	});
    </script>

