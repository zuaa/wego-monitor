package net.glamenvseptzen.quickstart;

import java.io.PrintWriter;

import redis.clients.jedis.Jedis;

public class Redis {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			/***** 1. 填写数据库相关信息(请查找数据库详情页) *****/
			String databaseName = " eiZrxDYAgZRYcJuTRljT";
			String host = "redis.duapp.com";
			String portStr = "80";
			int port = Integer.parseInt(portStr);
			String username = " urtgxzMPVigNEtOQF7yzg7C9";// 用户名(api key);
			String password = "1e0jDqkZ7fUwNgFD5LzwPY4YAQURFGYM";// 密码(secret
																	// key)

			/****** 2. 接着连接并选择数据库名为databaseName的服务器 ******/
			Jedis jedis = new Jedis(host, port);
			jedis.connect();
			jedis.auth(username + "-" + password + "-" + databaseName);
			/* 至此连接已完全建立，就可对当前数据库进行相应的操作了 */
			/* 3. 接下来就可以使用redis数据库语句进行数据库操作,详细操作方法请参考java-redis官方文档 */

			// 删除所有redis数据库中的key-value
			jedis.flushDB();
			// 简单的key-value设置
			jedis.set("name", "bae");
			System.out.println("name | " + jedis.get("name"));
		} catch (Exception e) {

		}

	}

}
