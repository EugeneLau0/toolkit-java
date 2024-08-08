package org.away.concurrent;

import java.util.HashMap;
import java.util.Map;

/**
 * 线程复用
 */
public class ThreadReuse {

    private static final ThreadLocal<Integer> currentUser = ThreadLocal.withInitial(() -> null);


    public Map<String, Object> exec(Integer userId) {
        // 设置用户信息之前先查询一次ThreadLocal中的用户信息
        String before  = Thread.currentThread().getName() + ":" + currentUser.get();
        // 设置用户信息到ThreadLocal
        currentUser.set(userId);
        //设置用户信息之后再查询一次ThreadLocal中的用户信息
        String after  = Thread.currentThread().getName() + ":" + currentUser.get();
        // 汇总输出两次查询结果
        Map<String, Object> result = new HashMap<>();
        result.put("before", before);
        result.put("after", after);
        return result;
    }

}
