package algoritmos;

import java.util.ArrayList;
import java.util.List;
import model.Matriz;
import model.Ponto;

public class Transformacao3D {
	private double[][] gerarMatrizTranslacao(int tx, int ty, int tz) {
		double[][] matriz = new double[4][4];

		matriz[0][0] = 1;
		matriz[0][1] = 0;
		matriz[0][2] = 0;
		matriz[0][3] = tx;

		matriz[1][0] = 0;
		matriz[1][1] = 1;
		matriz[1][2] = 0;
		matriz[1][3] = ty;

		matriz[2][0] = 0;
		matriz[2][1] = 0;
		matriz[2][2] = 1;
		matriz[2][3] = tz;

		matriz[3][0] = 0;
		matriz[3][1] = 0;
		matriz[3][2] = 0;
		matriz[3][3] = 1;

		return matriz;
	}

	/**
	 * Matriz de Escala em um objeto em tres dimens�es.
	 * 
	 * @param sx
	 * @param sy
	 * @param sz
	 * @return Vetor
	 */
	private double[][] gerarMatrizEscala(double sx, double sy, double sz) {
		double[][] matriz = new double[4][4];
		if (sx == 0) {
			sx = 1;
		}
		if (sy == 0) {
			sy = 1;
		}
		if (sz == 0) {
			sy = 1;
		}
		// coluna 0
		matriz[0][0] = sx;
		matriz[1][0] = 0;
		matriz[2][0] = 0;
		matriz[3][0] = 0;

		// Coluna 1
		matriz[0][1] = 0;
		matriz[1][1] = sy;
		matriz[2][1] = 0;
		matriz[3][1] = 0;

		// Coluna 2
		matriz[0][2] = 0;
		matriz[1][2] = 0;
		matriz[2][2] = sy;
		matriz[3][2] = 1;

		// Coluna 3
		matriz[0][3] = 0;
		matriz[1][3] = 0;
		matriz[2][3] = 0;
		matriz[3][3] = 0;

		return matriz;
	}

	/**
	 * Matriz de Reflex�o nos eixos X e Y em um objeto em tres dimens�es.
	 * @return
	 */
	private double[][] gerarMatrizReflexaoXY() {

		double[][] matriz = new double[4][4];

		// Coluna 0
		matriz[0][0] = 1;
		matriz[1][0] = 0;
		matriz[2][0] = 0;
		matriz[3][0] = 0;
		
		// Coluna 1
		matriz[0][1] = 0;
		matriz[1][1] = 1;
		matriz[2][1] = 0;
		matriz[3][1] = 0;

		// Coluna 2
		matriz[0][2] = 0;
		matriz[1][2] = 0;
		matriz[2][2] = -1;
		matriz[3][2] = 0;

		// Coluna 3
		matriz[0][3] = 0;
		matriz[1][3] = 0;
		matriz[2][3] = 0;
		matriz[3][3] = 1;
		
		return matriz;
		
	}

	/**
	 * Matriz de Reflex�o nos eixos Y e Z em um objeto em tres dimensoes.
	 * @return
	 */
	private double[][] gerarMatrizReflexaoYZ() {

		double[][] matriz = new double[4][4];

		// Coluna 0
		matriz[0][0] = -1;
		matriz[1][0] = 0;
		matriz[2][0] = 0;
		matriz[3][0] = 0;
		
		// Coluna 1
		matriz[0][1] = 0;
		matriz[1][1] = 1;
		matriz[2][1] = 0;
		matriz[3][1] = 0;

		// Coluna 2
		matriz[0][2] = 0;
		matriz[1][2] = 0;
		matriz[2][2] = 1;
		matriz[3][2] = 0;

		// Coluna 3
		matriz[0][3] = 0;
		matriz[1][3] = 0;
		matriz[2][3] = 0;
		matriz[3][3] = 1;
		
		return matriz;
	}
	
