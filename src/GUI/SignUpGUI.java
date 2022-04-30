package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import DTO.*;
import DAO.*;

// ȸ������ gui
public class SignUpGUI {
	Frame frame;
	Panel[] panel;
	Label lblTitle,lblName,lblBirth,lblGender,lblPhone,lblNewId,lblNewPd1,lblMasterOk;
	TextField tfName,tfBirth,tfGender,tfPhone,tfNewId,tfNewPd1,tfNewPd2,tfMasterOk;
	Button btnNewSignUp, btnCheck, btnBack;
	
	MasterDAO mdao = new MasterDAO();
	
	
	public SignUpGUI() {
		frame = new Frame("�ｺ�� �����ý��� - ȸ������");
		
		panel = new Panel[11];
		for (int i = 0; i < panel.length; i++) {
			panel[i] = new Panel();
		}
		
		lblTitle = new Label("ȸ������", Label.CENTER);
		Font font = new Font("dialog", Font.BOLD, 30);
		lblTitle.setFont(font);
		lblTitle.setForeground(new Color(123, 54, 32));
		lblName = new Label("�̸�");
		lblBirth = new Label("�������");
		lblGender = new Label("����");
		lblPhone = new Label("��ȭ��ȣ");
		lblNewId = new Label("���̵�");
		lblNewPd1 = new Label("��й�ȣ");
		lblMasterOk = new Label("������ �����ڵ�");
		tfName = new TextField("");
		tfBirth = new TextField("");
		tfGender = new TextField("");
		tfPhone = new TextField("");
		tfNewId = new TextField("");
		tfNewPd1 = new TextField("");
		tfMasterOk = new TextField("");
		btnCheck = new Button("�ߺ�Ȯ��");
		btnNewSignUp = new Button("�����ϱ�");
		btnBack = new Button("�ڷΰ���");
		
		btnBack.addActionListener(new ActionListener() { // �ڷΰ��� ��ư ȭ�� ��ȯ
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginGUI lg = new LoginGUI();
                lg.launchFrame();
                frame.setVisible(false); // â �Ⱥ��̰� �ϱ� 
            }
        });
		
		btnNewSignUp.addActionListener(new ActionListener() {
			// ȸ�������� ������ �������� �ڵ带 ����
			
			@Override
			public void actionPerformed(ActionEvent e) {

				MasterDTO mdto = new MasterDTO();
				
				mdto.setMasterId(tfNewId.getText());
				mdto.setMasterPd(tfNewPd1.getText());
				mdto.setMasterOk(tfMasterOk.getText());
				mdto.setsName(tfName.getText());
				mdto.setsBirth(Integer.parseInt(tfBirth.getText()));
				mdto.setsGender(tfGender.getText());
				mdto.setsPhone(tfPhone.getText());
								
				mdao.insertMaster(mdto);
				
				LoginGUI lg = new LoginGUI();
                lg.launchFrame();
                frame.setVisible(false);
				
				} 
			
		});
		
		btnCheck.addActionListener(new ActionListener() { 
			// �ߺ�Ȯ�� ��ư ȭ�� ��ȯ
			
            @Override
            public void actionPerformed(ActionEvent e) {
            	MasterDAO mdao = new MasterDAO();

				boolean result = mdao.idcheck(tfNewId.getText());
				
				if(result == true) { // ���̵� �ߺ�
					IdCheckGUI idg = new IdCheckGUI();
					idg.launchFrame();
					
				} else if(result == false) { // ���̵� ��밡��
					IdCheckOkGUI icog = new IdCheckOkGUI();
					icog.launchFrame();
					
				}

            }
        });
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
	}
	
	void launchFrame() {
		panel[0].setLayout(new BorderLayout());
		panel[0].add(lblTitle, "Center");
		panel[1].setLayout(new BorderLayout());
		panel[2].setLayout(new GridLayout(15, 1));
		panel[2].add(lblName);
		panel[2].add(new Label(" "));
		panel[2].add(lblBirth);
		panel[2].add(new Label(" "));
		panel[2].add(lblGender);
		panel[2].add(new Label(" "));
		panel[2].add(lblPhone);
		panel[2].add(new Label(" "));
		panel[2].add(lblNewId);
		panel[2].add(new Label(" "));
		panel[2].add(lblNewPd1);
		panel[2].add(new Label(" "));
		panel[2].add(lblMasterOk);
		panel[3].setLayout(new GridLayout(15, 1));
		panel[3].add(tfName);
		panel[3].add(new Label(" "));
		panel[3].add(tfBirth);
		panel[3].add(new Label(" "));
		panel[3].add(tfGender);
		panel[3].add(new Label(" "));
		panel[3].add(tfPhone);
		panel[3].add(new Label(" "));
		panel[3].add(tfNewId);
		panel[3].add(new Label(" "));
		panel[3].add(tfNewPd1);
		panel[3].add(new Label(" "));
		panel[3].add(tfMasterOk);
		panel[4].setLayout(new GridLayout(15, 1));
		panel[4].add(new Label(" "));
		panel[4].add(new Label(" "));
		panel[4].add(new Label(" "));
		panel[4].add(new Label(" "));
		panel[4].add(new Label(" "));
		panel[4].add(new Label(" "));
		panel[4].add(new Label(" "));
		panel[4].add(new Label(" "));
		panel[4].add(btnCheck);
		panel[5].setLayout(new BorderLayout());
		panel[5].add(panel[2], "West");
		panel[5].add(panel[3], "Center");
		panel[5].add(panel[4], "East");
		panel[6].setLayout(new BorderLayout());
		panel[7].add(btnNewSignUp);
		panel[7].add(new Label(" "));
		panel[7].add(btnBack);
		
		panel[0].setPreferredSize(new Dimension(100,100));
		panel[1].setPreferredSize(new Dimension(150,50));
		panel[6].setPreferredSize(new Dimension(150,50));
		panel[7].setPreferredSize(new Dimension(700,150));
		btnNewSignUp.setPreferredSize(new Dimension(120,40));
		btnBack.setPreferredSize(new Dimension(120,40));
		btnCheck.setPreferredSize(new Dimension(75,20));
		
		frame.add(panel[0], "North");
		frame.add(panel[1], "West");
		frame.add(panel[5], "Center");
		frame.add(panel[6], "East");
		frame.add(panel[7], "South");
		frame.pack();
		frame.setResizable(false);
		frame.setLocation(((frame.getToolkit().getScreenSize()).width - frame.getWidth()) / 2,
				((frame.getToolkit().getScreenSize()).height - frame.getHeight()) / 3);
		frame.setSize(700,700);
		frame.setVisible(true);
			
	}
	
	public static void main(String[] args) {
		SignUpGUI sg = new SignUpGUI();
		sg.launchFrame();

	}

}
