package net.glamenvseptzen.quickstart;

import java.io.PrintWriter;

import redis.clients.jedis.Jedis;

public class Redis {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			/***** 1. ��д���ݿ������Ϣ(��������ݿ�����ҳ) *****/
			String databaseName = " eiZrxDYAgZRYcJuTRljT";
			String host = "redis.duapp.com";
			String portStr = "80";
			int port = Integer.parseInt(portStr);
			String username = " urtgxzMPVigNEtOQF7yzg7C9";// �û���(api key);
			String password = "1e0jDqkZ7fUwNgFD5LzwPY4YAQURFGYM";// ����(secret
																	// key)

			/****** 2. �������Ӳ�ѡ�����ݿ���ΪdatabaseName�ķ����� ******/
			Jedis jedis = new Jedis(host, port);
			jedis.connect();
			jedis.auth(username + "-" + password + "-" + databaseName);
			/* ������������ȫ�������ͿɶԵ�ǰ���ݿ������Ӧ�Ĳ����� */
			/* 3. �������Ϳ���ʹ��redis���ݿ����������ݿ����,��ϸ����������ο�java-redis�ٷ��ĵ� */

			// ɾ������redis���ݿ��е�key-value
			jedis.flushDB();
			// �򵥵�key-value����
			jedis.set("name", "bae");
			System.out.println("name | " + jedis.get("name"));
		} catch (Exception e) {

		}

	}

}