	/**
	 * Matriz de Reflexão nos eixos X e Z em um objeto em três dimensões.
	 * @return
	 */
	private double[][] gerarMatrizReflexaoXZ() { 

		double[][] matriz = new double[4][4];

		// Coluna 0
		matriz[0][0] = 1;
		matriz[1][0] = 0;
		matriz[2][0] = 0;
		matriz[3][0] = 0;
		
		// Coluna 1
		matriz[0][1] = 0;
		matriz[1][1] = -1;
		matriz[2][1] = 0;
		matriz[3][1] = 0;

		// Coluna 2
		matriz[0][2] = 0;
		matriz[1][2] = 0;
		matriz[2][2] = 1;
		matriz[3][2] = 0;

		// Coluna 3
		matriz[0][3] = 0;
		matriz[1][3] = 0;
		matriz[2][3] = 0;
		matriz[3][3] = 1;
		
		return matriz;
		
	}
	
	private double[][] gerarMatrizCisalhamentoX(double a) {

		double[][] matriz = new double[4][4];

		// Linha 0
		matriz[0][0] = 1;
		matriz[1][0] = 0;
		matriz[2][0] = 0;
		matriz[3][0] = 0;
		// Linha 1
		matriz[0][1] = a;
		matriz[1][1] = 1;
		matriz[2][1] = 0;
		matriz[3][1] = 0;
		// Linha 2
		matriz[0][2] = 0;
		matriz[1][2] = 0;
		matriz[2][2] = 1;
		matriz[3][2] = 1;

		matriz[0][3] = 0;
		matriz[1][3] = 0;
		matriz[2][3] = 0;
		matriz[3][3] = 1;

		return matriz;
	}

	private double[][] gerarMatrizCisalhamentoY(double b) {

		double[][] matriz = new double[4][4];

		// Linha 0
		matriz[0][0] = 1;
		matriz[1][0] = b;
		matriz[2][0] = 0;
		matriz[3][0] = 0;
		// Linha 1
		matriz[0][1] = 0;
		matriz[1][1] = 1;
		matriz[2][1] = 0;
		matriz[3][1] = 0;
		// Linha 2
		matriz[0][2] = 0;
		matriz[1][2] = 0;
		matriz[2][2] = 1;
		matriz[3][2] = 1;

		matriz[0][3] = 0;
		matriz[1][3] = 0;
		matriz[2][3] = 0;
		matriz[3][3] = 1;

		return matriz;
	}

	private double[][] gerarMatrizCisalhamentoZ(double Z) {

		double[][] matriz = new double[4][4];

		// Coluna 0
		matriz[0][0] = 1;
		matriz[1][0] = 0;
		matriz[2][0] = 0;
		matriz[3][0] = 0;

		// Coluna 1
		matriz[0][1] = 0;
		matriz[1][1] = 1;
		matriz[2][1] = 0;
		matriz[3][1] = 0;

		// Coluna 2
		matriz[0][2] = 0;
		matriz[1][2] = Z;
		matriz[2][2] = 1;
		matriz[3][2] = 0;

		// Coluna 3
		matriz[0][3] = 0;
		matriz[1][3] = 0;
		matriz[2][3] = 0;
		matriz[3][3] = 1;
		return matriz;
	}

	/**
	 * Matriz de Rotação no eixo X.
	 * @param angulo
	 * @return
	 */
	private double[][] gerarMatrizRotacaoX(double angulo) {
		double[][] matriz = new double[4][4];

		double sen = Math.sin(Math.toRadians(angulo));
		double cos = Math.cos(Math.toRadians(angulo));
		
		// Linha 0
		matriz[0][0] = 1;
		matriz[1][0] = 0;
		matriz[2][0] = 0;
		matriz[3][0] = 0;
		
		// Linha 1
		matriz[0][1] = 0;
		matriz[1][1] = cos;
		matriz[2][1] = sen;
		matriz[3][1] = 0;
		
		// Linha 2
		matriz[0][2] = 0;
		matriz[1][2] = -sen;
		matriz[2][2] = cos;
		matriz[3][2] = 0;
		
		// linha 3
		matriz[0][3] = 0;
		matriz[1][3] = 0;
		matriz[2][3] = 0;
		matriz[3][3] = 1;
		
		return matriz;
	}

