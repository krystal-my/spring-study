package com.enjoy.Tools.cglib;

public class TestGglibProxy {
	
	public static void main(String[] args) {
		
		//JDK�Ķ�̬������ͨ���ӿ�������ǿ��ת����
		//�����Ժ�Ĵ�����󣬿���ǿ��ת��Ϊ�ӿ�
		
		
		//CGLib�Ķ�̬������ͨ������һ���������������࣬Ȼ����д����ķ���
		//�����Ժ�Ķ��󣬿���ǿ��ת��Ϊ���������Ҳ�������Լ�д���ࣩ
		//�������ø�ֵ������
		
		
		try {
			LiuZ obj = (LiuZ)new GPMeipo().getInstance(LiuZ.class);
			obj.findLove();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
