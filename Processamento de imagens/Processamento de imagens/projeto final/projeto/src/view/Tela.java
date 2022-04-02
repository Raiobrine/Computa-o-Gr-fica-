package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import modelo.Filtros;
import modelo.Histograma;
import modelo.Morfologia;
import modelo.Op_Logicas;
import modelo.Op_Matematicas;
import processarImagem.PanelDaImagem;

public class Tela extends JFrame {

	private static JPanel painelPrincipal;

	public static PanelDaImagem panelDaImagem1 = new PanelDaImagem(); // painel da primeira imagem
	public static PanelDaImagem panelDaImagem2 = new PanelDaImagem(); // painel da segunda imagem
	public static PanelDaImagem panelDaImagem3 = new PanelDaImagem(); // painel da quarta imagem - TESTE
	public static PanelDaImagem panelDaImagem4 = new PanelDaImagem(); // painel da quarta imagem - TESTE
	public static JPanel panelMascara = new JPanel(new GridLayout(3, 3));
	public static JLabel lblMenorValor = new JLabel("");
	public static JLabel lblMaiorValor = new JLabel("");
	Filtros panelDaImagem_ef = new Filtros();
	Op_Logicas panelDaImagem_ol = new Op_Logicas();
	Op_Matematicas panelDaImagem_om = new Op_Matematicas();
	Histograma panelDaImagem_h = new Histograma();
	Morfologia panelDaImagem_mor = new Morfologia();
	Color fundo = new Color(105,105,105);
	Color letra = new Color(0,255,127);

	public static JPanel panel_2 = new JPanel();
	public static PanelDaImagem panel_Pontos_1 = new PanelDaImagem();
	public static PanelDaImagem panel_Pontos_2 = new PanelDaImagem();
	public static JPanel panel_Pontos_3 = new JPanel(new GridLayout(9, 8));
	public static JPanel panel_Pontos_4 = new JPanel(new GridLayout(9, 8));
	public static JPanel panel_4 = new JPanel();
	public static JLabel lblNewLabel = new JLabel("");
	JLabel lblDica = new JLabel("");
	JLabel lblFiltro = new JLabel("");

	private boolean[][] CROSS = { { false, true, false }, { true, true, true }, { false, true, false } };

