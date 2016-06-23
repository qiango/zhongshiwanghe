<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<h1>hello, this is platform home page</h1>

	<p>${platform_list}</p>
	<div class="order-title stretch">
		<div class="title-text col-xs-3 padding-collapse">商品管理/商品</div>
	</div>
	<div class="panel panel-default col-xs-11 stretch panel-h-align border-none">
		<form class="form-horizontal" action="" target="_self" id="search_form" search_path="/platform/indexAjax.htmls">
			<div class="panel-body stretch">
				<div class="input-group col-xs-6 pull-left">
					<span class="input-group-addon">商品名称</span>
					<input type="hidden" name="page_size" id="page_size">
                    <input type="hidden" name="page_number" id="page_number">
					<input class="form-control" type="text" name="ware" id="search-input" placeholder="请输入商品名称">
				</div>
				<div class="col-xs-1">
					<button class="btn btn-primary bg-theme border-theme pull-left">搜索</button>
				</div>
			</div>
		</form>
	</div>
	<div class="panel panel-default col-xs-11 stretch panel-h-align">
		
		<table class="table table-hover table-striped table-align">
			<caption class="title">
				<div class="title-text col-xs-3 indent-collapse">商品分类列表</div>
				<a href="#/platform/toInsert.htmls"
					class="btn btn-primary bg-theme border-theme pull-right indent-collapse btn-fix"
					id="btn_save_load">录入</a>
			</caption>	
			<thead>
				<tr>
					<th>名称</th>
					<th>创建时间</th>
					<th>修改时间</th>
					<th>备注</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
 
			</tbody>
		</table>
	</div>
	<div class="display-align">
		<ul class="pagination pull-left">
			<li><a href="#">« 上一页</a></li>
			<li><a href="#">1</a></li>
			<li><a href="#">2</a></li>
			<li><a href="javascript:void(0);" class="page">...</a></li>
			<li><a href="#">13</a></li>
			<li><a class="bg-theme" href="#">14</a></li>
			<li><a href="#">15</a></li>
			<li><a href="#">16</a></li>
			<li><a href="#">17</a></li>
			<li><a href="javascript:void(0);" class="page">...</a></li>
			<li><a href="#">下一页 »</a></li>
		</ul>
		<div class="pull-left pagination indent">
			共100页,到第 <input type="text" class="input-page indent-collapse">
			页 <a class="go btn btn-default indent-collapse" href="javascript:void(0);">确定</a>
		</div>
	</div>
	
	
	<script type="text/javascript">
	function showData(data){
		var trs='';
		for(var i in data){
			trs += '<tr>';
			trs += "<td>"+(Number(i)+1)+"</td>";
			trs += "<td>"+data[i].platform_id+"</td>";
			trs += "<td>"+data[i].platform_name+"</td>";
			trs += "<td class=''><a href='javascript:void(0);' edit_path='/platform/modifyLoad/"+data[i].role_id+".htmls' class='fa fa-pencil-square-o v-align wj_edit_data' title=''></a><a href='javascript:void(0);' delete_id='"+data[i].role_id+"' delete_path='/platform/delete/"+ data[i].role_id +".htmls' back_path='/platform/index.htmls' class='fa fa-trash-o wj_delete_data' title=''></a></td>";
			trs+='</tr>';
		}
		$('table.table-align tbody').html(trs);
	}
	showData($.parseJSON('${platform_list}'));
	</script>
</body>
</html>