package modelo;

import processarImagem.PanelDaImagem;

public class Op_Logicas extends PanelDaImagem {

	// Aplica a opera��o de OR l�gico na imagem
	public void logica_or(int largura1, int altura1, int[][] matrizImagem1, int largura2, int altura2,
			int[][] matrizImagem2) {
		int altura = defineAlturaLargura(altura1, largura1, altura2, largura2)[0];
		int largura = defineAlturaLargura(altura1, largura1, altura2, largura2)[1];
		int matrizImagem[][] = new int[altura][largura];

		for (int i = 0; i < altura; i++) {
			for (int j = 0; j < largura; j++) {
				// operacao de OR
				matrizImagem[i][j] = (matrizImagem1[i][j] | matrizImagem2[i][j]);
			}

		}

		exibir(matrizImagem);
	}

	// Aplica a opera��o de AND l�gico na imagem
	public void logica_and(int largura1, int altura1, int[][] matrizImagem1, int largura2, int altura2,
			int[][] matrizImagem2) {
		int altura = defineAlturaLargura(altura1, largura1, altura2, largura2)[0];
		int largura = defineAlturaLargura(altura1, largura1, altura2, largura2)[1];

		int matrizImagem[][] = new int[altura][largura];
		for (int i = 0; i < altura; i++) {
			for (int j = 0; j < largura; j++) {
				// operacao de AND
				matrizImagem[i][j] = (matrizImagem1[i][j] & matrizImagem2[i][j]);
			}
		}

		exibir(matrizImagem);
	}

	// Aplica a opera��o de XOR l�gico na imagem
	public void logica_xor(int largura1, int altura1, int[][] matrizImagem1, int largura2, int altura2,
			int[][] matrizImagem2) {
		int altura = defineAlturaLargura(altura1, largura1, altura2, largura2)[0];
		int largura = defineAlturaLargura(altura1, largura1, altura2, largura2)[1];

		int matrizImagem[][] = new int[altura][largura];
		for (int i = 0; i < altura; i++) {
			for (int j = 0; j < largura; j++) {

				// operacao de AND
				matrizImagem[i][j] = (matrizImagem1[i][j] ^ matrizImagem2[i][j]);
			}
		}

		exibir(matrizImagem);
	}
}
