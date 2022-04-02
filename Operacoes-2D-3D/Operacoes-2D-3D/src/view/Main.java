package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 301, 216);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(105,105,105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		//Botão para primeira parte do projeto sobre transformações geometricas
		JButton btnOperaesd = new JButton("Operações 2D e 3D");
		btnOperaesd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Operacoes2D_3D();
			}
		});
		btnOperaesd.setBounds(50, 30, 200, 100);
		contentPane.add(btnOperaesd);
		
	}
}
