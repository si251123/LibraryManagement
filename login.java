package test3;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.TextArea;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
public class login extends Frame {

static Label label1, label2, label3;
TextField tex1, tex2;
    Button button1, button2, button3;
public String id1, pw1;


login() {
// �г�1
Panel p1 = new Panel();
// �⺻���̾ƿ��� flowLayout���� �ش�.
p1.setLayout(new FlowLayout());
// �г�2
Panel p2 = new Panel();
// ���гο��� �׸��巹�̾ƿ��� �ش� (����,���� , ����, ����)��� �����ϸ�ȴ�.
p2.setLayout(new GridLayout(2, 2, 1, 5));
// ��ư1
button1 = new Button("�α���");

// ��ư2
button2 = new Button("cancel");

//��ư3 
button3 = new Button("ȸ������");

// �г�1�� ��ư �߰�
p1.add(button1);
p1.add(button2);
p1.add(button3);
// ��
label1 = new Label("ID");
label1.setSize(10, 10);
// �г�2�� ���߰�
p2.add(label1);
// �ؽ�Ʈ�ʵ�
tex1 = new TextField();
tex1.setSize(10, 10);
// �г�2�� �ؽ�Ʈ�ʵ� �߰�.
p2.add(tex1);

label2 = new Label("PW");
label2.setSize(10, 10);
p2.add(label2);

tex2 = new TextField();
tex2.setSize(10, 10);
p2.add(tex2);

Panel p3 = new Panel();
p3.setLayout(new FlowLayout());
label3 = new Label("�α���");
p3.add(label3);

setTitle("java");
setSize(250, 250);
setVisible(true);
setLayout(new BorderLayout());
//p1�� ��ư���� �Ʒ������� ��ġ�Ѵ�. 
add(p1, BorderLayout.SOUTH);
add(p2);
//�α����̶� ���� ���ʿ� ��ġ�Ѵ�. 
add(p3, BorderLayout.NORTH);
// �����츮���ʸ� �༭ x������ awtâ�� �������� �Ѵ�.
addWindowListener(new WindowHandler());
}

}
// ������ �ڵ鷯 x��ư Ŭ���� ���� ���ִ°�.
class WindowHandler extends WindowAdapter {
public void windowClosing(WindowEvent e) {
System.exit(0);
}
}