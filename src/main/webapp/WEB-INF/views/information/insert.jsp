<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


	<div class="order-title stretch">
		<div class="title-text col-xs-3 padding-collapse">新增资讯</div>
		<button type="button" class="btn btn-default pull-right mt-10" id="return_index_btn">返回</button>
	</div>

   <form action="" target="_self" method="post" id="submit_form"
        class="edit-form form-horizontal panel-h-align col-xs-11">
       <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>赛事</span>
            <div class="col-xs-10 stretch">
               <select name="competition_id" class="drop-btn width-200 select-choose form-control" data-placeholder="请选择" required >
                     <option value=""></option>
               </select>
               <label id="competition_id-error" class="error" for="competition_id"></label>
            </div>
        </div>
        <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>平台</span>
            <div class="col-xs-10 stretch">
               <select name="platform_id" class="drop-btn width-200 select-choose form-control" data-placeholder="请选择" required >
                     <option value=""></option>
               </select>
               <label id="platform_id-error" class="error" for="platform_id"></label>
            </div>
        </div>
       <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right">资讯类型</span>
            <div class="col-xs-10 stretch">
               <select name="information_type" class="drop-btn width-200 select-choose form-control" data-placeholder="请选择">
               		<option value=""></option>
<%--                     <c:forEach items="${info_type }" var="info_t"> --%>
<%--                         <c:choose> --%>
<%--                             <c:when test="${information.information_type == info_t.id  }"> --%>
<%--                         <option value="${info_t.id }" selected="selected">${info_t.name } </option> --%>
<%--                             </c:when>     --%>
<%--                             <c:otherwise> --%>
<%--                         <option value="${info_t.id }">${info_t.name } </option> --%>
<%--                             </c:otherwise> --%>
<%--                         </c:choose> --%>
<%--                      </c:forEach>  --%>
               </select>
            </div>
        </div>
         <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right"><label class="warning-label">*</label>资讯标题</span>
            <div class="col-xs-10 stretch">
            	<input type="text" class="form-control width-350 display-align" name="information_title" required
                placeholder="输入资讯标题" >
            </div>
        </div>
        <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right">资讯摘要</span>
            <div class="col-xs-10 stretch">
                <textarea name="information_abstract" class="form-control width-350 display-align" rows="5" placeholder="输入资讯摘要"></textarea>
            </div>
        </div>
        <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right">上传图片</span>
            <div class="col-xs-10 stretch">
            	<div class="col-xs-12 display-table stretch">
	            	<label class="label-file btn btn-default">
	            		选择图片<input id="fileToUpload" type="file" size="45" name="fileToUpload">
	            	</label>
	            	<input type="text" name="submit_file" id="submit_file">
	            	<button type="button" class="btn btn-default upload-btn">上传</button>
	            	<label id="submit_file-error" class="error" for="submit_file"></label>
	            	<div class="file-path text-ellipsis"></div>
            	</div>
 				
 				<img class="viewImg collapse">
            </div>
        </div>
        <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right">资讯内容</span>	
            <div class="col-xs-10 stretch">
                <script id="editor" type="text/plain" style="width:500px;height:500px;" name="information_content"></script>
            </div>
        </div>
        <!-- 
     	 <div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right">备注</span>
            <div class="col-xs-10 stretch">
            	<input type="text" class="form-control width-200 display-align" name="remark"
                placeholder="输入备注" >
            </div>
        </div>
        -->
      


		<div class="col-xs-11 stretch form-group mt-10">
			
			<button class="btn btn-primary btn-theme pull-left col-xs-offset-2" id="submit_form_btn" type="submit">保存</button>
		</div>
	</form>
    
    <script type="text/javascript">
    cadleo_data('/information/insertLoad.htmls');
    function showData(data){
    	
    	
    	$.when((function(){
    		var df = $.Deferred();
    		load_page_data(data.data.information_type_list,'code','zh_value','select[name="information_type"]');
    		load_page_data(data.data.competition_list,'competition_id','competition_name','select[name="competition_id"]');
        	load_page_data(data.data.platform_list,'platform_id','platform_name','select[name="platform_id"]');
        	df.resolve();
        	return df.promise();
    	})())
    	.done(function(){
    		$(".select-choose").chosen().next('.chosen-container').css({width:$('.select-choose').css('width')});
    	});
    }
    
    var editor = new UE.ui.Editor();
    editor.render("editor");
    $("#submit_form").validate({
        submitHandler : function(){
            if(confirm("确定要提交数据吗？")) {
                $.ajax({
                     type: "POST",
                     url: url + "/information/insertSave.htmls",
                     dataType:"json",
                     data: $("#submit_form").serialize() ,
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
