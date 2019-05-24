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
// jdbc 설정
private String url = "jdbc:mysql://localhost/?useSSL=false&user=root&password=free1123";// user테이블을 수정하면

Connection con;
Statement stmt;
ResultSet rs;
String sql;

//
String id;
String pass;
String name;

// 로그인과 맴버 클래스를 가져온다.
member sumit = new member(); // false
login log = new login(); // true
LoginSub logSub = new LoginSub(); // false
Data d = new Data();

// 다이얼로그 , 버튼, 라벨,
Button ok;
Label msg;
Dialog info;

excute() {

try {
// 로그인 버튼이 클릭 되었을시에 Oracle드라이버를 등록한다.
	Class.forName("com.mysql.jdbc.Driver");
	String url = "jdbc:mysql://localhost/?useSSL=false&user=root&password=free1123";
	con = DriverManager.getConnection(url);
	stmt = con.createStatement();
} catch (Exception b) {
System.out.println("db연결실패");
}

// 회원가입 등록버튼 리스너
sumit.btn2.addActionListener(this);// 버튼에 리스너를 단다.
// 회원가입 취소 버튼
sumit.btn3.addActionListener(this);
// 회원가입 버튼
log.button3.addActionListener(this);
// 로그인 버튼
log.button1.addActionListener(this);
// LoginSub의 수정버튼
logSub.button1.addActionListener(this);
// LoginSub의 탈퇴버튼
logSub.button2.addActionListener(this);
// LoginSub의 로그아웃버튼
logSub.button3.addActionListener(this);

}

@Override
public void actionPerformed(ActionEvent e) {
// TODO Auto-generated method stub
// 해당하는 버튼값 가져오기
Object obj = e.getSource();
if (obj.equals(log.button3)) {
// 로그인창의 회원가입 버튼
// member클래스를 띄운다.
sumit.setVisible(true);
} else if (obj.equals(log.button1)) {
// 로그인창의 로그인 버튼
loginCheck();
// 해당 접속자 환영 메시지
logSub.label1.setText(name + "님 로그인 하였습니다.");
// title 변경
logSub.setTitle(name + "님 접속창");
// 로그인창은 잠시 안보이도록 한다.
log.setVisible(false);
} else if (obj.equals(logSub.button1)) {
// 검색
showUpdate();
} else if (obj.equals(logSub.button2)) {
// 탈퇴(삭제)
selectDelete();
} else if (obj.equals(logSub.button3)) {
// logSub 화면을 감춘다.
logSub.setVisible(false);
// 로그인창을 띄운다.
log.setVisible(true);

// 클리어
clearText();
// 로그인창의 텍스트를 초기화

} else if (obj.equals(sumit.btn2)) {
// 회원가입 창의 버튼 라벨이 등록 일경우 , 수정일 경우 각 해당하는 메소드를 실행 해준다.
if (sumit.btn2.getLabel().equals("등록")) {
// 삽입
selectInsert();
sumit.setVisible(false);
} else if (sumit.btn2.getLabel().equals("수정")) {
// 수정
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

// 수정
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
System.out.println(rss + "업데이트");

logSub.tex1.setText("업데이트 완료 ^^");

sumit.setVisible(false);
} catch (Exception e) {
e.printStackTrace(System.out);
}
}

// 삽입
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

System.out.println(rss + "삽입");
logSub.tex1.setText("삽입 완료 ^^");
} catch (Exception e) {
e.printStackTrace(System.out);
}
}

// 검색
void showUpdate() {
// TODO Auto-generated method stub
// String sql = "select * from xe where name='"+name+"'";
sumit.btn3.setLabel("수정취소");
sumit.btn2.setLabel("수정");
// 수정할 이름을 가지고 온다.
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
System.out.println(rs + "삭제");

logSub.tex1.setText("정상적으로 수정 완료됨 ^^");
}
} catch (Exception e) {
logSub.tex1.setText("수정 중 예외 발생 T.T");
System.out.println("수정 중 예외 발생 : " + e);
}
// 수정시 아이디는 고치지 못하도록
sumit.tf_name.setEnabled(false);
sumit.setVisible(true);
}

// 삭제
void selectDelete() {
// TODO Auto-generated method stub

String name = logSub.tex1.getText();
String sql = "delete from users where name='" + name + "'";
System.out.println(sql);
try {

int rss = stmt.executeUpdate(sql);

System.out.println(rss + "삭제");

// 커넥션으로부터 실제로 sql쿼리 실행시키기 위한 statement 객체를 얻어온다.

logSub.tex1.setText("삭제 완료 ^^");

} catch (Exception e) {
logSub.tex1.setText("삭제중 예외 발생 T.T?");
System.out.println("삭제중 예외 발생 : " + e);
}
}// deleteDB

// 로그인 체 크
void loginCheck() {
// 로그인창의 텍스트필드에 쓴값을 가져오기
id = log.tex1.getText().trim();
pass = log.tex2.getText().trim();

// SELECT 쿼리를 작성한다. 해당하는 아이디값의 패스워드를 검색한다.
String query = "SELECT pw,name FROM xe where id='" + id + "'";

System.out.println(query);
try {
// executeQuery() 메서드로 SELECT문의 실행시키고 결과로 ResultSet 객체를 받는다.
ResultSet rs = stmt.executeQuery(query);

// 레코드가 있는지 검사
if (rs.next()) {
//
name = rs.getString("name");

// 텍스트필드에 쓴값과 데이터베이스에 있는 패스워드 값을 비교한다.
if (pass.equals(rs.getString("pw"))) {

System.out.println("맞았어");
// 맞으면 로그인서브를 띄워준다.
logSub.setVisible(true);

}

}

} catch (Exception b) {
b.printStackTrace();
}
}

// 실행
public static void main(String[] args) {
new excute();

}
} 