	JPanel painel3; // painel das imagens resultado
	// condicoes booleanas para ativar e desativar a visibilidade do painel3(de
	// resultados)
	boolean logico = false;
	boolean aritmeticos = false;
	public static boolean efeitos1 = false;
	boolean his = false;
	public static boolean geo = false;
	public static boolean mor = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela frame = new Tela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tela() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1323, 865);
		setTitle("Operacoes com imagens");

		JMenuBar menuBar = new JMenuBar(); // barra de menu
		menuBar.setBackground(fundo);
		setJMenuBar(menuBar);

		Font fonte = new Font("Segoe UI Symbol", Font.BOLD | Font.PLAIN, 18);

		JMenu mnEfeitos = new JMenu("Filtros"); // meno efeitos
		mnEfeitos.setBackground(fundo);
		mnEfeitos.setFont(fonte);
		mnEfeitos.setForeground(letra);
		menuBar.add(mnEfeitos);

		painelPrincipal = new JPanel();
		painelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelPrincipal.setBackground(fundo);
		setContentPane(painelPrincipal);
		painelPrincipal.setLayout(null);

		JPanel panel_3 = new JPanel();// painel resultados
		panel_3.setLayout(null);
		panel_3.setBounds(681, 122, 256, 256);
		panel_3.setBorder(BorderFactory.createEtchedBorder());
		panel_3.setBackground(fundo);
		painelPrincipal.add(panel_3);

		JMenuItem media = new JMenuItem("Media"); // Bot�o efeito M�dia
		media.setFont(fonte);
		media.setForeground(letra);
		media.setBackground(fundo);
		mnEfeitos.add(media);
		media.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaInformacoes();

				efeitos1 = true;
				panelDaImagem_ef.setBounds(0, 0, 256, 256);
				panelDaImagem_ef.setVisible(efeitos1);
				panel_3.add(panelDaImagem_ef);

				String[] mascara = { "1/9", "1/9", "1/9", "1/9", "1/9", "1/9", "1/9", "1/9", "1/9" };
				plotaMascara(mascara);
				// chamada do m�todo para aplicar o efeito m�dia e retorna a imagem resultado
				// para o "panelDaImagem_ef"
				// podendo assim ser exibida
				panelDaImagem_ef.media(panelDaImagem1.largura, panelDaImagem1.altura, panelDaImagem1.matrizImagem);

				lblFiltro.setText("Filtro de Media");
			}

		});

		JMenuItem mediana = new JMenuItem("Mediana"); // Bot�o mediana
		mediana.setFont(fonte);
		mediana.setForeground(letra);
		mediana.setBackground(fundo);
		mnEfeitos.add(mediana);
		mediana.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaInformacoes();

				efeitos1 = true;
				panelDaImagem_ef.setBounds(0, 0, 256, 256);
				panelDaImagem_ef.setVisible(efeitos1);
				panel_3.add(panelDaImagem_ef);

				// chamada do m�todo para aplicar o efeito mediana e retorna a imagem resultado
				// para o "panelDaImagem_ef"
				// podendo assim ser exibida
				panelDaImagem_ef.mediana(panelDaImagem1.largura, panelDaImagem1.altura, panelDaImagem1.matrizImagem);

				lblFiltro.setText("Filtro de Mediana");
			
			}

		});

		JMenuItem passa_alta = new JMenuItem("Passa alta"); // Bot�o Passa Alta
		passa_alta.setForeground(letra);
		passa_alta.setFont(fonte);
		passa_alta.setBackground(fundo);
		mnEfeitos.add(passa_alta);
		passa_alta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaInformacoes();

				efeitos1 = true;
				panelDaImagem_ef.setBounds(0, 0, 256, 256);
				panelDaImagem_ef.setVisible(efeitos1);
				panel_3.add(panelDaImagem_ef);

				// chamada do m�todo para aplicar o efeito passa alta e retorna a imagem
				// resultado para o "panelDaImagem_ef"
				// podendo assim ser exibida
				panelDaImagem_ef.passa_alta(panelDaImagem1.largura, panelDaImagem1.altura, panelDaImagem1.matrizImagem);

				String[] mascara = { "-1", "-1", "-1", "-1", "8", "-1", "-1", "-1", "-1" };
				plotaMascara(mascara);

				lblFiltro.setText("Passa Alta");
			}

		});

		JMenuItem alto_reforco = new JMenuItem("Alto Reforco"); // Bot�o passa baixa
		alto_reforco.setForeground(letra);
		alto_reforco.setFont(fonte);
		alto_reforco.setBackground(fundo);
		alto_reforco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaInformacoes();

				efeitos1 = true;
				panelDaImagem_ef.setBounds(0, 0, 256, 256);
				panelDaImagem_ef.setVisible(efeitos1);
				panel_3.add(panelDaImagem_ef);

				// Fator auxiliar manipulado pelo us�ario para aumentar ou diminuir o efeito
				// aplicado pela equa��o
				// da passa baixo (W = 9A -1)
				double A = 1.2;
				A = Double.parseDouble(JOptionPane.showInputDialog("Valor para 'a', sendo a>=1: ", A));

				// chamada do m�todo para aplicar o efeito passa baixa e retorna a imagem
				// resultado para o "panelDaImagem_ef"
				// podendo assim ser exibida
				panelDaImagem_ef.alto_reforco(panelDaImagem1.largura, panelDaImagem1.altura, panelDaImagem1.matrizImagem,
						A);

				lblFiltro.setText("Alto Reforco");
				lblDica.setText("");
			}

		});
		mnEfeitos.add(alto_reforco);

		JMenuItem prewittX = new JMenuItem("Prewitt: X"); // Bot�o prewitt em X
		prewittX.setForeground(letra);
		prewittX.setFont(fonte);
		prewittX.setBackground(fundo);
		mnEfeitos.add(prewittX);
		prewittX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaInformacoes();

				efeitos1 = true;
				panelDaImagem_ef.setBounds(0, 0, 256, 256);
				panelDaImagem_ef.setVisible(efeitos1);
				panel_3.add(panelDaImagem_ef);

				// chamada do m�todo para aplicar o efeito prewitt e retorna a imagem resultado
				// para o "panelDaImagem_ef"
				// podendo assim ser exibida
				panelDaImagem_ef.prewitt_em_x(panelDaImagem1.largura, panelDaImagem1.altura,
						panelDaImagem1.matrizImagem);

				lblFiltro.setText("Prewitt em X");
				String[] mascara = { "-1", "0", "1", "-1", "0", "1", "-1", "0", "1" };
				plotaMascara(mascara);
			}
		});

		JMenuItem prewittY = new JMenuItem("Prewitt: Y"); // Bot�o prewitt em Y
		prewittY.setForeground(letra);
		prewittY.setFont(fonte);
		prewittY.setBackground(fundo);
		mnEfeitos.add(prewittY);
		prewittY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaInformacoes();

				efeitos1 = true;
				panelDaImagem_ef.setBounds(0, 0, 256, 256);
				panelDaImagem_ef.setVisible(efeitos1);
				panel_3.add(panelDaImagem_ef);

				// chamada do m�todo para aplicar o efeito prewitt e retorna a imagem resultado
				// para o "panelDaImagem_ef"
				// podendo assim ser exibida
				panelDaImagem_ef.prewitt_em_y(panelDaImagem1.largura, panelDaImagem1.altura,
						panelDaImagem1.matrizImagem);

				lblFiltro.setText("Prewitt em Y");

				String[] mascara = { "-1", "-1", "-1", "0", "0", "0", "1", "1", "1" };
				plotaMascara(mascara);
			}
		});

		JMenuItem prewitt = new JMenuItem("Prewitt: Magnitude"); // Bot�o prewitt
		prewitt.setForeground(letra);
		prewitt.setFont(fonte);
		prewitt.setBackground(fundo);
		mnEfeitos.add(prewitt);
		prewitt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaInformacoes();

				efeitos1 = true;
				panelDaImagem_ef.setBounds(0, 0, 256, 256);
				panelDaImagem_ef.setVisible(efeitos1);
				panel_3.add(panelDaImagem_ef);

				// chamada do m�todo para aplicar o efeito prewitt e retorna a imagem resultado
				// para o "panelDaImagem_ef"
				// podendo assim ser exibida
				panelDaImagem_ef.prewitt(panelDaImagem1.largura, panelDaImagem1.altura, panelDaImagem1.matrizImagem);

				lblFiltro.setText("Prewitt - Magnitude");
			}

		});

		JMenuItem sobelEmX = new JMenuItem("Sobel: X");// Bot�o operador de Sobel
		sobelEmX.setForeground(letra);
		sobelEmX.setFont(fonte);
		sobelEmX.setBackground(fundo);
		mnEfeitos.add(sobelEmX);
		sobelEmX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaInformacoes();

				efeitos1 = true;
				panelDaImagem_ef.setBounds(0, 0, 256, 256);
				panelDaImagem_ef.setVisible(efeitos1);
				panel_3.add(panelDaImagem_ef);

				// chamada do m�todo para aplicar o efeito negativo e retorna a imagem resultado
				// para o "panelDaImagem_ef"
				// podendo assim ser exibida
				panelDaImagem_ef.sobelEmX(panelDaImagem1.largura, panelDaImagem1.altura, panelDaImagem1.matrizImagem);

				lblFiltro.setText("Sobel em X");
				lblDica.setText("");
				String[] mascara = { "-1", "0", "1", "-2", "0", "2", "-1", "0", "1" };
				plotaMascara(mascara);
			}

		});

		JMenuItem sobelEmY = new JMenuItem("Sobel: Y");// Bot�o operador de Sobel
		sobelEmY.setForeground(letra);
		sobelEmY.setFont(fonte);
		sobelEmY.setBackground(fundo);
		mnEfeitos.add(sobelEmY);
		sobelEmY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaInformacoes();

				efeitos1 = true;
				panelDaImagem_ef.setBounds(0, 0, 256, 256);
				panelDaImagem_ef.setVisible(efeitos1);
				panel_3.add(panelDaImagem_ef);

				// chamada do m�todo para aplicar o efeito negativo e retorna a imagem resultado
				// para o "panelDaImagem_ef"
				// podendo assim ser exibida
				panelDaImagem_ef.sobelEmY(panelDaImagem1.largura, panelDaImagem1.altura, panelDaImagem1.matrizImagem);

				lblFiltro.setText("Sobel em Y");
				lblDica.setText("");
				String[] mascara = { "-1", "-1", "-1", "0", "0", "0", "1", "1", "1" };
				plotaMascara(mascara);
			}

		});

		JMenuItem sobel = new JMenuItem("Sobel: Magnitude");// Bot�o operador de Sobel
		sobel.setForeground(letra);
		sobel.setFont(fonte);
		sobel.setBackground(fundo);
		mnEfeitos.add(sobel);
		sobel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaInformacoes();

				efeitos1 = true;
				panelDaImagem_ef.setBounds(0, 0, 256, 256);
				panelDaImagem_ef.setVisible(efeitos1);
				panel_3.add(panelDaImagem_ef);

				// chamada do m�todo para aplicar o efeito negativo e retorna a imagem resultado
				// para o "panelDaImagem_ef"
				// podendo assim ser exibida
				panelDaImagem_ef.sobel(panelDaImagem1.largura, panelDaImagem1.altura, panelDaImagem1.matrizImagem);

				lblFiltro.setText("Sobel - Magnitude");
			}

		});

		JMenuItem gradienteEmX = new JMenuItem("Roberts: X"); // Bot�o operador de Roberts (gradiente)
		gradienteEmX.setForeground(letra);
		gradienteEmX.setFont(fonte);
		gradienteEmX.setBackground(fundo);
		mnEfeitos.add(gradienteEmX);
		gradienteEmX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaInformacoes();

				efeitos1 = true;
				panelDaImagem_ef.setBounds(0, 0, 256, 256);
				panelDaImagem_ef.setVisible(efeitos1);
				panel_3.add(panelDaImagem_ef);

				// chamada do m�todo para aplicar o efeito gradiente e retorna a imagem
				// resultado para o "panelDaImagem_ef"
				// podendo assim ser exibida
				panelDaImagem_ef.gradienteEmX(panelDaImagem1.largura, panelDaImagem1.altura, panelDaImagem1.matrizImagem);

				lblFiltro.setText("Gradiente / Operador de Roberts em X");
				String[] mascara = { "1", "0", "-1", "0" };
				plotaMascara(mascara);
			}

		});
		
		JMenuItem gradienteEmY = new JMenuItem("Roberts: Y"); // Bot�o operador de Roberts (gradiente)
		gradienteEmY.setForeground(letra);
		gradienteEmY.setFont(fonte);
		gradienteEmY.setBackground(fundo);
		mnEfeitos.add(gradienteEmY);
		gradienteEmY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaInformacoes();

				efeitos1 = true;
				panelDaImagem_ef.setBounds(0, 0, 256, 256);
				panelDaImagem_ef.setVisible(efeitos1);
				panel_3.add(panelDaImagem_ef);

				// chamada do m�todo para aplicar o efeito gradiente e retorna a imagem
				// resultado para o "panelDaImagem_ef"
				// podendo assim ser exibida
				panelDaImagem_ef.gradienteEmY(panelDaImagem1.largura, panelDaImagem1.altura, panelDaImagem1.matrizImagem);

				lblFiltro.setText("Gradiente / Operador de Roberts em Y");
			
				String[] mascara = { "1", "-1", "0", "0" };
				plotaMascara(mascara);
			}

		});
		
		JMenuItem gradiente = new JMenuItem("Roberts: Magnitude"); // Bot�o operador de Roberts (gradiente)
		gradiente.setForeground(letra);
		gradiente.setFont(fonte);
		gradiente.setBackground(fundo);
		mnEfeitos.add(gradiente);
		gradiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaInformacoes();

				efeitos1 = true;
				panelDaImagem_ef.setBounds(0, 0, 256, 256);
				panelDaImagem_ef.setVisible(efeitos1);
				panel_3.add(panelDaImagem_ef);

				// chamada do m�todo para aplicar o efeito gradiente e retorna a imagem
				// resultado para o "panelDaImagem_ef"
				// podendo assim ser exibida
				panelDaImagem_ef.gradiente(panelDaImagem1.largura, panelDaImagem1.altura, panelDaImagem1.matrizImagem);

				lblFiltro.setText("Gradiente / Operador de Roberts");
			}

		});

		JMenuItem mntmGradienteCruzadoEmX = new JMenuItem("Roberts Cruzado: X"); // Bot�o Gradiente Cruzado
		mntmGradienteCruzadoEmX.setForeground(letra);
		mntmGradienteCruzadoEmX.setFont(fonte);
		mntmGradienteCruzadoEmX.setBackground(fundo);
		mnEfeitos.add(mntmGradienteCruzadoEmX);
		mntmGradienteCruzadoEmX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaInformacoes();

				efeitos1 = true;
				panelDaImagem_ef.setBounds(0, 0, 256, 256);
				panelDaImagem_ef.setVisible(efeitos1);
				panel_3.add(panelDaImagem_ef);

				// chamada do m�todo para aplicar o efeito gradiente cruzado e retorna a imagem
				// resultado para o "panelDaImagem_ef"
				// podendo assim ser exibida
				panelDaImagem_ef.gradienteCruzadoEmX(panelDaImagem1.largura, panelDaImagem1.altura,
						panelDaImagem1.matrizImagem);

				lblFiltro.setText("Gradiente Cruzado / Roberts Cruzado em X");
				lblDica.setText("");
				
				String[] mascara = { "1", "0", "0", "-1" };
				plotaMascara(mascara);
			}

		});
		
		JMenuItem mntmGradienteCruzadoEmY = new JMenuItem("Roberts Cruzado: Y"); // Bot�o Gradiente Cruzado
		mntmGradienteCruzadoEmY.setForeground(letra);
		mntmGradienteCruzadoEmY.setFont(fonte);
		mntmGradienteCruzadoEmY.setBackground(fundo);
		mnEfeitos.add(mntmGradienteCruzadoEmY);
		mntmGradienteCruzadoEmY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaInformacoes();

				efeitos1 = true;
				panelDaImagem_ef.setBounds(0, 0, 256, 256);
				panelDaImagem_ef.setVisible(efeitos1);
				panel_3.add(panelDaImagem_ef);

				// chamada do m�todo para aplicar o efeito gradiente cruzado e retorna a imagem
				// resultado para o "panelDaImagem_ef"
				// podendo assim ser exibida
				panelDaImagem_ef.gradienteCruzadoEmY(panelDaImagem1.largura, panelDaImagem1.altura,
						panelDaImagem1.matrizImagem);

				lblFiltro.setText("Gradiente Cruzado / Roberts Cruzado em Y");
				lblDica.setText("");
				
				String[] mascara = { "0", "1", "-1", "0" };
				plotaMascara(mascara);
			}

		});
		
		JMenuItem mntmGradienteCruzado = new JMenuItem("Roberts Cruzado: Magnitude"); // Bot�o Gradiente Cruzado
		mntmGradienteCruzado.setForeground(letra);
		mntmGradienteCruzado.setFont(fonte);
		mntmGradienteCruzado.setBackground(fundo);
		mnEfeitos.add(mntmGradienteCruzado);
		mntmGradienteCruzado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaInformacoes();

				efeitos1 = true;
				panelDaImagem_ef.setBounds(0, 0, 256, 256);
				panelDaImagem_ef.setVisible(efeitos1);
				panel_3.add(panelDaImagem_ef);

				// chamada do m�todo para aplicar o efeito gradiente cruzado e retorna a imagem
				// resultado para o "panelDaImagem_ef"
				// podendo assim ser exibida
				panelDaImagem_ef.gradienteCruzado(panelDaImagem1.largura, panelDaImagem1.altura,
						panelDaImagem1.matrizImagem);

				lblFiltro.setText("Roberts Cruzado");
				lblDica.setText("");
				
			}

		});

		
		JMenu mnOperaesLgicas = new JMenu("Logicos");// Menu das operaçoes
		mnOperaesLgicas.setForeground(letra);
		mnOperaesLgicas.setFont(fonte);
		mnOperaesLgicas.setBackground(fundo);
		mnOperaesLgicas.setBackground(fundo);
		menuBar.add(mnOperaesLgicas);

		JMenuItem xor = new JMenuItem("Xor");// Bot�o para aplicar "xor" entre duas imagens
		xor.setForeground(letra);
		xor.setFont(fonte);
		xor.setBackground(fundo);
		mnOperaesLgicas.add(xor);
		xor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaInformacoes();

				logico = true;
				panelDaImagem_ol.setBounds(0, 0, 256, 256);
				panelDaImagem_ol.setVisible(logico);
				panel_3.add(panelDaImagem_ol);

				// chamada do m�todo para aplicar o efeito de "xor" duas imagens e retorna a
				// imagem resultado para o "panelDaImagem_ol"
				// podendo assim ser exibida
				panelDaImagem_ol.logica_xor(panelDaImagem1.largura, panelDaImagem1.altura, panelDaImagem1.matrizImagem,
						panelDaImagem2.largura, panelDaImagem2.altura, panelDaImagem2.matrizImagem);

				lblFiltro.setText("XOR Logico");
			}

		});

		JMenuItem and = new JMenuItem("And"); // Botao para aplicar um "And" entre duas imagens
		and.setForeground(letra);
		and.setFont(fonte);
		and.setBackground(fundo);
		mnOperaesLgicas.add(and);
		and.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaInformacoes();

				logico = true;
				panelDaImagem_ol.setBounds(0, 0, 256, 256);
				panelDaImagem_ol.setVisible(logico);
				panel_3.add(panelDaImagem_ol);

				// chamada do m�todo para aplicar o efeito de "and" duas imagens e retorna a
				// imagem resultado para o "panelDaImagem_ol"
				// podendo assim ser exibida
				panelDaImagem_ol.logica_and(panelDaImagem1.largura, panelDaImagem1.altura, panelDaImagem1.matrizImagem,
						panelDaImagem2.largura, panelDaImagem2.altura, panelDaImagem2.matrizImagem);

				lblFiltro.setText("AND Logico");
			}

		});

		JMenuItem or = new JMenuItem("Or"); // Bot�o para aplicar um "or" entre duas imagens
		or.setForeground(letra);
		or.setFont(fonte);
		or.setBackground(fundo);
		mnOperaesLgicas.add(or);
		or.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaInformacoes();

				logico = true;
				panelDaImagem_ol.setBounds(0, 0, 256, 256);
				panelDaImagem_ol.setVisible(logico);
				panel_3.add(panelDaImagem_ol);

				// chamada do m�todo para aplicar o efeito de "or" duas imagens e retorna a
				// imagem resultado para o "panelDaImagem_ol"
				// podendo assim ser exibida
				panelDaImagem_ol.logica_or(panelDaImagem1.largura, panelDaImagem1.altura, panelDaImagem1.matrizImagem,
						panelDaImagem2.largura, panelDaImagem2.altura, panelDaImagem2.matrizImagem);

				lblFiltro.setText("OR Logico");
			}

		});

		JMenu efeito_negativo = new JMenu("Negativo");
		efeito_negativo.setForeground(letra);
		efeito_negativo.setFont(fonte);
		efeito_negativo.setBackground(fundo);
		menuBar.add(efeito_negativo);

		JMenuItem mntmNegativo = new JMenuItem("Negativo"); // Botao efeito negativo
		mntmNegativo.setFont(fonte);
		mntmNegativo.setForeground(letra);
		mntmNegativo.setBackground(fundo);
		efeito_negativo.add(mntmNegativo);
		mntmNegativo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaInformacoes();

				efeitos1 = true;
				panelDaImagem_ef.setBounds(0, 0, 256, 256);
				panelDaImagem_ef.setVisible(efeitos1);
				panel_3.add(panelDaImagem_ef);

				// chamada do m�todo para aplicar o efeito negativo e retorna a imagem resultado
				// para o "panelDaImagem_ef"
				// podendo assim ser exibida
				panelDaImagem_ef.negativo(panelDaImagem1.largura, panelDaImagem1.altura, panelDaImagem1.matrizImagem);
				
				lblFiltro.setText("Negativo");
			}
		});

		JMenuItem gamma = new JMenuItem("Gamma");
		gamma.setForeground(letra);
		gamma.setFont(fonte);
		gamma.setBackground(fundo);
		efeito_negativo.add(gamma);
		gamma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaInformacoes();

				efeitos1 = true;
				panelDaImagem_ef.setBounds(0, 0, 256, 256);
				panelDaImagem_ef.setVisible(efeitos1);
				panel_3.add(panelDaImagem_ef);
				double gamma = 10;
				gamma = Double.parseDouble(JOptionPane.showInputDialog("Gamma: ", gamma));

				panelDaImagem_ef.negativo_gamma(panelDaImagem1.largura, panelDaImagem1.altura,
						panelDaImagem1.matrizImagem, gamma);

				lblFiltro.setText("Gamma");
			}

		});

		JMenuItem logaritmica = new JMenuItem("Logaritmo");
		logaritmica.setForeground(letra);
		logaritmica.setFont(fonte);
		logaritmica.setBackground(fundo);
		efeito_negativo.add(logaritmica);
		logaritmica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaInformacoes();

				efeitos1 = true;
				panelDaImagem_ef.setBounds(0, 0, 256, 256);
				panelDaImagem_ef.setVisible(efeitos1);
				panel_3.add(panelDaImagem_ef);
				double a = 2;
				a = Double.parseDouble(JOptionPane.showInputDialog("Constante: ", a));
				panelDaImagem_ef.negativo_logaritmico(panelDaImagem1.largura, panelDaImagem1.altura,
						panelDaImagem1.matrizImagem, a);

				lblFiltro.setText("Logaritmo");
			}

		});

		JMenuItem itf_sigmoide = new JMenuItem("ITF Sigmoide");
		itf_sigmoide.setForeground(letra);
		itf_sigmoide.setFont(fonte);
		itf_sigmoide.setBackground(fundo);
		efeito_negativo.add(itf_sigmoide);
		itf_sigmoide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaInformacoes();

				efeitos1 = true;
				panelDaImagem_ef.setBounds(0, 0, 256, 256);
				panelDaImagem_ef.setVisible(efeitos1);
				panel_3.add(panelDaImagem_ef);
				int w = Integer.parseInt(JOptionPane.showInputDialog("Preencha com o valor w", "127"));
				int sigma = Integer.parseInt(JOptionPane.showInputDialog("Preencha com o valor Sigma", "25"));
				panelDaImagem_ef.itf_sigmoide(panelDaImagem1.largura, panelDaImagem1.altura,
						panelDaImagem1.matrizImagem,w,sigma);

				lblFiltro.setText("ITF Sigmoide");
			}

		});

		JMenuItem faixa_dinamica = new JMenuItem("Faixa Dinamica");
		faixa_dinamica.setForeground(letra);
		faixa_dinamica.setFont(fonte);
		faixa_dinamica.setBackground(fundo);
		efeito_negativo.add(faixa_dinamica);
		faixa_dinamica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaInformacoes();

				efeitos1 = true;
				panelDaImagem_ef.setBounds(0, 0, 256, 256);
				panelDaImagem_ef.setVisible(efeitos1);
				panel_3.add(panelDaImagem_ef);
				int w = 255;
				w = Integer.parseInt(JOptionPane.showInputDialog("w_target: ", w));
				panelDaImagem_ef.faixa_dinamica(panelDaImagem1.largura, panelDaImagem1.altura,
						panelDaImagem1.matrizImagem, w);

				lblFiltro.setText("Faixa Dinamica");
			}

		});

		JMenuItem transformacao_linear = new JMenuItem("Transformacao Linear");
		transformacao_linear.setForeground(letra);
		transformacao_linear.setFont(fonte);
		transformacao_linear.setBackground(fundo);
		efeito_negativo.add(transformacao_linear);
		transformacao_linear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaInformacoes();

				efeitos1 = true;
				panelDaImagem_ef.setBounds(0, 0, 256, 256);
				panelDaImagem_ef.setVisible(efeitos1);
				panel_3.add(panelDaImagem_ef);
				int contraste = 1;
				int brilho = 1;
				contraste = Integer
						.parseInt(JOptionPane.showInputDialog("Adicione um valor para o contraste: ", contraste));
				brilho = Integer.parseInt(JOptionPane.showInputDialog("Adicione um valor para o brilho: ", brilho));
				panelDaImagem_ef.transformacao_linear(panelDaImagem1.largura, panelDaImagem1.altura,
						panelDaImagem1.matrizImagem, contraste, brilho);

				lblFiltro.setText("Transformacao Linear");
			}
		});

		JMenu morfologia = new JMenu("Morfologia");
		morfologia.setForeground(letra);
		morfologia.setFont(fonte);
		morfologia.setBackground(fundo);
		menuBar.add(morfologia);

		JMenuItem dilatar = new JMenuItem("Dilatar");
		dilatar.setForeground(letra);
		dilatar.setFont(fonte);
		dilatar.setBackground(fundo);
		morfologia.add(dilatar);
		dilatar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaInformacoes();

				mor = true;
				panelDaImagem_mor.setBounds(0, 0, 256, 256);
				panelDaImagem_mor.setVisible(mor);
				panel_3.add(panelDaImagem_mor);


				try {
					String times = JOptionPane.showInputDialog("Preencha com o numero de vezes que deseja aplicar", "2");
					int timesInt = Integer.parseInt(times);
					
					BufferedImage parcial = converterBuffered(panelDaImagem1.matrizImagem);
					parcial = panelDaImagem_mor.dilate(parcial, timesInt, CROSS);
					
					String[] mascara = {"0", "1", "0", "1", "1", "1", "0", "1", "0"};
					plotaMascara(mascara);
					
					lblFiltro.setText("Dilatar");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "O valor de entrada deve ser um inteiro!");
				}
			}
		});
		
		JMenuItem erodir = new JMenuItem("Erodir");
		erodir.setForeground(letra);
		erodir.setFont(fonte);
		erodir.setBackground(fundo);
		morfologia.add(erodir);
		erodir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaInformacoes();

				mor = true;
				panelDaImagem_mor.setVisible(mor);
				panelDaImagem_mor.setBounds(0, 0, 256, 256);
				panel_3.add(panelDaImagem_mor);

				try {
					String times = JOptionPane.showInputDialog("Preencha com o numero de vezes que deseja aplicar", "2");
					int timesInt = Integer.parseInt(times);
					
					BufferedImage parcial = converterBuffered(panelDaImagem1.matrizImagem);
					parcial = panelDaImagem_mor.erode(parcial, timesInt, CROSS);
					
					String[] mascara = {"0", "1", "0", "1", "1", "1", "0", "1", "0"};
					plotaMascara(mascara);
					
					lblFiltro.setText("Erodir");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "O valor de entrada deve ser um inteiro!");
				}
			}
		});
		
		JMenuItem gradienteMor = new JMenuItem("Gradiente Morfologico");
		gradienteMor.setForeground(letra);
		gradienteMor.setFont(fonte);
		gradienteMor.setBackground(fundo);
		morfologia.add(gradienteMor);
		gradienteMor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaInformacoes();

				mor = true;
				panelDaImagem_mor.setVisible(mor);
				panelDaImagem_mor.setBounds(0, 0, 256, 256);
				panel_3.add(panelDaImagem_mor);

				try {
					BufferedImage erosao = converterBuffered(panelDaImagem1.matrizImagem);
					erosao = panelDaImagem_mor.erodir(erosao, CROSS);
					
					BufferedImage dilatacao = converterBuffered(panelDaImagem1.matrizImagem);
					dilatacao = panelDaImagem_mor.dilatar(dilatacao, CROSS);
					
					panelDaImagem_mor.subtract(dilatacao, erosao);
					
					lblFiltro.setText("Gradiente Morfologico");
		
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro inesperado!");
				}
			}
		});
		
		JMenuItem abertura = new JMenuItem("Abertura");
		abertura.setForeground(letra);
		abertura.setFont(fonte);
		abertura.setBackground(fundo);
		morfologia.add(abertura);
		abertura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaInformacoes();

				mor = true;
				panelDaImagem_mor.setVisible(mor);
				panelDaImagem_mor.setBounds(0, 0, 256, 256);
				panel_3.add(panelDaImagem_mor);

				try {
					String times = JOptionPane.showInputDialog("Preencha com o numero de vezes que deseja aplicar", "2");
					int timesInt = Integer.parseInt(times);
					
					BufferedImage parcial = converterBuffered(panelDaImagem1.matrizImagem);
					parcial = panelDaImagem_mor.open(parcial, timesInt, CROSS);
					
					lblFiltro.setText("Abertura");
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "O valor de entrada deve ser um inteiro!");
				}
			}
		});
		
		JMenuItem fechamento = new JMenuItem("Fechamento");
		fechamento.setForeground(letra);
		fechamento.setFont(fonte);
		fechamento.setBackground(fundo);
		morfologia.add(fechamento);
		fechamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaInformacoes();

				// TODO - AJUSTAR2
				mor = true;
				panelDaImagem_mor.setVisible(mor);
				panelDaImagem_mor.setBounds(0, 0, 256, 256);
				panel_3.add(panelDaImagem_mor);

				try {
					String times = JOptionPane.showInputDialog("Preencha com o numero de vezes que deseja aplicar", "2");
					int timesInt = Integer.parseInt(times);
					
					BufferedImage parcial = converterBuffered(panelDaImagem1.matrizImagem);
					parcial = panelDaImagem_mor.close(parcial,timesInt,CROSS);
					
					lblFiltro.setText("Fechamento");
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "O valor de entrada deve ser um inteiro!");
				}
			}
		});
		
		JMenuItem topHat = new JMenuItem("Top Hat");
		topHat.setForeground(letra);
		topHat.setFont(fonte);
		topHat.setBackground(fundo);
		morfologia.add(topHat);
		topHat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaInformacoes();

				mor = true;
				panelDaImagem_mor.setVisible(mor);
				panelDaImagem_mor.setBounds(0, 0, 256, 256);
				panel_3.add(panelDaImagem_mor);

				try {
					BufferedImage imagem = converterBuffered(panelDaImagem1.matrizImagem);
					BufferedImage abertura = converterBuffered(panelDaImagem1.matrizImagem);

					String times = JOptionPane.showInputDialog("Preencha com o n�mero de vezes que deseja aplicar", "1");
					int timesInt = Integer.parseInt(times);
					
					abertura = panelDaImagem_mor.open(abertura, timesInt, CROSS);
					panelDaImagem_mor.subtract(imagem, abertura);
					
					lblFiltro.setText("Top Hat");
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "O valor de entrada deve ser um inteiro!");
				}
			}
		});
		
		JMenuItem bottomHat = new JMenuItem("Bottom Hat");
		bottomHat.setForeground(letra);
		bottomHat.setFont(fonte);
		bottomHat.setBackground(fundo);
		morfologia.add(bottomHat);
		bottomHat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaInformacoes();

				mor = true;
				panelDaImagem_mor.setVisible(mor);
				panelDaImagem_mor.setBounds(0, 0, 256, 256);
				panel_3.add(panelDaImagem_mor);

				try {
					BufferedImage imagem = converterBuffered(panelDaImagem1.matrizImagem);
					BufferedImage fechamento = converterBuffered(panelDaImagem1.matrizImagem);

					String times = JOptionPane.showInputDialog("Preencha com o n�mero de vezes que deseja aplicar", "2");
					int timesInt = Integer.parseInt(times);
					
					fechamento = panelDaImagem_mor.close(fechamento, timesInt, CROSS);
					panelDaImagem_mor.subtract(fechamento, imagem);
					
					lblFiltro.setText("Bottom Hat");
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "O valor de entrada deve ser um inteiro!");
				}
			}
		});
		
		JMenu histograma = new JMenu("Histograma");
		histograma.setForeground(letra);
		histograma.setFont(fonte);
		histograma.setBackground(fundo);
		menuBar.add(histograma);

		JMenuItem equalizar = new JMenuItem("Equalizar");
		equalizar.setForeground(letra);
		equalizar.setFont(fonte);
		equalizar.setBackground(fundo);
		histograma.add(equalizar);
		panel_4.setVisible(true);
		panel_Pontos_4.setVisible(true);
		equalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaInformacoes();
				apresenta2Imagem();
				removeDaTelaNormalizado2(true);
				
				panel_Pontos_1.setVisible(false);
				panel_Pontos_3.setVisible(false);
				panel_Pontos_4.setVisible(false);
				lblMenorValor.setVisible(false);
				lblMaiorValor.setVisible(false);
				

				his = true;
				panelDaImagem_h.setBounds(0, 0, 256, 256);
				panelDaImagem_h.setVisible(his);
				panel_3.add(panelDaImagem_h);
			try {
				panelDaImagem_h.recebeImagem(panelDaImagem1.largura, panelDaImagem1.altura,panelDaImagem1.matrizImagem);
				
				lblFiltro.setText("Equalizar Histograma");
			} catch (Exception IllegalArgumentException) {
				JOptionPane.showMessageDialog(null, "Adicione a imagem!");
			}
			}

		});

		JMenu mnOperaesMatemticas = new JMenu("Operaçoes"); // Menu das operações
		mnOperaesMatemticas.setForeground(letra);
		mnOperaesMatemticas.setFont(fonte);
		mnOperaesMatemticas.setBackground(fundo);
		mnOperaesMatemticas.setBackground(fundo);
		menuBar.add(mnOperaesMatemticas);

		JMenuItem soma = new JMenuItem("Soma"); // Botão para aplicar efeito de somas as duas imagens
		soma.setForeground(letra);
		soma.setFont(fonte);
		soma.setBackground(fundo);
		mnOperaesMatemticas.add(soma);
		soma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaInformacoes();

				aritmeticos = true;
				panelDaImagem_om.setBounds(0, 0, 256, 256);
				panelDaImagem_om.setVisible(aritmeticos);
				panel_3.add(panelDaImagem_om);

				try {
					// chamada do m�todo para aplicar o efeito de somar duas imagens e retorna a
					// imagem resultado para o "panelDaImagem_om"
					// podendo assim ser exibida
					panelDaImagem_om.soma(panelDaImagem1.largura, panelDaImagem1.altura, panelDaImagem1.matrizImagem,
							panelDaImagem2.largura, panelDaImagem2.altura, panelDaImagem2.matrizImagem);
					
					lblFiltro.setText("Operacao de Soma");
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

		});

		JMenuItem subtracao = new JMenuItem("Subtracao"); // Bot�o de subtra��o das duas imagens
		subtracao.setForeground(letra);
		subtracao.setFont(fonte);
		subtracao.setBackground(fundo);
		mnOperaesMatemticas.add(subtracao);
		subtracao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaInformacoes();

				aritmeticos = true;
				panelDaImagem_om.setBounds(0, 0, 256, 256);
				panelDaImagem_om.setVisible(aritmeticos);
				panel_3.add(panelDaImagem_om);

				// chamada do m�todo para aplicar o efeito de subtrair as duas imagens e retorna
				// a imagem resultado para o "panelDaImagem_om"
				// podendo assim ser exibida
				panelDaImagem_om.subtracao(panelDaImagem1.largura, panelDaImagem1.altura, panelDaImagem1.matrizImagem,
						panelDaImagem2.largura, panelDaImagem2.altura, panelDaImagem2.matrizImagem);

				lblFiltro.setText("Operacao de Subtracao");
			}

		});

		JMenuItem multiplicacao = new JMenuItem("Multiplicacao"); // Bot�o para multiplicar as duas imagens
		multiplicacao.setForeground(letra);
		multiplicacao.setFont(fonte);
		multiplicacao.setBackground(fundo);
		mnOperaesMatemticas.add(multiplicacao);
		multiplicacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaInformacoes();

				aritmeticos = true;
				panelDaImagem_om.setBounds(0, 0, 256, 256);
				panelDaImagem_om.setVisible(aritmeticos);
				panel_3.add(panelDaImagem_om);

				// chamada do m�todo para aplicar o efeito de multiplicar duas imagens e retorna
				// a imagem resultado para o "panelDaImagem_om"
				// podendo assim ser exibida
				panelDaImagem_om.multiplicacao(panelDaImagem1.largura, panelDaImagem1.altura,
						panelDaImagem1.matrizImagem, panelDaImagem2.largura, panelDaImagem2.altura,
						panelDaImagem2.matrizImagem);

				lblFiltro.setText("Operacao de Multiplicao");
			}

		});

		JMenuItem mntmDiviso = new JMenuItem("Divisao"); // Bot�o divis�o
		mntmDiviso.setForeground(letra);
		mntmDiviso.setFont(fonte);
		mntmDiviso.setBackground(fundo);
		mnOperaesMatemticas.add(mntmDiviso);
		mntmDiviso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaInformacoes();

				aritmeticos = true;
				panelDaImagem_om.setBounds(0, 0, 256, 256);
				panelDaImagem_om.setVisible(aritmeticos);
				panel_3.add(panelDaImagem_om);

				// chamada do m�todo para aplicar o efeito de dividir duas imagens e retorna a
				// imagem resultado para o "panelDaImagem_om"
				// podendo assim ser exibida
				panelDaImagem_om.divisao(panelDaImagem1.largura, panelDaImagem1.altura, panelDaImagem1.matrizImagem,
						panelDaImagem2.largura, panelDaImagem2.altura, panelDaImagem2.matrizImagem);

				lblFiltro.setText("Operacao de Divisao");
			}

		});

		JPanel painel1 = new JPanel();
		painelPrincipal.add(painel1);
		painel1.setLayout(null);

		panelDaImagem1.setBounds(30, 30, 256, 256);
		panelDaImagem1.setVisible(true);
		painel1.add(panelDaImagem1);

		JButton btAbrirImagem1 = new JButton("Abrir Imagem");// Bot�o abrir imagem
		btAbrirImagem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					// Instanciacao de fileChooser e alteracao do diretorio para buscar a imagem
					final JFileChooser fileChooser = new JFileChooser();
					fileChooser.setCurrentDirectory(new File("src/"));

					// Verificacao do fileChooser
					if (fileChooser.showOpenDialog(btAbrirImagem1) == JFileChooser.APPROVE_OPTION) {

						// Cria um file onde eh armazenada a imagem
						File file = fileChooser.getSelectedFile();

						apresenta2Imagem();
						panelDaImagem1.colocaImagemNoPainel(file.getPath());
						panelDaImagem1.plotaPixels(panel_Pontos_1);
					}

				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null, "Nao foi possivel carregar a imagem.");

				}
			}
		});

		btAbrirImagem1.setBounds(150, 403, 131, 33);
		painelPrincipal.add(btAbrirImagem1);

		panel_2.setBorder(new LineBorder(fundo, 3));
		panel_2.setBounds(384, 122, 256, 256);
		painelPrincipal.add(panel_2);
		panel_2.setLayout(null);

		panelDaImagem2.setBounds(0, 0, 256, 256);
		panelDaImagem2.setVisible(true);
		panel_2.add(panelDaImagem2);

		panelDaImagem3.setBounds(0, 0, 256, 256);
		panelDaImagem3.setVisible(true);
		panel_3.add(panelDaImagem3);

		panelDaImagem4.setBounds(0, 0, 256, 256);
		panelDaImagem4.setVisible(true);
		panel_4.add(panelDaImagem4);

		JButton btAbrirImagem2 = new JButton("Abrir Imagem");
		btAbrirImagem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					// Instanciacao de fileChooser e alteracao do diretorio para buscar a imagem
					final JFileChooser fileChooser = new JFileChooser();
					fileChooser.setCurrentDirectory(new File("src/"));

					// Verificacao do fileChooser
					if (fileChooser.showOpenDialog(btAbrirImagem2) == JFileChooser.APPROVE_OPTION) {
						// Cria um file onde eh armazenada a imagem
						File file = fileChooser.getSelectedFile();

						panelDaImagem2.colocaImagemNoPainel(file.getPath());
						panelDaImagem2.plotaPixels(panel_Pontos_2);
						apresenta2Imagem();
						repaint();
						validate();
					}

				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null, "Nao foi possivel carregar a imagem.");
				}
			}
		});

		btAbrirImagem2.setBounds(450, 403, 131, 33);
		painelPrincipal.add(btAbrirImagem2);

		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(743, 406, 163, 24);
		painelPrincipal.add(lblNewLabel);
		panelDaImagem1.setBounds(86, 122, 256, 256);

		panel_Pontos_1.setBounds(87, 456, 261, 261);
		panel_Pontos_2.setBounds(384, 456, 261, 261);
		panel_Pontos_3.setBounds(681, 456, 261, 261);
		painelPrincipal.add(panel_Pontos_1);
		painelPrincipal.add(panel_Pontos_2);
		painelPrincipal.add(panel_Pontos_3);

		panel_4.setLayout(null);
		panel_4.setBorder(BorderFactory.createEtchedBorder());
		panel_4.setBounds(973, 122, 256, 256);
		panel_4.setVisible(false);
		painelPrincipal.add(panel_4);

		panel_Pontos_4.setBounds(973, 456, 261, 261);
		panel_Pontos_4.setVisible(false);
		painelPrincipal.add(panel_Pontos_4);
		lblDica.setHorizontalAlignment(SwingConstants.CENTER);

		lblDica.setBounds(384, 51, 845, 46);
		painelPrincipal.add(lblDica);

		panelMascara.setBounds(168, 28, 74, 80);
		painelPrincipal.add(panelMascara);
		lblMenorValor.setHorizontalAlignment(SwingConstants.CENTER);

		lblMenorValor.setBounds(973, 730, 261, 16);
		painelPrincipal.add(lblMenorValor);
		lblMaiorValor.setHorizontalAlignment(SwingConstants.CENTER);

		lblMaiorValor.setBounds(973, 755, 261, 16);
		painelPrincipal.add(lblMaiorValor);
		
		lblFiltro.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFiltro.setHorizontalAlignment(SwingConstants.CENTER);
		lblFiltro.setBounds(549, 13, 482, 25);
		painelPrincipal.add(lblFiltro);
		
		painelPrincipal.add(panelDaImagem1);
		panelDaImagem1.setVisible(true);
	}

	/**
	 * Remove a 2 imagem e/ou a parte da imagem normalizada
	 */
	public static void removeElementos() {
		if (geo || efeitos1 || mor) {
			panel_Pontos_2.setVisible(false);
			panel_2.setVisible(false);
			panelDaImagem2.setVisible(false);

			if (geo) {
				removeDaTelaNormalizado(false);
				lblMenorValor.setText("");
				lblMaiorValor.setText("");
			}
		} else {
			apresenta2Imagem();
		}
	}

	private BufferedImage converterBuffered(int[][] mtzImg) {
		// matriz que vc passa como parametro definindo a largura da imagem
		int largura = mtzImg.length,
				// matriz que vc passa como parametro definindo a altura da imagem
				altura = mtzImg[0].length;

		// criando uma objeto BufferedImage a partir das dimens�es da imagem
		// representada pela matriz
		BufferedImage image = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);

		WritableRaster raster = image.getRaster();
		for (int h = 0; h < largura; h++) {
			for (int w = 0; w < altura; w++) {
				raster.setSample(h, w, 0, mtzImg[h][w]);
			}
		}

		return image;
	}

	public int corPixel(int corRGB) {
		Color cor = new Color(corRGB, corRGB, corRGB);
		return cor.getRGB();
	}

	/**
	 * Oculta o painel da imagem/pontos normalizados Ser� utilizado para as
	 * opera��es geometricas
	 * 
	 * @param tf - True para apresentar. False para ocultar
	 */
	public static void removeDaTelaNormalizado(boolean tf) {
		panel_4.setVisible(false);
		panel_Pontos_4.setVisible(false);
	}
	public static void removeDaTelaNormalizado2(boolean tf) {
		panel_4.setVisible(true);
		panel_Pontos_4.setVisible(true);
	}

	/**
	 * Apresenta a segunda imagem N�o apresenta para opera��es que utilizam apenas 1
	 * imagem, como os efeitos
	 */
	public static void apresenta2Imagem() {
		panel_2.setVisible(true);
		panelDaImagem2.setVisible(true);
		panel_Pontos_2.setVisible(true);
	}

	private void limpaInformacoes() {
		lblDica.setText("");
		lblFiltro.setText("");
		panelMascara.setVisible(false);
		lblNewLabel.setText("Imagem Resultado");

		logico = false;
		aritmeticos = false;
		efeitos1 = false;
		his = false;
		geo = false;
		mor = false;
		panelDaImagem_h.setVisible(false);
		panelDaImagem_om.setVisible(false);
		panelDaImagem_ol.setVisible(false);
		panelDaImagem_ef.setVisible(false);
		panelDaImagem_mor.setVisible(false);
	}

	public void plotaMascara(String[] mascara) {
		removeElementos();
		panelMascara.removeAll();
		panelMascara.setLayout(new GridLayout(3, 3));
		panelMascara.setBorder(BorderFactory.createLineBorder(fundo));
		panelMascara.setVisible(true);

		for (int i = 0; i < mascara.length; i++) {
			String text = mascara[i];
			JLabel label = new JLabel(text, SwingConstants.CENTER);
			label.setBorder(BorderFactory.createLineBorder(letra));
			panelMascara.add(label);
		}
		panelMascara.validate();
	}
}
