package view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import panel.PlanoCartesiano;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class Operacoes2D_3D extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6761863185579265491L;
	public static JLabel label_x, label_y, raio_x, raio_y, ponto_a, ponto_b, raio;
	public static JComboBox<String> comboBox;
	public static JRadioButton rdbtnDda, rdbtnPontoMdio_1, rdbtnEquaoExplicita, rdbtnPontoMdio, rdbtnTrigonometrica;
	private JTextField tf_raio, tf_raio_x, tf_raio_y, tf_altura, tf_largura, tf_x1, tf_y1, tf_x2, tf_y2;
	private JPanel panel_menu;
	private JButton btn_calc_reta, btn_calc_circ, btn_calc_elipse, btn_calc_quad, btn_calc_cubo;
	private PlanoCartesiano plano;
	private JTextField tf_transf_x, tf_transf_y, tf_transf_z;
	private JLabel lblReflexes;
	public static JRadioButton rb_rfly, rb_rflx_y, rb_rflxy, rb_rflyz, rb_rflxz, rb_rflx, rb_transl, rb_escala, rb_rotacao, rb_cis;
	private JSeparator separator_1;
	private JTextField tf_cubo_x;
	private JTextField tf_cubo_y;
	private JTextField tf_cubo_z;
	
	private JPanel panel_transformacao;
	private JLabel lb_z, lblTransformaes2D, lblTransformaes3D;
	private JTextField tf_angulo;
	public static JRadioButton rdbtnRx, rdbtnRy, rdbtnRz;

	/**
	 * Create the frame.
	 */
	public Operacoes2D_3D() {
		getContentPane().setLocation(-1002, -12);
		setLocationRelativeTo(null);
		setSize(new Dimension(1050, 680));
		getContentPane().setBackground(new Color(105,105,105));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.initTransformacoes();
		this.initOpcoesDesenho();
		this.initCoordenadas();
		
		setVisible(true);
	}
	
	/**
	 * Inicializa o painel de coordenadas e as labels das coordenadas
	 */
	private void initCoordenadas() {
		plano = new PlanoCartesiano();
		plano.setBounds(193, 12, 600, 601);
		getContentPane().add(plano);	
		
		JLabel lblDcx = new JLabel("DC X:");
		lblDcx.setBounds(10, 32, 46, 14);
		lblDcx.setFont(new Font("Segoe UI Symbol", Font.BOLD | Font.PLAIN, 14));
		lblDcx.setForeground(new Color(0,255,127));
		getContentPane().add(lblDcx);
		
		JLabel lblDcy = new JLabel("DC Y:");
		lblDcy.setBounds(10, 57, 46, 14);
		lblDcy.setForeground(new Color(0,255,127));
		lblDcy.setFont(new Font("Segoe UI Symbol", Font.BOLD | Font.PLAIN, 14));
		getContentPane().add(lblDcy);
		
		label_x = new JLabel("0");
		label_x.setBounds(66, 32, 46, 14);
		label_x.setForeground(new Color(0,255,127));
		label_x.setFont(new Font("Segoe UI Symbol", Font.BOLD | Font.PLAIN, 14));
		getContentPane().add(label_x);
		
		label_y = new JLabel("0");
		label_y.setBounds(66, 57, 46, 14);
		label_y.setForeground(new Color(0,255,127));
		label_y.setFont(new Font("Segoe UI Symbol", Font.BOLD | Font.PLAIN, 14));
		getContentPane().add(label_y);
	}
	

	private void initOpcoesDesenho() {		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(10, 109, 155, 20);

		comboBox.removeAllItems();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"RETA", "CIRCUNFERENCIA", "ELIPSE", "QUADRADO", "CUBO"}));
		getContentPane().add(comboBox);
		
		panel_menu = new JPanel();
		panel_menu.setBounds(10, 140, 171, 215);
		panel_menu.setBackground(new Color(105,105,105));
		panel_menu.setForeground(new Color(0,255,127));
		getContentPane().add(panel_menu);
		panel_menu.setLayout(null);
		
		raio_x = new JLabel("Raio X:");
		raio_x.setForeground(new Color(0,255,127));
		raio_x.setBounds(10, 11, 46, 14);
		
		raio_y = new JLabel("Raio Y:");
		raio_y.setForeground(new Color(0,255,127));
		raio_y.setBounds(10, 36, 46, 14);
		
		tf_raio_x = new JTextField();
		tf_raio_x.setBounds(66, 8, 30, 20);
		tf_raio_x.setColumns(10);
		
		tf_raio_y = new JTextField();
		tf_raio_y.setBounds(66, 33, 30, 20);
		tf_raio_y.setColumns(10);
		
		JLabel largura = new JLabel("Largura:");
		largura.setForeground(new Color(0,255,127));
		largura.setBounds(10, 11, 60, 14);
		
		JLabel altura = new JLabel("Altura:");
		altura.setForeground(new Color(0,255,127));
		altura.setBounds(10, 36, 60, 14);
		//vai até aqui a label
		
		tf_largura = new JTextField();
		tf_largura.setBounds(74, 8, 30, 20);
		tf_largura.setColumns(10);
		
		tf_altura = new JTextField();
		tf_altura.setBounds(74, 33, 30, 20);
		tf_altura.setColumns(10);
		
		rdbtnDda = new JRadioButton("DDA");
		rdbtnDda.setBackground(new Color(105,105,105));
		rdbtnDda.setForeground(new Color(0,255,127));
		rdbtnDda.setBounds(6, 7, 109, 23);
		
		rdbtnPontoMdio_1 = new JRadioButton("PONTO MEDIO");
		rdbtnPontoMdio_1.setBackground(new Color(105,105,105));
		rdbtnPontoMdio_1.setForeground(new Color(0,255,127));
		rdbtnPontoMdio_1.setBounds(6, 40, 130, 23);
		
		ponto_a = new JLabel("Ponto A");
		ponto_a.setForeground(new Color(0,255,127));
		ponto_a.setBounds(10, 68, 109, 23);		
		tf_x1 = new JTextField();
		tf_x1.setBounds(80, 70, 30, 20);
		tf_x1.setColumns(10);		
		tf_y1 = new JTextField();
		tf_y1.setBounds(120, 70, 30, 20);
		tf_y1.setColumns(10);
	
		ponto_b = new JLabel("Ponto B");
		ponto_b.setForeground(new Color(0,255,127));
		ponto_b.setBounds(10, 98, 109, 23);		
		tf_x2 = new JTextField();
		tf_x2.setBounds(80, 100, 30, 20);
		tf_x2.setColumns(10);		
		tf_y2 = new JTextField();
		tf_y2.setBounds(120, 100, 30, 20);
		tf_y2.setColumns(10); //não sei pra quê serve, tirar
		
		rdbtnEquaoExplicita = new JRadioButton("EXPLICITA");
		rdbtnEquaoExplicita.setForeground(new Color(0,255,127));
		rdbtnEquaoExplicita.setBackground(new Color(105,105,105));
		rdbtnEquaoExplicita.setBounds(6, 7, 180, 23);
		
		rdbtnPontoMdio = new JRadioButton("PONTO MEDIO");
		rdbtnPontoMdio.setBackground(new Color(105,105,105));
		rdbtnPontoMdio.setForeground(new Color(0,255,127));
		rdbtnPontoMdio.setBounds(6, 32, 152, 23);		
		
		rdbtnTrigonometrica = new JRadioButton("TRIGONOMETRICA");
		rdbtnTrigonometrica.setForeground(new Color(0,255,127));
		rdbtnTrigonometrica.setBackground(new Color(105,105,105));
		rdbtnTrigonometrica.setBounds(6, 57, 152, 23);
		
		raio = new JLabel("RAIO");
		raio.setForeground(new Color(0,255,127));
		raio.setBounds(10, 90, 80, 23);		
		tf_raio = new JTextField();
		tf_raio.setBounds(50, 92, 40, 20);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnEquaoExplicita);
		group.add(rdbtnTrigonometrica);
		group.add(rdbtnPontoMdio);
		group.add(rdbtnPontoMdio_1);
		group.add(rdbtnDda);
		
		
