package edu.jyu.erp.util.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import edu.jyu.erp.invoice.operdetail.vo.OperDetailModel;

/**
 * 代码生成工具类，因为项目有严格的规范，所以有一定的规律，可以根据实体类简单地来生成对应的其它代码，
 * 如Ebo、Action等，不过只具有最简单的通用的几个方法
 * 
 * @author Jason
 *
 */
public class GeneratorUtil {
	private Class clazz;
	private String b; // Emp
	private String l; // e
	private String s; // emp
	private String pkg; // edu.jyu.erp.auth.emp
	private String dir; // edu/jyu/erp/auth/emp/vo

	public static void main(String[] args) throws Exception {
		// EmpModel,RoleModel,ResModel,MenuModel
		// SupplierModel,GoodsTypeModel,GoodsModel
		// OrderModel,OrderDetailModel
		// StoreModel,StoreDetailModel,OperDetailModel
		new GeneratorUtil(OperDetailModel.class);
		System.out.println("struts.xml未进行映射");
		System.out.println("HbmXml未添加关联关系");
		System.out.println("QueryModel未添加自定义范围查询条件");
		System.out.println("DaoImpl中未对自定义查询条件形式条件设置");
	}

	public GeneratorUtil(Class clazz) throws Exception {
		this.clazz = clazz;
		// 生成所有的内容
		// -1.数据初始化
		dataInit();
		// 0.创建目录
		generatorDirectory();
		// 1.QueryModel
		generatorQueryModel();
		// 2.Hbm.xml
		generatorHbmXml();
		// 3.Dao
		generatorDao();
		// 4.Impl
		generatorImpl();
		// 5.Ebi
		generatorEbi();
		// 6.Ebo
		generatorEbo();
		// 7.Action
		generatorAction();
		// 8.applicationContext.xml
		generatorApplicationContextXml();
		// 9.struts.xml(选作)
		// modifyStrutsXml();
	}

	private void modifyStrutsXml() throws Exception {
		// 1.读取原始的内容
		// 2.读取到特定位置（package）添加指定内容

		// 我们要读的文件与写的文件是同一个文件
		/*
		 * RandomAccessFile类读写文件时 读取，一共100，读70，写，写的内容会覆盖后30 111 222 333 444
		 * 在333的后面写5 111 222 333 544 在333的后面写5 111 222 333 555
		 */
		// 方案一：
		/*
		 * 读取原始文件，将内容写入新文件 写之前判断，读取的内容是否是特定内容，特定内容写之前，加入新的内容
		 * 写完毕之后生成了新的文件，删除老的文件，使用新文件更名为老的文件
		 */
		// 方案二：
		// 1.读取原始文件的文件大小,字节总数1000
		File f = new File("resources/struts.xml");
		long len = f.length();
		// 2.创建一个字节数组，大小等于原始文件字节总数
		byte[] buf = new byte[(int) len];
		// 3.将原始文件读入该byte数组
		InputStream is = new FileInputStream(f);
		is.read(buf);
		is.close();
		// 4.将buf转化为字符串
		String all = new String(buf);
		// 5.查找固定位置
		int idx = all.lastIndexOf("    </package>");
		// 6.将要写入的内容插入该位置
		String info = "    	<!-- " + b + " -->\r\n    	<action name=\"" + s + "_*\" class=\"" + s
				+ "Action\" method=\"{1}\">\r\n    	</action>\r\n\r\n";
		// 7.将info加入all的指定位置
		StringBuilder sbf = new StringBuilder(all);
		sbf.insert(idx, info);
		// 8.将sbf中的组合最终内容写入struts.xml
		FileOutputStream fos = new FileOutputStream(f);
		fos.write(sbf.toString().getBytes());
		fos.close();
	}

