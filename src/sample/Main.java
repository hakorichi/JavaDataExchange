package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Group;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;


public class Main extends Application implements Serializable {


    int sync_int = 20;
    boolean enabled = true;
    public int Mode = 0;
    List<Graph_obj> objArray;
    Scene scene;
    Group root ;
    Stage primaryStageM;
    boolean UpdateBool;
    boolean TrailBool;
    boolean Collision;
    boolean debugDraw = false;
    int PORT = 27015;
    int Method = 0;
    ArrayList<Connect> connectArray = new ArrayList<>();

    javafx.scene.control.Button B2;
    javafx.scene.control.Button B3;
    javafx.scene.control.Button B4;
    javafx.scene.control.Button B5;
    javafx.scene.control.Button B6;
    javafx.scene.control.Button B7;
    javafx.scene.control.Button B8;
    javafx.scene.control.Button B9;
    javafx.scene.control.Button B10;
    javafx.scene.control.Button B11;
    javafx.scene.control.Button B12;
    javafx.scene.control.Button B13;
    javafx.scene.control.Button B14;
    javafx.scene.control.Button B15;
    javafx.scene.control.Button B16;
    javafx.scene.control.Button B17;
    javafx.scene.control.Button B18;
    javafx.scene.control.Button B20;
    javafx.scene.control.Button B21;
    javafx.scene.control.Button B23;
    javafx.scene.control.Button B_i;
    javafx.scene.control.TextField TF1;
    javafx.scene.control.TextField TF2;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        if (Mode == 0) StartMain(primaryStage);
        if (Mode == 1) StartServer(primaryStage);
        if (Mode == 2) StartClient(primaryStage);
    }

    @Override
    public void stop()
    {
        for(Connect Con : connectArray) Con.close();
        connectArray.clear();
        enabled = false;
    }

    public void StartMain(Stage primaryStage) throws Exception
    {

        UpdateBool = true;
        TrailBool = true;
        Collision = true;


        primaryStageM = primaryStage;
        objArray = new ArrayList<>();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Group Root = (Group)loader.load();
        root = new Group();
        Root.getChildren().add(1,root);
        root.setLayoutX(0);



        primaryStage.setTitle("(ТРПО) - Меркульев");
        scene = new Scene(Root, 800, 600);

        AddMouseEvent(root);

        B2 = (javafx.scene.control.Button)(Root.getChildren().get(2));
        B2.setOnAction(e ->  Buttom2());
        B3 = (javafx.scene.control.Button)(Root.getChildren().get(3));
        B3.setOnAction(e -> Buttom3());
        B4 = (javafx.scene.control.Button)(Root.getChildren().get(4));
        B4.setOnAction(e -> Buttom4());
        B5 = (javafx.scene.control.Button)(Root.getChildren().get(5));
        B5.setOnAction(e -> Buttom5());
        B6 = (javafx.scene.control.Button)(Root.getChildren().get(6));
        B6.setOnAction(e -> Buttom6());
        B7 = (javafx.scene.control.Button)(Root.getChildren().get(7));
        B7.setOnAction(e -> Buttom7());
        B8 = (javafx.scene.control.Button)(Root.getChildren().get(8));
        B8.setOnAction(e -> Buttom8());
        B9 = (javafx.scene.control.Button)(Root.getChildren().get(9));
        B9.setOnAction(e -> Buttom9());
        B10 = (javafx.scene.control.Button)(Root.getChildren().get(10));
        B10.setOnAction(e -> Buttom10());
        B11 = (javafx.scene.control.Button)(Root.getChildren().get(11));
        B11.setOnAction(e ->  Buttom11());
        B12 = (javafx.scene.control.Button)(Root.getChildren().get(12));
        B12.setOnAction(e -> Buttom12());
        B13 = (javafx.scene.control.Button)(Root.getChildren().get(13));
        B13.setOnAction(e -> Buttom13());
        B14 = (javafx.scene.control.Button)(Root.getChildren().get(14));
        B14.setOnAction(e -> Buttom14());
        B15 = (javafx.scene.control.Button)(Root.getChildren().get(15));
        B15.setOnAction(e -> Buttom15());
        B16 = (javafx.scene.control.Button)(Root.getChildren().get(16));
        B16.setOnAction(e -> Buttom16());
        B17 = (javafx.scene.control.Button)(Root.getChildren().get(17));
        B17.setOnAction(e -> Buttom17());
        B18 = (javafx.scene.control.Button)(Root.getChildren().get(18));
        B18.setOnAction(e -> Buttom18());
        B_i = (javafx.scene.control.Button)(Root.getChildren().get(19));
        B_i.setOnAction(e -> Buttom_Info());

        primaryStage.setScene(scene);
        primaryStage.show();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run()
            {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        Update();
                    }
                };
                while (enabled)
                {
                    try
                    {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {}
                    Platform.runLater(updater);
                }
            }

        });
        thread.setDaemon(true);
        thread.start();
    }

    public void StartClient(Stage primaryStage) throws Exception
    {
        UpdateBool = true;
        TrailBool = true;
        Collision = true;
        primaryStageM = primaryStage;
        objArray = new ArrayList<>();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Client_2.fxml"));
        Group Root = (Group)loader.load();
        root = new Group();
        Root.getChildren().add(1,root);
        root.setLayoutX(0);

        primaryStage.setTitle("(ТРПО) - Меркульев  - Клиен");
        scene = new Scene(Root, 800, 600);

        AddMouseEvent(root);

        B2 = (javafx.scene.control.Button)(Root.getChildren().get(2));
        B2.setOnAction(e ->  Buttom2());
        B3 = (javafx.scene.control.Button)(Root.getChildren().get(3));
        B3.setOnAction(e -> Buttom3());
        B4 = (javafx.scene.control.Button)(Root.getChildren().get(4));
        B4.setOnAction(e -> Buttom4());
        B5 = (javafx.scene.control.Button)(Root.getChildren().get(5));
        B5.setOnAction(e -> Buttom5());
        B6 = (javafx.scene.control.Button)(Root.getChildren().get(6));
        B6.setOnAction(e -> Buttom6());
        B7 = (javafx.scene.control.Button)(Root.getChildren().get(7));
        B7.setOnAction(e -> Buttom7());
        B8 = (javafx.scene.control.Button)(Root.getChildren().get(8));
        B8.setOnAction(e -> Buttom8());
        B9 = (javafx.scene.control.Button)(Root.getChildren().get(9));
        B9.setOnAction(e -> Buttom9());
        B10 = (javafx.scene.control.Button)(Root.getChildren().get(10));
        B10.setOnAction(e -> Buttom10());
        B11 = (javafx.scene.control.Button)(Root.getChildren().get(11));
        B11.setOnAction(e ->  Buttom11());
        B12 = (javafx.scene.control.Button)(Root.getChildren().get(12));
        B12.setOnAction(e -> Buttom12());
        B13 = (javafx.scene.control.Button)(Root.getChildren().get(13));
        B13.setOnAction(e -> Buttom13());
        B14 = (javafx.scene.control.Button)(Root.getChildren().get(14));
        B14.setOnAction(e -> Buttom14());
        B15 = (javafx.scene.control.Button)(Root.getChildren().get(15));
        B15.setOnAction(e -> Buttom15());
        B16 = (javafx.scene.control.Button)(Root.getChildren().get(16));
        B16.setOnAction(e -> Buttom16());
        B17 = (javafx.scene.control.Button)(Root.getChildren().get(17));
        B17.setOnAction(e -> Buttom17());
        B18 = (javafx.scene.control.Button)(Root.getChildren().get(18));
        B18.setOnAction(e -> Buttom18());

        TF1 = (javafx.scene.control.TextField)(Root.getChildren().get(19));
        TF2 = (javafx.scene.control.TextField)(Root.getChildren().get(22));

        B20 = (javafx.scene.control.Button)(Root.getChildren().get(20));
        B20.setOnAction(e ->  Buttom20());
        B21 = (javafx.scene.control.Button)(Root.getChildren().get(21));
        B21.setOnAction(e ->  Buttom21());
        B23 = (javafx.scene.control.Button)(Root.getChildren().get(23));
        B23.setOnAction(e ->  Buttom23());


        primaryStage.setScene(scene);
        primaryStage.show();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run()
            {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        Update();
                    }
                };
                while (enabled)
                {
                    try
                    {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {}
                    Platform.runLater(updater);
                }
            }

        });
        thread.setDaemon(true);
        thread.start();



    }

    public void StartServer(Stage primaryStage) throws Exception
    {

        Thread Server = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                ServerSocket serverSocket = null;
                try {
                    serverSocket = new ServerSocket(PORT);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                while(enabled)
                {
                    try
                    {
                        System.out.println("Ожидание клиента на порт " + serverSocket.getLocalPort() + "...");
                        connectArray.add(newConnect(serverSocket.accept()));
                        Thread.sleep(100);
                    } catch (SocketTimeoutException s) {
                        System.out.println("Время сокета истекло!");
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                        break;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        Server.setDaemon(true);
        Server.start();

        Thread Server2 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                DatagramSocket serverSocket = null;
                try
                {
                    serverSocket = new DatagramSocket(PORT+1);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                while(enabled)
                {
                    System.out.print("-->");
                    try {
                        Thread.sleep(sync_int);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    DataOutputStream dos = new DataOutputStream(bos);

                    try
                    {
                        for(int i =0; i < objArray.size();i++)
                        {
                            dos.writeInt(i);
                            dos.writeDouble(objArray.get(i).positionX);
                            dos.writeDouble(objArray.get(i).positionY);
                            dos.writeDouble(objArray.get(i).velocityX);
                            dos.writeDouble(objArray.get(i).velocityY);
                        }
                        dos.flush(); dos.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    byte[] sendingDataBuffer = bos.toByteArray();

                    for(int i =0; i < connectArray.size(); i ++) {
                        System.out.print(" Client " + i);
                        DatagramPacket outputPacket = new DatagramPacket(sendingDataBuffer, sendingDataBuffer.length, connectArray.get(0).socket.getInetAddress(), PORT + 2);
                        try {
                            serverSocket.send(outputPacket);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("");
                }

                serverSocket.close();
            }

        });
        Server2.setDaemon(true);
        Server2.start();



        UpdateBool = true;
        TrailBool = true;
        Collision = true;
        primaryStageM = primaryStage;
        objArray = new ArrayList<>();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Group Root = (Group)loader.load();
        root = new Group();
        Root.getChildren().add(1,root);
        root.setLayoutX(0);

        primaryStage.setTitle("(ТРПО) - Меркульев - Сервер");
        scene = new Scene(Root, 800, 600);
        AddMouseEvent(root);


        B2 = (javafx.scene.control.Button)(Root.getChildren().get(2));
        B2.setOnAction(e ->  Buttom2());
        B3 = (javafx.scene.control.Button)(Root.getChildren().get(3));
        B3.setOnAction(e -> Buttom3());
        B4 = (javafx.scene.control.Button)(Root.getChildren().get(4));
        B4.setOnAction(e -> Buttom4());
        B5 = (javafx.scene.control.Button)(Root.getChildren().get(5));
        B5.setOnAction(e -> Buttom5());
        B6 = (javafx.scene.control.Button)(Root.getChildren().get(6));
        B6.setOnAction(e -> Buttom6());
        B7 = (javafx.scene.control.Button)(Root.getChildren().get(7));
        B7.setOnAction(e -> Buttom7());
        B8 = (javafx.scene.control.Button)(Root.getChildren().get(8));
        B8.setOnAction(e -> Buttom8());
        B9 = (javafx.scene.control.Button)(Root.getChildren().get(9));
        B9.setOnAction(e -> Buttom9());
        B10 = (javafx.scene.control.Button)(Root.getChildren().get(10));
        B10.setOnAction(e -> Buttom10());
        B11 = (javafx.scene.control.Button)(Root.getChildren().get(11));
        B11.setOnAction(e ->  Buttom11());
        B12 = (javafx.scene.control.Button)(Root.getChildren().get(12));
        B12.setOnAction(e -> Buttom12());
        B13 = (javafx.scene.control.Button)(Root.getChildren().get(13));
        B13.setOnAction(e -> Buttom13());
        B14 = (javafx.scene.control.Button)(Root.getChildren().get(14));
        B14.setOnAction(e -> Buttom14());
        B15 = (javafx.scene.control.Button)(Root.getChildren().get(15));
        B15.setOnAction(e -> Buttom15());
        B16 = (javafx.scene.control.Button)(Root.getChildren().get(16));
        B16.setOnAction(e -> Buttom16());
        B17 = (javafx.scene.control.Button)(Root.getChildren().get(17));
        B17.setOnAction(e -> Buttom17());
        B18 = (javafx.scene.control.Button)(Root.getChildren().get(18));
        B18.setOnAction(e -> Buttom18());
        B_i = (javafx.scene.control.Button)(Root.getChildren().get(19));
        B_i.setOnAction(e -> Buttom_Info());

        primaryStage.setScene(scene);
        primaryStage.show();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run()
            {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        Update();
                    }
                };
                while (enabled)
                {
                    try
                    {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {}
                    Platform.runLater(updater);
                }
            }

        });
        thread.setDaemon(true);
        thread.start();

    }

    protected Thread _update;


    private void AddMouseEvent(Group root)
    {
        root.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                Graph_obj obj = belongs(mouseEvent.getX(), mouseEvent.getY());
                if (obj != null)
                {
                    if (Method == 0 || Method == 1) objArray.remove(obj);
                    if (Method == 2)
                    {
                        if (obj.UpdateBool) {obj._update.suspend(); obj.UpdateBool= false;}
                        else {obj._update.resume(); obj.UpdateBool = true;}
                    }
                    if (Method == 3)
                    {
                        if (obj.TrailBool)
                        {obj._Trail.suspend(); obj.TrailBool= false;
                            obj.PointsTrailX.clear();
                            obj.PointsTrailY.clear();
                            obj.PointsTrailXn.clear();
                            obj.PointsTrailYn.clear();
                        }
                        else {obj._Trail.resume(); obj.TrailBool = true;}
                    }
                    if (Method == 4) System.out.println(obj.toString());
                }
                else
                {
                    if (Method == 0)
                    {
                        Graph_obj O = new Star(mouseEvent.getX(), mouseEvent.getY());
                        objArray.add(O);
                        if(!UpdateBool) {O.UpdateBool = false; O._update.suspend();}
                        if(!TrailBool) {O.TrailBool = false; O._Trail.suspend();}
                    }
                    if (Method == 1)
                    {
                        Graph_obj O = new MyPolygon(mouseEvent.getX(), mouseEvent.getY());
                        objArray.add(O);
                        if(!UpdateBool) {O.UpdateBool = false; O._update.suspend();}
                        if(!TrailBool) {O.TrailBool = false; O._Trail.suspend();}
                    }
                }
            }
        });
    }

    void Update()
    {
        if (debugDraw)
            if (Method!=4)
                debugDraw = false;
        if (!debugDraw)
            if (Method==4)
                debugDraw = true;


        root.getChildren().clear();

        Rectangle r = new Rectangle(0,0,9999,9999);
        r.setFill(Color.BLACK);
        root.getChildren().add(r);

        synchronized (objArray) {
            if (Collision)
                for (Graph_obj obj1 : objArray) {
                    for (Graph_obj obj2 : objArray) {
                        if (obj1 != obj2) {
                            double Rx = obj1.positionX - obj2.positionX;
                            double Ry = obj1.positionY - obj2.positionY;
                            double length = Math.sqrt(Rx * Rx + Ry * Ry);
                            if (obj1.size + obj2.size > length) {
                                if (length >= 1) {
                                    Rx /= length;
                                    Ry /= length;
                                } else {
                                    Rx = 1;
                                    Ry = 0;
                                    length = obj1.size + obj2.size;
                                }

                                double impulse1 = obj1.size * Math.sqrt(obj1.velocityX * obj1.velocityX) + (obj1.velocityY * obj1.velocityY);
                                double impulse2 = obj2.size * Math.sqrt(obj2.velocityX * obj2.velocityX) + (obj2.velocityY * obj2.velocityY);

                                double impulse = (impulse1 + impulse2) / (obj1.size + obj2.size);

                                impulse = 1 + Math.random() * 2;
                                obj2.velocityX = -Rx * impulse;
                                obj2.velocityY = -Ry * impulse;
                                obj1.velocityX = Rx * impulse;
                                obj1.velocityY = Ry * impulse;

                                double Mx = (obj1.positionX + obj2.positionX) / 2;
                                double My = (obj1.positionY + obj2.positionY) / 2;

                                length++;
                                obj2.positionX = Mx + -Rx * length / 2;
                                obj2.positionY = My + -Ry * length / 2;
                                obj1.positionX = Mx + Rx * length / 2;
                                obj1.positionY = My + Ry * length / 2;

                            }
                        }
                    }
                }


            for (Graph_obj obj : objArray) {
                obj.SizeX = (int) scene.getWidth();
                obj.SizeY = (int) scene.getHeight();
            }
        }
        for (int i = 0; i < objArray.size(); i++)
        {
            objArray.get(i).drawTrail(root);
            objArray.get(i).draw(root);
            if(debugDraw) objArray.get(i).drawDebug(root);
        }
    }

    void Buttom2()
    {
        Method = 0;
        System.out.println("-*-");
    }

    void Buttom3()
    {
        Method = 1;
        System.out.println("-0-");
    }

    void Buttom4()
    {
        System.out.println("-Generate-");

        objArray.clear();
        for (int i =0; i < 20; i++)
        {
            double X =   Math.random() * (scene.getWidth() - root.getLayoutX());
            double Y =   Math.random() * scene.getHeight();
            objArray.add(new Star(X, Y));
        }
        for (int i =0; i < 20; i++)
        {
            double X =   Math.random() * (scene.getWidth() - root.getLayoutX());
            double Y =   Math.random() * scene.getHeight();
            objArray.add(new MyPolygon(X,Y));
        }
    }

    void Buttom5()
    {
        System.out.println("-5-");
        Method = 2;
    }

    void Buttom6()
    {
        System.out.println("-6-");
        Method = 3;
    }

    void Buttom7()
    {
        UpdateBool = !UpdateBool;
        for (Graph_obj obj: objArray)
        {
            if(UpdateBool)
            {
                obj._update.resume();

            }
            else {
                obj._update.suspend();

            }
            obj.UpdateBool = UpdateBool;
        }

    }

    void Buttom8()
    {
        TrailBool = !TrailBool;
        for (Graph_obj obj: objArray)
        {
            if(TrailBool)
            {
                obj._Trail.resume();
            }
            else
            {
                obj._Trail.suspend();
                obj.PointsTrailX.clear();
                obj.PointsTrailY.clear();
                obj.PointsTrailXn.clear();
                obj.PointsTrailYn.clear();
            }
            obj.TrailBool = TrailBool;
        }
    }

    void Buttom9()
    {
        Collision = !Collision;
    }

    void Buttom10()
    {
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                FileDialog fileDialog = new FileDialog(new Frame(), "Export...", FileDialog.SAVE);
                fileDialog.setDirectory(System.getProperty("user.dir"));
                fileDialog.setFile("*.txt");
                fileDialog.setVisible(true);
                String fileName = fileDialog.getFile();
                if (fileName != null)
                {
                    try
                    {
                        if (!fileName.endsWith(".txt")) {
                            fileName = fileName + ".txt";
                        }
                        FileWriter myWriter = null;
                        myWriter = new FileWriter(fileDialog.getDirectory() + fileName);
                        myWriter.write(""+(objArray.size()));
                        for (Graph_obj obj: objArray)
                        {
                            myWriter.write("\n"+ obj.getClass().getName() + obj.toString());
                        }

                        myWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    void Buttom11()
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run()
            {
                FileDialog fileDialog = new FileDialog(new Frame(), "Export...", FileDialog.LOAD);
                fileDialog.setDirectory(System.getProperty("user.dir"));
                fileDialog.setFile("*.txt");
                fileDialog.setVisible(true);
                String fileName = fileDialog.getFile();
                if (fileName != null) {
                    try {
                        FileReader file = null;
                        file = new FileReader(fileDialog.getDirectory() + fileName);
                        Scanner scan = new Scanner(file);
                        scan.nextLine();
                        synchronized (objArray) {
                            objArray.clear();
                            while (scan.hasNextLine()) {
                                String str = scan.nextLine();
                                String[] strArray = str.split(" ");

                                System.out.print(strArray[0]);
                                Class cls = Class.forName(strArray[0]);
                                if (cls != null) {
                                    System.out.print("  - found");
                                    Object obj = cls.newInstance();
                                    if (obj instanceof Graph_obj) {
                                        System.out.print("  - load");
                                        ((Graph_obj) obj).load(str.substring(strArray[0].length() + 1));
                                        objArray.add((Graph_obj) obj);
                                    }
                                }
                                System.out.println("");
                            }
                        }
                        System.out.println(objArray.size());
                        file.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    void Buttom12()
    { Thread thread = new Thread(new Runnable()
    {
        @Override
        public void run()
        {
        FileDialog fileDialog = new FileDialog(new Frame(), "Export...", FileDialog.SAVE);
        fileDialog.setDirectory(System.getProperty("user.dir"));
        fileDialog.setFile("*.bin");
        fileDialog.setVisible(true);
        String fileName = fileDialog.getFile();
        if (fileName != null)
        {
            try
            {
                if (!fileName.endsWith(".bin")) {
                    fileName = fileName + ".bin";
                }

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                DataOutputStream dos = new DataOutputStream(bos);
                dos.writeInt(objArray.size());

                for (Graph_obj obj: objArray)
                {
                    byte[] buffer = new byte[50];
                    byte[] S = obj.getClass().getName().getBytes(StandardCharsets.UTF_8);
                    for(int i = 0; (i < (S.length)) && (i < 50) ; i++) buffer[i] = S[i];
                    dos.write(buffer);
                    dos.write(obj.toBytes());
                }
                dos.write(new byte[8]);
                dos.flush();

                Files.write(Path.of(fileDialog.getDirectory() + fileName), bos.toByteArray());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }   }
    });
        thread.setDaemon(true);
        thread.start();
    }

    void Buttom13()
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run()
            {
                FileDialog fileDialog = new FileDialog(new Frame(), "Export...", FileDialog.LOAD);
                fileDialog.setDirectory(System.getProperty("user.dir"));
                fileDialog.setFile("*.bin");
                fileDialog.setVisible(true);
                String fileName = fileDialog.getFile();
                if (fileName != null) {
                    try {
                        synchronized (objArray)
                        {
                            objArray.clear();

                            ByteArrayInputStream bos = new ByteArrayInputStream(Files.readAllBytes(Path.of(fileDialog.getDirectory() + fileName)));
                            DataInputStream dos = new DataInputStream(bos);

                            dos.readInt();
                            byte[] buffer = dos.readAllBytes();
                            while (buffer.length >= 50) {
                                byte[] Bi = Arrays.copyOfRange(buffer, 0, 49);
                                buffer = Arrays.copyOfRange(buffer, 50, buffer.length - 1);
                                String Name = trimZeros(new String(Bi));
                                ;
                                System.out.print(Name);

                                Class cls = Class.forName(Name);
                                if (cls != null) {
                                    System.out.print("  - found");
                                    Object obj = cls.newInstance();
                                    if (obj instanceof Graph_obj) {
                                        System.out.print("  - load");
                                        buffer = ((Graph_obj) obj).load(buffer);
                                        objArray.add((Graph_obj) obj);
                                    }
                                }
                                System.out.println();
                            }
                            dos.close();
                        }

                        System.out.println(objArray.size());

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    void Buttom14()
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run()
            {
                FileDialog fileDialog = new FileDialog(new Frame(), "Export...", FileDialog.SAVE);
                fileDialog.setDirectory(System.getProperty("user.dir"));
                fileDialog.setFile("*.js");
                fileDialog.setVisible(true);
                String fileName = fileDialog.getFile();
                if (fileName != null)
                {
                    try
                    {
                        if (!fileName.endsWith(".js")) {
                            fileName = fileName + ".js";
                        }

                        FileOutputStream fos = new FileOutputStream(fileDialog.getDirectory() + fileName);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeInt(objArray.size());

                        for (Graph_obj obj: objArray)
                        {
                            oos.writeObject(obj);
                        }
                        oos.flush();
                        oos.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    void Buttom15()
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run()
            {
                FileDialog fileDialog = new FileDialog(new Frame(), "Export...", FileDialog.LOAD);
                fileDialog.setDirectory(System.getProperty("user.dir"));
                fileDialog.setFile("*.js");
                fileDialog.setVisible(true);
                String fileName = fileDialog.getFile();
                if (fileName != null) {
                    try {
                        synchronized (objArray) {
                            objArray.clear();

                            FileInputStream fis = new FileInputStream(fileDialog.getDirectory() + fileName);
                            ObjectInputStream oin = new ObjectInputStream(fis);
                            int Count = oin.readInt();

                            for (int i = 0; i < Count; i++) {
                                Graph_obj obj = (Graph_obj) (oin.readObject());

                                obj.PointsTrailX = new ArrayList<>();
                                obj.PointsTrailY = new ArrayList<>();
                                obj.PointsTrailXn = new ArrayList<>();
                                obj.PointsTrailYn = new ArrayList<>();

                                obj.update = () ->
                                {
                                    while (true) {
                                        try {
                                            Thread.sleep(10);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        obj.Update();
                                    }
                                };
                                obj.Trail = () ->
                                {
                                    while (true) {
                                        try {
                                            Thread.sleep(50);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        obj.TrailUpdate();
                                    }
                                };
                                obj._update = new Thread(obj.update);
                                obj._update.start();
                                obj._Trail = new Thread(obj.Trail);
                                obj._Trail.start();

                                objArray.add(obj);
                            }
                        }
                        System.out.println(objArray.size());

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    void Buttom16()
    {
        objArray.clear();
    }

    void Buttom17()
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run()
            {
                FileDialog fileDialog = new FileDialog(new Frame(), "Export...", FileDialog.SAVE);
                fileDialog.setDirectory(System.getProperty("user.dir"));
                fileDialog.setFile("*.xml");
                fileDialog.setVisible(true);
                String fileName = fileDialog.getFile();
                if (fileName != null)
                {
                    try
                    {
                        if (!fileName.endsWith(".xml")) {
                            fileName = fileName + ".xml";
                        }

                        FileOutputStream fos1 = new FileOutputStream(fileDialog.getDirectory() + fileName);
                        java.beans.XMLEncoder xe1 = new java.beans.XMLEncoder(fos1);

                        xe1.writeObject(objArray.size());
                        for (Graph_obj obj: objArray)
                        {
                            xe1.writeObject(obj);
                        }
                        xe1.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    void Buttom18()
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run()
            {
                FileDialog fileDialog = new FileDialog(new Frame(), "Export...", FileDialog.LOAD);
                fileDialog.setDirectory(System.getProperty("user.dir"));
                fileDialog.setFile("*.xml");
                fileDialog.setVisible(true);
                String fileName = fileDialog.getFile();
                if (fileName != null) {
                    try {
                        synchronized (objArray) {
                            objArray.clear();

                            FileInputStream fos1 = new FileInputStream(fileDialog.getDirectory() + fileName);
                            java.beans.XMLDecoder xe1 = new java.beans.XMLDecoder(fos1);

                            int Count = (int) xe1.readObject();

                            for (int i = 0; i < Count; i++) {
                                Graph_obj obj = (Graph_obj) (xe1.readObject());

                                objArray.add(obj);
                            }
                            xe1.close();
                        }
                        System.out.println(objArray.size());

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }


    Thread client_th;
    boolean enabled_con = false;
    void Buttom20()
    {
        if (connectArray.size() > 0)
        {
            connectArray.get(0).close();
            connectArray.clear();
            B20.setText("Подключиться");
            enabled_con = false;
        }
        else {
            try
            {
                System.out.println("Подключение к серверу -" + TF1.getText());
                Socket client = new Socket(TF1.getText(), PORT);
                connectArray.add(newConnect(client));
                B20.setText("Отключиться");
                enabled_con = true;

                if(client_th!= null)client_th.stop();

                client_th = new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        DatagramSocket clientSocket = null;
                        try
                        {
                            clientSocket = new DatagramSocket(PORT+2);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        while(enabled_con)
                        {
                            try {
                                Thread.sleep(sync_int);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            byte[] Buffer = new byte[objArray.size() * (Double.BYTES * 4 + 4)];

                            DatagramPacket receivingPacket = new DatagramPacket(Buffer,Buffer.length);

                            try {
                                clientSocket.receive(receivingPacket);

                                ByteArrayInputStream bos = new ByteArrayInputStream(Buffer);
                                DataInputStream dos = new DataInputStream(bos);

                                synchronized (objArray)
                                {
                                    for (int i = 0; i < objArray.size(); i++) {

                                        int j = dos.readInt();

                                        objArray.get(i).positionX = dos.readDouble();
                                        objArray.get(i).positionY = dos.readDouble();
                                        objArray.get(i).velocityX = dos.readDouble();
                                        objArray.get(i).velocityY = dos.readDouble();
                                    }
                                }
                            } catch (IOException e) {
                                //e.printStackTrace();
                            }
                        }
                        clientSocket.close();
                    }


                });
                client_th.setDaemon(true);
                client_th.start();



            } catch (ConnectException e) {
                System.out.println("Ошибка соединения");
            } catch (UnknownHostException e)
            {
                System.out.println("Ошибка");
                e.printStackTrace();
            } catch (IOException e)
            {
                System.out.println("Ошибка");
                e.printStackTrace();
            }
        }
    }
    void Buttom21()
    {
        if (connectArray.size() > 0)
        {
            int i = 0;
            i = Integer.parseInt(TF2.getText());
            connectArray.get(0).objectArrayServer(i);
        }
    }

    void Buttom23()
    {
        if (connectArray.size() > 0) connectArray.get(0).clearArrayServer();
    }

    void Buttom_Info()
    {
        Method = 4;
    }

    private Graph_obj belongs(double X, double Y)
    {
        return belongs((int) Math.round(X), (int) Math.round(Y));
    }

    private Graph_obj belongs(int X, int Y)
    {
        for (Graph_obj obj: objArray)
        {
            if( obj.belongs(new Point(X,Y)) )
            {
                return obj;
            }
        }
        return null;
    }

    static String trimZeros(String str) {
        int pos = str.indexOf(0);
        return pos == -1 ? str : str.substring(0, pos);
    }

    int connection_size = 0;

    Connect newConnect(Socket soc)
    {
        return new Connect(soc)
        {
            @Override
            public void clear()
            {
                synchronized (objArray) {
                    objArray.clear();}
            }
            @Override
            public void addObject(Graph_obj obj)
            {
                synchronized (objArray) {
                    objArray.add(obj); }
            }
            @Override
            public int getArraySize()
            {
                return objArray.size();
            }
            @Override
            public Graph_obj getObject(int i)
            {
                return objArray.get(i);
            }
            @Override
            public void setArraySize(int i)
            {
                connection_size = i;
            }
        };
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void init () throws Exception
    {
        super.init ();
        Parameters parameters = getParameters ();

        Map<String, String> namedParameters = parameters.getNamed ();
        List<String> rawArguments = parameters.getRaw ();
        List<String> unnamedParameters = parameters.getUnnamed ();

        if(rawArguments.size() > 0 ) if(rawArguments.get(0).equals("-server")) { Mode = 1;     System.out.println ("Server active");}
        if(rawArguments.size() > 0 ) if(rawArguments.get(0).equals("-client")) { Mode = 2;     System.out.println ("Client active");}
    }
}
