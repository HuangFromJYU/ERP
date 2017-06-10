# 前言
[ERP](https://github.com/HuangFromJYU/ERP)项目是我学完SSH框架后做的第二个项目练习，第一个是前面介绍的[JYUOA](https://github.com/HuangFromJYU/JYUOA)，这个项目的业务逻辑要比JYUOA复杂一下，但是在技术方面有很多使用的是类似的，像什么增删改查功能，还有公共功能抽取什么的。但是也有一些差别，你可以发现完成同一件事情会有不同的方法，通过两个项目的对比，你会觉得有些方法会比较好（没有比较就没有伤害~）。比如说在JYUOA中，是采用注解方式扫描bean和注入依赖，而ERP中是XML配置的方式，这样子一看我觉得用注解的方式比较好。好了，下面就具体来介绍一下这个项目。

# 项目简介
ERP(Enterprise Resource Planning)翻译过来就是企业资源计划，ERP 系统包括以下主要功能：供应链管理、销售与市场、分销、客户服务、财务管理、制造管理、库存管理、工厂与设备维护、人力资源、报表、制造执行系统 (Manufacturing Executive System，MES)、工作流服务和企业信息系统等。此外，还包括金融投资管理、质量管理、运输管理、项目管理、法规与标准和过程控制等补充功能。

上面那段是我在百度百科那拷贝过来的，说的很详细，但是我做的这个ERP并没有实现这么多功能，真的要全部实现起来，可能要好几十个人做上一两年呢，在后面我会介绍一下这个项目实现的一些功能。

# 项目整体架构
一般这个时候就要扯到三层架构和MVC模式了，不管很多人都这两个其实会搞混，说不出个所以然来，这里我强烈建议你去看看[对三层和MVC的认识过程](http://blog.csdn.net/timheath/article/details/72887267)。

这个ERP项目就是用到了三层架构和MVC模式了，在之前介绍的JYUOA项目采用的是两层架构，因为它的业务逻辑比较简单，所以直接把业务层和数据访问层整合一起了，但是这个ERP项目的业务逻辑要稍微复杂一点，所以是不可能这样子做的了。

下面是网站的主页

![](http://img.blog.csdn.net/20170610085742099?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvVGltSGVhdGg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

# 所用框架、技术

- Struts2 2.3.7
- Hibernate 3.6
- Spring 3.2.0
- jQuery 1.8.3
- jfreechart 1.0.13：图表绘制类库
- Jxl：操作excel表格的工具类库

说明：还有jQuery日历插件Calendar.js与jQuery.treeview等小插件。

# 项目目录结构与包结构

**目录结构**

- 源码文件夹
1. src：项目源代码
2. resources：配置文件
3. test：测试代码
- WebContent文件夹下
1. css：CSS样式文件
2. images：页面需要的一些图片
3. js：JavaScript脚本文件
4. jsps：项目静态文件，你可以访问WebContent下的index2.jsp来浏览项目静态文件
5. WEB-INF/jsps：jsp页面文件（再创建子文件夹分类存放）

**包结构**

- edu.jyu.erp.auth：系统的基础信息模块
- edu.jyu.erp.invoice：进销存模块
- edu.jyu.erp.util：存放一些工具类和通用的类

上面前两个包的子包是有定义规则，就拿用户模块`emp`为例，下面是包结构的截图

![](http://img.blog.csdn.net/20170610085820527?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvVGltSGVhdGg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

1. vo：存放的是实体类和hibernate实体映射文件，还有存放查询条件的实体类
2. dao：存放的是数据访问层的接口和实现类
3. business：存放的是业务层的接口和实现类
4. web：存放的是Action类

其中，类名的规范如下：

1. 实体模型类：模块名Model
2. 查询模型类：模块名QueryModel(按需求定义)
3. 数据层接口：模块名Dao
4. 数据层实现：模块名Impl
5. 业务层接口：模块名Ebi
6. 业务层实现：模块名Ebo
7. 表现层类：模块名Action


# 项目功能
关于项目功能，其实有些模块的个别功能我是没有做的，像删除、修改或者说查询等操作，因为整个项目的框架搭建好了，而且都有一定的规则，有些功能完全可以参照其它已经完成的模块，所需要做的就是复制粘贴换名字，这样子的重复编码工作，对于学习来说真的没什么意思。所以如果你发现一个功能没实现，你可以自己去实现一下，因为项目有着严格的命名的规范，你可以很容易地从其它模块的代码找到类似的功能实现。

正因为这个项目有着严格的命名规范，所以你可以使用`edu.jyu.erp.util.generator.GeneratorUtil`去根据一个模块的实体类去生成它对应的各层代码和配置文件，当时我看到这里的时候，心情是这样子的

![](http://img.blog.csdn.net/20170610085845569?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvVGltSGVhdGg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)


## 基础维护

### 部门维护
部门维护比较简单，以为它只有部门名称和电话两个字段，业务逻辑也不是很复杂，就是在删除部门的时候需要注意不能乱来，如果员工有关联一个部门的话，删除这个部门会出现错误，在查询员工列表的时候，因为在员工表中，关联部门的字段并没有外键约束，只是普通的一个字段，所以删除部门时就算有员工关联着也可以删除。因为在hibernate中部门实体类没有定义员工集合，部门在删除时也没有级联删除员工，所以就出现了这个问题，要解决这个问题我只想到了两种方案，第一种就是部门对员工进行级联删除，即删除部门时把部门下的员工也全部删除，不过这个感觉有点不靠谱；第二种是删除部门的时候，将员工表中关联部门的id置为null，这样子表明这个员工不属于任何部门。

在添加员工的时候要指定部门，部门跟员工是一对多的关系。

部门对应的实体类是`edu.jyu.erp.auth.dep.vo.DepModel`

![](http://img.blog.csdn.net/20170610085901262?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvVGltSGVhdGg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 员工维护
员工，同时也是系统的用户，他属于一个部门，可以同时拥有多个角色。

员工对应的实体类是`edu.jyu.erp.auth.emp.vo.EmpModel`

![](http://img.blog.csdn.net/20170610085915028?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvVGltSGVhdGg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 角色维护
角色用来关联资源和菜单，一个角色可以关联多个资源和菜单，一个用户如果拥有某个角色那么他就可以操作这个角色关联的资源和菜单。

角色对应的实体类是`edu.jyu.erp.auth.role.vo.RoleModel`

![](http://img.blog.csdn.net/20170610085931184?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvVGltSGVhdGg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 资源维护
资源其实就是具体对数据的操作，比如说查看员工列表，添加员工等等，可以给资源一个名和一个action的方法名，来表示代表什么操作。只有定义过的资源（就是资源列表中存在的）才会进行拦截操作，具体的你可以看权限拦截器`edu.jyu.erp.util.interceptor.AuthInterceptor`

资源对应的实体类是`edu.jyu.erp.auth.res.vo.ResModel`

![](http://img.blog.csdn.net/20170610085946868?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvVGltSGVhdGg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 菜单维护
这里的菜单就是指网页左侧菜单栏中的菜单，如果一个用户拥有的全部角色中都没有关联某个菜单的话，那么就不会在左侧显示该菜单。

菜单对应的实体类是`edu.jyu.erp.auth.menu.vo.MenuModel`

![](http://img.blog.csdn.net/20170610085958681?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvVGltSGVhdGg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 仓库维护
仓库，就是用来存放商品、货物的。

仓库对应的实体类是`edu.jyu.erp.invoice.store.vo.StoreModel`

![](http://img.blog.csdn.net/20170610090023447?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvVGltSGVhdGg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

## 商品管理

### 供应商
供应商，就是供应商品的东东，在添加商品类别和商品的时候需要指定供应商

供应商对应的实体类是`edu.jyu.erp.invoice.supplier.vo.SupplierModel`

![](http://img.blog.csdn.net/20170610090042310?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvVGltSGVhdGg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 商品类别
在添加商品的时候，可以让商品属于一个类别，商品类别属于一个供应商，即商品类别和供应商是多对一关系。

商品类别对应的实体类是`edu.jyu.erp.invoice.goodstype.vo.GoodsTypeModel`

![](http://img.blog.csdn.net/20170610090100073?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvVGltSGVhdGg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 商品
商品与商品类别的关系是多对一，在进行采购的时候，采购的就是商品。

商品对应的实体类是`edu.jyu.erp.invoice.goods.vo.GoodsModel`

![](http://img.blog.csdn.net/20170610090115573?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvVGltSGVhdGg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

## 采购管理

### 采购订单
采购商品的时候，就需要下一个订单了，指定采购商品的相关信息

采购订单对应的实体类是`edu.jyu.erp.invoice.order.vo.OrderModel`

![](http://img.blog.csdn.net/20170610090132826?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvVGltSGVhdGg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 采购审核
在制定好采购订单后，需要进行审核，其实就是改变采购订单的状态，才能进行下一步运作。

![](http://img.blog.csdn.net/20170610090149998?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvVGltSGVhdGg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

## 商品运输

### 任务指派
在采购订单审核通过后，就需要进行任务指派了，指派员工去运输商品。一般来说指派的候选人是运输部门中的员工，但是这里的实现的候选人是当前登录用户所在部门的员工，一般也是运输部门的领导才有任务指派的权限。

![](http://img.blog.csdn.net/20170610090207889?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvVGltSGVhdGg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 任务查询
在这里，就可以对任务进行查询，查看各个任务的信息。

![](http://img.blog.csdn.net/20170610090221123?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvVGltSGVhdGg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

## 仓库管理

### 库存查询
即查询仓库的使用情况

有关的实体类是`edu.jyu.erp.invoice.storedetail.vo.StoreDetailModel`

![](http://img.blog.csdn.net/20170610090247950?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvVGltSGVhdGg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 入库
即将上面任务指派好后，订单的商品运输回来了，并且在任务查询中对订单结单，那么就可以进行入库了，入库后订单的状态就是结单。

![](http://img.blog.csdn.net/20170610090300216?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvVGltSGVhdGg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 仓库操作明细
就是对仓库操作的记录。

有关的实体类是`edu.jyu.erp.invoice.operdetail.vo.OperDetailModel`

![](http://img.blog.csdn.net/20170610090319685?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvVGltSGVhdGg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

## 报表中心

### 采购报表
对订单进行查询，并根据查询出来的数据生成对应的饼图以及Excel文件。

![](http://img.blog.csdn.net/20170610090336467?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvVGltSGVhdGg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

# 项目搭建
整个项目大概就介绍到这里，现在就来说说怎么在你的机器上跑起来。我在本机运行这个项目的时候，用的JDK版本是1.7，Tomcat版本是8。那么你需要注意了，Spring 4.X才支持JDK 1.8，所以如果你用JDK 1.8的话，可能会出现莫名其妙的问题。


1. 下载这个项目后，先导入`erpdb.sql`到MySQL中，不用自己创建库，这个sql文件就可以帮你创建库和表，另外还有一些基本数据和测试数据。
2. 下载这个项目的代码，然后在你自己的IDE中创建Web工程，随便起你喜欢的名字，把下载好的代码拷贝到工程的对应位置中。你可以发现我没有上传`.project`文件，因为我用的是Eclipse，你可能用的是其它工具。我也没有上传`.classpath`，因为像Eclipse的`WebContent`到了`MyEclipse`就是`WebRoot`，`.classpath`文件可能会让产生一些让你疑惑的问题，比如说同时出现了`WebContent`和`WebRoot`，反正我就被懵过，至于其它的IDE我也没用过了，也不知道会出现什么问题，所以你要记得把`src`、`test`还有`resources`文件夹Build Path为源码目录(Source Folder)。
3. 然后就是部署到你的Web容器中啦，我只试过在在Tomcat 8中部署。

# 总结
这个项目相比我做的第一个项目JYUOA，业务逻辑要复杂不少，但也让我学到了更多的知识，里面有很多技巧都让我耳目一新，像给某些不易于展示的字段加一个视图值，还有因为有着严格的包名和类名规范，写个代码生成器去根据实体生成代码，实在是能节省不少的时间。当然这个项目的缺点也是一大堆，前台没有对表单进行校验，异常处理也没怎么做好，日志更是没有，还有一些零零散散的功能没有实现。最后需要说的是，这个项目我是学习传智播客的ERP项目的，所以非常感谢传智播客将他们的教学资源分享出来。

----------

项目地址https://github.com/HuangFromJYU/ERP
