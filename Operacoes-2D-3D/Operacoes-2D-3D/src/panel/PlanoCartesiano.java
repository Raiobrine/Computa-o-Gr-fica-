package panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.JPanel;
import algoritmos.DesenhosFiguras;
import algoritmos.Transformacao;
import algoritmos.Transformacao3D;
import model.Ponto;
import view.Operacoes2D_3D;

public class PlanoCartesiano extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3652299627184303562L;
	public final static int ALTURA = 600;
	public final static int LARGURA = 600;
	public final static int MEIO_X = LARGURA/2;
	public final static int MEIO_Y = ALTURA/2;
	private static BufferedImage plano;
	public static List<Ponto> pontos;
	public static Ponto xy;

	
	public PlanoCartesiano() {
		
		
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent event) {
				
				int x = event.getPoint().x;
				int y = event.getPoint().y;
				Operacoes2D_3D.label_x.setText(String.valueOf(x - PlanoCartesiano.MEIO_X));
				Operacoes2D_3D.label_y.setText(String.valueOf(PlanoCartesiano.MEIO_Y - y)); 
			}
			@Override
			public void mouseDragged(MouseEvent event) {
			}
		});
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				switch (Operacoes2D_3D.comboBox.getSelectedIndex()) {
					case 0:
						setPixel(new Ponto(e.getPoint().x - PlanoCartesiano.MEIO_X, PlanoCartesiano.MEIO_Y - e.getPoint().y));
						break;
				}
			}
		});
		
		
		
		plano = new BufferedImage(LARGURA, ALTURA, BufferedImage.TYPE_INT_ARGB);
		setBounds(0, 0, LARGURA, ALTURA);
		zerarImagem();
	}
	
	public void calcularTransformacoes(int x, int y, int z) {
		
		if (Operacoes2D_3D.comboBox.getSelectedItem().equals("CUBO")) {
			if (Operacoes2D_3D.rb_transl.isSelected()) {
				pontos = new Transformacao3D().translacao(pontos, x, y, z);		
			} else if (Operacoes2D_3D.rb_escala.isSelected()) {
				pontos = new Transformacao3D().escala(pontos, x, y, z);
			} else if (Operacoes2D_3D.rb_cis.isSelected()) {
				if ( y == 0 && z == 0) {
					pontos = new Transformacao3D().cisalhamentoEmX(pontos, x);
				} else if (x == 0 && z == 0) {
					pontos = new Transformacao3D().cisalhamentoEmY(pontos, y);
				} else if (y == 0 && x == 0) {
					pontos = new Transformacao3D().cisalhamentoEmZ(pontos, z);
				}
				
			}
			zerarImagem();
			setPixel3D();
			
		} else {
			if (Operacoes2D_3D.rb_transl.isSelected()) {
				pontos = new Transformacao().translacao(pontos, x, y);		
			} else if (Operacoes2D_3D.rb_escala.isSelected()) {
				pontos = new Transformacao().escala(pontos, x, y);
			} else if (Operacoes2D_3D.rb_cis.isSelected()) {
				pontos = new Transformacao().cisalhamento(pontos, x, y);
			}
			
			zerarImagem();
			//Necessário o if para que seja colocado o pixel certo na circunferência
			if (Operacoes2D_3D.comboBox.getSelectedItem().equals("ELIPSE") ||
				Operacoes2D_3D.comboBox.getSelectedItem().equals("CIRCUNFERENCIA")) {
				setCircunferencia();
			} else {
				for (Ponto ponto : pontos)
					setPixel(ponto);
			}
		}
	}
	
	/**
	 * Classe reponsável por calcular as reflexões 2D e 3D
	 */
	public void calcularReflexao() {
		if (Operacoes2D_3D.rb_rflx.isSelected()) {
			pontos = new Transformacao().reflexao(pontos, 0);
			zerarImagem();
			for (Ponto ponto : pontos)
				setPixel(ponto);
			
		} else if (Operacoes2D_3D.rb_rfly.isSelected()) {
			pontos = new Transformacao().reflexao(pontos, 1);
			zerarImagem();
			for (Ponto ponto : pontos)
				setPixel(ponto);
			
		} else if (Operacoes2D_3D.rb_rflx_y.isSelected()) {
			pontos = new Transformacao().reflexao(pontos, 2);
			zerarImagem();
			for (Ponto ponto : pontos)
				setPixel(ponto);
			
		} else if (Operacoes2D_3D.rb_rflxy.isSelected()) {
			pontos = new Transformacao3D().reflexaoXY(pontos);
			zerarImagem();
			setPixel3D();
			
		} else if (Operacoes2D_3D.rb_rflxz.isSelected()) {
			pontos = new Transformacao3D().reflexaoXZ(pontos);
			zerarImagem();
			setPixel3D();
			
		} else if (Operacoes2D_3D.rb_rflyz.isSelected()) {
			pontos = new Transformacao3D().reflexaoYZ(pontos);
			zerarImagem();
			setPixel3D();
		}
	}
	
	public void calcularRotacao(double angulo) {
		zerarImagem();
		if (Operacoes2D_3D.comboBox.getSelectedItem().equals("CUBO")) {
			if(Operacoes2D_3D.rdbtnRx.isSelected()) {
				pontos = new Transformacao3D().rotacaoX(pontos, angulo);
			} else if (Operacoes2D_3D.rdbtnRy.isSelected()) {
				pontos = new Transformacao3D().rotacaoY(pontos, angulo);
			} else if (Operacoes2D_3D.rdbtnRz.isSelected()) {
				pontos = new Transformacao3D().rotacaoZ(pontos, angulo);
			}
			
			setPixel3D();
			
			
		} else if (Operacoes2D_3D.comboBox.getSelectedItem().equals("ELIPSE") ||
					Operacoes2D_3D.comboBox.getSelectedItem().equals("CIRCUNFERENCIA")){
			pontos = new Transformacao().rotacao(pontos, angulo);
			setCircunferencia();
			
		} else {
			pontos = new Transformacao().rotacao(pontos, angulo);
			for (Ponto ponto : pontos) {
				setPixel(ponto);
			}
		}
		
	}
	
	/**
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @throws NullPointerException
	 * @throws NumberFormatException
	 */
	public void calcularReta(int x1, int y1, int x2, int y2) throws NullPointerException, NumberFormatException {
		if (Operacoes2D_3D.rdbtnDda.isSelected()) {
			pontos = new DesenhosFiguras().DDA(new Ponto(x1, y1), new Ponto(x2, y2));
		} else if(Operacoes2D_3D.rdbtnPontoMdio_1.isSelected()){
			pontos = new DesenhosFiguras().retaPontoMedio(new Ponto(x1, y1), new Ponto(x2, y2));
		}								
			
		for (Ponto ponto : pontos)
			setPixel(ponto);
	}
	
	public void calcularCircunferencia(int raio) throws NullPointerException, NumberFormatException, Exception {
		if (raio > PlanoCartesiano.MEIO_X || raio > PlanoCartesiano.MEIO_Y)
			throw new Exception();
		
		if (Operacoes2D_3D.rdbtnEquaoExplicita.isSelected()) {
			pontos = new DesenhosFiguras().CircunferenciaEqExplicita(raio);
			setCircunferencia();
		} else if(Operacoes2D_3D.rdbtnPontoMdio.isSelected()) {
			pontos = new DesenhosFiguras().CircunferenciaPontoMedio(raio);
			setCircunferencia(); 
		} else if(Operacoes2D_3D.rdbtnTrigonometrica.isSelected()) {
			pontos = new DesenhosFiguras().CircunferenciaTrigonometrica(raio);
			setCircunferencia(); 
		}
	}
	
	/**
	 * Os parâmetros são as coordenadas dos raios em x e em y
	 * O método chama a função da ElipsePontoMedio passando os parâmetros, após receber os pontos
	 * desenha como setCircunferência
	 * @param x
	 * @param y
	 */
	public void CalcularElipse(int x, int y) {
		zerarImagem();
		pontos = new DesenhosFiguras().ElipsePontoMedio(x, y);
		setCircunferencia();
	}
	
	public void calcularQuadrado(int x, int y){
		zerarImagem();
		pontos = new DesenhosFiguras().quadrado(x, y);
		for (Ponto ponto: pontos) {
			setPixel(ponto);
		}
	}
	
	public void calcularCubo(int x, int y, int z) {
		zerarImagem();
		this.eixoZ();
		pontos = new DesenhosFiguras().criarCubo(x, y, z);
		
		setPixel3D();
	}
	
	private void setPixel3D() {
		eixoZ();
		for (Ponto ponto : pontos) {
			try {
				if (ponto.getZ() == 0) {
					setPixel(new Ponto (ponto.getX() + PlanoCartesiano.MEIO_X, PlanoCartesiano.MEIO_Y - ponto.getY() ));
				} else {
					setPixel(new Ponto(ponto.getX() + PlanoCartesiano.MEIO_X - ponto.getZ() / 2, PlanoCartesiano.MEIO_Y - ponto.getY() + ponto.getZ() / 2));
				}

			} catch (Exception e) {
				System.out
						.println("Erro ao povoar os valores nas 3 dimensões.");
			}
		}
	}
	
	/**
	 * Limpa todo o plano carteziano, logo após recoloca as coordenadas
	 */
	public void zerarImagem() {
		
		for (int i = 0; i < LARGURA; i++)
			for (int j = 0; j < ALTURA; j++) {
				plano.setRGB(i, j, Color.BLACK.getRGB());
				repaint();
		}
		
		coordenadas();
	}
	/**
	 * Método seta os pixels no plano cartesiano, é utilizado para a elipse e para os demais algoritmos
	 * da circunferência
	 */
	private void setCircunferencia() {
		for (Ponto ponto : pontos) {
			setPixel(new Ponto(ponto.getX() + PlanoCartesiano.MEIO_X, PlanoCartesiano.MEIO_Y - ponto.getY()));
		}
	}
	
	
	/**
	 * Método privado sem retorno, realiza os calculos para poder setar as coordenadas
	 */
	private void coordenadas() {
		
		int meio_y = ALTURA/2;
		int meio_x = LARGURA/2;
		
		for (int i = 0; i < ALTURA; i++) {
			plano.setRGB(meio_x, i, Color.GREEN.getRGB());
			repaint();
		}
		
		for (int i = 0; i < LARGURA; i++) {
			plano.setRGB(i, meio_y, Color.GREEN.getRGB());
			repaint();
		}
		
	}
	
	/**
	 * Método insere no plano cartesiano a 3ª dimensão
	 */
	public void eixoZ() {
		for (int j = 1; j < LARGURA - 1; j++)
			plano.setRGB(j, LARGURA - j, Color.GREEN.getRGB());
	}
	
	/**
	 * Não há retorno, este método chama o metodo setRGB do BufferedImage para setar o pixel no painel
	 * @param pixel
	 */
	public void setPixel(Ponto pixel) {
		try {
			plano.setRGB(pixel.getX(), pixel.getY(), Color.YELLOW.getRGB());
		} catch (ArrayIndexOutOfBoundsException e) {}
		finally {
			repaint();
		}
		
		
	}
	
	public List<Ponto> getPontos() {
		return pontos;
	}
	
	public BufferedImage getPlano() {
		return plano;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.create();
		g.drawImage(plano, 0, 0, null);
	}
	
}
