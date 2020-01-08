package com.enjoy.Tools.jdk.proxy2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;


/**
 * @author ������ bullock
 */
public class JdkDynamicProxyTest {

    public static void main(String[] args) throws Exception {        // �򿪱���JDK��̬�������ɵ����ļ�
        /**
         * ��һ�ַ���: ͨ�� Proxy.newProxyInstance ���� ��ȡ�������
         */
        System.out.println("-------------------��һ�ִ��������෽��--------------");
        //����һ��ʵ��������������Ǳ�����Ķ���ί����
        Person person = new SoftwareEngineer("Vincent");
        //����һ����������������InvocationHandler,ÿһ�������඼��һ�������� InvocationHandler���������������ô��ݽ�ȥ
        InvocationHandler Handler = new PersonInvocationHandler<>(person);
        //����һ�� ������� personProxy ������ person�������Ĵ�������ÿ��ִ�з������ᱻ�滻ִ��Invocation�ӿ��е�invoke����
        Person personProxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class<?>[]{Person.class}, Handler);
        /** ��������Ϣ */
        System.out.println("package = " + personProxy.getClass().getPackage() +
                " SimpleName = " + personProxy.getClass().getSimpleName() + " name ="
                + personProxy.getClass().getName() + " CanonicalName = " + ""
                + personProxy.getClass().getCanonicalName() + " ʵ�ֵĽӿ� Interfaces = " +
                Arrays.toString(personProxy.getClass().getInterfaces()) + " superClass = "
                + personProxy.getClass().getSuperclass() + " methods =" + Arrays.toString(personProxy.getClass().getMethods()));
        // ͨ�� ������ ִ�� ί����Ĵ����߼�
        personProxy.goWorking(personProxy.getName(), "����");

    }

}
