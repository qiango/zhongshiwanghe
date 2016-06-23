<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


    <div class="order-title stretch">
        <div class="title-text col-xs-3 padding-collapse"> 赛事报名者名单</div>
        <button type="button" class="btn btn-default pull-right mt-10" id="return_index_btn">返回</button>
    </div>
    
    <div class="panel panel-default col-xs-11 stretch panel-h-align border-none">
        <form class="form-horizontal" action="" target="_self" id="search_form"   search_path="" >
        
            <div class="panel-body stretch">
                <div class="input-group col-xs-6 pull-left">
                    <span class="input-group-addon">关键字</span>
                    <input type="hidden" name="page_size" id="page_size">
                    <input type="hidden" name="page_number" id="page_number">
                    <input class="form-control" type="text" name="search" id="search-input" placeholder="请输入关键字">
                </div>
                <select name="search_category" class="drop-btn width-200 select-choose form-control ml-8 pull-left"  >
                   <option value="">查看全部 </option>
                   <option value="1">审核中</option>
                   <option value="98">已拒绝</option>
                   <option value="99">已报名</option>
                </select> 
                <div class="col-xs-1">
                    <button class="btn btn-primary bg-theme border-theme pull-left" type="submit">搜索</button>
                </div>
				<button class="btn btn-primary btn-theme-bg pull-right ml-20" type="button" id="check-pass">审核通过</button>
            	<button class="btn btn-primary btn-theme-bg pull-right" type="button" id="check-refuse-btn" data-toggle="modal" data-target="#fix-modal">审核不通过</button>
            </div>
        </form>
    </div>
    
    
    <form action="" target="_self" method="post" id="submit_form" class="form-horizontal">
    <div class="panel panel-default col-xs-11 stretch panel-h-align">
        
        <table class="table table-hover table-striped table-align">
        	<caption class="title">
	            <div class="title-text col-xs-3 indent-collapse">赛事报名者列表</div>
	            <input  type="hidden" id="competition_id" name="competition_id" competition_id=${competition_id }> 
        	</caption>
            <thead>
                <tr>
<!-- 	                <th>序号</th> -->
<%-- 	                <c:forEach items="${title_name }" var="t_name" > --%>
<%-- 	                    <th>${t_name }</th> --%>
<%-- 	                </c:forEach> --%>
<!-- 	                <th><label class="checkbox name-text margin-collapse"><input type="checkbox" id="check_all" >全选</label></th> -->
                </tr>
            </thead>
            
            <tbody>
                <c:forEach items="${display_user_info }" var="info" varStatus="status">
                <tr>
				    <td>${status.count }</td>
                    <c:forEach items="${info }" var="col" varStatus="tdst">
                        <c:choose>
                            <c:when test="${tdst.count=='1'}">
                                <input type="hidden" class="user_flag" user_id="${col}">
                            </c:when>    
                            <c:when test="${tdst.count=='2'}">
                                <input type="hidden" class="join_flag" user_join_status="${col}">
                            </c:when>    
                            <c:otherwise>
                                <td >${col} </td>
                            </c:otherwise>
                        </c:choose>

                    </c:forEach>
                    
                </tr>
                </c:forEach>  
            </tbody>
        </table>
    </div>
   	<div class="col-xs-11 stretch form-group mt-10">
		
	</div>
    </form>
	<div class="modal fade in" id="fix-modal">
		    <div class="modal-dialog">
		      <div class="modal-content">		
		        <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal"><span>×</span></button>
		          <h3 class="modal-title">请填写审核不通过的原因</h3>
		        </div>
		        <div class="modal-body container-fluid" id="view-detail">
		        	
<!-- 		        	<span class="col-xs-2 mt-7">请输入</span> -->
<!-- 		        	<div class="col-xs-10 stretch"> -->
              			<textarea name="reason" class="form-control" id="reason" rows="6"></textarea>
