<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<div class="order-title stretch">
		<div class="title-text col-xs-3 padding-collapse">申请管理</div>
	</div>
	<div class="panel panel-default col-xs-11 stretch panel-h-align scroll-auto">
		
		<table class="table table-hover table-striped table-align">
			<caption class="title">						
				<div class="title-text col-xs-4 stretch indent-collapse">申请加入俱乐部用户列表</div>					
			</caption>				
			<thead>
				<tr>
				    <th>序号</th>
                    <th>用户名</th>
                    <th>手机号</th>
                    <th>选择的俱乐部</th> 
                    <th>操作</th>
				</tr>
			</thead>
			<tbody>
			 
			</tbody>
		</table>
	</div>
	<div class="display-align">

	</div>
	<script type="text/javascript">
	cadleo_data('/userdetail/list.htmls');
	function showData(data){
		var data = data.data.user_list;
		var trs='';
		for(var i in data){
			var tds='<tr>';
			tds += "<td>"+(Number(i)+1)+"</td>";
			tds += "<td>"+(data[i].user_real_name||'')+"</td>";
			tds += "<td>"+(data[i].phone||'')+"</td>";
		 	tds += "<td>"+(data[i].club_name||'')+"</td>"; 
			tds += "<td class='text-ellipsis'><a href='javascript:void(0);'class='fa fa-cog wj_cog_data' club_id='"+data[i].club_id+"' user_id='"+data[i].user_id+"' set_path='/userdetail/agree.htmls' back_path='/userdetail/index' data-apply='confirm_pass' title='审核通过'></a></td>";
			tds+='</tr>';
			trs+=tds;
		}
		$('table.table-align tbody').html(trs);
	}
	

	</script>
