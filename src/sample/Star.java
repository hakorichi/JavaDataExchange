package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.io.Serializable;


public class Star extends Graph_obj implements Serializable
{

    int _n = 5;
    int _m = 2;
    double metallic = 0.0;

    public Star()
    {
        super();
        inicialiseStar();
    }
    public Star(int X, int Y) {
        super(X, Y);
        inicialiseStar();
    }
    public Star(double X, double Y) {
        super(X, Y);
        inicialiseStar();
    }

    void inicialiseStar()
    {
        _n = 5 + (int)(Math.pow(Math.random(),2.5)* 8);

        _m = (int) (Math.random() * (double)(((_n - 1) / 2) - 1)) + 2;

        metallic = Math.random();
        sizeTrail = size * Math.cos(Math.PI / _n * _m) / Math.cos(Math.PI / _n * (_m -1));
    }

    @Override
    public void load(String str)
    {
        str = subload(str);
        String[] strArray = str.split(" ");
        _n = Integer.parseInt(strArray[0]);
        _m = Integer.parseInt(strArray[1]);
        metallic = Double.parseDouble(strArray[2]);
    }

    @Override
    public byte[] toBytes() throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        dos.write(super.toBytes());
        dos.writeInt(_n);
        dos.writeInt(_m);
        dos.writeDouble(metallic);
        dos.flush();
        return bos.toByteArray();
    }

    public void set_n(int _n) {
        this._n = _n;
    }

    public void set_m(int _m) {
        this._m = _m;
    }

    public void setMetallic(double metallic) {
        metallic = metallic;
    }

    public int get_n() {
        return _n;
    }

    public int get_m() {
        return _m;
    }

    public double getMetallic() {
        return metallic;
    }

    @Override
    public byte[] load(byte[] buffer) throws IOException
    {
        buffer = subload(buffer);
        ByteArrayInputStream bos = new ByteArrayInputStream(buffer);
        DataInputStream dos = new DataInputStream(bos);
        _n = dos.readInt();
        _m = dos.readInt();
        metallic = dos.readDouble();

        try{ buffer = dos.readAllBytes(); }
        catch (IOException e){ e.printStackTrace();  buffer = null;}
        dos.close();
        return  buffer;
    }

    @Override
    public void load(DataInputStream buffer) throws IOException
    {
        subload(buffer);
        _n = buffer.readInt();
        _m = buffer.readInt();
        metallic = buffer.readDouble();
    }

    @Override
    public boolean belongs(Point I)
    {
        double L = Math.sqrt((I.getX() - positionX)*(I.getX() - positionX) +(I.getY() - positionY)*(I.getY() - positionY));
        return (L < size);
    }

    @Override
    public String toString()
    {
        String str = super.toString();
        str += " " + _n;
        str += " " + _m;
        str += " " + metallic;
        return str;
    }

    @Override
    public void draw(Group root)
    {

        double R2 = size * Math.cos(Math.PI / _n * _m) / Math.cos(Math.PI / _n * (_m -1));

        double x = positionX;
        double y = positionY;
        double xxOld = 0;
        double yyOld = 0;

        for(int i = 0; i <= _n *2; i++)
        {
            double Scale = size;
            if(i % 2 == 0) Scale = R2;
            double Angle = rotation +  ((double)i)/(_n *2) * 2 * Math.PI;

            double xx = Math.cos(Angle) * Scale;
            double yy = Math.sin(Angle) * Scale;

            if (i > 0)
            {

                double Brightness =          (Math.cos(Angle - ((double)_m/(_n*1)  * Math.PI )) + 1 ) / 2 * 0.7 + 0.3;
                if (i % 2 == 0) Brightness = (Math.cos(Angle + ((double)(_m-1)/(_n*1)  * Math.PI )) + 1 ) / 2 * 0.7 + 0.3;




                Brightness = (Brightness*Brightness*Brightness * (2)) * metallic + (1 - metallic) * Brightness;


                double Over = Brightness - 1;
                if (Over < 0) Over = 0;
                if (Brightness > 1) Brightness = 1;



                double R = ((double)color.getRed()/ 255 * Brightness )  + Over; if(R>1) R=1; if(R<0) R=0;
                double G = ((double)color.getGreen()/ 255 * Brightness)  + Over;if(G>1) G=1; if(G<0) G=0;
                double B = ((double)color.getBlue()/ 255 * Brightness )  + Over;if(B>1) B=1; if(B<0) B=0;

                Color C = Color.color(R,G,B);

                ArrayList<Double> list = new ArrayList<Double>();

                list.add(x); //x
                list.add(y); //y

                list.add(x + xx ); //x
                list.add(y + yy); //y

                list.add(x + xxOld); //x
                list.add(y + yyOld); //y

                Polygon polygon = new Polygon();
                polygon.getPoints().addAll(list);

                polygon.setFill(C);
                root.getChildren().add(polygon);
            }
            xxOld=xx;
            yyOld=yy;
        }
    }


    void draw2(Group root)
    {

        double R2 = size * Math.cos(Math.PI / _n * _m) / Math.cos(Math.PI / _n * (_m -1));

        double x = positionX;
        double y = positionY;

        ArrayList<Double> list = new ArrayList<Double>();



        double xxOld = 0;
        double yyOld = 0;
        for(int i = 0; i < _n *2; i++)
        {
            double Scale = size;
            if(i % 2 == 0) Scale = R2;
            double Angle = rotation +  ((double)i)/(_n *2) * 2 * Math.PI;

            double xx = Math.cos(Angle);
            double yy = Math.sin(Angle);

            if (i > 0)
            {
                list.add(x + 0 * Scale); //x
                list.add(y + 0 * Scale); //y

                list.add(x + xxOld * Scale); //x
                list.add(y + yyOld * Scale); //y

                list.add(x + xx * Scale); //x
                list.add(y + yy * Scale); //y
            }
            xxOld=xx;
            yyOld=yy;
        }

        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(list);

        polygon.setFill(ToColor(color));
        root.getChildren().add(polygon);
    }
}
