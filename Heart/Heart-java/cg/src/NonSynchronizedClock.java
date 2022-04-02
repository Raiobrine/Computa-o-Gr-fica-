
import java.awt.*;
import java.awt.Window.Type;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.*;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.sun.java.swing.plaf.windows.resources.windows;
import com.sun.java.swing.plaf.windows.resources.windows_zh_CN;



/**
Grupo: Rafaela, João Lucas e Jefferson
*/
public class NonSynchronizedClock extends JFrame
{

  private int windowWidth;
  private int windowHeight;

  /**
  * Constructor
  * @param width   
  * @param height  
  */
  NonSynchronizedClock(int width, int height){
    //habilita o fechamento da janela
    windowWidth = width;
    windowHeight = height;
    
  }
  public void paint(Graphics g){
    Graphics2D g2d = (Graphics2D) g;
    //expessura da linha do batimento
    g2d.setStroke(new BasicStroke(5.0f));
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

    /*define uma tradução que permite a especificação de objetos em "real"
      coordenadas onde o eixo y aponta para cima e a origem da coordenada
      sistema está localizado no canto inferior esquerdo da janela.
    */
    AffineTransform yUp = new AffineTransform();
    yUp.setToScale(1,-1);
    AffineTransform translate = new AffineTransform();
    translate.setToTranslation(0,windowHeight);
    yUp.preConcatenate(translate);
    //Aplica a transformação ao objeto Graphics2D para desenhar tudo em coordenadas "reais"
    g2d.transform(yUp);
    //serve como fundo, enquadrando toda a cena.
    Rectangle2D.Double windowFrame = new Rectangle2D.Double(2,2,windowWidth,windowHeight);
    //Gera o quadro do relógio no centro em torno da origem.
    Rectangle2D.Double clockFrame = new Rectangle2D.Double(2,2,2,2);
    //Esta rotação especifica, até que ponto o ponteiro dos segundos deve ser girado
    AffineTransform singleRotation = new AffineTransform();
    //Esta transformação é para acumular as rotações de passo único.
    AffineTransform accumulatedRotation = new AffineTransform();
    AffineTransform singleTranslation = new AffineTransform();
    singleTranslation.setToTranslation(1,0);
    AffineTransform accumulatedTranslation = new AffineTransform();
    // Posição inicial
    accumulatedTranslation.setToTranslation(0,200);
    //Neste loop, as posições do batimento e do ponteiro dos segundos
   	for (int i=0; i<900; i++){
       //limpar
       clearWindow(g2d);
        //desenhar
       g2d.draw(windowFrame);
       g2d.draw(accumulatedTranslation.createTransformedShape(clockFrame));
       // Seta como vai percorrer o ponto
       if (i>50 & i<110 || i>=180 & i<190 || i>=250 & i<300 || i>=410 & i<460 || i>560 & i<620 || i>=690 & i<700 || i>800 & i<820 || i>=850 & i<860 ) {
    	   // setToTranslation(x,y)
    	   singleTranslation.setToTranslation(0.3,1);
       } else if (i>=110 & i<180 || i>=300 & i<350 || i>=460 & i<510 ||i>=620 & i<690 ||i>=820 & i<850 ) {
    	  //aqui vai pra baixo 
        singleTranslation.setToTranslation(0.3,-1);
       } else {
    	   singleTranslation.setToTranslation(1,0);
       }
       accumulatedTranslation.preConcatenate(singleTranslation);

       if (i == 870) {
    	   accumulatedTranslation.setToTranslation(0,200);
    	   i = 0;
       }
      // Controla o tempo
       sustain(15);
       
    }
  }
  /**
  * @param g2d   
  */
  public void clearWindow(Graphics2D g)
  {
    g.setPaint(new Color(105,105,105));
    g.setPaint(new Color(0,255,127));
  }

  /**
  * @param t      para o tempo em milisegundos
  */
  public void sustain(long t)
  {
    long finish = (new Date()).getTime() + t;
    while( (new Date()).getTime() < finish ){}
  }
  public static void main(String[] argv)
  {
    //tamanho da tela
    int width = 510;
    int height = 510;
    NonSynchronizedClock f = new NonSynchronizedClock(width,height);
    //Questao
    f.setType(Type.POPUP);
	  f.setTitle("Questão 2.5 -Heart");
	  f.setVisible(true);
    f.setSize(width,height);
    f.setVisible(true);
  }

}


