import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame implements Runnable{
    @Override
    public void run() {
        JFrame frame = new JFrame("내 사양 확인");
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
                GetComputerSpec getComputerSpec = new GetComputerSpec();

                String os = getComputerSpec.getOSInfo();
                String GPU = getComputerSpec.getGPUInfo(os);
                String CPU = getComputerSpec.getCPUInfo(os);
                String RAM = getComputerSpec.getRAMInfo(os);
                String Disk = getComputerSpec.getDiskInfo(os).replace("\n", "<br>");

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
