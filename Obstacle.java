import java.awt.*;

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

    public int getY () {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getRadius() {
        return width/ 2.0;
    }

    public void render(Graphics2D g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, width, height);
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
    
        // Verifica se a distância é menor ou igual à soma dos raios da bola e do obstáculo
        return distance <= (ballRadius + obstacleRadius);
    }
}