	/**
	 * Matriz de Rotação nos eixo Y.
	 * @param angulo
	 * @return
	 */
	private double[][] gerarMatrizRotacaoY(double angulo) {
		double[][] matriz = new double[4][4];

		double sen = Math.sin(Math.toRadians(angulo));
		double cos = Math.cos(Math.toRadians(angulo));
		
		// Linha 0
		matriz[0][0] = cos;
		matriz[1][0] = 0;
		matriz[2][0] = sen;
		matriz[3][0] = 0;
		
		// Linha 1
		matriz[0][1] = 0;
		matriz[1][1] = 1;
		matriz[2][1] = 0;
		matriz[3][1] = 0;
		
		// Linha 2
		matriz[0][2] = -sen;
		matriz[1][2] = 0;
		matriz[2][2] = cos;
		matriz[3][2] = 0;
		
		// linha 3
		matriz[0][3] = 0;
		matriz[1][3] = 0;
		matriz[2][3] = 0;
		matriz[3][3] = 1;
		
		return matriz;
	}
	
	/**
	 * Matriz de Rotação no eixo Z.
	 * @param angulo
	 * @return
	 */
	private double[][] gerarMatrizRotacaoZ(double angulo) {
		double[][] matriz = new double[4][4];

		double sen = Math.sin(Math.toRadians(angulo));
		double cos = Math.cos(Math.toRadians(angulo));
		
		// Linha 0
		matriz[0][0] = cos;
		matriz[1][0] = sen;
		matriz[2][0] = 0;
		matriz[3][0] = 0;
		
		// Linha 1
		matriz[0][1] = -sen;
		matriz[1][1] = cos;
		matriz[2][1] = 0;
		matriz[3][1] = 0;
		
		// Linha 2
		matriz[0][2] = 0;
		matriz[1][2] = 0;
		matriz[2][2] = 1;
		matriz[3][2] = 0;
		
		// linha 3
		matriz[0][3] = 0;
		matriz[1][3] = 0;
		matriz[2][3] = 0;
		matriz[3][3] = 1;
		
		return matriz;
	}
	
