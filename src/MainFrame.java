import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame implements Runnable{
    @Override
    public void run() {
        JFrame frame = new JFrame("내 사양 확인");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 290);

        JButton button = new JButton("사양 확인");
        String[] columnNames = {"항목", "정보"};
        String[][] data = {
                {"운영 체제(OS)", ""},
                {"프로세서(CPU)", ""},
                {"그래픽 카드(GPU)", ""},
                {"메모리(RAM)", ""}
        };

        JTable table = new JTable(data, columnNames);
        table.setRowHeight(50);
        JScrollPane scrollPane = new JScrollPane(table);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GetComputerSpec getComputerSpec = new GetComputerSpec();

                String OSInfo = getComputerSpec.getOSInfo();
                String GPUInfo = getComputerSpec.getGPUInfo(OSInfo);
                String CPUInfo = getComputerSpec.getCPUInfo(OSInfo);
                String RAMInfo = getComputerSpec.getRAMInfo(OSInfo);

                DataSender.sendData("test2", OSInfo, GPUInfo, CPUInfo, RAMInfo);

                table.setValueAt("<html>" + OSInfo + "</html>", 0, 1);
                table.setValueAt("<html>" + GPUInfo + "</html>", 1, 1);
                table.setValueAt("<html>" + CPUInfo + "</html>", 2, 1);
                table.setValueAt("<html>" + RAMInfo + "</html>", 3, 1);
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.add(button, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}