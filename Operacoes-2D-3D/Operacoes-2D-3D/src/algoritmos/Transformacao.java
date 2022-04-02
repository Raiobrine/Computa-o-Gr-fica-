package algoritmos;

import java.util.ArrayList;
import java.util.List;
import model.Matriz;
import model.Ponto;


/*
 * Classe responsável pelas trasnformações 2D e 3D
 */
public class Transformacao {
	
	/**
	 * 
	 * 1  0  tx 
	 * 0  1  ty
	 * 0  0  1 
	 * 
	 */
	private double[][] matrizTranslacao(int tx, int ty) {
		double[][] matriz = new double[3][3];

		matriz[0][0] = 1;
		matriz[0][1] = 0;
		matriz[0][2] = tx;

		matriz[1][0] = 0;
		matriz[1][1] = 1;
		matriz[1][2] = ty;

		matriz[2][0] = 0;
		matriz[2][1] = 0;
		matriz[2][2] = 1;
		return matriz;
	}
	
	/**
	 * 
	 * Sx 0  0 
	 * 0  Sy 0
	 * 0  0  1 
	 * 
	 */

	private double[][] matrizEscala(int sx, int sy) {
		double[][] matriz = new double[3][3];
		
		matriz[0][0] = sx;
		matriz[1][0] = 0;
		matriz[2][0] = 0;

		matriz[0][1] = 0;
		matriz[1][1] = sy;
		matriz[2][1] = 0;

		matriz[0][2] = 0;
		matriz[1][2] = 0;
		matriz[2][2] = 1;

		return matriz;
	}
	
	/**
	 * 
	 */
	private double[][] matrizRotacao(double teta) {
		double[][] matriz2D = new double[3][3];

		double seno = Math.sin(Math.toRadians(teta));
		double coseno = Math.cos(Math.toRadians(teta));

		
		// Coluna 0
		matriz2D[0][0] = Math.abs(coseno);
		matriz2D[1][0] = seno;
		matriz2D[2][0] = 0;
		// Coluna 1
		matriz2D[0][1] = -seno;
		matriz2D[1][1] = Math.abs(coseno);
		matriz2D[2][1] = 0;
		// Coluna 2
		matriz2D[0][2] = 0;
		matriz2D[1][2] = 0;
		matriz2D[2][2] = 1;

		return matriz2D;
	}
	
	private double[][] matrizReflexaoX() {

		double[][] matriz = new double[3][3];

		// Linha 0
		matriz[0][0] = 1;
		matriz[1][0] = 0;
		matriz[2][0] = 0;
		// Linha 1
		matriz[0][1] = 0;
		matriz[1][1] = -1;
		matriz[2][1] = 0;
		// Linha 2
		matriz[0][2] = 0;
		matriz[1][2] = 0;
		matriz[2][2] = 1;

		return matriz;
	}

	private double[][] matrizReflexaoY() {

		double[][] matriz = new double[3][3];

		// Linha 0
		matriz[0][0] = -1;
		matriz[1][0] = 0;
		matriz[2][0] = 0;
		// Linha 1
		matriz[0][1] = 0;
		matriz[1][1] = 1;
		matriz[2][1] = 0;
		// Linha 2
		matriz[0][2] = 0;
		matriz[1][2] = 0;
		matriz[2][2] = 1;

		return matriz;
	}

	private double[][] matrizReflexaoXY() {

		double[][] matriz = new double[3][3];

		// Linha 0
		matriz[0][0] = -1;
		matriz[1][0] = 0;
		matriz[2][0] = 0;
		// Linha 1
		matriz[0][1] = 0;
		matriz[1][1] = -1;
		matriz[2][1] = 0;
		// Linha 2
		matriz[0][2] = 0;
		matriz[1][2] = 0;
		matriz[2][2] = 1;

		return matriz;
	}

	/**
	 * 0 0 1
	 * 0 1 0
	 * 1 0 0
	 * @return
	 */
	private double[][] matrizReflexaoReta() {//M�todo que gera matriz de reflex�o da reta

		double[][] matriz = new double[3][3];

		// Linha 0
		matriz[0][0] = 0;
		matriz[1][0] = 1;
		matriz[2][0] = 1;
		// Linha 1
		matriz[0][1] = 1;
		matriz[1][1] = 0;
		matriz[2][1] = 0;
		// Linha 2
		matriz[0][2] = 0;
		matriz[1][2] = 0;
		matriz[2][2] = 1;

		return matriz;
	}
	

