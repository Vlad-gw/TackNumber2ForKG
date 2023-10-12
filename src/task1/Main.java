package task1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class AnimatedCircleSectorDrawer extends JPanel implements ActionListener {

    private int centerX;
    private int centerY;
    private int radius;
    private double startAngle;
    private double endAngle;
    private Color colorCenter;
    private Color colorOuter;   //цвет снаружи
    private Timer timer;

    private double angleOffset;  // угловое смещение

    public AnimatedCircleSectorDrawer() {
        this.centerX = 270;
        this.centerY = 300;
        this.radius = 250;
        this.startAngle = Math.toRadians(45);
        this.endAngle = Math.toRadians(90);
        this.colorCenter = Color.RED;
        this.colorOuter = Color.YELLOW;

        this.timer = new Timer(40, this);  // 40 миллисекунд для обновления анимации
        this.angleOffset = 0;
        this.timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Очистим предыдущее изображение
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        double currentStartAngle = startAngle + angleOffset;
        double currentEndAngle = endAngle + angleOffset;

        double currentStartAngle2 = startAngle - angleOffset;
        double currentEndAngle2 = endAngle - angleOffset;

        // Рисуем сектор окружности
        g2d.setColor(colorCenter);
        g2d.fillArc(centerX - radius, centerY - radius, 2 * radius, 2 * radius,
                (int) Math.toDegrees(currentStartAngle), (int) Math.toDegrees(currentEndAngle - currentStartAngle));
        g2d.drawArc(centerX - radius, centerY - radius, 2 * radius, 2 * radius,
                (int) Math.toDegrees(currentStartAngle), (int) Math.toDegrees(currentEndAngle - currentStartAngle));

        g2d.setColor(colorOuter);
        g2d.fillArc(centerX - radius, centerY - radius, 2 * radius, 2 * radius,
                (int) Math.toDegrees(currentStartAngle2), (int) Math.toDegrees(currentEndAngle2 - currentStartAngle2));
        g2d.drawArc(centerX - radius, centerY - radius, 2 * radius, 2 * radius,
                (int) Math.toDegrees(currentStartAngle2), (int) Math.toDegrees(currentEndAngle2 - currentStartAngle2));

        // Увеличиваем смещение для анимации
        angleOffset += Math.toRadians(1);

        // Проверка на полный оборот (360 градусов)
        if (angleOffset >= 2 * Math.PI)
            angleOffset = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();  // Обновляем анимацию при каждом тике таймера
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Сектора круга");
        AnimatedCircleSectorDrawer drawer = new AnimatedCircleSectorDrawer();
        frame.add(drawer);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}