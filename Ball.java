import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball
{

	public int x, y, width = 25, height = 25;

	public int motionX, motionY;

	public Random random;

	private Pong pong;

	public int amountOfHits;

	public Ball(Pong pong)
	{
		this.pong = pong;

		this.random = new Random();

		spawn();
	}

	public void update(Paddle paddle1, Obstacle obstacle)
	{
		if (this.y + this.height >= paddle1.y && this.y + this.height <= paddle1.y + motionY && this.x >= paddle1.x && this.x <= paddle1.x + paddle1.width) {
            // Bola quica
            this.motionY *= -1;
            // Reposiciona a bola logo acima da barra
            this.y = paddle1.y - this.height;
        }

		if (obstacle.collidesWith(this)) {
			// Ajusta a posição da bola para fora do obstáculo
			double dx = x + width / 2 - (obstacle.getX() + obstacle.getWidth() / 2);
			double dy = y + height / 2 - (obstacle.getY() + obstacle.getHeight() / 2);
			double angle = Math.atan2(dy, dx);
			double distance = Math.sqrt(dx * dx + dy * dy);
			double minDistance = obstacle.getRadius() + getRadius();
			x = (int) ((obstacle.getX() + obstacle.getWidth() / 2) + Math.cos(angle) * minDistance - width / 2);
			y = (int) ((obstacle.getY() + obstacle.getHeight() / 2) + Math.sin(angle) * minDistance - height / 2);
			
			// Inverte a direção vertical da bola
			motionY *= -1;
		}
		
        int speed = 5;
		
        this.x += motionX * speed;
        this.y += motionY * speed;

        // Verifica colisão com as paredes verticais (extremidades da tela)
        if (this.x + width >= pong.width || this.x <= 0) {
            motionX *= -1; // Inverte a direção horizontal
        }

        // Verifica colisão com as paredes horizontais (topo e fundo da tela)
        if (this.y <= 0 || this.y + height >= pong.height) {
            motionY *= -1; // Inverte a direção vertical
        }

		if (checkCollision(paddle1) == 1)
		{
			this.motionX = 1 + (amountOfHits / 5);
			this.motionY = -2 + random.nextInt(2);
			
			if (motionY == 0)
			{
				motionY--;
			}
			
			if (motionX == 0)
			{
				motionX--;
			}
			amountOfHits++;
			pong.score += 20;
		}
		
		if (checkCollision(paddle1) == 2)
		{
			paddle1.lifes--;
			spawn();
		}
	}

	public void spawn()
	{
		this.amountOfHits = 0;
		this.x = pong.width / 2 - this.width / 2;
		this.y = pong.height / 2 - this.height / 2;

		this.motionY = -2 + random.nextInt(4);

		if (motionY == 0)
		{
			motionY = 1;
		}

		if (random.nextBoolean())
		{
			motionX = -1;
		}
		else
		{
			motionX = 1;
		}
	}
	
	public int getX () {
		return x;
	}

	public int getY () {
		return y;
	}

	public double getRadius() {
		return width/ 2.0;
	}

	public int checkCollision(Paddle paddle) {
		
	    if (this.x + this.width >= paddle.x && this.x <= paddle.x + paddle.width && 
        this.y + this.height >= paddle.y && this.y <= paddle.y + paddle.height) {
        return 1; //bounce
    } else if ((paddle.y < y && paddle.paddleNumber == 1) || (paddle.y > y - width && paddle.paddleNumber == 2)) {
        return 2; //score
    }
    return 0; //nothing
		
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillOval(x, y, width, height);
	}

}