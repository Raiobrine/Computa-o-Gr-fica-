package model;

public class Matriz {

	/**
	 * Multiplicação entre matrizes 2D
	 * @param matriz_1
	 * @param matriz_2
	 * @return retorna o resultado da multiplicação entre as matrizes
	 * @throws Exception é lançado caso as matrizes não possam ser multiplicadas pela validação de linha coluna
	 */
	public static double[][] multiplicacao2D(double[][] matriz_1, double[][] matriz_2) throws Exception {		
		int col = matriz_2[0].length;		
		if (matriz_1[0].length == matriz_2.length) { 
			
			double[][] resultado = new double[3][matriz_2[0].length];			
			
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < col; j++) {
					for (int k = 0; k < 3; k++) {
						resultado[i][j] += matriz_1[i][k] * matriz_2[k][j];
					}
				}
			}
			return resultado;
		} else {
			throw new Exception("Erro: Matrizes com valores de linha e coluna diferentes!");
		}
	}
	
	/**
	 * Multiplicação entre matrizez 3D
	 * @param matriz_1
	 * @param matriz_2
	 * @return retorna o resultado da multiplicação entre as matrizes
	 * @throws Exception é lançado caso as matrizes não possam ser multiplicadas pela validação de linha coluna
	 */
	public static double[][] multiplicacao3D(double[][] matriz_1, double[][] matriz_2) throws Exception {	
		int col = matriz_2[0].length;
		if (matriz_1[0].length == matriz_2.length) {
			double[][] resultado = new double[4][matriz_2[0].length];			
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < col; j++) {
					for (int k = 0; k < 4; k++) {
						resultado[i][j] += matriz_1[i][k] * matriz_2[k][j];
					}
				}
			}
			return resultado;
		} else {
			throw new Exception("Erro: Matrizes com valores de linha e coluna diferentes!");
		}
	}
}
