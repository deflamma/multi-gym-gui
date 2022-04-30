package GUI;

import java.awt.*;
import DTO.*;
import DAO.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.StringTokenizer;

// �α��� ȭ�� gui
public class LoginGUI {
	Frame frame;
	Panel[] panel;
	Label lblTitle, lblTitleBar, lblId, lblPd, lblOk;
	TextField tfId, tfPd, tfOk;
	Button btnLogin, btnSignUp, btnLeave;
	
	public LoginGUI() {
		frame = new Frame("�ｺ�� �����ý��� - �α���");
		
		panel = new Panel[11];
		for (int i = 0; i < panel.length; i++) {
			panel[i] = new Panel();
		}
		
		lblTitle = new Label("�α���", Label.CENTER);
		Font fontTitle = new Font("dialog", Font.BOLD, 30);
		lblTitle.setFont(fontTitle);
		lblTitle.setForeground(new Color(123, 54, 32));
		lblId = new Label("�� �� ��");
		lblPd = new Label("�� �� �� ȣ");
		lblOk = new Label("������ ��ȣ");
		Font fontIdPd = new Font("dialog", Font.BOLD, 15);
		lblId.setFont(fontIdPd);
		lblPd.setFont(fontIdPd);
		lblOk.setFont(fontIdPd);
		tfId = new TextField("");
		tfPd = new TextField("");
		tfOk = new TextField("");
		btnLogin = new Button("�α���");
		btnSignUp = new Button("ȸ������");
		btnLeave = new Button("Ż���ϱ�");

		
		btnSignUp.addActionListener(new ActionListener() { // ȸ������ ��ư ȭ�� ��ȯ
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUpGUI sg = new SignUpGUI();
                sg.launchFrame();
                frame.setVisible(false); // â �Ⱥ��̰� �ϱ� 
            }
        });
		
		btnLeave.addActionListener(new ActionListener() { // Ż���ϱ� ��ư ȭ�� ��ȯ
            @Override
            public void actionPerformed(ActionEvent e) {
                LeaveGUI lg = new LeaveGUI();
                lg.launchFrame();
                frame.setVisible(false); // â �Ⱥ��̰� �ϱ� 
            }
        });
		
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
	} // LoginGUI()
	
	void launchFrame() {
		
		panel[0].setLayout(new BorderLayout());
		panel[0].add(lblTitle, "South");
		panel[1].setLayout(new GridLayout(4, 1));
		panel[1].add(lblId);
		panel[1].add(lblPd);
		panel[1].add(new Label(" "));
		panel[1].add(lblOk);
		panel[2].setLayout(new GridLayout(4, 1));
		panel[2].add(tfId);
		panel[2].add(tfPd);
		panel[2].add(new Label(" "));
		panel[2].add(tfOk);
		panel[3].setLayout(new BorderLayout());
		panel[3].add(panel[1], "West");
		panel[3].add(panel[2], "Center");
		panel[3].add(new Label(""), "East");
		panel[4].add(btnLogin);
		panel[4].add(new Label(" "));
		panel[4].add(btnSignUp);
		panel[4].add(new Label(" "));
		panel[4].add(btnLeave);
		panel[5].setLayout(new BorderLayout());
		panel[5].add(panel[0], "South");
		panel[8].setLayout(new BorderLayout());
		panel[9].setLayout(new BorderLayout());
		panel[6].setLayout(new BorderLayout());
		panel[6].add(panel[8], "West");
		panel[6].add(panel[3], "Center");
		panel[6].add(panel[9], "East");
		panel[7].setLayout(new BorderLayout());
		panel[7].add(panel[4], "North");
		
		panel[5].setPreferredSize(new Dimension(350,120));
		panel[7].setPreferredSize(new Dimension(350,120));
		panel[8].setPreferredSize(new Dimension(120,80));
		panel[9].setPreferredSize(new Dimension(120,80));
		btnLogin.setPreferredSize(new Dimension(100,40));
		btnSignUp.setPreferredSize(new Dimension(100,40));
		btnLeave.setPreferredSize(new Dimension(100,40));
		
		
		frame.add(panel[5], "North");
		frame.add(panel[6], "Center");
		frame.add(panel[7], "South");
		frame.pack();
		frame.setResizable(false);
		frame.setLocation(((frame.getToolkit().getScreenSize()).width - frame.getWidth()) / 2 - 50,
				((frame.getToolkit().getScreenSize()).height - frame.getHeight()) / 2);
		frame.setSize(500,400);
		frame.setVisible(true);
		
		btnLogin.addActionListener(new ActionListener() { // �α��� ��ư ȭ�� ��ȯ
			@Override
			public void actionPerformed(ActionEvent e) {
				
				SelectInfoStaffGUI sisg = new SelectInfoStaffGUI();
				SelectInfoMasterGUI simg = new SelectInfoMasterGUI();
				LoginResultGUI lrg = new LoginResultGUI();
				MasterDAO mdao = new MasterDAO();
				
				int result = mdao.login(tfId.getText(), tfPd.getText(), tfOk.getText());
					
					if(result == 1) {
						if(Integer.parseInt(tfOk.getText()) == 1234){ // ������ ���ι�ȣ
							simg.launchFrame();
							frame.setVisible(false);
							
						}
						else if(Integer.parseInt(tfOk.getText()) == 5678) { // ���� ���ι�ȣ
							sisg.launchFrame();
							frame.setVisible(false);
						}
						
					}
					else {
					lrg.launchFrame();
					}
			}

		});
			
	}
	
	
	public static void main(String[] args) {
		LoginGUI lg = new LoginGUI();
		lg.launchFrame();

	} // end main()
	
} // end class LoginGUI
