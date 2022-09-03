package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static sample.Main.trimZeros;

public class Connect
{
    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    Thread in_thread;
    boolean enabled = true;

    public Connect(Socket s)
    {
        socket = s;

        try
        {
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {e.printStackTrace();}

        Thread in_thread = new Thread(() -> {
            while (enabled)
            {
                try{Thread.sleep(500);}
                catch (InterruptedException e) {e.printStackTrace();}

                synchronized (in)
                {
                    try {
                        int I;
                        I = in.readInt();
                        switch (I){
                            case 1: {  clear(); break;}
                            case 2: {  Graph_obj o = readObject(); if (o!=null) addObject(o); break;}
                            case 3: {  arraySize(getArraySize()); break;}
                            case 4: {  objectArray(); break;}
                            case 5: {  setArraySize(); break;}
                            case 6: {   break;}
                            case -1: {  close(); break;}
                            default: break;
                        }

                    }
                    catch (IOException e)
                    {
                        System.out.println("Disconnected"); break;
                    }
                }

            }
        });
     // in_thread.setDaemon(true);
        in_thread.start();
    }

    //Global Func
    public void clearArrayServer()
    {
        try
        {
            out.writeInt(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sizeArrayServer()
    {
        try
        {
            out.writeInt(3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void objectArrayServer(int I)
    {
        try
        {
            out.writeInt(4);
            out.writeInt(I);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //private Func
    public Graph_obj readObject()
    {
        System.out.println("object<-");

        byte[] buffer = new byte[50];
        try {
            in.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Graph_obj O = null;

        byte[] Bi = Arrays.copyOfRange(buffer, 0, 49);

        String Name = trimZeros(new String(Bi));;
        Class cls = null;
        try {
            cls = Class.forName(Name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (cls != null)
        {
            Object obj = null;
            try {
                obj = cls.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (obj instanceof Graph_obj)
            {
                try
                {
                    ((Graph_obj) obj).load(in);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                O = (Graph_obj) obj;
            }
        }

        return O;
    };
    private void arraySize(int size)
    {
        try
        {
            out.writeInt(5);
            out.writeInt(size);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void objectArray()
    {
        int I = -1;
        try {
            I = in.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if ((I != -1) &&(I < getArraySize()))
        {
            try {
                out.writeInt(2);

                byte[] buffer = new byte[50];
                byte[] S = getObject(I).getClass().getName().getBytes(StandardCharsets.UTF_8);
                for(int i = 0; (i < (S.length)) && (i < 50) ; i++) buffer[i] = S[i];

                out.write(buffer);
                out.write(getObject(I).toBytes());
                System.out.println("object->");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void setArraySize()
    {
        int I = -1;
        try {
            I = in.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (I != -1) setArraySize(I);
    }

    //Override Func
    public void clear(){}
    public void addObject(Graph_obj obj){}
    public int getArraySize(){return 0;};
    public Graph_obj getObject(int i){return null;}
    public void setArraySize(int size){}



    public void close()
    {
        enabled = false;
        if (in_thread != null) in_thread.stop();
        try
        {
            out.writeInt(-1);
            socket.close();
        } catch (IOException e)
        {
            //e.printStackTrace();
        }

    }

}