	/**
	 * Matriz de cisalhamento
	 * 	1	a 	0
	 * 	b	1 	0
	 * 	0 	0	1
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private double[][] matrizCisalhamento(int a, int b) {//M�todo que gera matriz de cisalhamento

		double[][] matriz = new double[3][3];
		
		// Coluna 0
		matriz[0][0] = 1;
		matriz[1][0] = b;
		matriz[2][0] = 0;
		// Coluna 1
		matriz[0][1] = a;
		matriz[1][1] = 1;
		matriz[2][1] = 0;
		// Coluna 2
		matriz[0][2] = 0;
		matriz[1][2] = 0;
		matriz[2][2] = 1;
		
		return matriz;
	}
	
	/**
	 * Realiza a tranlação passando uma matriz, utilizada para transformações multiplas
	 * @param matriz matriz que sofrerá a translação
	 * @param x - translação eixo x
	 * @param y - translação eixo y
	 * @return
	 */
	private double[][] translacao(double[][] matriz, int x, int y) {
		try {
			
			double[][] retorno = Matriz.multiplicacao2D(this.matrizTranslacao(x, y), matriz);

			return retorno;
		} catch (Exception e) {
			System.out.println("ERRO NA TRANSLAÇAO");
		}
		return matriz;
	}
	
	/*
	 * =================================================================================================================================
	 * 															CALCULOS
	 * =================================================================================================================================
	 */	

