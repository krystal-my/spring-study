package com.enjoy.Tools.jdk.proxy2;

/**
 * @author ������ bullock
 */
public class MonitorUtil {

    private static ThreadLocal<Long> tl = new ThreadLocal<>();

    public static void start() {
        tl.set(System.currentTimeMillis());
    }

    /**
     * ����ʱ��ӡ��ʱ
     *
     * @param methodName ������
     */
    public static void finish(String methodName) {
        long finishTime = System.currentTimeMillis();
        System.out.println(methodName + "����ִ�к�ʱ" + (finishTime - tl.get()) + "ms");
    }
}
