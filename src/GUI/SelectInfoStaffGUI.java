package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.StringTokenizer;
import DTO.*;
import DAO.*;

//�������� �α��ν� ���� ���� ����â
public class SelectInfoStaffGUI {
	Frame frame;// ������ ȭ�� ��ü ūȭ�� 1��
	Panel[] panel;
	Button btnMember, btnProgram;
	
	public SelectInfoStaffGUI() {
		frame = new Frame("�ｺ�� �����ý��� - ����");
		
		panel = new Panel[6];
		for (int i = 0; i < panel.length; i++) {
			panel[i] = new Panel();
		}
		
		btnMember = new Button("ȸ �� �� ��");
		btnProgram = new Button("�� �� �� �� �� ��");
		
		btnMember.addActionListener(new ActionListener() { // ȸ������ ��ư ȭ�� ��ȯ
            @Override
            public void actionPerformed(ActionEvent e) {
            	MemberGUI meb = new MemberGUI();
                meb.launchFrame();
                frame.setVisible(false); // â �Ⱥ��̰� �ϱ� 
            }
        });
		
		
		btnProgram.addActionListener(new ActionListener() { // ���α׷� ���� ��ư ȭ�� ��ȯ
            @Override
            public void actionPerformed(ActionEvent e) {
                ProgramGUI pg = new ProgramGUI();
                pg.launchFrame();
                frame.setVisible(false);
            }
        });
		
		frame.addWindowListener(new WindowAdapter() { // ������ ���� ����
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
	} // end SelectUserGUI() { }

	void launchFrame() { // ȭ���� �����ϴ� �޼ҵ�
		
		panel[0].setLayout(new BorderLayout());
		panel[1].add(btnMember);
		panel[2].add(btnProgram);
		panel[3].setLayout(new GridLayout(2, 1));
		panel[3].add(panel[1]);
		panel[3].add(panel[2]);
		panel[4].setLayout(new BorderLayout());
		panel[4].add(panel[3], "Center");
		panel[5].setLayout(new BorderLayout());
		
		// ��ư ������ ����
		panel[0].setPreferredSize(new Dimension(300,35));
		btnMember.setPreferredSize(new Dimension(160,70));
		btnProgram.setPreferredSize(new Dimension(160,70));
		
		frame.add(panel[0], "North");
		frame.add(panel[4], "Center");
		frame.add(panel[5], "South");
		frame.pack();
		frame.setResizable(false);
		frame.setLocation(((frame.getToolkit().getScreenSize()).width - frame.getWidth()) / 2-25,
				((frame.getToolkit().getScreenSize()).height - frame.getHeight()) / 2 -70);
		frame.setSize(400, 400);
		frame.setVisible(true);
		
	} // end private launchFrame()

	public static void main(String[] args) {
		SelectInfoStaffGUI sisg = new SelectInfoStaffGUI();
		sisg.launchFrame();

	} // end main()

} // end class SelectUserGUI
