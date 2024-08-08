package org.away.concurrent;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程复用 test
 */
public class ThreadReuseTest {

    @Test
    public void exec() {
        // 创建一个固定线程数为1的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(1);

        List<Object> result = new ArrayList<>(2);

        for (int i = 0; i < 2; i++) {
            threadPool.execute(() -> {
                ThreadReuse threadReuse = new ThreadReuse();
                Map<String, Object> res = threadReuse.exec(1);
                result.add(res);
            });
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        result.forEach(System.out::println);
        threadPool.shutdown();
    }
}