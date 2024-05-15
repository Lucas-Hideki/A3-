
import java.awt.*;
import java.awt.image.BufferedImage;

public class Obstacle {

    private int x, y, width, height;

    public Obstacle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getRadius() {
        return width / 2.0;
    }

    public void render(Graphics2D g) {
        // Definindo a cor base
        Color baseColor = Color.RED;

        // Desenhar o objeto principal
        g.setColor(baseColor);
        g.fillOval(x, y, width, height);

        // Adicionando efeito de iluminação especular
        // Aqui estou adicionando uma sobreposição de cor amarela
        // Você pode ajustar a cor e a intensidade conforme desejado
        int lightSizeIncrease = 50; // Aumento no tamanho da luz
        g.setColor(new Color(255, 255, 0, 70)); // Amarelo com transparência
        g.fillOval(x + width / 4 - lightSizeIncrease, y + height / 4 - lightSizeIncrease, width / 2 + 2 * lightSizeIncrease, height / 2 + 2 * lightSizeIncrease);
        
        testando(g);
    }

    public void testando(Graphics2D g) {
        // Definindo cores
        Color baseColor = Color.RED; // Cor base do obstáculo
        Color darkBaseColor = new Color(baseColor.getRed() / 2, baseColor.getGreen() / 2, baseColor.getBlue() / 2); // Cor base mais escura
        Color lightColor = Color.WHITE; // Cor da luz

        // Criando gradiente radial
        int width = getWidth();
        int height = getHeight();
        int radius = Math.min(width, height) / 2; // Raio do gradiente maior que o obstáculo

        // Define um array de posições (0.0f to 1.0f) para cada cor
        float[] fractions = {0.0f, 1.0f}; // Primeira cor em 0.0f, segunda cor em 1.0f

        // Define um array de cores para o gradiente
        Color[] colors = {lightColor, darkBaseColor}; // Branco no centro, vermelho escuro na borda

        // Cria o objeto RadialGradientPaint
        RadialGradientPaint gradient = new RadialGradientPaint(width / 2f, height / 2f, radius, fractions, colors);

        // Criando imagem temporária e desenhando esfera
        BufferedImage tempImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D tempG = tempImage.createGraphics();

        // Desenhando gradiente radial na imagem temporária
        tempG.setPaint(gradient);
        tempG.fillOval(0, 0, width, height);

        // Desenhando imagem temporária com gradiente sobre o obstáculo
        g.drawImage(tempImage, 960, 512, null);
    }

    public boolean collidesWith(Ball ball) {
        double ballX = ball.getX() + ball.getRadius(); // Calcula a posição do centro da bola
        double ballY = ball.getY() + ball.getRadius();
        double ballRadius = ball.getRadius();

        double obstacleX = x + width / 2; // Calcula a posição do centro do obstáculo
        double obstacleY = y + height / 2;
        double obstacleRadius = width / 2;

        // Calcula a distância entre o centro da bola e o centro do obstáculo
        double distance = Math.sqrt(Math.pow(ballX - obstacleX, 2) + Math.pow(ballY - obstacleY, 2));

        // Verifica se a distância é menor ou igual à soma dos raios da bola e do
        // obstáculo
        return distance <= (ballRadius + obstacleRadius);
    }
}
