package com.enjoy.Tools.jdk.proxy2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author ������ bullock
 * PersonInvocationHandler �� ʵ��InvocationHandler�ӿڣ�������г���һ�����������(ί����)��ʵ��target�������JDK Proxy��ص�
 * * InvocationHandler �ӿ�����һ��invoke��������һ������ʵ���ķ���������ʱ���������������벢�ַ��� InvocationHandler�ӿڵ�invoke����ִ��
 */
public class PersonInvocationHandler<T> implements InvocationHandler {

     /**
      * ������������ã�
      *invoke ��������method
      *��Ҫʹ����� ���������
    */
    T target;

    public PersonInvocationHandler(T target) {
        this.target = target;
    }

    /**
     *
     * @param proxy  ����̬���ɵ� ��̬���� ����ʵ��
     * @param method ��������ί����Ľӿڷ����������ɵĴ�����ʵ�����õĽӿڷ�����һ�µģ�����Ӧ��Method ʵ��
     * @param args   ������ýӿڷ�����Ӧ��Object�������飬����ӿ����޲Σ���Ϊnull�� ����ԭʼ�������ͷ��ص����İ�װ���͡�
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {        /**
     * ��ת������Ŀ�����֮ǰ������ִ��һЩ���ܴ���
     */
        System.out.println("����̬������ص�ִ��, ������ proxyClass =" + proxy.getClass() + " ������: " + method.getName() +
                "����. �����������ͣ�" + method.getReturnType()
                + " �ӿڷ����������: " + (args == null ? "null" : Arrays.toString(args)));        /**
         * ��������в����ⷽ��,����÷�����ʱ
         */
        MonitorUtil.start();
        Thread.sleep(1);
        /** ���ñ�����������ʵ����*/
        Object result = method.invoke(target, args);
        MonitorUtil.finish(method.getName());
        return result;
    }
}
