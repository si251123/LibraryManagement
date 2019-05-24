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
// 패널1
Panel p1 = new Panel();
// 기본레이아웃을 flowLayout으로 준다.
p1.setLayout(new FlowLayout());
// 패널2
Panel p2 = new Panel();
// 이패널에는 그리드레이아웃을 준다 (가로,세로 , 넓이, 높이)라고 생각하면된다.
p2.setLayout(new GridLayout(2, 2, 1, 5));
// 버튼1
button1 = new Button("로그인");

// 버튼2
button2 = new Button("cancel");

//버튼3 
button3 = new Button("회원가입");

// 패널1에 버튼 추가
p1.add(button1);
p1.add(button2);
p1.add(button3);
// 라벨
label1 = new Label("ID");
label1.setSize(10, 10);
// 패널2에 라벨추가
p2.add(label1);
// 텍스트필드
tex1 = new TextField();
tex1.setSize(10, 10);
// 패널2에 텍스트필드 추가.
p2.add(tex1);

label2 = new Label("PW");
label2.setSize(10, 10);
p2.add(label2);

tex2 = new TextField();
tex2.setSize(10, 10);
p2.add(tex2);

Panel p3 = new Panel();
p3.setLayout(new FlowLayout());
label3 = new Label("로그인");
p3.add(label3);

setTitle("java");
setSize(250, 250);
setVisible(true);
setLayout(new BorderLayout());
//p1즉 버튼들을 아래쪽으로 배치한다. 
add(p1, BorderLayout.SOUTH);
add(p2);
//로그인이란 라벨을 위쪽에 배치한다. 
add(p3, BorderLayout.NORTH);
// 윈도우리스너를 줘서 x누르면 awt창이 꺼지도록 한다.
addWindowListener(new WindowHandler());
}

}
// 윈도우 핸들러 x버튼 클릭시 종료 해주는것.
class WindowHandler extends WindowAdapter {
public void windowClosing(WindowEvent e) {
System.exit(0);
}
}