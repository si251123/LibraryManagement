package test3;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
public class member extends Frame{
	// Ŭ���� ������ ���� ����������� �����ϴ� �κ��Դϴ�..
	Panel p1, p2, p3,  p9, p10, pt,tp1,tp2;
	Label la_name, la_id, la_pass,  la_addr,la_title;
	TextField tf_name, tf_id, tf_pass;
	Button btn1, btn2, btn3;
	Choice job;
	Checkbox cb1, cb2;
	CheckboxGroup cbg;
	TextArea ta;

	// ������ ���� �κ��� �ǰڽ��ϴ�.
	member() {

	// ȭ���ġ�� �����ϰ� �Ǵ� �κ�
	// p1~ p3���� ��ü������ �ϸ鼭 FlowLayout�� ����ؼ� ȭ���ġ
	p1 = new Panel(new FlowLayout(FlowLayout.LEFT));
	p2 = new Panel(new FlowLayout(FlowLayout.LEFT));
	p3 = new Panel(new FlowLayout(FlowLayout.LEFT));

	//��ư �г�
	p9 = new Panel();
	p10 = new Panel();

	//���� �г� ȸ������
	pt = new Panel();
	//table �г�
	tp1 = new Panel();
	tp2 = new Panel();

	// label���� ������ �̸��� ���� Label��ü���� ����
	la_name = new Label("�� ��");
	la_id = new Label("I D");
	la_pass = new Label("��� ��ȣ");
	la_title = new Label("ȸ�� ����");

	// ���� 20�̶�� ���̸� ����ä�� TextField ��ü�� ����
	tf_name = new TextField(20);
	tf_id = new TextField(20);
	tf_pass = new TextField(20);
	job = new Choice();
	job.add("�����������ϼ���");
	job.add("���α׷���");
	job.add("����");
	job.add("ȸ���");
	job.add("������");
	job.add("�ڿ���");

	// ��ư 
	btn1 = new Button("���̵��ߺ�üũ");
	btn2 = new Button();
	btn2.setLabel("���");
	btn3 = new Button("�������");



	// pt�κп� label2�� ����
	pt.add(la_title);

	//�� �󺧵��� ���� �гο� �־��ش�. 
	//p1.add(la_name);
	p1.add(tf_name);
	//p2.add(la_id);
	p2.add(tf_id);
	//p3.add(la_pass);
	p3.add(tf_pass);
	//p4.add(la_pass2);


	tp1.setLayout(new GridLayout(8,1));
	tp1.add(la_name);
	tp1.add(la_id);
	tp1.add(la_pass);

	tp2.setLayout(new GridLayout(8,1));
	tp2.add(p1);
	tp2.add(p2);
	tp2.add(p3);

	p9.add(btn2);
	p9.add(btn3);

	add("North",pt);
	add("West",tp1);
	add("Center",tp2);
	add("South",p9);
	// �������� ������ setting �κ��� �Ǳ���..
	setSize(400, 400);
	// �������� �������� �Ⱥ��̰� �������� setting�ϴ� �κ�
	setVisible(false);
	// ������â ���� �޼ҵ�
	addWindowListener(new WindowHandler());



	}


	class WindowHandler extends WindowAdapter {
	public void windowClosing(WindowEvent e) {
	System.exit(0);
	}
	}
	} 