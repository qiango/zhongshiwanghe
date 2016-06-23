<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


	<div class="order-title stretch">
		<div class="title-text col-xs-3 padding-collapse">菜单管理</div>
	</div>
	<div class="panel panel-default col-xs-11 stretch panel-h-align border-none">
		<form class="form-horizontal" action="" target="_self" id="search_form" method="post" search_path="/menu/list.htmls">
			<div class="panel-body stretch">
				<div class="input-group col-xs-6 pull-left">
					<span class="input-group-addon">菜单名称</span>
					<input type="hidden" name="page_size" id="page_size">
                    <input type="hidden" name="page_number" id="page_number">
					<input class="form-control" type="text" name="menu_name" id="search-input" placeholder="请输入菜单名称">						
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
				<div class="title-text col-xs-3 indent-collapse">菜单列表</div>
			</caption>
			<thead>
				<tr>
					<th>序号</th>
					<th>菜单名</th>
					<th>菜单链接</th>
					<th>菜单父节点</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
	
    
	<div class="display-align cad-page">
	</div>
	<script type="text/javascript">
	cadleo_data('/menu/list.htmls');
	function showData(data){
		var trs='',
			data=data.data;
		for(var i in data){
			var tds='<tr>';
			tds += "<td>"+(Number(i)+1)+"</td>";
			tds += "<td>"+data[i].menu_name+"</td>";
			tds += "<td>"+(data[i].menu_url||'')+"</td>";
			tds += "<td>"+data[i].menu_parent_id+"</td>";
			tds+='</tr>';
			trs+=tds;
		}
		$('table.table-align tbody').html(trs);
	}
		
	</script>

