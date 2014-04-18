package net.glamenvseptzen.quickstart;

import redis.clients.jedis.Jedis;

public class Redis {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String databaseName = " eiZrxDYAgZRYcJuTRljT";
			String host = "redis.duapp.com";
			String portStr = "80";
			int port = Integer.parseInt(portStr);
			String username = " urtgxzMPVigNEtOQF7yzg7C9";
			String password = "1e0jDqkZ7fUwNgFD5LzwPY4YAQURFGYM";
			Jedis jedis = new Jedis(host, port);
			jedis.connect();
			jedis.auth(username + "-" + password + "-" + databaseName);
			jedis.flushDB();
			jedis.set("name", "bae");
			System.out.println("name | " + jedis.get("name"));
		} catch (Exception e) {

		}

	}

}
