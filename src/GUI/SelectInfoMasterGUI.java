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

// �����ڷ� �α��� �� �������� ����â
public class SelectInfoMasterGUI {
	Frame frame;// ������ ȭ�� ��ü ūȭ�� 1��
	Panel[] panel;
	Button btnMember, btnStaff, btnProgram;
	
	public SelectInfoMasterGUI() {
		frame = new Frame("�ｺ�� �����ý��� - ������");
		
		panel = new Panel[7];
		for (int i = 0; i < panel.length; i++) {
			panel[i] = new Panel();
		}
		
		btnMember = new Button("ȸ �� �� ��");
		btnStaff = new Button("�� �� �� ��");
		btnProgram = new Button("�� �� �� �� �� ��");
		
		btnMember.addActionListener(new ActionListener() { // ȸ������ ��ư ȭ�� ��ȯ
            @Override
            public void actionPerformed(ActionEvent e) {
                MemberMasterGUI mebm = new MemberMasterGUI();
                mebm.launchFrame();
                frame.setVisible(false); // â �Ⱥ��̰� �ϱ� 
            }
        });
		
		btnStaff.addActionListener(new ActionListener() { // �������� ��ư ȭ�� ��ȯ
            @Override
            public void actionPerformed(ActionEvent e) {
            	StaffGUI fg = new StaffGUI();
                fg.launchFrame();
                frame.setVisible(false); 
            }
        });
		
		btnProgram.addActionListener(new ActionListener() { // ���α׷� ���� ��ư ȭ�� ��ȯ
            @Override
            public void actionPerformed(ActionEvent e) {
                ProgramMasterGUI pmg = new ProgramMasterGUI();
                pmg.launchFrame();
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
		panel[2].add(btnStaff);
		panel[3].add(btnProgram);
		panel[4].setLayout(new GridLayout(3, 1));
		panel[4].add(panel[1]);
		panel[4].add(panel[2]);
		panel[4].add(panel[3]);
		panel[5].setLayout(new BorderLayout());
		panel[5].add(panel[4], "Center");
		panel[6].setLayout(new BorderLayout());
		
		// ��ư ������ ����
		panel[0].setPreferredSize(new Dimension(300,35));
		btnMember.setPreferredSize(new Dimension(160,70));
		btnStaff.setPreferredSize(new Dimension(160,70));
		btnProgram.setPreferredSize(new Dimension(160,70));
		
		frame.add(panel[0], "North");
		frame.add(panel[5], "Center");
		frame.add(panel[6], "South");
		frame.pack();
		frame.setResizable(false);
		frame.setLocation(((frame.getToolkit().getScreenSize()).width - frame.getWidth()) / 2-25,
				((frame.getToolkit().getScreenSize()).height - frame.getHeight()) / 2 -70);
		frame.setSize(400, 400);
		frame.setVisible(true);
		
	} // end private launchFrame()

	public static void main(String[] args) {
		SelectInfoMasterGUI simg = new SelectInfoMasterGUI();
		simg.launchFrame();

	} // end main()

} // end class SelectUserGUI
