import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginFrame extends JFrame {
    JPanel basePanel = new JPanel(new BorderLayout());
    JPanel centerPanel = new JPanel(new BorderLayout());
    JPanel westPanel = new JPanel();
    JPanel eastPanel = new JPanel();
    JPanel southPanel = new JPanel();

    /* Label */
    JLabel idL = new JLabel("아이디");
    JLabel pwL = new JLabel("비밀번호");

    /* TextField */
    JTextField id = new JTextField();
    JPasswordField pw = new JPasswordField();

    /* Button */
    JButton loginBtn = new JButton("로그인");
    JButton exitBtn = new JButton("프로그램 종료");

    public LoginFrame() {
        setTitle("로그인");
        JPanel panel = new JPanel();

        /* Panel 크기 작업 */
        centerPanel.setPreferredSize(new Dimension(260, 80));
        westPanel.setPreferredSize(new Dimension(210, 75));
        eastPanel.setPreferredSize(new Dimension(90, 75));
        southPanel.setPreferredSize(new Dimension(290, 40));

        /* Label 크기 작업 */
        idL.setPreferredSize(new Dimension(50, 30));
        pwL.setPreferredSize(new Dimension(50, 30));

        /* TextField 크기 작업 */
        id.setPreferredSize(new Dimension(140, 30));
        pw.setPreferredSize(new Dimension(140, 30));

        /* Button 크기 작업 */
        loginBtn.setPreferredSize(new Dimension(75, 63));
        exitBtn.setPreferredSize(new Dimension(135, 25));

        /* Panel 추가 작업 */
        setContentPane(basePanel);	//panel을 기본 컨테이너로 설정

        basePanel.add(centerPanel, BorderLayout.CENTER);
        basePanel.add(southPanel, BorderLayout.SOUTH);
        centerPanel.add(westPanel, BorderLayout.WEST);
        centerPanel.add(eastPanel, BorderLayout.EAST);

        westPanel.setLayout(new FlowLayout());
        eastPanel.setLayout(new FlowLayout());
        southPanel.setLayout(new FlowLayout());

        /* westPanel 컴포넌트 */
        westPanel.add(idL);
        westPanel.add(id);
        westPanel.add(pwL);
        westPanel.add(pw);

        /* eastPanel 컴포넌트 */
        eastPanel.add(loginBtn);

        /* southPanel 컴포넌트 */
        southPanel.add(exitBtn);

        setSize(310, 150);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (authenticate(id.getText(), new String(pw.getPassword()))) {
                        JOptionPane.showMessageDialog(LoginFrame.this, "로그인 성공!");
                        System.out.println("로그인 성공");
                        SwingUtilities.invokeLater(new MainFrame());
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(LoginFrame.this, "로그인 실패: 아이디 또는 비밀번호가 잘못되었습니다.");
                        System.out.println("로그인 실패");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("프로그램 종료");
                    System.exit(0);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        add(panel);
    }

    private boolean authenticate(String account, String password) throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/spec_check", "spec_check", "1234");
        PreparedStatement ps = connection.prepareStatement("SELECT password FROM member WHERE account = ?");
        ps.setString(1, account);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String hashedPassword = rs.getString("password");
            return BCrypt.checkpw(password, hashedPassword);
        }

        return false;
    }
}