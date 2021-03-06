package cn.cerc.jdb.oss;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import cn.cerc.jdb.core.StubHandle;

public class OssQueryTest {
	// private static final Logger log = Logger.getLogger(OssQueryTest.class);
	private OssQuery ds;
	private StubHandle handle;

	@Before
	public void setUp() {
		handle = new StubHandle();
		ds = new OssQuery(handle);
	}

	/**
	 * 保存文件/覆盖文件
	 * 
	 */
	@Test
	@Ignore
	public void saveFile() {
		ds.setOssMode(OssMode.create);
		ds.add("select * from %s", "id_00001.txt");
		ds.open();
		System.out.println(ds.getActive());
		ds.append();
		ds.setField("num", ds.getInt("num") + 1);
		ds.save();
		System.out.println(ds);
	}

	/**
	 * 删除文件
	 * 
	 */
	@Test
	@Ignore
	public void deleteFile() {
		ds.add("select * from %s", "id_00001.txt");
		ds.open();
		ds.remove();
	}

}
