<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<div class="order-title stretch">
		<div class="title-text col-xs-3 padding-collapse">新增商品</div>
	</div>
	<form action="" target="_self"
		class="edit-form form-horizontal panel-h-align col-xs-11">
		<div class="col-xs-11 stretch form-group float-none">
			<span class="col-xs-2 align-right">商品供应商</span>
			<div class="col-xs-10 stretch">
				<input type="hidden" id="drop-value-supp" name="catagory" value="">
				<!--下拉框提交隐藏域-->
				<div class="btn-group">
					<button type="button" class="btn btn-default dropdown-toggle drop-btn width-200 text-ellipsis"
						data-toggle="dropdown">选择商品供应商</button>
					<span class="caret drop-arrow"></span>
					<ul class="dropdown-menu">
						<li><a href="#" title="">商品供应商品供应商商</a></li>
						<li><a href="#" title="">商品商品供应商商供应商</a></li>
						<li><a href="#" title="">商品供应商商</a></li>
						<li><a href="#" title="">商品供应商</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="col-xs-11 stretch form-group float-none">
			<span class="col-xs-2 align-right">商品名称</span> <input type="text"
				class="form-control width-200 display-align" name="ware"
				placeholder="输入商品名称">
		</div>
		<div class="col-xs-11 stretch form-group float-none">
			<span class="col-xs-2 align-right">商品货号</span>
			<div class="col-xs-10 stretch">
				<input type="text" class="form-control width-200 display-align" name="ware" id="ware"
					placeholder="输入商品货号">
				<label class="label-warning">*</label>
				<label id="ware-error" class="error" for="ware">输入商品货号错误</label>
			</div>
		</div>
		<div class="col-xs-11 stretch form-group float-none">
			<span class="col-xs-2 align-right">商品分类</span>
			<input type="hidden" id="drop-value-cata" name="catagory" value="">
			<!--下拉框提交隐藏域-->
			
			<div class="btn-group">
				<button type="button"
					class="btn btn-default dropdown-toggle drop-btn width-200 text-ellipsis"
					data-toggle="dropdown">选择商品分类</button>
				<span class="caret drop-arrow"></span>
				<ul class="dropdown-menu">
					<li class="stretch" data-toggle="modal"
						data-target="#catagory-modal"><i class="fa fa-plus fa-add"></i><a
						href="#">新建分类</a></li>
					<li><a href="#" title="">笔记本</a></li>
					<li><a href="#" title="">电视机</a></li>
					<li><a href="#" title="">手机</a></li>
				</ul>
			</div>
			
		</div>
		<div class="col-xs-11 stretch form-group float-none">
			<span class="col-xs-2 align-right">商品类型</span> <input type="hidden"
				id="drop-value-style" name="markingstyle" value="">
			<!--下拉框提交隐藏域-->
			<div class="btn-group">
				<button type="button"
					class="btn btn-default dropdown-toggle drop-btn width-200 text-ellipsis"
					data-toggle="dropdown">选择商品类型</button>
				<span class="caret drop-arrow"></span>
				<ul class="dropdown-menu">
					<li data-value="1"><a href="#" title="">选择商品类型</a></li>
					<li data-value="2"><a href="#" title="">商品类型商品类型商品类型</a></li>
					<li data-value="3"><a href="#" title="">商品类型</a></li>
					<li data-value="4"><a href="#" title="">商品类型商品类型商品类型商品类型</a></li>
				</ul>
			</div>
		</div>
		<div class="col-xs-11 stretch form-group float-none">
			<span class="col-xs-2 align-right">商品品牌</span> <input type="text"
				class="form-control width-200 display-align" name="brand"
				placeholder="输入商品品牌">
		</div>
		<div class="col-xs-11 stretch form-group float-none">
			<span class="col-xs-2 align-right">商品详细说明</span>
			<div class="col-xs-10 display-align stretch">
				<script id="editor" type="text/plain"></script>
			</div>
		</div>
		<div class="col-xs-11 stretch form-group float-none">
			<span class="col-xs-2 align-right">是否上架</span> <input type="checkbox"
				name="onsell" class="margin-align">
		</div>
		<div class="col-xs-11 stretch form-group float-none">
			<span class="col-xs-2 align-right">商品价格</span> <input type="text"
				class="form-control width-200 display-align" name="wareprice"
				placeholder="输入商品价格">
		</div>
		<div class="col-xs-11 stretch form-group float-none">
			<span class="col-xs-2 align-right">商品图片</span>
			<div class="col-xs-10 stretch">
				<label class="label-file btn btn-default btn-select"
					for="upload-img"> 上传图片<input type="file" name="upload-img"
					id="upload-img">
				</label>
				<div class="file-value text-ellipsis text-tips" title=""></div>
				<ul class="list-inline">
					<li class="stretch one"><img height="120"
						src="images/list-pic-1.jpg">
						<div class="img-delete">
							<i class="fa fa-remove fa-4x"></i>
						</div></li>
					<li class="stretch one"><img height="120"
						src="images/list-pic-2.jpg">
						<div class="img-delete">
							<i class="fa fa-remove fa-4x"></i>
						</div></li>
					<li class="stretch one"><img height="120"
						src="images/list-pic-3.jpg">
						<div class="img-delete">
							<i class="fa fa-remove fa-4x"></i>
						</div></li>
				</ul>
			</div>
		</div>
		<button class="btn btn-primary btn-theme pull-left col-xs-offset-2">保存</button>
	</form>

	<div class="order-title stretch">
		<div class="title-text col-xs-3">新增商品</div>
	</div>
	<form action="" target="_self"
		class="edit-form form-horizontal panel-h-align col-xs-11">
		<div class="col-xs-11 stretch form-group float-none">
			<span class="col-xs-2 align-right">商品名称</span> <input type="text"
				class="form-control width-200 display-align" name="ware"
				placeholder="输入商品名称">
		</div>
		<div class="col-xs-11 stretch form-group float-none">
			<span class="col-xs-2 align-right">商品货号</span> <input type="text"
				class="form-control width-200 display-align" name="warenumber"
				placeholder="输入商品货号"> <label class="text-tips indent">输入商品货号错误</label>
		</div>
		<div class="col-xs-11 stretch form-group float-none">
			<span class="col-xs-2 align-right">商品分类</span> <input type="hidden"
				id="drop-value-cata" name="catagory" value="">
			<!--下拉框提交隐藏域-->
			<div class="btn-group">
				<button type="button"
					class="btn btn-default dropdown-toggle drop-btn width-200 text-ellipsis"
					data-toggle="dropdown">选择商品分类</button>
				<span class="caret drop-arrow"></span>
				<ul class="dropdown-menu">
					<li class="stretch" data-toggle="modal"
						data-target="#catagory-modal"><i class="fa fa-plus fa-add"></i><a
						href="#">新建分类</a></li>
					<li><a href="#" title="">笔记本</a></li>
					<li><a href="#" title="">电视机</a></li>
					<li><a href="#" title="">手机</a></li>
				</ul>
			</div>
		</div>
		<div class="col-xs-11 stretch form-group float-none">
			<span class="col-xs-2 align-right">商品属性</span>
			<div class="col-xs-10 stretch">
				<div class="btn btn-default btn-select" data-toggle="modal"
					data-target="#ware-modal">选择属性</div>
				<div class="panel panel-default">
					<table class="table table-hover table-align table-bordered">
						<thead>
							<tr>
								<th>属性名称</th>
								<th>属性值</th>
								<th>备注</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>颜色</td>
								<td><input type="text" class="form-control" name=""></td>
								<td><input type="text" class="form-control" name=""></td>
								<td><i class="fa fa-remove fa-delete"></i></td>
							</tr>
							<tr>
								<td>尺寸</td>
								<td><input type="text" class="form-control" name=""></td>
								<td><input type="text" class="form-control" name=""></td>
								<td><i class="fa fa-remove fa-delete"></i></td>
							</tr>
							<tr>
								<td>制式</td>
								<td><input type="text" class="form-control" name=""></td>
								<td><input type="text" class="form-control" name=""></td>
								<td><i class="fa fa-remove fa-delete"></i></td>
							</tr>
							<tr>
								<td>型号</td>
								<td><input type="text" class="form-control" name=""></td>
								<td><input type="text" class="form-control" name=""></td>
								<td><i class="fa fa-remove fa-delete"></i></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="col-xs-11 stretch form-group float-none">
			<span class="col-xs-2 align-right">商品类型</span> <input type="hidden"
				id="drop-value-style" name="markingstyle" value="">
			<!--下拉框提交隐藏域-->
			<div class="btn-group">
				<button type="button"
					class="btn btn-default dropdown-toggle drop-btn width-200 text-ellipsis"
					data-toggle="dropdown">选择商品类型</button>
				<span class="caret drop-arrow"></span>
				<ul class="dropdown-menu">
					<li data-value="1"><a href="#" title="">选择商品类型</a></li>
					<li data-value="2"><a href="#" title="">商品类型商品类型商品类型</a></li>
					<li data-value="3"><a href="#" title="">商品类型</a></li>
					<li data-value="4"><a href="#" title="">商品类型商品类型商品类型商品类型</a></li>
				</ul>
			</div>
		</div>
		<div class="col-xs-11 stretch form-group float-none">
			<span class="col-xs-2 align-right">商品品牌</span> <input type="text"
				class="form-control width-200 display-align" name="brand"
				placeholder="输入商品品牌">
		</div>
		<div class="col-xs-11 stretch form-group float-none">
			<span class="col-xs-2 align-right">商品详细说明</span>
			<div class="col-xs-10 display-align stretch">
				<script id="editor" type="text/plain"></script>
			</div>
		</div>
		<div class="col-xs-11 stretch form-group float-none">
			<span class="col-xs-2 align-right">是否上架</span> <input type="checkbox"
				name="onsell" class="margin-align">
		</div>
		<div class="col-xs-11 stretch form-group float-none">
			<span class="col-xs-2 align-right">商品价格</span> <input type="text"
				class="form-control width-200 display-align" name="wareprice"
				placeholder="输入商品价格">
		</div>
		<div class="col-xs-11 stretch form-group float-none">
			<span class="col-xs-2 align-right">商品图片</span>
			<div class="col-xs-10 stretch">
				<label class="label-file btn btn-default btn-select"
					for="upload-img"> 上传图片<input type="file" name="upload-img"
					id="upload-img">
				</label>
				<div class="file-value text-ellipsis text-tips" title=""></div>
				<ul class="list-inline">
					<li class="stretch one"><img height="120"
						src="images/list-pic-1.jpg">
						<div class="img-delete">
							<i class="fa fa-remove fa-4x"></i>
						</div></li>
					<li class="stretch one"><img height="120"
						src="images/list-pic-2.jpg">
						<div class="img-delete">
							<i class="fa fa-remove fa-4x"></i>
						</div></li>
					<li class="stretch one"><img height="120"
						src="images/list-pic-3.jpg">
						<div class="img-delete">
							<i class="fa fa-remove fa-4x"></i>
						</div></li>
				</ul>
			</div>
		</div>
		<button class="btn btn-primary btn-theme pull-left col-xs-offset-2">保存</button>
	</form>



</body>
</html>