<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	<div class="order-title stretch">
		<div class="title-text col-xs-3 padding-collapse">商品管理/商品</div>
	</div>
	<div class="panel panel-default col-xs-11 stretch panel-h-align border-none">
		<form class="form-horizontal" action="" id="search_form" target="_self" search_path="">
			<div class="panel-body stretch">
				<div class="input-group col-xs-6 pull-left">
					<span class="input-group-addon">俱乐部名称</span>
					<input type="hidden" name="page_size" id="page_size">
                    <input type="hidden" name="page_number" id="page_number">
					<input class="form-control" type="text" name="club_name" id="search-input" placeholder="请输入"> 
				</div>
                <div class="col-xs-1">
					<button type="submit" class="btn btn-primary btn-theme-bg pull-left">搜索</button>
				</div>
			</div>
		</form>
	</div>
	<div class="panel panel-default col-xs-11 stretch panel-h-align">
		
		<table class="table table-hover table-striped table-align">
			<caption class="title">
				<div class="title-text col-xs-3 indent-collapse">商品分类列表</div>
				<a href="#/platform/toInsert.htmls" class="btn btn-primary btn-theme-bg pull-right indent-collapse btn-fix"
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
			 	<c:forEach items="${advertising_fodders_list}" var="advertising_fodders" varStatus="status">
				<tr>
					<td><c:out value="${status.index + 1}" /> </td>
					<td><c:out value="${advertising_fodders.user_name}" /></td>
					<td>2015-10-14</td>
					<td>家政服务</td>
					<td class=""><a href="#" class="fa fa-pencil-square-o v-align"
						title=""></a> <a href="#" class="fa fa-trash-o" title=""></a></td>
				</tr>
			 	</c:forEach>  
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
			页 <a class="go btn btn-default indent-collapse" href="#">确定</a>
		</div>
	</div>
	
	
	<script type="text/javascript">
	</script>

