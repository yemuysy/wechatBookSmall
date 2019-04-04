package cn.zxhysy.booksmall.utils.mybaitsGenerator;

public class GeneratorDisplay {

	/** 其他项目使用 */
	private void generator() throws Exception{

//		List<String> warnings = new ArrayList<String>();
//		boolean overwrite = true;
//		//指定 逆向工程配置文件
//		// 这里generatorConfig.xml在根目录下
//		File configFile = new File("generatorConfig.xml");
//		ConfigurationParser cp = new ConfigurationParser(warnings);
//		Configuration config = cp.parseConfiguration(configFile);
//		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
//		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
//				callback, warnings);
//		myBatisGenerator.generate(null);

	}

	public static void main(String[] args) throws Exception {
		try {
			GeneratorDisplay generatorSqlmap = new GeneratorDisplay();
			generatorSqlmap.generator();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
