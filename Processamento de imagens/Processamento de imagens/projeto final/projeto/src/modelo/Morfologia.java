package modelo;

import java.awt.Color;
import java.awt.image.BufferedImage;

import processarImagem.PanelDaImagem;

public class Morfologia extends PanelDaImagem {

	/**
     * Subtrai duas imagens do mesmo tamanho. Considera a imagem em tons de cinza.
     */
    public BufferedImage subtract(BufferedImage img1, BufferedImage img2) {
        //Cria a imagem de sa�da
        BufferedImage out = new BufferedImage(img1.getWidth(), img1.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < img1.getHeight(); y++) {
            for (int x = 0; x < img1.getWidth(); x++) {
                //Le o pixel
                Color p1 = new Color(img1.getRGB(x, y));
                Color p2 = new Color(img2.getRGB(x, y));

                //Faz a subtra��o, canal a canal
                int tone = p1.getRed() - p2.getRed();

                //Define a cor de sa�da na imagem de sa�da (out).
                out.setRGB(x, y, toColor(tone, tone, tone));
            }
        }
        exibir(out.getRaster());
        return out;
    }

    /**
     * Opera��o de eros�o morfol�gica.
     * Nesta opera��o buscamos entre o pixel e seus vizinhos aqueles com o tom de cinza mais escuro (de menor valor).
     * Os pixels considerados na busca s�o aqueles marcados com true no kernel.
     */
    public BufferedImage erodir(BufferedImage img, boolean[][] kernel) {
        //Cria a imagem de sa�da
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);

        //Percorre a imagem de entrada
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                //A eros�o busca pelo pixel de menor valor
                int min = 255;
                //Para cada pixel percorrido na imagem, precisamos percorrer os seus 9 vizinhos
                //Os vizinhos que ser�o considerados est�o marcados como true no kernel
                for (int ky = 0; ky < 3; ky++) {
                    for (int kx = 0; kx < 3; kx++) {
                        //Observe que os �ndices de kx e ky variam de 0 at� 2. J� os vizinhos de x seriam
                        //x+(-1), x+0 + x+1. Por isso, subtra�mos 1 de kx e ky para chegar no vizinho.
                        int px = x + (kx-1);
                        int py = y + (ky-1);

                        //Nas bordas, px ou py podem acabar caindo fora da imagem. Quando isso ocorre, pulamos para o
                        // pr�ximo pixel.
                        if (px < 0 || px >= img.getWidth() || py < 0 || py >= img.getHeight()) {
                            continue;
                        }

                        //Obtem o tom de cinza do pixel
                        int tone = new Color(img.getRGB(px, py)).getRed();

                        //Se ele for mais escuro que o menor j� encontrado, substitui
                        if (kernel[kx][ky] && tone < min) {
                            min = tone;
                        }
                    }
                }

                //Define essa cor na imagem de sa�da.
                out.setRGB(x, y, new Color(min, min, min).getRGB());
            }
        }
        return out;
    }
    /**
     * Aplica a erosao times vezes.
     */
    public BufferedImage erode(BufferedImage img, int times, boolean[][] kernel) {
        BufferedImage out = img;
        for (int i = 0; i < times; i++) {
            out = erodir(out, kernel);
        }
        exibir(out.getRaster());
        return out;
    }

    public BufferedImage dilatar(BufferedImage img, boolean[][] kernel) {
        //Cria a imagem de sa�da
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);

        //Percorre a imagem de entrada
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                //A dilata��o busca pelo pixel de maior valor
                int max = 0;
                //Para cada pixel percorrido na imagem, precisamos percorrer os seus 9 vizinhos
                //Os vizinhos que ser�o considerados est�o marcados como true no kernel
                for (int ky = 0; ky < 3; ky++) {
                    for (int kx = 0; kx < 3; kx++) {
                        //Observe que os �ndices de kx e ky variam de 0 at� 2. J� os vizinhos de x seriam
                        //x+(-1), x+0 + x+1. Por isso, subtra�mos 1 de kx e ky para chegar no vizinho.
                        int px = x + (kx-1);
                        int py = y + (ky-1);

                        //Nas bordas, px ou py podem acabar caindo fora da imagem. Quando isso ocorre, pulamos para o
                        // pr�ximo pixel.
                        if (px < 0 || px >= img.getWidth() || py < 0 || py >= img.getHeight()) {
                            continue;
                        }

                        //Obtem o tom de cinza do pixel
                        int tone = new Color(img.getRGB(px, py)).getRed();

                        //Se ele for mais claro que o maior j� encontrado, substitui
                        if (kernel[kx][ky] && tone > max) {
                            max = tone;
                        }
                    }
                }

                //Define essa cor na imagem de sa�da.
                out.setRGB(x, y, new Color(max, max, max).getRGB());
            }
        }
        return out;
    }

    /**
     * Aplica a dilata��o times vezes.
     */
    public BufferedImage dilate(BufferedImage img, int times, boolean[][] kernel) {
        BufferedImage out = img;
        for (int i = 0; i < times; i++) {
            out = dilatar(out, kernel);
        }
        exibir(out.getRaster());
        return out;
    }

    /**
     * Abertura morfol�gica.
     * Trata-se de v�rias eros�es seguidas do mesmo n�mero de dilata��es. Isso faz com que �reas pequenas da imagem
     * tendam a desaparecer, e estruturas maiores sejam mantidas.
     */
    public BufferedImage open(BufferedImage img, int times, boolean[][] kernel) {
        BufferedImage c = dilate(erode(img, times, kernel), times, kernel);
        exibir(c.getRaster());
    	return c;
    }

    /**
     * Fechamento morfol�gico.
     * Trata-se de v�rias dilata��es seguidas do mesmo n�mero de eros�es. Isso faz com que "buracos" pequenos na imagem
     * tendam a desaparecer.
     */
    public BufferedImage close(BufferedImage img, int times, boolean[][] kernel) {
    	BufferedImage c = erode(dilate(img, times, kernel), times, kernel);
    	exibir(c.getRaster());
    	return c;
    }

	 /**
     * Garante que o valor do pixel estar� entre 0 e 255.
     */
    private int clamp(float value) {
        int v = (int)value;
        return v > 255 ? 255 : (v < 0 ? 0 : v);
    }

    /**
     * Converte os valores de r, g e b para o inteiro da cor.
     * Os valores podem estar fora do intervalo de 0 at� 255, pois
     * a fun��o ajusta chamando a fun��o clamp (acima).
     */
    private int toColor(float r, float g, float b) {
        return new Color(clamp(r), clamp(g), clamp(b)).getRGB();
    }

}
