package com.enjoy.Tools.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author ������ bullock
 */
public class MeiPo implements InvocationHandler {

    //����������������Ϊһ����Ա������������
    private Person target;

    //��ȡ�������˵ĸ�������
    public Object getInstance(Person target) throws Exception{
        this.target = target;
        Class clazz = target.getClass();
        System.out.println("����������class��:"+clazz);
        System.out.println(this);
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("����ý�ţ�" + "�ø����Ҹ����Բ���");
        System.out.println("��ʼ���к�ѡ...");
        System.out.println("------------");

        //���õ�ʱ��
        method.invoke(this.target, args);
        System.out.println("------------");
        System.out.println("������ʵĻ�����׼������");

        return null;
    }
}