/* =====================================================================================================================================
 *                                               BOTÕES
 */
		btn_calc_reta = new JButton("Calcular");
		btn_calc_reta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				plano.zerarImagem();
				try {
					int x1, y1, x2, y2;
					x1 = Integer.parseInt(tf_x1.getText());
					y1 = Integer.parseInt(tf_y1.getText());
					x2 = Integer.parseInt(tf_x2.getText());
					y2 = Integer.parseInt(tf_y2.getText());
					
					
					if (x1>PlanoCartesiano.MEIO_X ||
						x2>PlanoCartesiano.MEIO_X ||
						y1>PlanoCartesiano.MEIO_Y ||
						y2>PlanoCartesiano.MEIO_Y) {
						JOptionPane.showMessageDialog(null, "Informe o valor de x até +- " + PlanoCartesiano.MEIO_X + " e y até +- "+ PlanoCartesiano.MEIO_Y);
					} else {
						plano.calcularReta(x1, y1, x2, y2);
					}
				} catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(null, "Selecione o algoritmo!");
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Apenas numeros inteiros!");
				}
			}
		});
		btn_calc_reta.setBounds(10, 140, 100, 23);

		
		btn_calc_circ = new JButton("Calcular");
		btn_calc_circ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					plano.zerarImagem();
					int raio;
					raio = Integer.parseInt(tf_raio.getText());
					plano.calcularCircunferencia(raio);
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null, "Selecione o algoritmo!");
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Apenas numeros inteiros!");
				} catch (Exception e3) {
					JOptionPane.showMessageDialog(null, "Raio <= 250!");
				}
			}
		});
		btn_calc_circ.setBounds(10, 130, 100, 23);
		

		btn_calc_quad = new JButton("Calcular");
		btn_calc_quad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				plano.zerarImagem();
				int largura = Integer.parseInt(tf_largura.getText());
				int altura = Integer.parseInt(tf_altura.getText());
				
				if (largura>PlanoCartesiano.MEIO_X || altura>PlanoCartesiano.MEIO_Y) {
					JOptionPane.showMessageDialog(null, "Informe a largura até +-"+PlanoCartesiano.MEIO_X + " e altura até +-"+ PlanoCartesiano.MEIO_Y);
				} else {
					plano.calcularQuadrado(largura, altura);
				}
				
			}
		});
		btn_calc_quad.setBounds(10, 63, 100, 23);
		

		btn_calc_cubo = new JButton("Calcular");
		btn_calc_cubo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				plano.zerarImagem();
				
				int x = Integer.parseInt(tf_cubo_x.getText());
				int y = Integer.parseInt(tf_cubo_y.getText());
				int z = Integer.parseInt(tf_cubo_z.getText());
				
				plano.calcularCubo(x, y, z);

			}
		});
		btn_calc_cubo.setBounds(33, 95, 100, 23);		
		

		btn_calc_elipse = new JButton("Calcular");
		btn_calc_elipse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				plano.zerarImagem();
				plano.CalcularElipse(Integer.parseInt(tf_raio_x.getText()), Integer.parseInt(tf_raio_y.getText()));
			}
		});
		btn_calc_elipse.setBounds(10, 63, 100, 23);
		
		JLabel lblX = new JLabel("X");
		lblX.setFont(new Font("Dialog", Font.BOLD | Font.PLAIN, 12));
		lblX.setForeground(new Color(0,255,127));
		lblX.setBounds(12, 12, 26, 15);
		
		JLabel lby = new JLabel("Y");
		lby.setForeground(new Color(0,255,127));
		lby.setFont(new Font("Dialog", Font.BOLD | Font.PLAIN, 12));
		lby.setBounds(12, 39, 26, 15);
		
		JLabel lbz = new JLabel("Z");
		lbz.setForeground(new Color(0,255,127));
		lbz.setFont(new Font("Dialog", Font.BOLD | Font.PLAIN, 12));
		lbz.setBounds(12, 66, 26, 15);
		
		tf_cubo_x = new JTextField();
		tf_cubo_x.setBounds(45, 10, 44, 19);
		tf_cubo_x.setColumns(10);
		
		tf_cubo_y = new JTextField();
		tf_cubo_y.setColumns(10);
		tf_cubo_y.setBounds(45, 37, 44, 19);
		
		tf_cubo_z = new JTextField();
		tf_cubo_z.setColumns(10);
		tf_cubo_z.setBounds(45, 64, 44, 19);
		

		comboBox.addItemListener(new ItemListener() {			
			@Override
			public void itemStateChanged(ItemEvent e) {
					if (e.getItem().equals("CIRCUNFERENCIA")) {
					this.limparMenu();
					getContentPane().add(panel_transformacao);
					panel_transformacao.remove(lb_z);
					panel_transformacao.remove(tf_transf_z);
					panel_transformacao.remove(rb_rflxy);
					panel_transformacao.remove(rb_rflxz);
					panel_transformacao.remove(rb_rflyz);
					panel_transformacao.add(rb_rflx);
					panel_transformacao.add(rb_rfly);
					panel_transformacao.add(rb_rflx_y);
					panel_transformacao.remove(rdbtnRx);
					panel_transformacao.remove(rdbtnRy);
					panel_transformacao.remove(rdbtnRz);
					panel_transformacao.add(lblTransformaes2D);
					panel_transformacao.remove(lblTransformaes3D);
					
					panel_menu.add(rdbtnEquaoExplicita);
					panel_menu.add(rdbtnPontoMdio);
					panel_menu.add(rdbtnTrigonometrica);
					panel_menu.add(raio);
					panel_menu.add(tf_raio);
					panel_menu.add(btn_calc_circ);
					repaint();
				} else if (e.getItem().equals("RETA")) {
					this.limparMenu();
					getContentPane().add(panel_transformacao);
					panel_transformacao.remove(lb_z);
					panel_transformacao.remove(tf_transf_z);
					panel_transformacao.remove(rb_rflxy);
					panel_transformacao.remove(rb_rflxz);
					panel_transformacao.remove(rb_rflyz);
					panel_transformacao.add(rb_rflx);
					panel_transformacao.add(rb_rfly);
					panel_transformacao.add(rb_rflx_y);
					panel_transformacao.remove(rdbtnRx);
					panel_transformacao.remove(rdbtnRy);
					panel_transformacao.remove(rdbtnRz);
					panel_transformacao.add(lblTransformaes2D);
					panel_transformacao.remove(lblTransformaes3D);
					
					panel_menu.add(rdbtnPontoMdio_1);
					panel_menu.add(rdbtnDda);
					panel_menu.add(ponto_a);
					panel_menu.add(tf_x1);
					panel_menu.add(tf_y1);
					panel_menu.add(tf_x2);
					panel_menu.add(tf_y2);
					panel_menu.add(ponto_b);
					panel_menu.add(btn_calc_reta);
					repaint();
				} else if (e.getItem().equals("ELIPSE")){
					this.limparMenu();
					getContentPane().add(panel_transformacao);
					panel_transformacao.remove(lb_z);
					panel_transformacao.remove(tf_transf_z);
					panel_transformacao.remove(rb_rflxy);
					panel_transformacao.remove(rb_rflxz);
					panel_transformacao.remove(rb_rflyz);
					panel_transformacao.add(rb_rflx);
					panel_transformacao.add(rb_rfly);
					panel_transformacao.add(rb_rflx_y);
					panel_transformacao.remove(rdbtnRx);
					panel_transformacao.remove(rdbtnRy);
					panel_transformacao.remove(rdbtnRz);
					panel_transformacao.add(lblTransformaes2D);
					panel_transformacao.remove(lblTransformaes3D);
					
					panel_menu.add(raio_x);
					panel_menu.add(tf_raio_x);
					panel_menu.add(raio_y);
					panel_menu.add(tf_raio_y);
					panel_menu.add(btn_calc_elipse);
					repaint();
				} else if (e.getItem().equals("QUADRADO")) {
					this.limparMenu();
					getContentPane().add(panel_transformacao);
					panel_transformacao.remove(lb_z);
					panel_transformacao.remove(tf_transf_z);
					panel_transformacao.remove(rb_rflxy);
					panel_transformacao.remove(rb_rflxz);
					panel_transformacao.remove(rb_rflyz);
					panel_transformacao.add(rb_rflx);
					panel_transformacao.add(rb_rfly);
					panel_transformacao.add(rb_rflx_y);
					panel_transformacao.remove(rdbtnRx);
					panel_transformacao.remove(rdbtnRy);
					panel_transformacao.remove(rdbtnRz);
					panel_transformacao.add(lblTransformaes2D);
					panel_transformacao.remove(lblTransformaes3D);
					
					panel_menu.add(largura);
					panel_menu.add(tf_largura);
					panel_menu.add(altura);
					panel_menu.add(tf_altura);
					panel_menu.add(btn_calc_quad);
					repaint();
				} else if (e.getItem().equals("CUBO")) {
					this.limparMenu();
					getContentPane().add(panel_transformacao);
					panel_transformacao.add(lb_z);
					panel_transformacao.add(tf_transf_z);
					panel_transformacao.add(rb_rflxy);
					panel_transformacao.add(rb_rflxz);
					panel_transformacao.add(rb_rflyz);
					panel_transformacao.remove(rb_rflx);
					panel_transformacao.remove(rb_rfly);
					panel_transformacao.remove(rb_rflx_y);
					panel_transformacao.add(rdbtnRx);
					panel_transformacao.add(rdbtnRy);
					panel_transformacao.add(rdbtnRz);
					panel_transformacao.remove(lblTransformaes2D);
					panel_transformacao.add(lblTransformaes3D);
					
					panel_menu.add(lblX);
					panel_menu.add(lby);
					panel_menu.add(lbz);
					panel_menu.add(tf_cubo_x);
					panel_menu.add(tf_cubo_y);
					panel_menu.add(tf_cubo_z);
					panel_menu.add(btn_calc_cubo);
					repaint();
				}
			}

			private void limparMenu() {
				plano.zerarImagem();
				panel_menu.remove(raio_x);
				panel_menu.remove(tf_raio_x);
				panel_menu.remove(raio_y);
				panel_menu.remove(tf_raio_y);
				panel_menu.remove(btn_calc_elipse);
				panel_menu.remove(altura);
				panel_menu.remove(largura);
				panel_menu.remove(tf_x1);
				panel_menu.remove(tf_y1);
				panel_menu.remove(tf_x2);
				panel_menu.remove(tf_y2);
				panel_menu.remove(ponto_a);
				panel_menu.remove(btn_calc_reta);
				panel_menu.remove(ponto_b);
				panel_menu.remove(raio);
				panel_menu.remove(tf_raio);
				panel_menu.remove(btn_calc_circ);
				panel_menu.remove(tf_altura);
				panel_menu.remove(tf_largura);				
				panel_menu.remove(btn_calc_quad);
				panel_menu.remove(rdbtnPontoMdio_1);
				panel_menu.remove(rdbtnDda);
				panel_menu.remove(rdbtnEquaoExplicita);
				panel_menu.remove(rdbtnPontoMdio);
				panel_menu.remove(rdbtnTrigonometrica);
				panel_menu.remove(lblX);
				panel_menu.remove(lby);
				panel_menu.remove(lbz);
				panel_menu.remove(tf_cubo_x);
				panel_menu.remove(tf_cubo_y);
				panel_menu.remove(tf_cubo_z);
				panel_menu.remove(btn_calc_cubo);
				repaint();
				
			}
		});
	}
	
	private void initTransformacoes() {
		getContentPane().setLayout(null);
		panel_transformacao = new JPanel();
		panel_transformacao.setBounds(800, 12, 223, 343);
		panel_transformacao.setBackground(new Color(105,105,105));
		panel_transformacao.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_transformacao.setLayout(null);
		
		
		lblTransformaes2D = new JLabel("TRANSFORMAÇÕES 2D");
		lblTransformaes2D.setHorizontalAlignment(SwingConstants.CENTER);
		lblTransformaes2D.setFont(new Font("Segoe UI Symbol", Font.BOLD | Font.ITALIC, 14));
		lblTransformaes2D.setBackground(new Color(105,105,105));
		lblTransformaes2D.setBounds(5, 11, 212, 36);
		lblTransformaes2D.setForeground(new Color(0,255,127));
				
		lblTransformaes3D = new JLabel("TRANSFORMAÇÕES 3D");
		lblTransformaes3D.setHorizontalAlignment(SwingConstants.CENTER);
		lblTransformaes3D.setFont(new Font("Segoe UI Symbol", Font.BOLD | Font.ITALIC, 14));
		lblTransformaes3D.setBackground(new Color(105,105,105));
		lblTransformaes3D.setBounds(5, 11, 212, 36);
		lblTransformaes3D.setForeground(new Color(0,255,127));
		
		JLabel lb_x = new JLabel("X");
		lb_x.setFont(new Font("Segoe UI Symbol", Font.BOLD | Font.PLAIN, 12));
		lb_x.setHorizontalAlignment(SwingConstants.CENTER);
		lb_x.setForeground(new Color(0,255,127));
		lb_x.setBounds(133, 73, 19, 14);
		panel_transformacao.add(lb_x);
		
		JLabel lb_y = new JLabel("Y");
		lb_y.setHorizontalAlignment(SwingConstants.CENTER);
		lb_y.setForeground(new Color(0,255,127));
		lb_y.setFont(new Font("Segoe UI Symbol", Font.BOLD | Font.PLAIN, 12));
		lb_y.setBounds(133, 100, 19, 14);
		panel_transformacao.add(lb_y);
		
		lb_z = new JLabel("Z");
		lb_z.setHorizontalAlignment(SwingConstants.CENTER);
		lb_z.setForeground(new Color(0,255,127));
		lb_z.setFont(new Font("Segoe UI Symbol", Font.BOLD | Font.PLAIN, 12));
		lb_z.setBounds(133, 128, 19, 14);
		panel_transformacao.add(lb_z);
		
		tf_transf_x = new JTextField();
		tf_transf_x.setBounds(162, 71, 35, 20);
		panel_transformacao.add(tf_transf_x);
		tf_transf_x.setColumns(10);
		
		tf_transf_y = new JTextField();
		tf_transf_y.setColumns(10);
		tf_transf_y.setBounds(162, 98, 35, 20);
		panel_transformacao.add(tf_transf_y);
		
		tf_transf_z = new JTextField();
		tf_transf_z.setColumns(10);
		tf_transf_z.setBounds(162, 125, 35, 20);
		panel_transformacao.add(tf_transf_z);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(105,105,105));
		separator.setForeground(new Color(0,255,127));
		separator.setBounds(5, 220, 212, 2);
		panel_transformacao.add(separator);
		
		rb_transl = new JRadioButton("Translação");
		rb_transl.setBackground(new Color(105,105,105));
		rb_transl.setFont(new Font("Segoe UI Symbol", Font.BOLD | Font.PLAIN, 12));
		rb_transl.setForeground(new Color(0,255,127));
		rb_transl.setBounds(12, 66, 109, 23);
		panel_transformacao.add(rb_transl);
		
		rb_escala = new JRadioButton("Escala");
		rb_escala.setForeground(new Color(0,255,127));
		rb_escala.setFont(new Font("Segoe UI Symbol", Font.BOLD | Font.PLAIN, 12));
		rb_escala.setBackground(new Color(105,105,105));
		rb_escala.setBounds(12, 94, 109, 23);
		panel_transformacao.add(rb_escala);
		
		rb_rotacao = new JRadioButton("Rotação");
		rb_rotacao.setForeground(new Color(0,255,127));
		rb_rotacao.setFont(new Font("Segoe UI Symbol", Font.BOLD | Font.PLAIN, 12));
		rb_rotacao.setBackground(new Color(105,105,105));
		rb_rotacao.setBounds(12, 151, 80, 23);
		panel_transformacao.add(rb_rotacao);
		
		rb_cis = new JRadioButton("Cisalhamento");
		rb_cis.setForeground(new Color(0,255,127));
		rb_cis.setFont(new Font("Segoe UI Symbol", Font.BOLD | Font.PLAIN, 12));
		rb_cis.setBackground(new Color(105,105,105));
		rb_cis.setBounds(12, 122, 115, 23);
		panel_transformacao.add(rb_cis);
		
		rb_rflx = new JRadioButton("X");
		rb_rflx.setForeground(new Color(0,255,127));
		rb_rflx.setFont(new Font("Segoe UI Symbol", Font.BOLD | Font.PLAIN, 12));
		rb_rflx.setBackground(new Color(105,105,105));
		rb_rflx.setBounds(27, 254, 43, 23);
		panel_transformacao.add(rb_rflx);
		
		lblReflexes = new JLabel("Reflexões");
		lblReflexes.setFont(new Font("Segoe UI Symbol", Font.BOLD | Font.PLAIN, 12));
		lblReflexes.setForeground(new Color(0,255,127));
		lblReflexes.setBounds(79, 233, 73, 14);
		panel_transformacao.add(lblReflexes);
		
		rb_rfly = new JRadioButton("Y");
		rb_rfly.setForeground(new Color(0,255,127));
		rb_rfly.setFont(new Font("Segoe UI Symbol", Font.BOLD | Font.PLAIN, 12));
		rb_rfly.setBackground(new Color(105,105,105));
		rb_rfly.setBounds(79, 254, 43, 23);
		panel_transformacao.add(rb_rfly);
		
		rb_rflx_y = new JRadioButton("X e Y");
		rb_rflx_y.setForeground(new Color(0,255,127));
		rb_rflx_y.setFont(new Font("Segoe UI Symbol", Font.BOLD | Font.PLAIN, 12));
		rb_rflx_y.setBackground(new Color(105,105,105));
		rb_rflx_y.setBounds(133, 254, 64, 23);
		panel_transformacao.add(rb_rflx_y);
		
		rb_rflxy = new JRadioButton("XY");
		rb_rflxy.setForeground(new Color(0,255,127));
		rb_rflxy.setFont(new Font("Segoe UI Symbol", Font.BOLD | Font.PLAIN, 12));
		rb_rflxy.setBackground(new Color(105,105,105));
		rb_rflxy.setBounds(27, 254, 43, 23);
		panel_transformacao.add(rb_rflxy);
		
		rb_rflyz = new JRadioButton("YZ");
		rb_rflyz.setForeground(new Color(0,255,127));
		rb_rflyz.setFont(new Font("Segoe UI Symbol", Font.BOLD | Font.PLAIN, 12));
		rb_rflyz.setBackground(new Color(105,105,105));
		rb_rflyz.setBounds(78, 254, 43, 23);
		panel_transformacao.add(rb_rflyz);
		
		rb_rflxz = new JRadioButton("XZ");
		rb_rflxz.setForeground(new Color(0,255,127));
		rb_rflxz.setFont(new Font("Segoe UI Symbol", Font.BOLD | Font.PLAIN, 12));
		rb_rflxz.setBackground(new Color(105,105,105));
		rb_rflxz.setBounds(133, 254, 43, 23);
		panel_transformacao.add(rb_rflxz);
		
		
		ButtonGroup group = new ButtonGroup();
		group.add(rb_rflx);
		group.add(rb_rflx_y);
		group.add(rb_cis);
		group.add(rb_rotacao);
		group.add(rb_escala);
		group.add(rb_transl);
		group.add(rb_rfly);
		group.add(rb_rflxy);
		group.add(rb_rflyz);
		group.add(rb_rflxz);
		
		separator_1 = new JSeparator();
		separator_1.setForeground(new Color(0,255,127));
		separator_1.setBackground(new Color(105,105,105));
		separator_1.setBounds(5, 288, 212, 2);
		panel_transformacao.add(separator_1);
		
		JButton jb_transf = new JButton("Calcular");
		jb_transf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				if (Operacoes2D_3D.rb_rflx.isSelected() ||
					Operacoes2D_3D.rb_rfly.isSelected() ||
					Operacoes2D_3D.rb_rflx_y.isSelected()||
					Operacoes2D_3D.rb_rflxy.isSelected() ||
					Operacoes2D_3D.rb_rflxz.isSelected() ||
					Operacoes2D_3D.rb_rflyz.isSelected()) {
					plano.calcularReflexao();
				} else if (Operacoes2D_3D.rb_rotacao.isSelected()) {
					plano.calcularRotacao(Double.parseDouble(tf_angulo.getText()));
				} else {
					int x = 0, y = 0, z = 0;
					if (!tf_transf_x.getText().equals(""))
						x = Integer.parseInt(tf_transf_x.getText());
					
					if (!tf_transf_y.getText().equals(""))
						y= Integer.parseInt(tf_transf_y.getText());
					
					if (!tf_transf_z.getText().equals(""))
						z= Integer.parseInt(tf_transf_z.getText());
					
					plano.calcularTransformacoes(x, y, z);
				}
			}
		});
		jb_transf.setBounds(67, 308, 119, 23);
		panel_transformacao.add(jb_transf);
		
		tf_angulo = new JTextField();
		tf_angulo.setBounds(162, 153, 35, 20);
		panel_transformacao.add(tf_angulo);
		tf_angulo.setColumns(10);
		
		JLabel lb_angulo = new JLabel("Angulo");
		lb_angulo.setHorizontalAlignment(SwingConstants.CENTER);
		lb_angulo.setForeground(new Color(0,255,127));
		lb_angulo.setFont(new Font("Segoe UI Symbol", Font.BOLD | Font.PLAIN, 12));
		lb_angulo.setBounds(98, 148, 64, 28);
		panel_transformacao.add(lb_angulo);
		
		rdbtnRx = new JRadioButton("Rx");
		rdbtnRx.setForeground(new Color(0,255,127));
		rdbtnRx.setFont(new Font("Segoe UI Symbol", Font.BOLD | Font.PLAIN, 12));
		rdbtnRx.setBackground(new Color(105,105,105));
		rdbtnRx.setBounds(22, 177, 43, 23);
		
		rdbtnRy = new JRadioButton("Ry");
		rdbtnRy.setForeground(new Color(0,255,127));
		rdbtnRy.setFont(new Font("Segoe UI Symbol", Font.BOLD | Font.PLAIN, 12));
		rdbtnRy.setBackground(new Color(105,105,105));
		rdbtnRy.setBounds(67, 177, 43, 23);
		
		rdbtnRz = new JRadioButton("Rz");
		rdbtnRz.setForeground(new Color(0,255,127));
		rdbtnRz.setFont(new Font("Segoe UI Symbol", Font.BOLD | Font.PLAIN, 12));
		rdbtnRz.setBackground(new Color(105,105,105));
		rdbtnRz.setBounds(119, 177, 43, 23);
		
		
		ButtonGroup group3 = new ButtonGroup();
		group3.add(rdbtnRx);
		group3.add(rdbtnRy);
		group3.add(rdbtnRz);
		
		
	}
}

