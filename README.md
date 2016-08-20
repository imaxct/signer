# signer
    A Java Tieba signer
    
- ps:部署时需修改```hibernate.cfg.xml```和```pom.xml```中的数据库与远程tomcat账户名和密码
## 遇到的问题
- 2016-08-17 jsp页面中EL表达式无法正确显示, 解决方法:去掉web.xml中的```DOCTYPE```标签

- 2016-08-18 绑定账号时Account表无法更新, hibernate出现错误
    ```NonUniqueObjectException: a different object with the same identifier value was already associated with the session```
    
    原因是在渲染页面时获取了一次Account对象, hibernate的Session中已经存在了一个持久化对象, 后面update之前又获取了一次, 出现了2个Id相同的持久化对象.
    
    解决方法: 改用```session.merge(Obj)```方法, 合并两个持久化对象.
    
- 2016-08-20 GBK编码的网页读取后乱码
    
    解决方法: 构建```InputStreamReader```时加上第二个编码参数, 与网页的编码相对应, new String时同样加上相应的编码, 最后转换成UTF-8编码的String储存到数据库.