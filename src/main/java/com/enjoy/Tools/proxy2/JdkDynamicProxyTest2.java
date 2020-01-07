package com.enjoy.Tools.proxy2;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author ������ bullock
 */
public class JdkDynamicProxyTest2 {

    public static void main(String[] args) throws Exception {        // �򿪱���JDK��̬�������ɵ����ļ�

        System.out.println("-------------------�ڶ��ִ��������෽��--------------");

        /**
         *       ��̬���������
         *      1�� ����һ����������������� InvocationHandler���Լ���ʵ��ί����ʵ��
         *      2��Proxy���getProxyClass��̬��������һ����̬������stuProxyClass������̳�Proxy��
         *      ʵ�� Person.java�ӿڣ�JDK��̬������ص��Ǵ��������̳�Proxy��
         *      3��ͨ�������� proxyClass ������Ĵ�InvocationHandler �ӿڵĹ��캯�� ProxyConstructor
         *      4��ͨ�� ���캯��ʵ�� ProxyConstructor ʵ����һ��������󣬲���  InvocationHandler �ӿ�ʵ�����ݸ������ࡣ
         */
        // 1������ InvocationHandler ʵ�������ô����Ŀ�������
        Person persontwo = new SoftwareEngineer("Vincent");
        InvocationHandler Handlertwo = new PersonInvocationHandler<>(persontwo);


        // 2 ����������,��һ���ֽ����ļ�, �� proxyClass �����������ܿ��� ���̳�Proxy �࣬ʵ��Person�ӿ�
        Class<?> proxyClass = Proxy.getProxyClass(Person.class.getClassLoader(), new Class<?>[]{Person.class});
        /** ��������Ϣ */
        System.out.println("package = " + proxyClass.getPackage() + " SimpleName = "
                + proxyClass.getSimpleName() + " name =" + proxyClass.getName() + " CanonicalName = " +
                "" + proxyClass.getCanonicalName() + " ʵ�ֵĽӿ� Interfaces = "
                + Arrays.toString(proxyClass.getInterfaces()) + " superClass = "
                + proxyClass.getSuperclass() + " methods =" + Arrays.toString(proxyClass.getMethods()));
        // 3��  ͨ�� proxyClass ��� һ������InvocationHandler�����Ĺ�����constructor
        Constructor<?> ProxyConstructor = proxyClass.getConstructor(InvocationHandler.class);
        // 4��ͨ������������һ��  ��̬������ ʵ��
        Person stuProxy = (Person) ProxyConstructor.newInstance(Handlertwo);
        /** ������ɵ����Ƿ��Ǵ����� */
        System.out.println("stuProxy isProxy " + Proxy.isProxyClass(stuProxy.getClass()));
        /** ��ȡ ����������� InvocationHandler ���ĸ�*/
        InvocationHandler handlerObject = Proxy.getInvocationHandler(stuProxy);
        System.out.println(handlerObject.getClass().getName());
        stuProxy.goWorking(stuProxy.getName(), "����");
        // ���������
        saveClass("$PersonProxy0", proxyClass.getInterfaces(), "D:/jvm/");
    }

    /**
     * ���ɴ����� class �����ֵ��ļ���
     *
     * @param className  ���ɵĴ���������
     * @param interfaces ��������Ҫʵ�ֵĽӿ�
     * @param pathdir    �����ౣ���Ŀ¼·��,��Ŀ¼�ָ�����β
     */
    public static void saveClass(String className, Class<?>[] interfaces, String pathdir) {        /**
     * ��һ�������� ������ �� ��
     * �ڶ��������� ��������Ҫʵ�ֵĽӿ�
     */
        byte[] classFile = ProxyGenerator.generateProxyClass(className, interfaces);        /**
         * ���Ŀ¼�����ھ��½�������Ŀ¼
         */
        Path path1 = Paths.get(pathdir);
        if (!path1.toFile().exists()) {
            path1.toFile().mkdirs();
        }
        String path = pathdir + className + ".class";
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(classFile);
            fos.flush();
            System.out.println("������class�ļ�д��ɹ�");
        } catch (Exception e) {
            System.out.println("д�ļ�����");
        }
    }

    /**
     * ���ñ���Java��̬�������ɵ����ļ���
     *
     * @throws Exception
     */
    public static void saveGeneratedJdkProxyFiles() throws Exception {
        Field field = System.class.getDeclaredField("props");
        field.setAccessible(true);
        Properties props = (Properties) field.get(null);
        props.put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
    }
}
