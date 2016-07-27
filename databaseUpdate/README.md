### 说明

databaseUpdate 目录记录每次投产对数据库所做的变更操作

### 使用方法

1. `update.sql` ; 每次开发时有数据库变更操作, 要把变更 sql 语句添加到 update.sql 文件中 ;
2. 接产前对  `update.sql`  中语句进行检查和测试;
3. 投产时直接用 `update.sql` 文件对生产数据库进行变更操作;
```shell
   mysql -h localhost -u `user_name` -p `database_name`< update.sql
```
4. 接产完成后把 `update.sql` 文件重命名为 `(投产日的八位日期格式).sql`
5. 在 `databaseUpdate` 目录中新建 `update.sql` 进行下一轮开发 ;




