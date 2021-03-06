package aljava.saida;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

public class Tela extends JFrame
{
    private final int defaultScreenWidth = 7;
    private final int defaultScreenHeight = 33;
    private int largura;
    private int altura;

    private Graphics g;
    private BufferStrategy bf;

    private int renderScreenStartX;
    private int renderScreenStartY;

    public Tela(GraphicsConfiguration gc, int width, int height)
    {
        super(gc);
        this.largura = width;
        this.altura = height;
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(this.largura+defaultScreenWidth, this.altura+defaultScreenHeight);
        setResizable(false);
        setVisible(true);
        
        createBufferStrategy(2);

        renderScreenStartX = this.getContentPane().getLocationOnScreen().x;
        renderScreenStartY = this.getContentPane().getLocationOnScreen().y;

        bf = getBufferStrategy();
    }
    
    public void tamanho(int largura, int altura){
      this.largura = largura;
      this.altura = altura;
      setSize(this.largura+defaultScreenWidth, this.altura+defaultScreenHeight);
      dispose();
    }

    public int getLargura(){
        return this.largura;
    }
    
    public int getAltura(){
        return this.altura;
    }
    
    public int getStartX()
    {
        return renderScreenStartX;
    }

    public int getStartY()
    {
        return renderScreenStartY;
    }

    @Override
    public Graphics2D getGraphics()
    {        
        g = bf.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        return g2d;
    }

    public void trocaBuffers()
    {
        bf.show();
        g.dispose();       
        Toolkit.getDefaultToolkit().sync();
    }
}