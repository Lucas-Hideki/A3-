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

    // public void render(Graphics2D g, Vector3 lightDirection, Vector3 viewDirection, Vector3 observerPosition) {
    //     // Definindo a cor base
    //     Color baseColor = Color.RED;

    //     if (observerPosition == null) {
    //         observerPosition = new Vector3(x + width / 2.0, y + height / 2.0, 0); // Center of the obstacle
    //       }
    
    //     // Coordenadas do centro do objeto
    //     int centerX = x + width / 2;
    //     int centerY = y + height / 2;
    
    //     // Calculando o vetor normal da superfície
    //     Vector3 surfaceNormal = new Vector3(0, 0, 1);
    
    //     // Calculando o vetor direção da luz incidente
    //     Vector3 surfaceToLight = new Vector3(centerX - lightDirection.getX(), centerY - lightDirection.getY(), 0)
    //             .normalize();
    
    //     // Calculando o ângulo entre o vetor normal e o vetor direção da luz
    //     double diffuseAngle = surfaceNormal.dot(surfaceToLight);
    
    //     // Ajustando a intensidade da cor com base na iluminação difusa
    //     int diffuseIntensity = (int) (Math.max(0, diffuseAngle) * 255);
    
    //     // Calculando o vetor direção da luz refletida
    //     Vector3 reflectedLightDirection = surfaceToLight.reflect(surfaceNormal);
    
    //     // Calculando o ângulo entre o vetor normal, o vetor direção da luz refletida e o vetor direção do observador
    //     double specularAngle = surfaceNormal.dot(reflectedLightDirection);
    
    //     // Ajustando a intensidade da cor com base na iluminação especular
    //     int specularIntensity = (int) (Math.max(0, specularAngle) * 255);
    
    //     // Ajustando a cor do objeto com base na iluminação difusa e especular
    //     Color adjustedColor = new Color(
    //             Math.min(255, baseColor.getRed() + diffuseIntensity + specularIntensity),
    //             Math.min(255, baseColor.getGreen() + diffuseIntensity + specularIntensity),
    //             Math.min(255, baseColor.getBlue() + diffuseIntensity + specularIntensity));
    
    //     // Desenhar o objeto com a cor ajustada
    //     g.setColor(adjustedColor);
    //     g.fillOval(x, y, width, height);
    // }

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
        g.fillOval(x + width/4 - lightSizeIncrease, y + height/4 - lightSizeIncrease, width/2 + 2 * lightSizeIncrease, height/2 + 2 * lightSizeIncrease);
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