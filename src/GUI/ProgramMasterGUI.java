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

//���α׷� ���� ���� gui(������ �̿�)
public class ProgramMasterGUI {
	// ȭ�� ����� Ŭ���� ��
	Frame frame;
	Panel[] panel;
	Label lblTitle, lblproName, lblproCode, lblsCode, lblproTime, lblproDay, lblproMember, lblStatus;
	TextField tfproName, tfproCode, tfsCode, tfproTime, tfproDay, tfproMember;
	Button btnSave, btnDelete, btnUpdate, btnSearch, btnBack, btnReset;
	List list;
	
	
	public ProgramMasterGUI() {
		frame = new Frame("�ｺ�� �����ý��� - ���α׷� ����");
		panel = new Panel[15];
		for(int i=0;i<panel.length;i++) {
			panel[i] = new Panel();
		}
		lblTitle = new Label("���α׷� ����", Label.CENTER);
		Font font = new Font("dialog", Font.BOLD, 20);
		lblTitle.setFont(font);
		lblTitle.setForeground(new Color(142,24,232));
		lblproName = new Label("���α׷���");
		lblproCode = new Label("���α׷��ڵ�");
		lblsCode = new Label("�����ڵ�");
		lblproTime = new Label("�ð�");
		lblproDay = new Label("����");
		lblproMember = new Label("�ο���");
		lblStatus = new Label("");
		lblStatus.setBackground(Color.LIGHT_GRAY);
		tfproName = new TextField("");
		tfproCode = new TextField("");
		tfsCode = new TextField("");
		tfproTime = new TextField("");
		tfproDay = new TextField("");
		tfproMember = new TextField("");
		btnSave = new Button("����");
		btnSearch = new Button("�˻�");
		btnDelete = new Button("����");
		btnUpdate = new Button("����");
		btnReset = new Button("�ʱ�ȭ");
		btnBack = new Button("�ڷΰ���");
		list = new List(15,false);
		
		btnBack.addActionListener(new ActionListener() { // �ڷΰ��� ��ư ȭ�� ��ȯ
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectInfoMasterGUI simg = new SelectInfoMasterGUI();
                simg.launchFrame();
                frame.setVisible(false);
            }
        });
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
	}
	
	public void launchFrame() {
		// ȭ�鼳���ϱ�
		panel[1].setLayout(new GridLayout(2,1));
		panel[1].add(lblproName);
		panel[1].add(lblproCode);
		panel[2].setLayout(new GridLayout(2,1));
		panel[2].add(tfproName);
		panel[2].add(tfproCode);
		panel[3].setLayout(new BorderLayout());
		panel[3].add(panel[1], "West");
		panel[3].add(panel[2], "Center");
		panel[3].add(new Label(""), "East");
		panel[4].setLayout(new GridLayout(2,1));
		panel[4].add(lblsCode);
		panel[4].add(lblproTime);
		panel[5].setLayout(new GridLayout(2,1));
		panel[5].add(tfsCode);
		panel[5].add(tfproTime);
		panel[6].setLayout(new BorderLayout());
		panel[6].add(panel[4], "West");
		panel[6].add(panel[5], "Center");
		panel[6].add(new Label(""), "East");
		panel[7].setLayout(new GridLayout(2,1));
		panel[7].add(lblproDay);
		panel[7].add(lblproMember);
		panel[8].setLayout(new GridLayout(2,1));
		panel[8].add(tfproDay);
		panel[8].add(tfproMember);
		panel[9].setLayout(new BorderLayout());
		panel[9].add(panel[7], "West");
		panel[9].add(panel[8], "Center");
		panel[9].add(new Label(""), "East");
		panel[10].setLayout(new GridLayout(1,3));
		panel[10].add(panel[3]);
		panel[10].add(panel[6]);
		panel[10].add(panel[9]);
		panel[11].setLayout(new BorderLayout());
		panel[11].add(lblTitle, "North");
		panel[11].add(panel[10], "Center");
		panel[11].add(panel[0], "South");
		
		
		panel[12].setLayout(new GridLayout(1,6));
		panel[12].setBackground(Color.black);
		panel[12].setForeground(Color.white);
		panel[12].add(new Label("���α׷��� "));
		panel[12].add(new Label("���α׷��ڵ� "));
		panel[12].add(new Label("�����ڵ� "));
		panel[12].add(new Label("�� �� "));
		panel[12].add(new Label("�� �� "));
		panel[12].add(new Label("�ο��� "));
		
		panel[13].setLayout(new BorderLayout());
		panel[13].add(panel[12], "North");
		panel[13].add(list, "Center");
		panel[0].add(btnSave);
		panel[0].add(new Label(" "));
		panel[0].add(btnSearch);
		panel[0].add(new Label(" "));
		panel[0].add(btnDelete);
		panel[0].add(new Label(" "));
		panel[0].add(btnUpdate);
		panel[0].add(new Label(" "));
		panel[0].add(btnReset);
		panel[0].add(new Label(" "));
		panel[0].add(btnBack);
		
		panel[14].setLayout(new BorderLayout());
		panel[14].add(panel[11], "North");
		panel[14].add(panel[13], "Center");
		frame.add(panel[14], "Center");
		frame.add(lblStatus, "South");
		frame.pack();
		frame.setResizable(false);
		//�����쿡�� ȭ���� ��ġ
		frame.setLocation(((frame.getToolkit().getScreenSize()).width - frame.getWidth())/2,
				((frame.getToolkit().getScreenSize()).height - frame.getHeight())/2);
		//ȭ���� ũ��
		frame.setSize(600,450);
		frame.setVisible(true);

		displayAll();
		
		//ȸ������ �����ϱ�. �����ϱ� ��ư�� ������ �����ϰ� �����
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//�����ư�� ������ �������� �ڵ带 ���� //tfAge�� �Էµ� ���� ��� ���ڿ� ó���� ���ڵ�.
				String proName = tfproName.getText();
				String proCode = tfproCode.getText();
				String sCode = tfsCode.getText();
				String proTime = tfproTime.getText();
				String proDay = tfproDay.getText();
				int proMember = Integer.parseInt(tfproMember.getText());

				ProgramDAO dao = new ProgramDAO();
				dao.insert(proName, proCode, sCode, proTime, proDay, proMember);//�������� �������� �ִ� �޼ҵ�� ȣ��
				displayAll();
				
			}
		});
		
		
	
		list.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// ����Ʈ�� �׸���� ������ ó������ ������ �ڵ带 ����
				String str = list.getSelectedItem();//"������ 17 .."
				StringTokenizer st = new StringTokenizer(str);
				tfproName.setText( st.nextToken() );
				tfproCode.setText( st.nextToken() );
				tfsCode.setText( st.nextToken() );
				tfproTime.setText( st.nextToken() );
				tfproDay.setText( st.nextToken() );
				tfproMember.setText( st.nextToken() );
				
				
			}
		});
		
		//������ �����ϱ�
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String proCode = tfproCode.getText();
				ProgramDAO dao = new ProgramDAO();
				dao.delete(proCode); 
				displayAll();
	
			}
		});
		
		//������ �����ϱ�
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//�����ϱ�
				String proName = tfproName.getText();
				String proCode = tfproCode.getText();
				String sCode = tfsCode.getText();
				String proTime = tfproTime.getText();
				String proDay = tfproDay.getText();
				int proMember = Integer.parseInt(tfproMember.getText());
				
				ProgramDAO dao = new ProgramDAO();
				dao.update(proName, proCode, sCode, proTime, proDay, proMember);
				displayAll();

			}
		});
		
		//�˻��ϱ� ��ư ����
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String proName = tfproName.getText();
				ProgramDAO dao = new ProgramDAO();
				display(dao, proName);
				
			}
		});
		
		//�ʱ�ȭ ��ư ����
		btnReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tfproName.setText("");
				tfproCode.setText("");
				tfsCode.setText("");
				tfproTime.setText("");
				tfproDay.setText("");
				tfproMember.setText("");
				displayAll();
			}
			
		});
		
		
		
	}//end launch()

	private void displayAll() {
		//ȸ�� ��������
		list.removeAll();// ȭ�鿡 �ִ� �߰��� �������� ������ ����Ʈ�� ������ ����� ��
		ProgramDAO dao = new ProgramDAO();
		ArrayList<ProgramDTO> allData = dao.select();
		for(ProgramDTO dto : allData) {
			String proName = dto.getProName();
			String sCode = dto.getsCode();
			String proCode = dto.getProCode();
			String proTime = dto.getProTime();
			char proDay = dto.getProDay();
			int proMember = dto.getProMember();
			list.add(proName + "                     " + proCode + "               " + sCode + "                   " + proTime + "                          " + proDay + "                              " + proMember);//ȭ�鿡 �ִ� ����Ʈ�� ����(add)
		}
		
		
	}
	
	private void display(ProgramDAO dao, String proName) {
		list.removeAll();
		ArrayList<ProgramDTO> allData = dao.search(proName);
		for(ProgramDTO dto : allData) {
			String proName1 = dto.getProName();
			String sCode = dto.getsCode();
			String proCode = dto.getProCode();
			String proTime = dto.getProTime();
			char proDay = dto.getProDay();
			int proMember = dto.getProMember();
			list.add(proName1 + "                     " + proCode + "               " + sCode + "                   " + proTime + "                          " + proDay + "                              " + proMember);//ȭ�鿡 �ִ� ����Ʈ�� ����(add)
		}
	}

	public static void main(String[] args) {
		
		ProgramMasterGUI pro = new ProgramMasterGUI();
		pro.launchFrame();
		
		
	}

	

}