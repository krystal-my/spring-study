package com.enjoy.Tools.jdk.proxy2;

/**
 * @author ������ bullock
 * ��̬����ί���� --- ʵ�֣� ʵ�ֽӿ� Person�� ����̬���ɵĴ��������
 */
public class SoftwareEngineer implements Person {

    public SoftwareEngineer() {

    }
    public SoftwareEngineer(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void goWorking(String name, String dst) {
        System.out.println("name =" + name + " �� ȥ " + dst + " ����");
    }
}