<!--             		</div> -->
            		
	           	</div>
	            <div class="modal-footer">
        	   	   <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        	   	   <button type="button" class="btn btn-primary" data-dismiss="modal" id="modal-refuse-btn">提交</button>
      		    </div>
	        </div>
	      </div>
	    </div>
	<div class="display-align cad-page">

    </div>
    
    <script type="text/javascript">
	function filter_keys(data,key){
		var keys=[];
		for(var i in data){
			(i.indexOf(key)!=-1) && keys.push(i.replace(key,''));
		}
		return keys;
	}
    cadleo_data('/competitionApplication/eye.htmls?competition_id='+sessionStorage.getItem('edit_id'));
	function showData(data){
		$.Deferred(function(){ 
			$('#search_form').attr('search_path','/competitionApplication/eye.htmls?competition_id='+sessionStorage.getItem('edit_id'));
			
			$('.cad-page').html(data.data.pageModel.pageString); 
			$('#competition_id').val(data.data.competition_id)
			var str = '',td = '';
			for(var i=0;i<data.data.title_name.length;i++){
				i == 0 && (str += '<th>序号</th><th>俱乐部名称</th>');
				str += '<th>' + data.data.title_name[i] + '</th>';
				i == (data.data.title_name.length-1) && (str += '<th>报名状态</th><th>报名时间</th><th>审核时间</th><th class="checkbox col-"><label class="checkbox name-text margin-collapse"><input type="checkbox" id="check_all" >全选</label></th>',$('thead tr').html(str));
			}
			for(var i=0;i<data.data.pageModel.result.length;i++){
				td +='<tr><td>' + (i+1) + '</td>';
				td +='<td>' + data.data.pageModel.result[i].club_name + '</td>';
				td +='<input type="hidden" class="user_flag" user_id="'+ data.data.pageModel.result[i].user_id +'">';
				td +='<input type="hidden" class="join_flag" user_join_status="'+ data.data.pageModel.result[i].user_join_status +'">';
// 				td +='<td>' + data.data.pageModel.result[i]['col1'] + '</td>';
// 				td +='<td>' + data.data.pageModel.result[i]['col2'] + '</td>';
// 				td +='<td>' + data.data.pageModel.result[i]['col3'] + '</td>';
// 				td +='<td>' + data.data.pageModel.result[i]['col4'] + '</td>';
// 				td +='<td>' + data.data.pageModel.result[i]['col5'] + '</td>';
// 				td +='<td>' + data.data.pageModel.result[i]['col6'] + '</td>';
				var arr = filter_keys(data.data.pageModel.result[i],'col').sort();
				for(var j=0; j<arr.length;j++ ){
					td +='<td>' + data.data.pageModel.result[i]['col'+arr[j]] + '</td>';
				}
				td +='<td>' + data.data.pageModel.result[i].user_competition_status +'</td>';
				td +='<td>' + data.data.pageModel.result[i].create_time +'</td>';
				var apply_or_refuse_time = undefined==data.data.pageModel.result[i].apply_or_refuse_time? "" : data.data.pageModel.result[i].apply_or_refuse_time;
				td +='<td>' + apply_or_refuse_time +'</td>';
				td +='<td><input type="checkbox" class="check_user_id"></td></tr>';
			}
			$('tbody').html(td);
		})
		.done( $('.join_flag').each(function(){
	    	if($(this).attr('user_join_status')=='99'){
	    		$(this).siblings('td').find('.check_user_id').remove();
	    	}
	    }));
    }	
	$('.table-align').on('change','#check_all',function(){
    	if(this.checked){
    		$('.check_user_id').prop('checked',true);
    	} else { 
    		$('.check_user_id').prop('checked',false);
    	}
    });
	$('.table-align').on('change','.check_user_id',function(){
    	if($('#submit_form').find(':checked').not('#check_all').length==$('.check_user_id').length){
    		$('#check_all').prop('checked',true);
    	} else {
    		$('#check_all').prop('checked',false);
    	}
    });
   
    $('#submit_form').submit(function(){
    	var datas='',
    		checked=$(this).find(':checked').not('#check_all');
    	if(checked.length == 0){
			alert('请至少勾选一项');
			return false;
		}
    	for(var i=0,len=checked.length;i<len;i++){
    		datas += ','+checked.eq(i).parent().siblings('.user_flag').attr('user_id'); 
    	}
		datas=datas.substring(1);
		if(confirm("确定要通过审核吗")) {
            $.ajax({
                 type: "POST",
                 url: url+'/competitionApplication/apply.htmls',
                 data: {competition_id:$('#competition_id').val(),user_id: datas},
                 dataType:"json",
                 success: function(data,status){
       //         	 alert('审核通过');
                	 goUrl(location.hash.substring(1));
                 },
                 error: internal_error
            });
        }
    	return false;
    });
	$('#check-refuse-btn').click(function(e){
		var checked=$('#submit_form').find(':checked').not('#check_all');
		if(checked.length == 0){
			alert('请至少勾选一项');
			e.stopImmediatePropagation();
		}
	});
    $('#modal-refuse-btn').click(function(){
    	var datas='',
			checked=$('#submit_form').find(':checked').not('#check_all');
		for(var i=0,len=checked.length;i<len;i++){
			datas += ','+checked.eq(i).parent().siblings('.user_flag').attr('user_id'); 
		}
    	$.ajax({
            type: "POST",
            url: url+'/competitionApplication/refuse.htmls',
            data: {competition_id:$('#competition_id').val(),user_id: datas.substring(1),reason:$('#reason').val()},
            dataType:"json",
       })
       .always(function(){
    	   $('.modal-backdrop').remove();
       })
       .done(function(data){
    		   //alert('审核通过');
    		  goUrl(location.hash.substring(1));
       })
       .fail(internal_error);
    });
    $('#check-pass').click(function(){
    	$('#submit_form').submit();
    });
    $('[name="search_category"]').change(function(){
    	$('#search_form').submit();
    });
    </script>

