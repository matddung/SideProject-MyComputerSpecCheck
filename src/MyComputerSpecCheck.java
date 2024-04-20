import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyComputerSpecCheck implements Runnable {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new MyComputerSpecCheck());
    }

    @Override
    public void run() {
        JFrame frame = new JFrame("내 사양 확인하기");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);

        JButton button = new JButton("사양 확인");
        String[] columnNames = {"항목", "정보"};
        String[][] data = {
                {"운영 체제(OS)", ""},
                {"프로세서(CPU)", ""},
                {"그래픽 카드(GPU)", ""},
                {"메모리(RAM)", ""},
                {"디스크(Disk)", ""}
        };

        JTable table = new JTable(data, columnNames);
        table.setRowHeight(100);
        JScrollPane scrollPane = new JScrollPane(table);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Method method = new Method();

                String os = method.getOSInfo();
                String GPU = method.getGPUInfo(os);
                String CPU = method.getCPUInfo(os);
                String RAM = method.getRAMInfo(os);
                String Disk = method.getDiskInfo(os).replace("\n", "<br>");

                // 테이블에 정보 업데이트
                table.setValueAt("<html>" + os + "</html>", 0, 1);
                table.setValueAt("<html>" + CPU + "</html>", 1, 1);
                table.setValueAt("<html>" + GPU + "</html>", 2, 1);
                table.setValueAt("<html>" + RAM + "</html>", 3, 1);
                table.setValueAt("<html>" + Disk + "</html>", 4, 1);
            }
        });

        frame.setLayout(new BorderLayout());
        frame.add(button, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}