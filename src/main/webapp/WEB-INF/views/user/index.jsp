<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


    <div class="order-title stretch">
        <div class="title-text col-xs-3 padding-collapse">用户管理</div>
    </div>
    <div class="panel panel-default col-xs-11 stretch panel-h-align border-none">
        <form class="form-horizontal" action="" target="_self" id="search_form" search_path="/user/list.htmls">
            <div class="panel-body stretch">
                <div class="input-group col-xs-6 pull-left">
                    <span class="input-group-addon">用户登陆名</span>
                    <input type="hidden" name="page_size" id="page_size">
                    <input type="hidden" name="page_number" id="page_number">
                    <input class="form-control" type="text" name="user_name" id="search-input" placeholder="请输入用户登陆名">                        
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
				<div class="title-text col-xs-3 indent-collapse">用户列表</div>
	            <a href="#/user/insert"
	                class="btn btn-primary btn-theme-bg pull-right indent-collapse btn-fix"
	                id="btn_save_load">录入</a>
			</caption>
            <thead>
                <tr>
                    <th class="text-ellipsis">序号</th>
              <!--       <th>用户ID</th> -->
                    <th>真实姓名</th>
                    <th>登陆名</th>
                    <th>电话</th>
                    <th>邮箱</th>
                    <th>用户状态</th>
      <!--               <th>备注</th> -->
                    <th>创建日期</th>
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
    cadleo_data('/user/list.htmls');
    function showData(data){
		$('.cad-page').html(data.data.pageString);
		var data = data.data.result;
    	var trs='';
    	for(var i in data){
    		var tds='<tr>';
    		tds += "<td>"+(Number(i)+1)+"</td>";
    	/* 	tds += "<td>"+data[i].user_id+"</td>"; */
    		tds += "<td>"+data[i].user_real_name+"</td>";
    		tds += "<td>"+data[i].user_login_name+"</td>";
    		if(data[i].phone==null){
    			tds += "<td></td>";
    		}else{
    			tds += "<td>"+data[i].phone+"</td>";
    		}
    		if(data[i].mail_address==null){
    			tds += "<td></td>";
    		}else{
    			tds += "<td>"+data[i].mail_address+"</td>";
    		}
    		tds += "<td>"+(data[i].user_status||'')+"</td>";
    	/* 	tds += "<td>"+data[i].remark+"</td>"; */
    		if(data[i].create_date==null){
    			tds += "<td></td>";
    		}else{
    			tds += "<td>"+data[i].create_date+"</td>";
    		}
    		tds += "<td class='text-ellipsis'><a href='#/user/modify' edit_id='"+data[i].user_id+"' class='fa fa-pencil-square-o v-align wj_edit_data' title='编辑'></a>\
    							<a href='javascript:void(0);'delete_id='"+data[i].user_id+"' delete_path='/user/delete.htmls?user_id="+ data[i].user_id +"' back_path='/user/index' class='fa fa-trash-o wj_delete_data' title='删除'></a></td>";
			tds+='</tr>';
			trs+=tds;
    	}
    	$('table.table-align tbody').html(trs);
    }
    
         
    </script>