	// 8.applicationContext.xml
	private void generatorApplicationContextXml() throws Exception {
		File f = new File("resources/applicationContext-" + s + ".xml");
		if (f.exists()) {
			return;
		}
		f.createNewFile();
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));

		bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		bw.newLine();

		bw.write("<beans xmlns=\"http://www.springframework.org/schema/beans\"");
		bw.newLine();

		bw.write("	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
		bw.newLine();

		bw.write("	xsi:schemaLocation=\"");
		bw.newLine();

		bw.write("		http://www.springframework.org/schema/beans ");
		bw.newLine();

		bw.write("		http://www.springframework.org/schema/beans/spring-beans.xsd");
		bw.newLine();

		bw.write("		\"> ");
		bw.newLine();

		bw.write("	<!-- Action -->");
		bw.newLine();

		bw.write("	<bean id=\"" + s + "Action\" class=\"" + pkg + ".web." + b + "Action\" scope=\"prototype\">");
		bw.newLine();

		bw.write("		<property name=\"" + s + "Ebi\" ref=\"" + s + "Ebi\"/>");
		bw.newLine();

		bw.write("	</bean>");
		bw.newLine();

		bw.write("	<!-- Ebi -->");
		bw.newLine();

		bw.write("	<bean id=\"" + s + "Ebi\" class=\"" + pkg + ".business.ebo." + b + "Ebo\">");
		bw.newLine();

		bw.write("		<property name=\"" + s + "Dao\" ref=\"" + s + "Dao\"/>");
		bw.newLine();

		bw.write("	</bean>");
		bw.newLine();

		bw.write("	<!-- Dao -->");
		bw.newLine();

		bw.write("	<bean id=\"" + s + "Dao\" class=\"" + pkg + ".dao.impl." + b + "Impl\">");
		bw.newLine();

		bw.write("		<property name=\"sessionFactory\" ref=\"sessionFactory\"/>");
		bw.newLine();

		bw.write("	</bean>");
		bw.newLine();

		bw.write("</beans>");
		bw.newLine();

		bw.flush();
		bw.close();
	}

	// 7.Action
	private void generatorAction() throws Exception {
		File f = new File("src/" + dir + "/web/" + b + "Action.java");
		if (f.exists()) {
			return;
		}
		f.createNewFile();
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));

		bw.write("package " + pkg + ".web;");
		bw.newLine();

		bw.newLine();

		bw.write("import java.util.List;");
		bw.newLine();

		bw.newLine();

		bw.write("import " + pkg + ".business.ebi." + b + "Ebi;");
		bw.newLine();

		bw.write("import " + pkg + ".vo." + b + "Model;");
		bw.newLine();

		bw.write("import " + pkg + ".vo." + b + "QueryModel;");
		bw.newLine();

		bw.write("import edu.jyu.erp.util.base.BaseAction;");
		bw.newLine();

		bw.newLine();

		bw.write("public class " + b + "Action extends BaseAction{");
		bw.newLine();

		bw.write("	public " + b + "Model " + l + "m = new " + b + "Model();");
		bw.newLine();

		bw.write("	public " + b + "QueryModel " + l + "qm = new " + b + "QueryModel();");
		bw.newLine();

		bw.newLine();

		bw.write("	private " + b + "Ebi " + s + "Ebi;");
		bw.newLine();

		bw.write("	public void set" + b + "Ebi(" + b + "Ebi " + s + "Ebi) {");
		bw.newLine();

		bw.write("		this." + s + "Ebi = " + s + "Ebi;");
		bw.newLine();

		bw.write("	}");
		bw.newLine();

		bw.newLine();

		bw.write("	//列表");
		bw.newLine();

		bw.write("	public String list(){");
		bw.newLine();

		bw.write("		setDataTotal(" + s + "Ebi.getCount(" + l + "qm));");
		bw.newLine();

		bw.write("		List<" + b + "Model> " + s + "List = " + s + "Ebi.getAll(" + l + "qm,pageNum,pageCount);");
		bw.newLine();

		bw.write("		put(\"" + s + "List\", " + s + "List);");
		bw.newLine();

		bw.write("		return LIST;");
		bw.newLine();

		bw.write("	}");
		bw.newLine();

		bw.newLine();

		bw.write("	//到添加");
		bw.newLine();

		bw.write("	public String input(){");
		bw.newLine();

		bw.write("		if(" + l + "m.getUuid()!=null){");
		bw.newLine();

		bw.write("			" + l + "m = " + s + "Ebi.get(" + l + "m.getUuid());");
		bw.newLine();

		bw.write("		}");
		bw.newLine();

		bw.write("		return INPUT;");
		bw.newLine();

		bw.write("	}");
		bw.newLine();

		bw.newLine();

		bw.write("	//添加");
		bw.newLine();

		bw.write("	public String save(){");
		bw.newLine();

		bw.write("		if(" + l + "m.getUuid() == null){");
		bw.newLine();

		bw.write("			" + s + "Ebi.save(" + l + "m);");
		bw.newLine();

		bw.write("		}else{");
		bw.newLine();

		bw.write("			" + s + "Ebi.update(" + l + "m);");
		bw.newLine();

		bw.write("		}");
		bw.newLine();

		bw.write("		return TO_LIST;");
		bw.newLine();

		bw.write("	}");
		bw.newLine();

		bw.newLine();

		bw.write("	//删除");
		bw.newLine();

		bw.write("	public String delete(){");
		bw.newLine();

		bw.write("		" + s + "Ebi.delete(" + l + "m);");
		bw.newLine();

		bw.write("		return TO_LIST;");
		bw.newLine();

		bw.write("	}");
		bw.newLine();

		bw.newLine();

		bw.write("}");
		bw.newLine();

		bw.flush();
		bw.close();
	}

	// 6.Ebo
	private void generatorEbo() throws Exception {
		File f = new File("src/" + dir + "/business/ebo/" + b + "Ebo.java");
		if (f.exists()) {
			return;
		}
		f.createNewFile();
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));

		bw.write("package " + pkg + ".business.ebo;");
		bw.newLine();

		bw.newLine();

		bw.write("import java.io.Serializable;");
		bw.newLine();

		bw.write("import java.util.List;");
		bw.newLine();

		bw.newLine();

		bw.write("import " + pkg + ".business.ebi." + b + "Ebi;");
		bw.newLine();

		bw.write("import " + pkg + ".dao.dao." + b + "Dao;");
		bw.newLine();

		bw.write("import " + pkg + ".vo." + b + "Model;");
		bw.newLine();

		bw.write("import edu.jyu.erp.util.base.BaseQueryModel;");
		bw.newLine();

		bw.newLine();

		bw.write("public class " + b + "Ebo implements " + b + "Ebi{");
		bw.newLine();

		bw.write("	private " + b + "Dao " + s + "Dao;");
		bw.newLine();

		bw.write("	public void set" + b + "Dao(" + b + "Dao " + s + "Dao) {");
		bw.newLine();

		bw.write("		this." + s + "Dao = " + s + "Dao;");
		bw.newLine();

		bw.write("	}");
		bw.newLine();

		bw.newLine();

		bw.write("	public void save(" + b + "Model " + l + "m) {");
		bw.newLine();

		bw.write("		" + s + "Dao.save(" + l + "m);");
		bw.newLine();

		bw.write("	}");
		bw.newLine();

		bw.newLine();

		bw.write("	public void update(" + b + "Model " + l + "m) {");
		bw.newLine();

		bw.write("		" + s + "Dao.update(" + l + "m);");
		bw.newLine();

		bw.write("	}");
		bw.newLine();

		bw.newLine();

		bw.write("	public void delete(" + b + "Model " + l + "m) {");
		bw.newLine();

		bw.write("		" + s + "Dao.delete(" + l + "m);");
		bw.newLine();

		bw.write("	}");
		bw.newLine();

		bw.newLine();

		bw.write("	public " + b + "Model get(Serializable uuid) {");
		bw.newLine();

		bw.write("		return " + s + "Dao.get(uuid);");
		bw.newLine();

		bw.write("	}");
		bw.newLine();

		bw.newLine();

		bw.write("	public List<" + b + "Model> getAll() {");
		bw.newLine();

		bw.write("		return " + s + "Dao.getAll();");
		bw.newLine();

		bw.write("	}");
		bw.newLine();

		bw.newLine();

		bw.write("	public List<" + b + "Model> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {");
		bw.newLine();

		bw.write("		return " + s + "Dao.getAll(qm,pageNum,pageCount);");
		bw.newLine();

		bw.write("	}");
		bw.newLine();

		bw.newLine();

		bw.write("	public Integer getCount(BaseQueryModel qm) {");
		bw.newLine();

		bw.write("		return " + s + "Dao.getCount(qm);");
		bw.newLine();

		bw.write("	}");
		bw.newLine();

		bw.newLine();

		bw.write("}");
		bw.newLine();

		bw.flush();
		bw.close();
	}

	// 5.Ebi
	private void generatorEbi() throws Exception {
		File f = new File("src/" + dir + "/business/ebi/" + b + "Ebi.java");
		if (f.exists()) {
			return;
		}
		f.createNewFile();
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));

		bw.write("package " + pkg + ".business.ebi;");
		bw.newLine();

		bw.newLine();

		bw.write("import org.springframework.transaction.annotation.Transactional;");
		bw.newLine();

		bw.newLine();

		bw.write("import " + pkg + ".vo." + b + "Model;");
		bw.newLine();

		bw.write("import edu.jyu.erp.util.base.BaseEbi;");
		bw.newLine();

		bw.newLine();

		bw.write("@Transactional");
		bw.newLine();

		bw.write("public interface " + b + "Ebi extends BaseEbi<" + b + "Model>{");
		bw.newLine();

		bw.newLine();

		bw.write("}");
		bw.newLine();

		bw.flush();
		bw.close();
	}

	// 4.Impl
	private void generatorImpl() throws Exception {
		File f = new File("src/" + dir + "/dao/impl/" + b + "Impl.java");
		if (f.exists()) {
			return;
		}
		f.createNewFile();
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));

		bw.write("package " + pkg + ".dao.impl;");
		bw.newLine();

		bw.newLine();

		bw.write("import org.hibernate.criterion.DetachedCriteria;");
		bw.newLine();

		bw.write("import org.hibernate.criterion.Restrictions;");
		bw.newLine();

		bw.newLine();

		bw.write("import " + pkg + ".dao.dao." + b + "Dao;");
		bw.newLine();

		bw.write("import " + pkg + ".vo." + b + "Model;");
		bw.newLine();

		bw.write("import " + pkg + ".vo." + b + "QueryModel;");
		bw.newLine();

		bw.write("import edu.jyu.erp.util.base.BaseImpl;");
		bw.newLine();

		bw.write("import edu.jyu.erp.util.base.BaseQueryModel;");
		bw.newLine();

		bw.newLine();

		bw.write("public class " + b + "Impl extends BaseImpl<" + b + "Model> implements " + b + "Dao{");
		bw.newLine();

		bw.newLine();

		bw.write("	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){");
		bw.newLine();

		bw.write("		" + b + "QueryModel " + l + "qm = (" + b + "QueryModel)qm;");
		bw.newLine();

		bw.write("		// TODO 添加自定义查询条件");
		bw.newLine();

		bw.write("	}");
		bw.newLine();

		bw.newLine();

		bw.write("}");
		bw.newLine();

		bw.flush();
		bw.close();
	}

	// 3.Dao
	private void generatorDao() throws Exception {
		File f = new File("src/" + dir + "/dao/dao/" + b + "Dao.java");
		if (f.exists()) {
			return;
		}
		f.createNewFile();
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));

		bw.write("package " + pkg + ".dao.dao;");
		bw.newLine();

		bw.newLine();

		bw.write("import " + pkg + ".vo." + b + "Model;");
		bw.newLine();

		bw.write("import edu.jyu.erp.util.base.BaseDao;");
		bw.newLine();

		bw.newLine();

		bw.write("public interface " + b + "Dao extends BaseDao<" + b + "Model> {");
		bw.newLine();

		bw.newLine();

		bw.write("}");
		bw.newLine();

		bw.flush();
		bw.close();
	}

	// 2.Hbm.xml
	private void generatorHbmXml() throws Exception {
		// 1.创建文件
		File f = new File("src/" + dir + "/vo/" + b + "Model.hbm.xml");

		if (f.exists()) {
			return;
		}

		f.createNewFile();
		// 2.IO写入内容
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));

		bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		bw.newLine();

		bw.write("<!DOCTYPE hibernate-mapping PUBLIC");
		bw.newLine();

		bw.write("        '-//Hibernate/Hibernate Mapping DTD 3.0//EN'");
		bw.newLine();

		bw.write("        'http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd'>");
		bw.newLine();

		bw.write("<hibernate-mapping>");
		bw.newLine();

		bw.write("    <class name=\"" + pkg + ".vo." + b + "Model\" table=\"tbl_" + s + "\">");
		bw.newLine();

		bw.write("        <id name=\"uuid\">");
		bw.newLine();

		bw.write("            <generator class=\"native\" />");
		bw.newLine();

		bw.write("        </id>");
		bw.newLine();

		// hibernate的映射配置文件中要对原始模型类中的属性进行配置，反射获取所有字段
		Field[] fds = clazz.getDeclaredFields();
		for (Field fd : fds) {
			// 如果字段的修饰符是private，生成
			if (fd.getModifiers() == Modifier.PRIVATE && !fd.getName().equals("uuid")) {
				// 如果是关联关系不生成,不是关联关系(Long,Integer,Double,String)
				if (fd.getType().equals(String.class) || fd.getType().equals(Long.class)
						|| fd.getType().equals(Integer.class) || fd.getType().equals(Double.class)) {
					if (!fd.getName().endsWith("View")) {
						bw.write("        <property name=\"" + fd.getName() + "\"/>");
						bw.newLine();
					}
				}
			}
		}

		bw.write("    </class>");
		bw.newLine();

		bw.write("</hibernate-mapping>");
		bw.newLine();

		bw.flush();
		bw.close();
	}

	// 1.QueryModel
	private void generatorQueryModel() throws Exception {
		// 1.创建文件
		File f = new File("src/" + dir + "/vo/" + b + "QueryModel.java");

		// 判断：如果该文件存在，终止操作
		if (f.exists()) {
			return;
		}

		f.createNewFile();
		// 2.IO写入内容
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));

		bw.write("package " + pkg + ".vo;");
		bw.newLine();

		bw.newLine();

		bw.write("import edu.jyu.erp.util.base.BaseQueryModel;");
		bw.newLine();

		bw.newLine();

		bw.write("public class " + b + "QueryModel extends " + b + "Model implements BaseQueryModel{");
		bw.newLine();

		bw.write("	// TODO 添加自定义查询条件");
		bw.newLine();

		bw.write("}");
		bw.newLine();

		bw.flush();
		bw.close();
	}

	// 0.创建目录
	private void generatorDirectory() {
		// business/ebi
		// src+//edu.jyu.erp.auth.emp+business/ebi .vo
		File f = new File("src/" + dir + "/business/ebi");
		f.mkdirs();
		// business/ebo
		f = new File("src/" + dir + "/business/ebo");
		f.mkdirs();
		// dao/dao
		f = new File("src/" + dir + "/dao/dao");
		f.mkdirs();
		// dao/impl
		f = new File("src/" + dir + "/dao/impl");
		f.mkdirs();
		// web
		f = new File("src/" + dir + "/web");
		f.mkdirs();
	}

	// -1.数据初始化
	private void dataInit() {
		String className = clazz.getSimpleName(); // EmpModel
		b = className.substring(0, className.length() - 5); // Emp
		String first = b.substring(0, 1); // E
		l = first.toLowerCase(); // e
		s = l + b.substring(1); // emp
		String rootPkg = clazz.getPackage().getName(); // edu.jyu.erp.auth.emp.vo
		pkg = rootPkg.substring(0, rootPkg.length() - 3); // edu.jyu.erp.auth.emp
		dir = pkg.replace(".", "/"); // edu/jyu/erp/auth/emp/vo
	}

	/*
	 * public static void main(String[] args) throws Exception {
	 * //核心工作原理：文件IO+反射 File f = new File("src/EmpAction.java");
	 * f.createNewFile();
	 * 
	 * BufferedWriter bw = new BufferedWriter(new FileWriter(f));
	 * bw.write("public class EmpAction{}"); bw.flush(); bw.close(); }
	 */
}
