package myth;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @description:
 * @author: yuang gang
 * @create: 2019-03-05 15:21
 **/
public class Curator {

  public static void main(String[] args) {
    String connectionInfo = "18.179.178.195:2181";
    RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

    //静态工厂
    CuratorFramework client1 =
        CuratorFrameworkFactory.newClient(
            connectionInfo, 5000, 3000, retryPolicy);

    //fluent风格,
    CuratorFramework client2 =
        CuratorFrameworkFactory.builder()
            .connectString(connectionInfo)
            .sessionTimeoutMs(5000)
            .connectionTimeoutMs(3000)
            .retryPolicy(retryPolicy)
            .namespace("zk-demo")
            .build();
    client1.start();
    client2.start();

  }
}
