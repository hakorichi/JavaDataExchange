package sample;

import com.sun.javafx.geom.Vec2d;
import javafx.css.Size;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.effect.Light;
import javafx.scene.paint.*;
import javafx.scene.shape.Polygon;

import javax.lang.model.element.Name;
import java.awt.Color;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.Serializable;


public abstract class Graph_obj implements Serializable {


    int SizeX = 800;
    int SizeY = 600;
    int nnn = 20;

    public void setSizeX(int sizeX) {
        SizeX = sizeX;
    }

    public void setSizeY(int sizeY) {
        SizeY = sizeY;
    }

    public void setSizeTrail(double sizeTrail) {
        this.sizeTrail = sizeTrail;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void setRotation(Double rotation) {
        this.rotation = rotation;
    }

    public void setRotation_speed(double rotation_speed) {
        this.rotation_speed = rotation_speed;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public int getSizeX() {
        return SizeX;
    }

    public int getSizeY() {
        return SizeY;
    }


    public double getSizeTrail() {
        return sizeTrail;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public double getSize() {
        return size;
    }

    public Double getRotation() {
        return rotation;
    }

    public double getRotation_speed() {
        return rotation_speed;
    }

    public Color getColor() {
        return color;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    transient ArrayList<Double> PointsTrailX;
    transient ArrayList<Double> PointsTrailY;
    transient ArrayList<Double> PointsTrailXn;
    transient ArrayList<Double> PointsTrailYn;

    protected double sizeTrail;
    protected double positionX;
    protected double positionY;
    protected double size;
    protected Double rotation;
    protected double rotation_speed;
    protected Color color;
    protected double velocityX;
    protected double velocityY;

    transient Thread _update;
    transient Thread _Trail;
    boolean UpdateBool;
    boolean TrailBool;

    abstract public boolean belongs(Point I);

    abstract public void draw(Group root);

    public Graph_obj() {
        inicialise();
    }

    public Graph_obj(int X, int Y) {
        positionX = X;
        positionY = Y;
        inicialise();
    }

    public Graph_obj(Double X, Double Y) {
        positionX = X;
        positionY = Y;
        inicialise();
    }

    void inicialise() {
        java.awt.Color C = java.awt.Color.getHSBColor((float) (Math.random()), 1, 1);
        color = new Color((float) C.getRed() / 255, (float) C.getGreen() / 255, ((float) C.getBlue() / 255));
        size = 10.0 + Math.random() * 40;
        sizeTrail = size;
        rotation = Math.random() * 2 * Math.PI;
        velocityX = Math.random() * 4 - 2;
        velocityY = Math.random() * 4 - 2;

        rotation_speed = -2 + (Math.random() * 4);

        PointsTrailX = new ArrayList<>();
        PointsTrailY = new ArrayList<>();
        PointsTrailXn = new ArrayList<>();
        PointsTrailYn = new ArrayList<>();


        _update = new Thread(update);
        _update.start();
        _Trail = new Thread(Trail);
        _Trail.start();
    }

    public String toString() {
        String str = "";
        str += " " + positionX;
        str += " " + positionY;
        str += " " + size;
        str += " " + sizeTrail;
        str += " " + rotation;
        str += " " + rotation_speed;
        str += " " + color.getRed();
        str += " " + color.getGreen();
        str += " " + color.getBlue();
        str += " " + UpdateBool;
        str += " " + TrailBool;
        str += " " + velocityX;
        str += " " + velocityY;
        return str;
    }

    public void load(String str) {
        subload(str);
    }

    public String subload(String str) {
        String[] strArray = str.split(" ");
        positionX = Double.parseDouble(strArray[0]);
        positionY = Double.parseDouble(strArray[1]);
        size = Double.parseDouble(strArray[2]);
        sizeTrail = Double.parseDouble(strArray[3]);
        rotation = Double.parseDouble(strArray[4]);
        rotation_speed = Double.parseDouble(strArray[5]);
        int R = Integer.parseInt(strArray[6]);
        int G = Integer.parseInt(strArray[7]);
        int B = Integer.parseInt(strArray[8]);
        color = new Color(R,G,B);
        UpdateBool = Boolean.parseBoolean(strArray[9]);
        TrailBool = Boolean.parseBoolean(strArray[10]);
        velocityX = Double.parseDouble(strArray[11]);
        velocityY = Double.parseDouble(strArray[12]);

        return str.substring(strArray[0].length()
                + strArray[1].length()
                + strArray[2].length()
                + strArray[3].length()
                + strArray[4].length()
                + strArray[5].length()
                + strArray[6].length()
                + strArray[7].length()
                + strArray[8].length()
                + strArray[9].length()
                + strArray[10].length()
                + strArray[11].length()
                + strArray[12].length() + 13);
    }


    public byte[] toBytes() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeDouble(positionX);
        dos.writeDouble(positionY);
        dos.writeDouble(size);
        dos.writeDouble(sizeTrail);
        dos.writeDouble(rotation);
        dos.writeDouble(rotation_speed);
        dos.writeInt(color.getRed());
        dos.writeInt(color.getGreen());
        dos.writeInt(color.getBlue());
        dos.writeBoolean(UpdateBool);
        dos.writeBoolean(TrailBool);
        dos.writeDouble(velocityX);
        dos.writeDouble(velocityY);
        dos.flush();

        return bos.toByteArray();
    }

    public javafx.scene.paint.Color ToColor(java.awt.Color awtColor)
{
    int r = awtColor.getRed();
    int g = awtColor.getGreen();
    int b = awtColor.getBlue();
    int a = awtColor.getAlpha();
    double opacity = a / 255.0;
    return javafx.scene.paint.Color.rgb(r,g,b,opacity);
}

    public byte[] load(byte[] buffer) throws IOException {
        return subload(buffer);
    }
    public byte[] subload(byte[] buffer) throws IOException {
        ByteArrayInputStream bos = new ByteArrayInputStream(buffer);
        DataInputStream dos = new DataInputStream(bos);
        positionX = dos.readDouble();
        positionY = dos.readDouble();
        size = dos.readDouble();
        sizeTrail = dos.readDouble();
        rotation = dos.readDouble();
        rotation_speed = dos.readDouble();
        color = new Color( dos.readInt(),dos.readInt(), dos.readInt());
        UpdateBool = dos.readBoolean();
        TrailBool = dos.readBoolean();
        velocityX = dos.readDouble();
        velocityY = dos.readDouble();
        try{ buffer = dos.readAllBytes(); }
        catch (IOException e){ e.printStackTrace();  buffer = null;}
        dos.close();
        return  buffer;
    }

    public void load(DataInputStream buffer) throws IOException
    {
        subload(buffer);
    }
    public void subload(DataInputStream buffer) throws IOException
    {
        positionX = buffer.readDouble();
        positionY = buffer.readDouble();
        size = buffer.readDouble();
        sizeTrail = buffer.readDouble();
        rotation = buffer.readDouble();
        rotation_speed = buffer.readDouble();
        color = new Color( buffer.readInt(),buffer.readInt(), buffer.readInt());
        UpdateBool = buffer.readBoolean();
        TrailBool = buffer.readBoolean();
        velocityX = buffer.readDouble();
        velocityY = buffer.readDouble();
    }

    void Update()
    {
        rotation = (rotation +  0.01 * rotation_speed);
        positionX += velocityX;
        positionY += velocityY;
        if (positionX- size <0 ) {positionX = size; velocityX= Math.abs(velocityX);}
        if (positionY- size <0 ) {positionY = size; velocityY= Math.abs(velocityY);}
        if (SizeX - size < positionX ) {positionX = SizeX - size; velocityX= -Math.abs(velocityX);}
        if (SizeY - size < positionY ) {positionY = SizeY - size; velocityY= -Math.abs(velocityY);}
    }

    transient Runnable update = () ->
    {
        while(true)
        {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Update();
        }
    };

    transient Runnable Trail = () ->
    {
        while(true)
        {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            TrailUpdate();
        }
    };

    void TrailUpdate()
    {
        double Rx = velocityY;
        double Ry = velocityX;
        double l = Math.sqrt(Rx * Rx + Ry * Ry);

        PointsTrailX.add(positionX);
        PointsTrailY.add(positionY);
        PointsTrailXn.add(velocityX/l);
        PointsTrailYn.add(velocityY/l);

        if(PointsTrailX.size() > nnn) PointsTrailX.remove(0);
        if(PointsTrailY.size() > nnn) PointsTrailY.remove(0);
        if(PointsTrailXn.size() > nnn) PointsTrailXn.remove(0);
        if(PointsTrailYn.size() > nnn) PointsTrailYn.remove(0);
    }

    void drawTrail(Group root) {
        double R = (double) color.getRed() / 255;
        double G = (double) color.getGreen() / 255;
        double B = (double) color.getBlue() / 255;

        for (int i = 1; i < PointsTrailYn.size(); i++) {
            try {
                javafx.scene.paint.Color C = javafx.scene.paint.Color.color((float) R, (float) G, (float) B, (float) (0.2 * i / nnn));


                ArrayList<Double> list = new ArrayList<Double>();

                list.add(PointsTrailX.get(i - 1) + PointsTrailYn.get(i - 1) * sizeTrail * Math.pow(((double) i - 1) / nnn, 1.4));
                list.add(PointsTrailY.get(i - 1) - PointsTrailXn.get(i - 1) * sizeTrail * Math.pow(((double) i - 1) / nnn, 1.4));

                list.add(PointsTrailX.get(i - 1) - PointsTrailYn.get(i - 1) * sizeTrail * Math.pow(((double) i - 1) / nnn, 1.4));
                list.add(PointsTrailY.get(i - 1) + PointsTrailXn.get(i - 1) * sizeTrail * Math.pow(((double) i - 1) / nnn, 1.4));

                list.add(PointsTrailX.get(i) - PointsTrailYn.get(i) * sizeTrail * Math.pow((double) i / nnn, 1.4));
                list.add(PointsTrailY.get(i) + PointsTrailXn.get(i) * sizeTrail * Math.pow((double) i / nnn, 1.4));

                list.add(PointsTrailX.get(i) + PointsTrailYn.get(i) * sizeTrail * Math.pow((double) i / nnn, 1.4));
                list.add(PointsTrailY.get(i) - PointsTrailXn.get(i) * sizeTrail * Math.pow((double) i / nnn, 1.4));


                javafx.scene.shape.Polygon polygon = new Polygon();
                polygon.getPoints().addAll(list);

                polygon.setFill(C);
                root.getChildren().add(polygon);
            } catch (IndexOutOfBoundsException e) {
            }
        }
    }

    void drawDebug(Group root)
    {
        javafx.scene.paint.Color C = javafx.scene.paint.Color.color(1, 1, 1, 1);
        {
            ArrayList<Double> list = new ArrayList<Double>();
            list.add(positionX - 1);
            list.add(positionY - 10);
            list.add(positionX + 1);
            list.add(positionY - 10);
            list.add(positionX + 1);
            list.add(positionY + 10);
            list.add(positionX - 1);
            list.add(positionY + 10);
            javafx.scene.shape.Polygon polygon = new Polygon();
            polygon.getPoints().addAll(list);
            polygon.setFill(C);
            root.getChildren().add(polygon);
        }
        {
            ArrayList<Double> list = new ArrayList<Double>();
            list.add(positionX - 10);
            list.add(positionY - 1);
            list.add(positionX + 10);
            list.add(positionY - 1);
            list.add(positionX + 10);
            list.add(positionY + 1);
            list.add(positionX - 10);
            list.add(positionY + 1);
            javafx.scene.shape.Polygon polygon = new Polygon();
            polygon.getPoints().addAll(list);
            polygon.setFill(C);
            root.getChildren().add(polygon);
        }
    }


}

