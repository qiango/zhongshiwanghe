<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


	<div class="order-title stretch">
		<div class="title-text col-xs-3 padding-collapse">角色管理</div>
	</div>
	<div class="panel panel-default col-xs-11 stretch panel-h-align border-none">
		<form class="form-horizontal" action="" target="_self" id="search_form" method="post" search_path="/role/list.htmls">
			<div class="panel-body stretch">
				<div class="input-group col-xs-6 pull-left">
					<span class="input-group-addon">角色名称</span>
					<input type="hidden" name="page_size" id="page_size">
                    <input type="hidden" name="page_number" id="page_number">
					<input class="form-control" type="text" name="role_name" id="search-input" placeholder="请输入角色名称">					
                </div>
                <div class="col-xs-1">
					<button type="submit" class="btn btn-primary btn-theme-bg pull-left" >搜索</button>
				</div>
			</div>
		</form>
	</div>
	<div class="panel panel-default col-xs-11 stretch panel-h-align">
		
		<table class="table table-hover table-striped table-align">
			<caption class="title">
				<div class="title-text col-xs-3 indent-collapse">角色列表</div>
				<a href="#/role/insert"
					class="btn btn-primary btn-theme-bg pull-right indent-collapse btn-fix"
					id="btn_save_load">录入</a>
			</caption>
			<thead>
				<tr>
					<th>序号</th>
					<th>角色名称</th>
					<th>备注</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			
			</tbody>
		</table>
	</div>
	<div class="display-align cad-page">
	</div>	
	<script type="text/javascript">

	cadleo_data('/role/list.htmls');
	function showData(data){
		$('.cad-page').html(data.data.pageString);
		var data = data.data.role_list;
		var trs='';
		for(var i in data){
			var tds='<tr>';
			tds += "<td>"+(Number(i)+1)+"</td>";
			tds += "<td>"+(data[i].role_name||'')+"</td>";
			tds += "<td>"+(data[i].remark||'')+"</td>";
			tds += "<td class=''><a href='#/role/modify' edit_id='"+data[i].role_id+"' class='fa fa-pencil-square-o v-align wj_edit_data' title='编辑'></a>\
								<a href='javascript:void(0);' delete_id='"+data[i].role_id+"' delete_path='/role/delete.htmls?role_id="+ data[i].role_id +"' back_path='/role/index' class='fa fa-trash-o wj_delete_data' title='删除'></a>\
								<a href='javascript:void(0);' cog_path='/role/userRole' edit_id='"+data[i].role_id+"'  class='fa fa-cog wj_cog_data' title='配置'></a></td>";
			tds+='</tr>';
			trs+=tds;
		}
		$('table.table-align tbody').html(trs);
	}
	

	</script>




