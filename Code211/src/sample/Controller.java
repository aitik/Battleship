package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class Controller {
    boolean hit =false;
    boolean gameover=false;
    int rx, ry, rn, important;
    int count=0;
    int level;
    int[][] ships1 =new int[10][10];
    int[][] ships2 =new int[10][10];
    int[][] aiarr = new int[10][10];
    Button[][] btns1 = new Button[10][10];
    Button[][] btns2 = new Button[10][10];
    @FXML
    private GridPane field1;
    @FXML
    private GridPane field2;
    @FXML
    private AnchorPane game;
    @FXML
    private AnchorPane intro;
    @FXML
    private Label battlelog1;
    @FXML
    private Label battlelog2;
    @FXML
    private void easy(){
        level=1;
        intro.setVisible(false);
        game.setVisible(true);
    }
    @FXML
    private void hard(){
        level=2;
        intro.setVisible(false);
        game.setVisible(true);
    }
    @FXML
    private void impossible(){
        level=3;
        intro.setVisible(false);
        game.setVisible(true);
    }
    @FXML
    private void start(){
        for(int i=0;i<btns1.length; i++){
            for(int j=0; j<btns1.length; j++){
                btns1[i][j]= new Button();
                btns1[i][j].setPrefSize(29.9,29.9);
                btns1[i][j].setDisable(true);
                field1.add(btns1[i][j],i,j);
            }
        }
        for(int i=0;i<btns2.length; i++){
            for(int j=0; j<btns2.length; j++){
                btns2[i][j]= new Button();
                int xi = i;
                int yj=j;
                btns2[i][j].setOnAction(event -> shot(xi,yj));
                btns2[i][j].setPrefSize(29.9,29.9);
                field2.add(btns2[i][j],i,j);
            }
        }
        for(int i=0;i<ships1.length; i++){
            for(int j=0; j<ships1.length; j++){
                ships1[i][j]=0;
            }
        }
        for(int i=0;i<ships2.length; i++){
            for(int j=0; j<ships2.length; j++){
                ships2[i][j]=0;
            }
        }
        for(int i=0;i<aiarr.length; i++){
            for(int j=0; j<aiarr.length; j++){
                aiarr[i][j]=0;
            }
        }
        placeships1();
        placeships2();
    }
    @FXML
    private void newgame(){
        gameover=false;
        for(int i=0;i<ships1.length; i++){
            for(int j=0; j<ships1.length; j++){
                ships1[i][j]=0;
            }
        }
        for(int i=0;i<ships2.length; i++){
            for(int j=0; j<ships2.length; j++){
                ships2[i][j]=0;
            }
        }
        for(int i=0;i<aiarr.length; i++){
            for(int j=0; j<aiarr.length; j++){
                aiarr[i][j]=0;
            }
        }
        for(int i=0;i<aiarr.length; i++){
            for(int j=0; j<aiarr.length; j++){
                btns1[i][j].setStyle("-fx-background-color:ffffff; ");
                btns2[i][j].setStyle("-fx-background-color:ffffff; ");
                btns2[i][j].setDisable(false);
                btns2[i][j].setDisable(true);
                btns2[i][j].setDisable(false);
            }
        }
        placeships1();
        count=0;
        placeships2();
    }
    public void shot(int x, int y){
        if(ships2[x][y]!=0){
            btns2[x][y].setStyle("-fx-background-color:ff0000; ");
            btns2[x][y].setDisable(true);
            ships2[x][y]=0;
            battlelog1.setText("You hit "+x+" "+ry);
        }
        else{
            btns2[x][y].setStyle("-fx-background-color:blue; ");
            btns2[x][y].setDisable(true);
            battlelog1.setText("You missed "+x+" "+ry);
        }
        checkwin1();
        if(gameover){
            return;
        }
        if(level==2){
            aishotmedium();
        }
        if(level==1){
            aishoteasy();
        }
        if(level==3){
            aishothard();
        }

    }
    public void placeships1(){
        placing(1,0,0,0);
        placing(1,0,9,0);
        placing(1,9,9,0);
        placing(1,9,0,0);
        placing(2,2,8,2);
        placing(2,2,1,1);
        placing(2,7,8,3);
        placing(3,1,3,3);
        placing(3,8,5,1);
        placing(4,3,4,2);
    }
    public void placeships2(){
        rx=(int)(Math.random()*6)+2;
        if(rx>3){
            ry=(int)(Math.random()*2)+2;
            placing2(4,rx,ry,3);
            for(int i=0;i<4;i++){
                changeaiarr(rx,ry+i);
            }
        }
        else{
            ry=(int)(Math.random()*6)+2;
            placing2(4,rx,ry,2);
            for(int i=0;i<4;i++){
                changeaiarr(rx+i,ry);
            }
        }
        add3();
        add2();
        add1();
        for(int i=0;i<aiarr.length;i++){
            for(int j=0;j<aiarr.length;j++){
                aiarr[i][j]=0;
            }
        }
    }
    public void placing(int size, int x, int y, int direction){
        if(size==1){
            btns1[x][y].setStyle("-fx-background-color:ff5733; ");
            ships1[x][y]=1;
        }
        else{
            if(direction==1){
             for(int i=0;i<size;i++){
                 btns1[x][y-i].setStyle("-fx-background-color:ff5733; ");
                 ships1[x][y-i]=size;
             }
            }
            else if(direction==2){
                for(int i=0;i<size;i++){
                    btns1[x+i][y].setStyle("-fx-background-color:ff5733; ");
                    ships1[x+i][y]=size;
                }
            }
            else if(direction==3){
                for(int i=0;i<size;i++){
                    btns1[x][y+i].setStyle("-fx-background-color:ff5733; ");
                    ships1[x][y+i]=size;
                }
            }
            else if(direction==4){
                for(int i=0;i<size;i++){
                    btns1[x-i][y].setStyle("-fx-background-color:ff5733; ");
                    ships1[x-i][y]=size;
                }
            }
        }
    }
    public void placing2(int size, int x, int y, int direction){
        if(size==1){
            ships2[x][y]=1;
            aiarr[x][y]=1;
            changeaiarr(x,y);
            chg(x,y);
        }
        else{
            if(direction==1){
                for(int i=0;i<size;i++){
                    ships2[x][y-i]=size;
                    aiarr[x][y-i]=1;
                    changeaiarr(x,y-i);
                    chg(x,y-i);
                }
            }
            else if(direction==2){
                for(int i=0;i<size;i++){
                    ships2[x+i][y]=size;
                    aiarr[x+i][y]=1;
                    changeaiarr(x+i,y);
                    chg(x+i,y);
                }
            }
            else if(direction==3){
                for(int i=0;i<size;i++){
                    ships2[x][y+i]=size;
                    aiarr[x][y+i]=1;
                    changeaiarr(x,y+i);
                    chg(x,y+i);
                }
            }
            else if(direction==4){
                for(int i=0;i<size;i++){
                    ships2[x-i][y]=size;
                    aiarr[x-i][y]=1;
                    changeaiarr(x-i,y);
                    chg(x-i,y);
                }
            }

        }
    }
    public void checkwin1(){
        for(int i=0;i<ships2.length;i++){
            for(int j=0;j<ships2.length;j++){
                if(ships2[i][j]!=0){
                    return;
                }
            }
        }
        for(int i=0;i<ships2.length;i++){
            for(int j=0;j<ships2.length;j++){
                btns2[i][j].setDisable(true);
            }
        }
        battlelog2.setText("You won");
        gameover=true;
    }
    public void checkwin2(){
        for(int i=0;i<ships1.length;i++){
            for(int j=0;j<ships1.length;j++){
                if(ships1[i][j]!=0){
                    return;
                }
            }
        }
        for(int i=0;i<ships1.length;i++){
            for(int j=0;j<ships1.length;j++){
                btns2[i][j].setDisable(true);
            }
        }
        battlelog1.setText("AI won");
        for(int i=0; i<ships1.length; i++){
            for(int j=0; j<ships1.length;j++){
                if(ships2[i][j]!=0){
                    btns2[i][j].setStyle("-fx-background-color:yellow; ");
                }
            }
        }
    }
    public void aishoteasy(){
        if(hit){
        }
        else{
            rx=(int)(Math.random()*10);
            ry=(int)(Math.random()*10);
            while(aiarr[rx][ry]==2 || aiarr[rx][ry]==1){
                rx=(int)(Math.random()*10);
                ry=(int)(Math.random()*10);
            }
            if(ships1[rx][ry]!=0){
                btns1[rx][ry].setStyle("-fx-background-color:ff0000; ");
                btns1[rx][ry].setDisable(true);
                ships1[rx][ry]=0;
                aiarr[rx][ry]=1;
                battlelog2.setText("AI hit "+rx+" "+ry);
            }
            else{
                btns1[rx][ry].setStyle("-fx-background-color:blue; ");
                btns1[rx][ry].setDisable(true);
                aiarr[rx][ry]=2;
                battlelog2.setText("AI missed "+rx+" "+ry);
            }
            checkwin2();
        }
    }
    public void aishotmedium(){
        if(hit){
        }
        else{
            rx=(int)(Math.random()*10);
            ry=(int)(Math.random()*10);
            while(!(aiarr[rx][ry]==0)){
                rx=(int)(Math.random()*10);
                ry=(int)(Math.random()*10);
            }
            if(ships1[rx][ry]!=0){
                btns1[rx][ry].setStyle("-fx-background-color:ff0000; ");
                btns1[rx][ry].setDisable(true);
                ships1[rx][ry]=0;
                aiarr[rx][ry]=1;
                changeaiarr(rx,ry);
                battlelog2.setText("AI hit "+rx+" "+ry);
            }
            else{
                btns1[rx][ry].setStyle("-fx-background-color:blue; ");
                btns1[rx][ry].setDisable(true);
                aiarr[rx][ry]=2;
                battlelog2.setText("AI missed "+rx+" "+ry);
            }
            checkwin2();
        }
    }
    public void aishothard(){
        for(int i=0;i<ships1.length;i++){
            for(int j=0;j<ships1.length;j++){
                if(ships1[i][j]!=0){
                    rx=i;
                    ry=j;
                    break;
                }
            }
        }
        if(ships1[rx][ry]!=0){
            btns1[rx][ry].setStyle("-fx-background-color:ff0000; ");
            btns1[rx][ry].setDisable(true);
            ships1[rx][ry]=0;
            aiarr[rx][ry]=1;
            changeaiarr(rx,ry);
            battlelog2.setText("AI hit "+rx+" "+ry);
        }
        else{
            btns1[rx][ry].setStyle("-fx-background-color:blue; ");
            btns1[rx][ry].setDisable(true);
            aiarr[rx][ry]=2;
            battlelog2.setText("AI missed "+rx+" "+ry);
        }
        checkwin2();
    }public void chg(int x, int y){
        if(x>0 && x<9){
            if(y>0 && y<9){
                aiarr[x+1][y]=2;
                aiarr[x][y-1]=2;
                aiarr[x-1][y]=2;
                aiarr[x][y+1]=2;
            }
            else if(y==0){
                aiarr[x+1][y]=2;
                aiarr[x-1][y]=2;
                aiarr[x][y+1]=2;
            }
            else if(y==9){
                aiarr[x+1][y]=2;
                aiarr[x][y-1]=2;
                aiarr[x-1][y]=2;
            }
        }
        else if(x==0){
            if(y>0 && y<9){
                aiarr[x+1][y]=2;
                aiarr[x][y-1]=2;
                aiarr[x][y+1]=2;
            }
            else if(y==0){
                aiarr[x+1][y]=2;
                aiarr[x][y+1]=2;
            }
            else if(y==9){
                aiarr[x+1][y]=2;
                aiarr[x][y-1]=2;
            }
        }
        else if(x==9){
            if(y>0 && y<9){
                aiarr[x][y-1]=2;
                aiarr[x-1][y]=2;
                aiarr[x][y+1]=2;
            }
            else if(y==0){
                aiarr[x-1][y]=2;
                aiarr[x][y+1]=2;
            }
            else if(y==9){
                aiarr[x][y-1]=2;
                aiarr[x-1][y]=2;
            }
        }
    }
    public void changeaiarr(int x, int y){
        aiarr[x][y]=2;
        if(x>0 && x<9 && y>0 && y<9){
            aiarr[x+1][y+1]=2;
            aiarr[x+1][y-1]=2;
            aiarr[x-1][y+1]=2;
            aiarr[x-1][y-1]=2;
        }
        else if(x==0){
            if(y>0 && y<9){
                aiarr[x+1][y-1]=2;
                aiarr[x+1][y+1]=2;
            }
            else if(y==0){
                aiarr[x+1][y+1]=2;
            }
            else if(y==9){
                aiarr[x+1][y-1]=2;
            }
        }
        else if(x==9){
            if(y>0 && y<9){
                aiarr[x-1][y-1]=2;
                aiarr[x-1][y+1]=2;
            }
            else if(y==0){
                aiarr[x-1][y+1]=2;
            }
            else if(y==9){
                aiarr[x-1][y-1]=2;
            }
        }
        else if(y==0){
            aiarr[x-1][y+1]=2;
            aiarr[x+1][y+1]=2;
        }
        else if(y==9){
            aiarr[x-1][y-1]=2;
            aiarr[x+1][y-1]=2;
        }
    }
    public void add3(){
        rn=(int)(Math.random()*2)+1;
        if(rn==1){
            important=1;
            rx=1;
            ry=0;
            placing2(3,rx,ry,2);
            for(int i=0; i<3;i++){
                changeaiarr(rx+i,ry);
                chg(rx+i,ry);
            }
            rx=9;
            ry=0;
            placing2(3,rx,ry,3);
            for(int i=0; i<3;i++){
                changeaiarr(rx,ry+i);
                chg(rx,ry+i);
            }
        }
        else{
            important=2;
            rx=0;
            ry=(int)(Math.random()*3);
            placing2(3,rx,ry,3);
            for(int i=0; i<3;i++){
                changeaiarr(rx,ry+i);
                chg(rx,ry+i);
            }
            rx=(int)(Math.random()*2);
            ry=9;
            placing2(3,rx,ry,2);
            for(int i=0; i<3;i++){
                changeaiarr(rx+i,ry);
                chg(rx+i,ry);
            }
        }
    }
    public void add2(){
        if(important==1){
            rn=(int)(Math.random()*2)+3;
        }
        else{
            rn=(int)(Math.random()*2)+1;
        }
        for(int j=0;j<3;j++){
            if(rn==1){
                rx=(int)(Math.random()*9);
                ry=(int)(Math.random()*2);
                while(aiarr[rx][ry]!=0 &&aiarr[rx+1][ry]!=0){
                    rx=(int)(Math.random()*8);
                    ry=(int)(Math.random()*2);
                    count++;
                    if(count>100){
                        count=0;
                        bug();
                        return;
                    }
                }
                changeaiarr(rx,ry);
                changeaiarr(rx+1,ry);
                placing2(2,rx,ry,2);
                rn=(int)(Math.random()*4)+1;
                while(rn==1){
                    rn=(int)(Math.random()*4)+1;
                }
            }
            else if(rn==2){
                rx=(int)(Math.random()*2)+8;
                ry=(int)(Math.random()*9);
                while(aiarr[rx][ry]!=0 &&aiarr[rx][ry+1]!=0){
                    rx=(int)(Math.random()*2)+8;
                    ry=(int)(Math.random()*8);
                    count++;
                    if(count>100){
                        count=0;
                        bug();
                        return;
                    }
                }
                changeaiarr(rx,ry);
                changeaiarr(rx,ry+1);
                placing2(2,rx,ry,3);
                rn=(int)(Math.random()*4)+1;
                while(rn==2){
                    rn=(int)(Math.random()*4)+1;
                }
            }
            else if(rn==3){
                rx=(int)(Math.random()*9);
                ry=(int)(Math.random()*2)+8;
                while(aiarr[rx][ry]!=0 &&aiarr[rx+1][ry]!=0){
                    rx=(int)(Math.random()*8);
                    ry=(int)(Math.random()*2)+8;
                    count++;
                    if(count>100){
                        count=0;
                        bug();
                        return;
                    }
                }
                changeaiarr(rx,ry);
                changeaiarr(rx+1,ry);
                placing2(2,rx,ry,2);
                rn=(int)(Math.random()*4)+1;
                while(rn==3){
                    rn=(int)(Math.random()*4)+1;
                }
            }
            else if(rn==4){
                rx=(int)(Math.random()*2);
                ry=(int)(Math.random()*9);
                while(aiarr[rx][ry]!=0 &&aiarr[rx][ry+1]!=0){
                    rx=(int)(Math.random()*2);
                    ry=(int)(Math.random()*8);
                    count++;
                    if(count>100){
                        count=0;
                        bug();
                        return;
                    }
                }
                changeaiarr(rx,ry);
                changeaiarr(rx,ry+1);
                placing2(2,rx,ry,3);
                rn=(int)(Math.random()*4)+1;
                while(rn==4){
                    rn=(int)(Math.random()*4)+1;
                }
            }
        }
    }
    public void add1(){
        if(important==1){
            rn=(int)(Math.random()*2)+1;
        }
        else{
            rn=(int)(Math.random()*2)+3;
        }
        for(int j=0;j<4;j++){
            if(rn==1){
                rx=(int)(Math.random()*10);
                ry=(int)(Math.random()*2);
                while(aiarr[rx][ry]!=0){
                    rx=(int)(Math.random()*8);
                    ry=(int)(Math.random()*2);
                    count++;
                    if(count>100){
                        count=0;
                        bug();
                        return;
                    }
                }
                changeaiarr(rx,ry);
                placing2(1,rx,ry,0);
                rn=(int)(Math.random()*4)+1;
                while(rn==1){
                    rn=(int)(Math.random()*4)+1;
                }
            }
            else if(rn==2){
                rx=(int)(Math.random()*2)+8;
                ry=(int)(Math.random()*10);
                while(aiarr[rx][ry]!=0){
                    rx=(int)(Math.random()*2)+8;
                    ry=(int)(Math.random()*8);
                    count++;
                    if(count>100){
                        count=0;
                        bug();
                        return;
                    }
                }
                changeaiarr(rx,ry);
                placing2(1,rx,ry,3);
                rn=(int)(Math.random()*4)+1;
                while(rn==2){
                    rn=(int)(Math.random()*4)+1;
                }
            }
            else if(rn==3){
                rx=(int)(Math.random()*10);
                ry=(int)(Math.random()*2)+8;
                while(aiarr[rx][ry]!=0){
                    rx=(int)(Math.random()*8);
                    ry=(int)(Math.random()*2)+8;
                    count++;
                    if(count>100){
                        count=0;
                        bug();
                        return;
                    }
                }
                changeaiarr(rx,ry);
                placing2(1,rx,ry,2);
                rn=(int)(Math.random()*4)+1;
                while(rn==3){
                    rn=(int)(Math.random()*4)+1;
                }
            }
            else if(rn==4){
                rx=(int)(Math.random()*2);
                ry=(int)(Math.random()*10);
                while(aiarr[rx][ry]!=0){
                    rx=(int)(Math.random()*2);
                    ry=(int)(Math.random()*8);
                    count++;
                    if(count>100){
                        count=0;
                        bug();
                        return;
                    }
                }
                changeaiarr(rx,ry);
                placing2(1,rx,ry,3);
                rn=(int)(Math.random()*4)+1;
                while(rn==4){
                    rn=(int)(Math.random()*4)+1;
                }
            }
        }
        for(int i=0;i<aiarr.length;i++){
            for(int j=0;j<aiarr.length;j++){
                aiarr[i][j]=0;
            }
        }
    }
    public void bug(){
        gameover=false;
        for(int i=0;i<ships1.length; i++){
            for(int j=0; j<ships1.length; j++){
                ships1[i][j]=0;
            }
        }
        for(int i=0;i<ships2.length; i++){
            for(int j=0; j<ships2.length; j++){
                ships2[i][j]=0;
            }
        }
        for(int i=0;i<aiarr.length; i++){
            for(int j=0; j<aiarr.length; j++){
                aiarr[i][j]=0;
            }
        }
        for(int i=0;i<aiarr.length; i++){
            for(int j=0; j<aiarr.length; j++){
//                btns1[i][j].setStyle("-fx-background-color:ffffff; ");
//                btns2[i][j].setStyle("-fx-background-color:ffffff; ");
                btns2[i][j].setDisable(true);
                btns2[i][j].setDisable(false);
            }
        }
        placeships1();
        rn=(int)(Math.random()*4)+1;
        if(rn==1){
            placing2(1,0,0,0);
            placing2(1,9,0,0);
            placing2(1,9,9,0);
            placing2(1,0,9,0);
            placing2(2,2,8,2);
            placing2(2,2,1,1);
            placing2(2,7,8,3);
            placing2(3,1,3,3);
            placing2(3,8,5,1);
            placing2(4,3,4,2);
        }
        else if(rn==2){
            placing2(1,2,0,0);
            placing2(1,7,9,0);
            placing2(1,9,9,0);
            placing2(1,0,5,0);
            placing2(2,0,7,3);
            placing2(2,7,3,3);
            placing2(2,1,3,4);
            placing2(3,3,2,2);
            placing2(3,5,9,1);
            placing2(4,3,5,3);
        }
        else if(rn==3){
            placing2(1,6,7,0);
            placing2(1,8,8,0);
            placing2(1,9,6,0);
            placing2(1,0,7,0);
            placing2(2,0,9,2);
            placing2(2,4,9,2);
            placing2(2,1,4,3);
            placing2(3,9,0,3);
            placing2(3,6,2,3);
            placing2(4,1,0,2);
        }
        else if(rn==4){
            placing2(1,1,4,0);
            placing2(1,7,8,0);
            placing2(1,9,7,0);
            placing2(1,0,2,0);
            placing2(2,0,9,2);
            placing2(2,8,0,2);
            placing2(2,5,1,4);
            placing2(3,3,3,3);
            placing2(3,5,3,3);
            placing2(4,1,9,2);
        }
    }
}