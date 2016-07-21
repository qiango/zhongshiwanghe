<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<div class="order-title stretch">
		<div class="title-text col-xs-3 padding-collapse">俱乐部-运动派配置</div>
		<button type="button" class="btn btn-default pull-right mt-10" id="return_index_btn">返回</button>
	</div>
	<form action="" target="_self" method="post" id="submit_form"
        class="edit-form form-horizontal panel-h-align col-xs-11">
        <input type="hidden" name="club_id" value="${ club_list.club_id}">
<!-- 		<div class="col-xs-11 stretch form-group float-none"> -->
<!--             <span class="col-xs-2 align-right">俱乐部</span>  -->
<%--             <div class="col-xs-10 stretch checkbox">${club_list.club_name }</div>    --%>
<!--         </div> -->
		<div class="col-xs-11 stretch form-group float-none">
            <span class="col-xs-2 align-right">运动派</span> 
              <div class="col-xs-10 checkbox stretch sportscamp_checkbox" >
	             <c:forEach items="${sports_camp_listAll}" var="sports">
		             <label>
		               <input type="checkbox" name="sports_camp_id" id="sports_camp_id_${sports.sports_camp_id }" value="${sports.sports_camp_id }" >  ${sports.sports_camp_name } 
		             </label>
	             </c:forEach>             
             </div>
        </div>

		<div class="col-xs-11 stretch form-group mt-10">
						
			<button class="btn btn-primary btn-theme pull-left col-xs-offset-2" id="submit_form_btn" type="submit" data-role="loading">保存</button>
		</div>
    </form>
    
    
    <script type="text/javascript">
    cadleo_data('/club/sportscamp.htmls?club_id='+sessionStorage.getItem('edit_id'));
    function showData(data){
    	$.when((function(){
    		var df = $.Deferred();
    		load_checkbox(data.data.sports_camps_all,'sports_camp_id','sports_camp_id','sports_camp_id_','sports_camp_name','.sportscamp_checkbox');
    		df.resolve();
    		return df.promise();
    	})())
    	.done(function(){
	    	for(var i=0;i< data.data.sports_camps_id_list.length;i++){
	       	  $("#sports_camp_id_"+data.data.sports_camps_id_list[i]).prop("checked",'ture');
	        }
	    });
    }
    $('[name="club_id"]').val(sessionStorage.getItem('edit_id'));
    $("#submit_form").validate({
        submitHandler : function(){
        	var ids='';
        	$(":checked").each(function(){
        		ids += ',' + $(this).val()
        	});
            if(confirm("确定要提交数据吗？")) {
                $.ajax({
                     type: "POST",
                     url: url+"/club/sportscampSave.htmls",
                     data: {club_id:$('[name="club_id"]').val(),sports_camp_id:ids.substring(1)},
                     success: function(data){
    //                    alert('提交成功');
                        change_hash("/club/index");
                     },
                     error: internal_error
                 });
            }
            return false;//阻止表单提交
        }
    }); 
 
    $("#club_create_date").datepicker({
	format:'yyyy-mm-dd'
	});

//     var checkobj=$.parseJSON('${sports_camp_list}'),
//     	allobj=$.parseJSON('${sports_camp_listAll}');   
//     for(var k=0;k<checkobj.length;k++){
//     	for(var w=0;w<allobj.length;w++){
//     		if(allobj[w].sports_camp_id==checkobj[k].sports_camp_id){
//     			$('#sports_camp_id_'+allobj[w].sports_camp_id).prop('checked',true);
//     		}
//     	}
//     }
  
    </script>
</body>
</html>