	/**
	 *  Operações básicas
	 * @param matriz
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	private double[][] translacao(double[][] matriz, int x, int y, int z) {

		try {
			double[][] d = Matriz.multiplicacao3D(gerarMatrizTranslacao(x, y, z), matriz);

			return d;
		} catch (Exception e) {
			System.out.println("ERRO NA TRANSLA��O");
		}
		return matriz;
	}

	public List<Ponto> translacao(List<Ponto> pontos, int x, int y, int z) {
		double[][] matriz = new double[4][pontos.size()];

		// Criando o objeto de matriz
		for (int i = 0; i < pontos.size(); i++) {
			matriz[0][i] = pontos.get(i).getX(); // Coluna i na linha 0
			matriz[1][i] = pontos.get(i).getY(); // Coluna i na linha 1
			matriz[2][i] = pontos.get(i).getZ(); // Coluna i na linha 2 
			matriz[3][i] = 1; // Coluna i na linha 3
		}
		
		double[][] resultado = null;
		try {
			resultado = Matriz.multiplicacao3D(gerarMatrizTranslacao(x, y, z), matriz);
		} catch (Exception e) {
			System.out.println("ERRO NA TRANSLA��O");
		}
		List<Ponto> list = new ArrayList<Ponto>();
		for (int i = 0; i < resultado[0].length; i++) {
			list.add(new Ponto((int) resultado[0][i], (int) resultado[1][i], (int) resultado[2][i], (int) resultado[3][i]));
		}
		return list;
	}

	public List<Ponto> escala(List<Ponto> lista, double x, double y, double z) {

		List<Ponto> list = new ArrayList<Ponto>();
		double[][] matriz = new double[4][lista.size() + 1];

		// Criando o objeto de matriz
		for (int i = 0; i < lista.size(); i++) {
			matriz[0][i] = lista.get(i).getX(); // Coluna i na linha 0
			matriz[1][i] = lista.get(i).getY(); // Coluna i na linha 1
			matriz[2][i] = lista.get(i).getZ(); // Coluna i na linha 1
			matriz[3][i] = 1; // Coluna i na linha 2 = 1
		}

		int translacaox = lista.get(0).getX();
		int translacaoy = lista.get(0).getY();
		int translacaoz = lista.get(0).getZ();

		// Fazer a transla��o do objeto
		double[][] matrizNaOrigem = translacao(matriz, -translacaox, -translacaoy, -translacaoz);

		// Matriz da escala.
		double[][] escala = gerarMatrizEscala(x, y, z);

		// Gerando a matriz da escala
		double[][] esc = null;
		try {
			new Matriz();
			esc = Matriz.multiplicacao3D(escala, matrizNaOrigem);
		} catch (Exception e) {
			System.out.println("Erro: Escala em 3D.");
			e.printStackTrace();
		}

		// Voltar a reta a posi��o de origem
		double[][] mat = translacao(esc, translacaox, translacaoy, translacaoz);

		for (int i = 0; i < mat[0].length; i++) {
			list.add(new Ponto((int) mat[0][i], (int) mat[1][i],
					(int) mat[2][i], (int) mat[3][i]));
		}
		return list;
	}

	public List<Ponto> rotacaoX(List<Ponto> lis, double angulo) { 

		final int transx = lis.get(0).getX();
		final int transy = lis.get(0).getY();
		final int transz = lis.get(0).getZ();

		// Fazer a translação para a origem.
		double[][] matrizNaOrigem = new double[4][lis.size()];

		// Criando o objeto na matriz 4 x (? - quantidade de pontos)
		// Se não está na origem.
		if (!(transx == 0 || transy == 0 || transz == 0)) {
			
			List<Ponto> trans = translacao(lis, -transx, -transy, -transz);
			
			for (int i = 0; i < lis.size(); i++) {
				matrizNaOrigem[0][i] = (double) trans.get(i).getX();
				matrizNaOrigem[1][i] = (double) trans.get(i).getY();
				matrizNaOrigem[2][i] = (double) trans.get(i).getZ();
				matrizNaOrigem[3][i] = 1;
			}

			// Se Est� na origem
		} else {
			for (int i = 0; i < lis.size(); i++) {
				matrizNaOrigem[0][i] = (double) lis.get(i).getX();
				matrizNaOrigem[1][i] = (double) lis.get(i).getY();
				matrizNaOrigem[2][i] = (double) lis.get(i).getZ();
				matrizNaOrigem[3][i] = 1;
			}
		}
		
		// Gerar a matriz de rota��o
		double[][] rotacao = gerarMatrizRotacaoX(angulo);

		// Fazer a rota��o
		double[][] lisPonto = null;
		try {
			lisPonto = Matriz.multiplicacao3D(rotacao, matrizNaOrigem);
		} catch (Exception e) {
			System.err.println("Erro ao multiplicar a matriz de rota��o");
			e.printStackTrace();
		}

		lis.clear();
		for (int i = 0; i < lisPonto[0].length; i++) {
			lis.add(new Ponto((int) lisPonto[0][i], (int) lisPonto[1][i], (int) lisPonto[2][i], (int) lisPonto[3][i]));
		}
		
		if (!(transx == 0 || transy == 0 || transz == 0)) {
			List<Ponto> resultado = translacao(lis, transx, transy, transz);
			return resultado;
		} else return lis;	
	}

	public List<Ponto> rotacaoY(List<Ponto> lis, double angulo) { 

		final int transx = lis.get(0).getX();
		final int transy = lis.get(0).getY();
		final int transz = lis.get(0).getZ();
		
		double[][] matrizNaOrigem = new double[4][lis.size()];

		if (!(transx == 0 || transy == 0 || transz == 0)) {
			List<Ponto> trans = translacao(lis, -transx, -transy, -transz);
			
			for (int i = 0; i < lis.size(); i++) {
				matrizNaOrigem[0][i] = (double) trans.get(i).getX();
				matrizNaOrigem[1][i] = (double) trans.get(i).getY();
				matrizNaOrigem[2][i] = (double) trans.get(i).getZ();
				matrizNaOrigem[3][i] = 1;
			}

		} else {
			
			for (int i = 0; i < lis.size(); i++) {
				matrizNaOrigem[0][i] = (double) lis.get(i).getX();
				matrizNaOrigem[1][i] = (double) lis.get(i).getY();
				matrizNaOrigem[2][i] = (double) lis.get(i).getZ();
				matrizNaOrigem[3][i] = 1;
			}
		}
		
		// Gerar a matriz de rota��o
		double[][] rotacao = gerarMatrizRotacaoY(angulo);

		// Fazer a rota��o
		double[][] lisPonto = null;
		try {
			lisPonto = Matriz.multiplicacao3D(rotacao, matrizNaOrigem);
		} catch (Exception e) {
			System.err.println("Erro ao multiplicar a matriz de rota��o");
			e.printStackTrace();
		}

		lis.clear();
		
		for (int i = 0; i < lisPonto[0].length; i++) {
			lis.add(new Ponto((int) lisPonto[0][i], (int) lisPonto[1][i],
					(int) lisPonto[2][i], (int) lisPonto[3][i]));
		}
		
		if (!(transx == 0 || transy == 0 || transz == 0)) {
			List<Ponto> resultado = translacao(lis, transx, transy, transz);
			return resultado;
		} else return lis;
	}

	public List<Ponto> rotacaoZ(List<Ponto> lis, double angulo) { 
		final int transx = lis.get(0).getX();
		final int transy = lis.get(0).getY();
		final int transz = lis.get(0).getZ();
		
		double[][] matrizNaOrigem = new double[4][lis.size()];
		
		if (!(transx == 0 || transy == 0 || transz == 0)) {
			List<Ponto> trans = translacao(lis, -transx, -transy, -transz);

			for (int i = 0; i < lis.size(); i++) {
				matrizNaOrigem[0][i] = (double) trans.get(i).getX();
				matrizNaOrigem[1][i] = (double) trans.get(i).getY();
				matrizNaOrigem[2][i] = (double) trans.get(i).getZ();
				matrizNaOrigem[3][i] = 1;
			}

		} else {
			for (int i = 0; i < lis.size(); i++) {
				matrizNaOrigem[0][i] = (double) lis.get(i).getX();
				matrizNaOrigem[1][i] = (double) lis.get(i).getY();
				matrizNaOrigem[2][i] = (double) lis.get(i).getZ();
				matrizNaOrigem[3][i] = 1;
			}
		}
				
		// Gerar a matriz de rota��o
		double[][] rotacao = gerarMatrizRotacaoZ(angulo);

		// Fazer a rota��o
		double[][] lisPonto = null;
		try {
			lisPonto = Matriz.multiplicacao3D(rotacao, matrizNaOrigem);
		} catch (Exception e) {
			System.err.println("Erro ao multiplicar a matriz de rota��o");
			e.printStackTrace();
		}

		lis.clear();
		for (int i = 0; i < lisPonto[0].length; i++) {
			lis.add(new Ponto((int) lisPonto[0][i], (int) lisPonto[1][i],
					(int) lisPonto[2][i], (int) lisPonto[3][i]));
		}
		
		if (!(transx == 0 || transy == 0 || transz == 0)) {
			List<Ponto> resultado = translacao(lis, transx, transy, transz);
			return resultado;
		} else return lis;
	}
	
	public List<Ponto> reflexaoXY(List<Ponto> lista) {
		List<Ponto> list = new ArrayList<Ponto>();
		double[][] matriz = new double[4][lista.size()];

		// Criando o objeto de matriz
		for (int i = 0; i < lista.size(); i++) {
			matriz[0][i] = lista.get(i).getX(); // Coluna i na linha 0
			matriz[1][i] = lista.get(i).getY(); // Coluna i na linha 1
			matriz[2][i] = lista.get(i).getZ(); // Coluna i na linha 1
			matriz[3][i] = 1; // Coluna j na linha 2 = 1
		}

		double[][] reflexao = gerarMatrizReflexaoXY();

		double[][] matrizRefetida = null;
		try {
			matrizRefetida = Matriz.multiplicacao3D(reflexao, matriz);
		} catch (Exception e) {
			System.out.println("Erro na reflex�o.");
			e.printStackTrace();
		}

		list.clear();
		for (int i = 0; i < matrizRefetida[0].length; i++) {
			list.add(new Ponto((int) matrizRefetida[0][i],
					(int) matrizRefetida[1][i], (int) matrizRefetida[2][i], (int) matrizRefetida[3][i]));
		}

		
		return list;
	}

	public List<Ponto> reflexaoYZ(List<Ponto> lista) {  
		List<Ponto> list = new ArrayList<Ponto>();
		double[][] matriz = new double[4][lista.size()];

		// Criando o objeto de matriz
		for (int i = 0; i < lista.size(); i++) {
			matriz[0][i] = lista.get(i).getX(); // Coluna i na linha 0
			matriz[1][i] = lista.get(i).getY(); // Coluna i na linha 1
			matriz[2][i] = lista.get(i).getZ(); // Coluna i na linha 1
			matriz[3][i] = 1; // Coluna j na linha 2 = 1
		}

		double[][] reflexao = gerarMatrizReflexaoYZ();

		double[][] matrizRefetida = null;
		try {
			matrizRefetida = Matriz.multiplicacao3D(reflexao, matriz);
		} catch (Exception e) {
			System.out.println("Erro na reflex�o.");
			e.printStackTrace();
		}

		list.clear();
		for (int i = 0; i < matrizRefetida[0].length; i++) {
			list.add(new Ponto((int) matrizRefetida[0][i],
					(int) matrizRefetida[1][i], (int) matrizRefetida[2][i], (int) matrizRefetida[3][i]));
		}

		return list;
	}
	
	public List<Ponto> reflexaoXZ(List<Ponto> lista) {
		List<Ponto> list = new ArrayList<Ponto>();
		double[][] matriz = new double[4][lista.size()];

		// Criando o objeto de matriz
		for (int i = 0; i < lista.size(); i++) {
			matriz[0][i] = lista.get(i).getX(); // Coluna i na linha 0
			matriz[1][i] = lista.get(i).getY(); // Coluna i na linha 1
			matriz[2][i] = lista.get(i).getZ(); // Coluna i na linha 1
			matriz[3][i] = 1; // Coluna j na linha 2 = 1
		}

		double[][] reflexao = gerarMatrizReflexaoXZ();

		double[][] matrizRefetida = null;
		try {
			matrizRefetida = Matriz.multiplicacao3D(reflexao, matriz);
		} catch (Exception e) {
			System.out.println("Erro na reflex�o.");
			e.printStackTrace();
		}

		list.clear();
		for (int i = 0; i < matrizRefetida[0].length; i++) {
			list.add(new Ponto((int) matrizRefetida[0][i],
					(int) matrizRefetida[1][i], (int) matrizRefetida[2][i], (int) matrizRefetida[3][i]));
		}

		return list;
	}
		
	public List<Ponto> cisalhamentoEmZ(List<Ponto> lista, double a) {
		List<Ponto> list = new ArrayList<Ponto>();
		double[][] matriz = new double[4][lista.size()];

		// Criando o objeto de matriz
		for (int i = 0; i < lista.size(); i++) {
			matriz[0][i] = lista.get(i).getX(); // Coluna i na linha 0
			matriz[1][i] = lista.get(i).getY(); // Coluna i na linha 1
			matriz[2][i] = lista.get(i).getZ(); // Coluna j na linha 2 = 1
			matriz[3][i] = 1; // Coluna j na linha 2 = 1
		}

		double[][] cisalhamento = gerarMatrizCisalhamentoZ(a);

		double[][] matrizRefetida = null;
		try {
			matrizRefetida = Matriz.multiplicacao3D(cisalhamento, matriz);
		} catch (Exception e) {
			System.out.println("Erro no  cisalhamento em X e Y.");
			e.printStackTrace();
		}

		list.clear();
		for (int i = 0; i < matrizRefetida[0].length; i++) {
			list.add(new Ponto((int) matrizRefetida[0][i],
					(int) matrizRefetida[1][i], (int) matrizRefetida[2][i],
					(int) matrizRefetida[3][i]));
		}

		return list;
	}

	public List<Ponto> cisalhamentoEmY(List<Ponto> lista, double a) {
		List<Ponto> list = new ArrayList<Ponto>();
		double[][] matriz = new double[4][lista.size()];

		// Criando o objeto de matriz
		for (int i = 0; i < lista.size(); i++) {
			matriz[0][i] = lista.get(i).getX(); // Coluna i na linha 0
			matriz[1][i] = lista.get(i).getY(); // Coluna i na linha 1
			matriz[2][i] = lista.get(i).getZ(); // Coluna j na linha 2 = 1
			matriz[3][i] = 1; // Coluna j na linha 2 = 1
		}
		
		double[][] cisalhamento = gerarMatrizCisalhamentoY(a);

		double[][] matrizRefetida = null;
		try {
			matrizRefetida = Matriz.multiplicacao3D(cisalhamento, matriz);
		} catch (Exception e) {
			System.out.println("Erro no  cisalhamento em X e Y.");
			e.printStackTrace();
		}

		list.clear();
		for (int i = 0; i < matrizRefetida[0].length; i++) {
			list.add(new Ponto((int) matrizRefetida[0][i],
					(int) matrizRefetida[1][i], (int) matrizRefetida[2][i],
					(int) matrizRefetida[3][i]));
		}

		return list;
	}

	public List<Ponto> cisalhamentoEmX(List<Ponto> lista, int a) {
		List<Ponto> list = new ArrayList<Ponto>();
		double[][] matriz = new double[4][lista.size()];

		// Criando o objeto de matriz
		for (int i = 0; i < lista.size(); i++) {
			matriz[0][i] = lista.get(i).getX(); // Coluna i na linha 0
			matriz[1][i] = lista.get(i).getY(); // Coluna i na linha 1
			matriz[2][i] = lista.get(i).getZ(); // Coluna j na linha 2 = 1
			matriz[3][i] = 1; // Coluna j na linha 2 = 1
		}

		double[][] cisalhamento = gerarMatrizCisalhamentoX(a);

		double[][] matrizRefetida = null;
		try {
			matrizRefetida = Matriz.multiplicacao3D(cisalhamento, matriz);
		} catch (Exception e) {
			System.out.println("Erro no  cisalhamento em X.");
			e.printStackTrace();
		}

		list.clear();
		for (int i = 0; i < matrizRefetida[0].length; i++) {
			list.add(new Ponto((int) matrizRefetida[0][i],
					(int) matrizRefetida[1][i], (int) matrizRefetida[2][i],
					(int) matrizRefetida[3][i]));
		}

		return list;
	}

}
