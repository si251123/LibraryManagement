package test3;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class excute extends Frame implements ActionListener {
// jdbc ����
private String url = "jdbc:mysql://localhost/?useSSL=false&user=root&password=free1123";// user���̺��� �����ϸ�

Connection con;
Statement stmt;
ResultSet rs;
String sql;

//
String id;
String pass;
String name;

// �α��ΰ� �ɹ� Ŭ������ �����´�.
member sumit = new member(); // false
login log = new login(); // true
LoginSub logSub = new LoginSub(); // false
Data d = new Data();

// ���̾�α� , ��ư, ��,
Button ok;
Label msg;
Dialog info;

excute() {

try {
// �α��� ��ư�� Ŭ�� �Ǿ����ÿ� Oracle����̹��� ����Ѵ�.
	Class.forName("com.mysql.jdbc.Driver");
	String url = "jdbc:mysql://localhost/?useSSL=false&user=root&password=free1123";
	con = DriverManager.getConnection(url);
	stmt = con.createStatement();
} catch (Exception b) {
System.out.println("db�������");
}

// ȸ������ ��Ϲ�ư ������
sumit.btn2.addActionListener(this);// ��ư�� �����ʸ� �ܴ�.
// ȸ������ ��� ��ư
sumit.btn3.addActionListener(this);
// ȸ������ ��ư
log.button3.addActionListener(this);
// �α��� ��ư
log.button1.addActionListener(this);
// LoginSub�� ������ư
logSub.button1.addActionListener(this);
// LoginSub�� Ż���ư
logSub.button2.addActionListener(this);
// LoginSub�� �α׾ƿ���ư
logSub.button3.addActionListener(this);

}

@Override
public void actionPerformed(ActionEvent e) {
// TODO Auto-generated method stub
// �ش��ϴ� ��ư�� ��������
Object obj = e.getSource();
if (obj.equals(log.button3)) {
// �α���â�� ȸ������ ��ư
// memberŬ������ ����.
sumit.setVisible(true);
} else if (obj.equals(log.button1)) {
// �α���â�� �α��� ��ư
loginCheck();
// �ش� ������ ȯ�� �޽���
logSub.label1.setText(name + "�� �α��� �Ͽ����ϴ�.");
// title ����
logSub.setTitle(name + "�� ����â");
// �α���â�� ��� �Ⱥ��̵��� �Ѵ�.
log.setVisible(false);
} else if (obj.equals(logSub.button1)) {
// �˻�
showUpdate();
} else if (obj.equals(logSub.button2)) {
// Ż��(����)
selectDelete();
} else if (obj.equals(logSub.button3)) {
// logSub ȭ���� �����.
logSub.setVisible(false);
// �α���â�� ����.
log.setVisible(true);

// Ŭ����
clearText();
// �α���â�� �ؽ�Ʈ�� �ʱ�ȭ

} else if (obj.equals(sumit.btn2)) {
// ȸ������ â�� ��ư ���� ��� �ϰ�� , ������ ��� �� �ش��ϴ� �޼ҵ带 ���� ���ش�.
if (sumit.btn2.getLabel().equals("���")) {
// ����
selectInsert();
sumit.setVisible(false);
} else if (sumit.btn2.getLabel().equals("����")) {
// ����
selectUpdate();
}
} else if (obj.equals(sumit.btn3)) {
sumit.setVisible(false);
}
}

private void clearText() {
// TODO Auto-generated method stub
log.tex1.setText("");
log.tex2.setText("");
sumit.tf_id.setText("");
sumit.tf_pass.setText("");
sumit.tf_name.setText("");
logSub.tex1.setText("");
}

// ����
private void selectUpdate() {
// TODO Auto-generated method stub
Data d = new Data();
d.id = sumit.tf_id.getText().trim();
d.pw = sumit.tf_pass.getText().trim();
d.name = sumit.tf_name.getText().trim();

String sql = "update users set id='" + d.id + "',pw='" + d.pw
+ "' where name='" + d.name + "'";
try {
int rss = stmt.executeUpdate(sql);
System.out.println(rss + "������Ʈ");

logSub.tex1.setText("������Ʈ �Ϸ� ^^");

sumit.setVisible(false);
} catch (Exception e) {
e.printStackTrace(System.out);
}
}

// ����
private void selectInsert() {
// TODO Auto-generated method stub
Data d = new Data();
d.id = sumit.tf_id.getText().trim();
d.pw = sumit.tf_pass.getText().trim();
d.name = sumit.tf_name.getText().trim();
String sql = "insert into users value('" + d.id + "','" + d.pw + "','"
+ d.name + "')";

try {

int rss = stmt.executeUpdate(sql);

System.out.println(rss + "����");
logSub.tex1.setText("���� �Ϸ� ^^");
} catch (Exception e) {
e.printStackTrace(System.out);
}
}

// �˻�
void showUpdate() {
// TODO Auto-generated method stub
// String sql = "select * from xe where name='"+name+"'";
sumit.btn3.setLabel("�������");
sumit.btn2.setLabel("����");
// ������ �̸��� ������ �´�.
String name = logSub.tex1.getText();
String id = sumit.tf_id.getText();
String pw = sumit.tf_pass.getText();
String sql = "select * from xe where name='" + name + "'";
System.out.println(sql);
try {
rs = stmt.executeQuery(sql);
if (rs.next()) {
sumit.tf_id.setText(rs.getString("id"));
sumit.tf_pass.setText(rs.getString("pw"));
sumit.tf_name.setText(rs.getString("name"));
System.out.println(rs + "����");

logSub.tex1.setText("���������� ���� �Ϸ�� ^^");
}
} catch (Exception e) {
logSub.tex1.setText("���� �� ���� �߻� T.T");
System.out.println("���� �� ���� �߻� : " + e);
}
// ������ ���̵�� ��ġ�� ���ϵ���
sumit.tf_name.setEnabled(false);
sumit.setVisible(true);
}

// ����
void selectDelete() {
// TODO Auto-generated method stub

String name = logSub.tex1.getText();
String sql = "delete from users where name='" + name + "'";
System.out.println(sql);
try {

int rss = stmt.executeUpdate(sql);

System.out.println(rss + "����");

// Ŀ�ؼ����κ��� ������ sql���� �����Ű�� ���� statement ��ü�� ���´�.

logSub.tex1.setText("���� �Ϸ� ^^");

} catch (Exception e) {
logSub.tex1.setText("������ ���� �߻� T.T?");
System.out.println("������ ���� �߻� : " + e);
}
}// deleteDB

// �α��� ü ũ
void loginCheck() {
// �α���â�� �ؽ�Ʈ�ʵ忡 ������ ��������
id = log.tex1.getText().trim();
pass = log.tex2.getText().trim();

// SELECT ������ �ۼ��Ѵ�. �ش��ϴ� ���̵��� �н����带 �˻��Ѵ�.
String query = "SELECT pw,name FROM xe where id='" + id + "'";

System.out.println(query);
try {
// executeQuery() �޼���� SELECT���� �����Ű�� ����� ResultSet ��ü�� �޴´�.
ResultSet rs = stmt.executeQuery(query);

// ���ڵ尡 �ִ��� �˻�
if (rs.next()) {
//
name = rs.getString("name");

// �ؽ�Ʈ�ʵ忡 ������ �����ͺ��̽��� �ִ� �н����� ���� ���Ѵ�.
if (pass.equals(rs.getString("pw"))) {

System.out.println("�¾Ҿ�");
// ������ �α��μ��긦 ����ش�.
logSub.setVisible(true);

}

}

} catch (Exception b) {
b.printStackTrace();
}
}

// ����
public static void main(String[] args) {
new excute();

}
} 