	/**
	 * Realiza a translação de uma chamada externa, onde são passados pontos
	 * @param pontos
	 * @param x
	 * @param y
	 * @return lista de pontos a serem plotados na tela
	 */
	public List<Ponto> translacao(List<Ponto> pontos, int x, int y) {
		List<Ponto> list = new ArrayList<Ponto>();
		try {
			double[][] matriz = this.gerarMatrizFigura(pontos, 3, pontos.size());
			double[][] mResultado = Matriz.multiplicacao2D(this.matrizTranslacao(x, y), matriz);
			for (int i = 0; i < mResultado[0].length; i++) {
				list.add(new Ponto((int) mResultado[0][i], (int) mResultado[1][i], (int) mResultado[2][i]));
			}
		} catch (Exception e) {
			System.out.println("ERRO NA TRANSLAÇÃO");
		}

		return list;
	}

	
	/**
	 * Realiza a escala em uma figura ou reta, seguindo os seguintes passos: Translação para origem, aplicando a escala, translação para o ponto original
	 * @param pontos
	 * @param x
	 * @param y
	 * @return retorna uma lista de pontos para ser plotados na tela
	 */
	public List<Ponto> escala(List<Ponto> pontos, int x, int y) {
		List<Ponto> list = new ArrayList<Ponto>();
		try {
			double[][] matriz = this.gerarMatrizFigura(pontos, 3, pontos.size()+1); 
	
			int tx = pontos.get(0).getX();
			int ty = pontos.get(0).getY();
	
			// Fazer a transla��o do objeto
			double[][] matrizOrigem = this.translacao(matriz, -tx, -ty);
	
			// Matriz da escala.
			double[][] escala = this.matrizEscala(x, y);
	
	
			double[][] matrizEscalada  = Matriz.multiplicacao2D(escala, matrizOrigem);
			
			// Voltar a reta a posi��o de origem
			double[][] mResultado = this.translacao(matrizEscalada, tx, ty);

			for (int i = 0; i < mResultado[0].length; i++) {
				list.add(new Ponto((int) mResultado[0][i], (int) mResultado[1][i], (int) mResultado[2][i]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Realiza a rotação sobre os eixos XY, seguindo os seguintes passos: Translação para origem, aplicando a rotação, translação para o ponto original
	 * @param pontos
	 * @param angulo
	 * @return
	 */
	public List<Ponto> rotacao(List<Ponto> pontos, double angulo) {
	
		List<Ponto> lista = new ArrayList<Ponto>();
		try {
	
			// Ponto de origem.
			final int translacaoX = pontos.get(0).getX();
			final int translacaoY = pontos.get(0).getY();
			
			// Fazer a transla��o.
			List<Ponto> matrixTranslacao = this.translacao(pontos, -translacaoX, -translacaoY);
			
			double[][] matrizNaOrigem = this.gerarMatrizFigura(matrixTranslacao, 3, pontos.size()); 
					
			// Gerar a matriz de rota��o
			double[][] matrizRotacao = this.matrizRotacao(angulo);

		
			double[][] matrizRotacionada = Matriz.multiplicacao2D(matrizRotacao, matrizNaOrigem);	
			
			double[][] matrizVoltar = this.translacao(matrizRotacionada, translacaoX, translacaoY);
			
			for (int i = 0; i < matrizVoltar[0].length; i++) {
				lista.add(new Ponto((int) Math.round(matrizVoltar[0][i])
									,(int) Math.round(matrizVoltar[1][i])
									,(int) Math.round(matrizVoltar[2][i])));
			}
			
		} catch (Exception e) {
			System.err.println("Erro ao multiplicar a matriz de rotacao");
			e.printStackTrace();
		}		
		return lista;
	}

	/**
	 * Realiza a reflexão sobre os eixos X, Y, XY e sobre uma reta qualquer no plano.
	 * @param pontos
	 * @param flag, identifica em qual eixo será a reflexão: 0 - eixo X; 1 - eixo Y; 2 - eixo XY; 3 - sobre uma reta
	 * @return
	 */
	public List<Ponto> reflexao(List<Ponto> pontos, int flag) {
		List<Ponto> lista = new ArrayList<Ponto>();
		try {
			double[][] matriz = this.gerarMatrizFigura(pontos, 3, pontos.size()); 
			double[][] matrizReflexao = null;
			if (flag == 0) {
				matrizReflexao = this.matrizReflexaoX();
			} else if (flag == 1) {
				matrizReflexao = this.matrizReflexaoY();
			} else if (flag == 2) {
				matrizReflexao = this.matrizReflexaoXY();
			} else if (flag == 3) {
				matrizReflexao = this.matrizReflexaoReta();
			}
					
			double[][] matrizRefletida = Matriz.multiplicacao2D(matrizReflexao, matriz);
			
//			list.clear();
			for (int i = 0; i < matrizRefletida[0].length; i++) {
				lista.add(new Ponto((int) matrizRefletida[0][i], (int) matrizRefletida[1][i], (int) matrizRefletida[2][i]));
			}
			
		} catch (Exception e) {
			System.out.println("Erro na reflexao.");
			e.printStackTrace();
		}
		return lista;
	}

	/**
	 * Cisalhamento realizado no eixo X e Y
	 * @param pontos
	 * @param a
	 * @param b
	 * @return lista de pontos para serem plotados na tela
	 */
	public List<Ponto> cisalhamento(List<Ponto> pontos, int a, int b) {
		List<Ponto> lista = new ArrayList<Ponto>();
		try {
			double[][] matriz = this.gerarMatrizFigura(pontos, 3, pontos.size()); 
	
			double[][] matrizCisalhamento = matrizCisalhamento(a, b);
	
			double[][] matrizCisalhada = Matriz.multiplicacao2D(matrizCisalhamento, matriz);
			
			for (int i = 0; i < matrizCisalhada[0].length; i++) {
				lista.add(new Ponto((int) matrizCisalhada[0][i], (int) matrizCisalhada[1][i], (int) matrizCisalhada[2][i]));

			}
		} catch (Exception e) {
			System.out.println("Erro no  cisalhamento em X e Y.");
			e.printStackTrace();
		}



		return lista;
	}


	/**
	 * Gera a matriz de coeficientes da figura
	 * @param lista com todos os pontos da figura
	 * @return retorna a matriz da figura
	 */
	private double[][] gerarMatrizFigura(List<Ponto> lista, int linha, int coluna) {
		double[][] matriz = new double[linha][coluna];
		
		for (int i = 0; i < lista.size(); i++) {
			matriz[0][i] = lista.get(i).getX();
			matriz[1][i] = lista.get(i).getY();
			matriz[2][i] = 1;
		}
		return matriz;
	}
	
}
