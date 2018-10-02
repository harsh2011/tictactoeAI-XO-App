package com.harsh.xo;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.harsh.xo.views.DrawModel;
import com.harsh.xo.views.DrawView;

import org.tensorflow.Operation;
import org.tensorflow.contrib.android.TensorFlowInferenceInterface;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private DrawView d00,d01,d02,
            d10,d11,d12,
            d20,d21,d22;

    private TextView t_d00,t_d01,t_d02,
            t_d10,t_d11,t_d12,
            t_d20,t_d21,t_d22;

    private Button btn_clear,btn_info;


    private static final String MODEL_FILE_CNN = "file:///android_asset/opt_xo_differ_v2.pb";

    private static final String MODEL_GAN_O = "file:///android_asset/opt_gan_O.pb";
    private static final String MODEL_GAN_X = "file:///android_asset/opt_gan_X.pb";


    private static final String INPUT_NODE = "input";
    private static final String OUTPUT_NODE = "output";


    private TensorFlowInferenceInterface inferenceInterface;

    private DrawModel drawModel00,
            drawModel01,
            drawModel02,
            drawModel10,
            drawModel11,
            drawModel12,
            drawModel20,
            drawModel21,
            drawModel22;

    private ImageView iv00,iv01,iv02,
            iv10,iv11,iv12,
            iv20,iv21,iv22;

    private static final int PIXEL_WIDTH = 28;


    private Random rand;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inferenceInterface = new TensorFlowInferenceInterface(getAssets(),MODEL_FILE_CNN);

        rand = new Random();

        loadGANs();

        //get the model object
        drawModel00 = new DrawModel(PIXEL_WIDTH, PIXEL_WIDTH);
        drawModel01 = new DrawModel(PIXEL_WIDTH, PIXEL_WIDTH);
        drawModel02 = new DrawModel(PIXEL_WIDTH, PIXEL_WIDTH);
        drawModel10 = new DrawModel(PIXEL_WIDTH, PIXEL_WIDTH);
        drawModel11 = new DrawModel(PIXEL_WIDTH, PIXEL_WIDTH);
        drawModel12 = new DrawModel(PIXEL_WIDTH, PIXEL_WIDTH);
        drawModel20 = new DrawModel(PIXEL_WIDTH, PIXEL_WIDTH);
        drawModel21 = new DrawModel(PIXEL_WIDTH, PIXEL_WIDTH);
        drawModel22 = new DrawModel(PIXEL_WIDTH, PIXEL_WIDTH);

        d00 = (DrawView) findViewById(R.id.draw00);
        d01 = (DrawView) findViewById(R.id.draw01);
        d02 = (DrawView) findViewById(R.id.draw02);
        d10 = (DrawView) findViewById(R.id.draw10);
        d11 = (DrawView) findViewById(R.id.draw11);
        d12 = (DrawView) findViewById(R.id.draw12);
        d20 = (DrawView) findViewById(R.id.draw20);
        d21 = (DrawView) findViewById(R.id.draw21);
        d22 = (DrawView) findViewById(R.id.draw22);

        iv00 = (ImageView) findViewById(R.id.image00);
        iv01 = (ImageView) findViewById(R.id.image01);
        iv02 = (ImageView) findViewById(R.id.image02);
        iv10 = (ImageView) findViewById(R.id.image10);
        iv11 = (ImageView) findViewById(R.id.image11);
        iv12 = (ImageView) findViewById(R.id.image12);
        iv20 = (ImageView) findViewById(R.id.image20);
        iv21 = (ImageView) findViewById(R.id.image21);
        iv22 = (ImageView) findViewById(R.id.image22);

        t_d00= (TextView) findViewById(R.id.tv_draw00);
        t_d01= (TextView) findViewById(R.id.tv_draw01);
        t_d02= (TextView) findViewById(R.id.tv_draw02);
        t_d10= (TextView) findViewById(R.id.tv_draw10);
        t_d11= (TextView) findViewById(R.id.tv_draw11);
        t_d12= (TextView) findViewById(R.id.tv_draw12);
        t_d20= (TextView) findViewById(R.id.tv_draw20);
        t_d21= (TextView) findViewById(R.id.tv_draw21);
        t_d22= (TextView) findViewById(R.id.tv_draw22);

        btn_clear = (Button) findViewById(R.id.btn_clear);
        btn_info = (Button) findViewById(R.id.info);

        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog info = new Dialog(MainActivity.this);
                info.setContentView(R.layout.dailog_info);

                info.show();
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClear(drawModel00,d00);
                onClear(drawModel01,d01);
                onClear(drawModel02,d02);
                onClear(drawModel10,d10);
                onClear(drawModel11,d11);
                onClear(drawModel12,d12);
                onClear(drawModel20,d20);
                onClear(drawModel21,d21);
                onClear(drawModel22,d22);

                t_d00.setText("-");
                t_d01.setText("-");
                t_d02.setText("-");
                t_d10.setText("-");
                t_d11.setText("-");
                t_d12.setText("-");
                t_d20.setText("-");
                t_d21.setText("-");
                t_d22.setText("-");
            }
        });

        d00.setModel(drawModel00);
        d00.reset();
        d00.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return onTouuuuuu(motionEvent ,drawModel00,d00,t_d00);
            }
        });

        d01.setModel(drawModel01);
        d01.reset();
        d01.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return onTouuuuuu(motionEvent ,drawModel01,d01,t_d01);
            }
        });

        d02.setModel(drawModel02);
        d02.reset();
        d02.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return onTouuuuuu(motionEvent ,drawModel02,d02,t_d02);
            }
        });


        d10.setModel(drawModel10);
        d10.reset();
        d10.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return onTouuuuuu(motionEvent ,drawModel10,d10,t_d10);
            }
        });

        d11.setModel(drawModel11);
        d11.reset();
        d11.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return onTouuuuuu(motionEvent ,drawModel11,d11,t_d11);
            }
        });

        d12.setModel(drawModel12);
        d12.reset();
        d12.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return onTouuuuuu(motionEvent ,drawModel12,d12,t_d12);
            }
        });

        d20.setModel(drawModel20);
        d20.reset();
        d20.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return onTouuuuuu(motionEvent ,drawModel20,d20,t_d20);
            }
        });
        d21.setModel(drawModel21);
        d21.reset();
        d21.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return onTouuuuuu(motionEvent ,drawModel21,d21,t_d21);
            }
        });
        d22.setModel(drawModel22);
        d22.reset();
        d22.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return onTouuuuuu(motionEvent ,drawModel22,d22,t_d22);
            }
        });




        d00.setVisibility(View.VISIBLE);
        d01.setVisibility(View.VISIBLE);
        d02.setVisibility(View.VISIBLE);
        d10.setVisibility(View.VISIBLE);
        d11.setVisibility(View.VISIBLE);
        d12.setVisibility(View.VISIBLE);
        d20.setVisibility(View.VISIBLE);
        d21.setVisibility(View.VISIBLE);
        d22.setVisibility(View.VISIBLE);

        iv00.setVisibility(View.GONE);
        iv01.setVisibility(View.GONE);
        iv02.setVisibility(View.GONE);
        iv10.setVisibility(View.GONE);
        iv11.setVisibility(View.GONE);
        iv12.setVisibility(View.GONE);
        iv20.setVisibility(View.GONE);
        iv21.setVisibility(View.GONE);
        iv22.setVisibility(View.GONE);



    }




    public ArrayList<Integer> checkempty(){

        ArrayList<Integer> emptylist =  new ArrayList<>();
        emptylist.clear();

        if(t_d00.getText().toString().equals("-")){
            emptylist.add(0);
        }if(t_d01.getText().toString().equals("-")){
            emptylist.add(1);
        }if(t_d02.getText().toString().equals("-")) {
            emptylist.add(2);
        }if(t_d10.getText().toString().equals("-")){
            emptylist.add(3);
        }if(t_d11.getText().toString().equals("-")){
            emptylist.add(4);
        }if(t_d12.getText().toString().equals("-")){
            emptylist.add(5);
        }if(t_d20.getText().toString().equals("-")){
            emptylist.add(6);
        }if(t_d21.getText().toString().equals("-")){
            emptylist.add(7);
        }if(t_d22.getText().toString().equals("-")){
            emptylist.add(8);
        }

        return emptylist;
    }


    DrawView last_location;
    char last_turn;

    public void detect(DrawView d,TextView t){
        float out[] = getDetectofDV(d);

        if(out[1]>out[0]){ //true : X and false : O
            float pixels[] = d.getPixelData();
            float mean = mean(pixels);

            if(mean>15) {

                if(last_turn=='o') {
                    last_turn = 'x';
                    last_location = d;
                    t.setText("X");

                    // Generate the next location for Generator Model O
                    if(comp_draw=='o') {
                        newGenerate('o');
                    }
                    if(toCheck()){
                        Toast.makeText(MainActivity.this,"X is the winner",Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    if(last_location!=d) {
                        onClear(d.getmModel(), d);
                    }
                }
            }

        }
        else{
            float pixels[] = d.getPixelData();
            float mean = mean(pixels);

            if(mean>15) {
                if(last_turn =='x'){
                    last_turn = 'o';
                    last_location = d;
                    t.setText("O");

                    // Generate the next location for Generator Model X
                    if(comp_draw=='x') {
                        newGenerate('x');
                    }
                    if(toCheck()){
                        Toast.makeText(MainActivity.this,"O is the winner",Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    if(last_location!=d) {
                        onClear(d.getmModel(), d);
                    }
                }
            }

        }
    }


    public void newGenerate(char var){
        ArrayList<Integer> empty = checkempty();
        int location;
        int index;

        if(empty.size()!=0){
            if(empty.size()==1){
                location = empty.get(0);
            }
            else{
                index = new Random().nextInt(empty.size());
                location = empty.get(index);
            }
            DrawView drawView = drawAtLocation(location);
            TextView textView = textAtLocation(location);
            ImageView imageView = imageviewAtLocation(location);


            if(drawView!=null && textView!=null){
                last_location = drawView;
                genXorO(drawView,imageView,textView,var);
            }
            else{
                System.out.println("Error in Appp");
            }
        }
    }

    public void reset(){
        onClear(drawModel00,d00);
        onClear(drawModel01,d01);
        onClear(drawModel02,d02);
        onClear(drawModel10,d10);
        onClear(drawModel11,d11);
        onClear(drawModel12,d12);
        onClear(drawModel20,d20);
        onClear(drawModel21,d21);
        onClear(drawModel22,d22);

        t_d00.setText("-");
        t_d01.setText("-");
        t_d02.setText("-");
        t_d10.setText("-");
        t_d11.setText("-");
        t_d12.setText("-");
        t_d20.setText("-");
        t_d21.setText("-");
        t_d22.setText("-");

        d00.setVisibility(View.VISIBLE);
        d01.setVisibility(View.VISIBLE);
        d02.setVisibility(View.VISIBLE);
        d10.setVisibility(View.VISIBLE);
        d11.setVisibility(View.VISIBLE);
        d12.setVisibility(View.VISIBLE);
        d20.setVisibility(View.VISIBLE);
        d21.setVisibility(View.VISIBLE);
        d22.setVisibility(View.VISIBLE);

        iv00.setVisibility(View.GONE);
        iv01.setVisibility(View.GONE);
        iv02.setVisibility(View.GONE);
        iv10.setVisibility(View.GONE);
        iv11.setVisibility(View.GONE);
        iv12.setVisibility(View.GONE);
        iv20.setVisibility(View.GONE);
        iv21.setVisibility(View.GONE);
        iv22.setVisibility(View.GONE);

    }

    public boolean toCheck(){
        String s00 = t_d00.getText().toString();
        String s01 = t_d01.getText().toString();
        String s02 = t_d02.getText().toString();
        String s10 = t_d10.getText().toString();
        String s11 = t_d11.getText().toString();
        String s12 = t_d12.getText().toString();
        String s20 = t_d20.getText().toString();
        String s21 = t_d21.getText().toString();
        String s22 = t_d22.getText().toString();

        String h = booleanhorizontal(s00,s01,s02,s10,s11,s12,s20,s21,s22);
        if(h != null){
            if(h.equals("h0")){
                reset();
                return true;
            }
            if(h.equals("h1")){
                reset();
                return true;
            }
            if(h.equals("h2")){
                reset();
                return true;
            }
        }

        String v = booleanvertical(s00,s01,s02,s10,s11,s12,s20,s21,s22);

        if(v != null){
            if(h.equals("v0")){
                reset();
                return true;
            }
            if(h.equals("v1")){
                reset();
                return true;
            }
            if(h.equals("v2")){
                reset();
                return true;
            }
        }


        String c = booleancross(s00,s01,s02,s10,s11,s12,s20,s21,s22);

        if(c != null){
            if(c.equals("c0")){
                reset();
                return true;
            }
            if(c.equals("c1")){
                reset();
                return true;
            }
        }

        return false;
    }

    public String booleanvertical(String s00,String s01,String s02,
                                   String s10,String s11,String s12,
                                   String s20,String s21,String s22){
        if(check(s00,s01,s02)){
            return "v0";
        }
        if(check(s10,s11,s12)){
            return "v1";
        }
        if(check(s20,s21,s22)){
            return "v2";
        }
        return null;
    }

    public String booleanhorizontal(String s00,String s01,String s02,
                                   String s10,String s11,String s12,
                                   String s20,String s21,String s22){
        if(check(s00,s10,s20)){
            return "h0";
        }
        if(check(s01,s11,s21)){
            return "h1";
        }
        if(check(s02,s12,s22)){
            return "h2";
        }
        return null;
    }


    public String booleancross(String s00,String s01,String s02,
                                     String s10,String s11,String s12,
                                     String s20,String s21,String s22){
        if(check(s00,s11,s22)){
            return "c0";
        }
        if(check(s02,s11,s20)) {
            return "c1";
        }
        return null;
    }

    public boolean check(String a,String b,String c){
        boolean value = a.equals(b) && b.equals(c) && a.equals(c);
        if(a.equals("-") || b.equals("-") || c.equals("-")){
            value = false;
        }
        return value;
    }



    public float mean(float [] data){
        float sum = 0;
        for (int i=0;i<28*28;i++){
            sum = sum + data[i];
            System.out.println("data : "+ data[i]);
        }
        return sum/784;
    }

    public float[] getDetectofDV(DrawView d){
        float pixels[] = d.getPixelData();

        inferenceInterface.feed(INPUT_NODE, pixels,1,28,28,1);
        inferenceInterface.feed("keep_prob", new float[] { 1 });
        final Operation operation = inferenceInterface.graphOperation(OUTPUT_NODE);
        final int numClasses = (int) operation.output(0).shape().size(1);

        inferenceInterface.run(new String[]{OUTPUT_NODE},false);

        float [] outputarray = new float[numClasses];
        //get the output
        inferenceInterface.fetch(OUTPUT_NODE,outputarray);


        return outputarray;
    }

    @Override
    protected void onResume() {
        d00.onResume();
        d01.onResume();
        d02.onResume();
        d10.onResume();
        d11.onResume();
        d12.onResume();
        d20.onResume();
        d21.onResume();
        d22.onResume();

        reset();

        last_turn = 'o';

        int ran = new Random().nextInt(2);
        if(ran==0){
            comp_draw = 'x';
            user_draw ='o';
            ArrayList<Integer> empty = checkempty();
            int index = new Random().nextInt(empty.size());
            int location = (int) empty.get(index);
            DrawView drawView = drawAtLocation(location);
            TextView textView = textAtLocation(location);
            ImageView imageView = imageviewAtLocation(location);

            if(drawView!=null && textView!=null){
                last_location = drawView;
                genXorO(drawView,imageView,textView,'x');
            }
            else{
                System.out.println("Error in Appp");
            }
        }
        else{
            comp_draw = 'o';
            user_draw ='x';
            Toast.makeText(MainActivity.this,"Make your first move",Toast.LENGTH_LONG).show();
        }

        super.onResume();
    }

    char comp_draw;
    char user_draw;


    public DrawView drawAtLocation(int location){
        if(location ==0){
            return d00;
        }if(location ==1){
            return d01;
        }if(location ==2){
            return d02;
        }if(location ==3){
            return d10;
        }if(location ==4){
            return d11;
        }if(location ==5){
            return d12;
        }if(location ==6){
            return d20;
        }if(location ==7){
            return d21;
        }if(location ==8){
            return d22;
        }
        return null;
    }

    public TextView textAtLocation(int location){
        if(location ==0){
            return t_d00;
        }if(location ==1){
            return t_d01;
        }if(location ==2){
            return t_d02;
        }if(location ==3){
            return t_d10;
        }if(location ==4){
            return t_d11;
        }if(location ==5){
            return t_d12;
        }if(location ==6){
            return t_d20;
        }if(location ==7){
            return t_d21;
        }if(location ==8){
            return t_d22;
        }
        return null;
    }
    public ImageView imageviewAtLocation(int location){
        if(location ==0){
            return iv00;
        }if(location ==1){
            return iv01;
        }if(location ==2){
            return iv02;
        }if(location ==3){
            return iv10;
        }if(location ==4){
            return iv11;
        }if(location ==5){
            return iv12;
        }if(location ==6){
            return iv20;
        }if(location ==7){
            return iv21;
        }if(location ==8){
            return iv22;
        }
        return null;
    }

    private PointF mTmpPiont = new PointF();

    private float mLastX;
    private float mLastY;

    public void onClear(DrawModel dm,DrawView dv){
        dm.clear();
        dv.reset();
        dv.invalidate();
    }


    private boolean onTouuuuuu(MotionEvent motionEvent ,DrawModel drawModel, DrawView d,TextView t){
        int action = motionEvent.getAction() & MotionEvent.ACTION_MASK;
        if (action == MotionEvent.ACTION_DOWN) {
            processTouchDown(motionEvent,drawModel,d);
            return true;

        } else if (action == MotionEvent.ACTION_MOVE) {
            processTouchMove(motionEvent,drawModel,d);
            return true;
            //if finger is lifted, stop drawing
        } else if (action == MotionEvent.ACTION_UP) {
            processTouchUp(drawModel);
            detect(d,t);
            return true;
        }
        return false;
    }
    private void processTouchDown(MotionEvent event,DrawModel drawModel,DrawView d) {
        mLastX = event.getX();
        mLastY = event.getY();

        d.calcPos(mLastX, mLastY, mTmpPiont);

        float lastConvX = mTmpPiont.x;
        float lastConvY = mTmpPiont.y;

        drawModel.startLine(lastConvX, lastConvY);
    }
    private void processTouchMove(MotionEvent event,DrawModel drawModel,DrawView d) {
        float x = event.getX();
        float y = event.getY();

        d.calcPos(x, y, mTmpPiont);


        float newConvX = mTmpPiont.x;
        float newConvY = mTmpPiont.y;
        drawModel.addLineElem(newConvX, newConvY);

        mLastX = x;
        mLastY = y;

        d.invalidate();

    }
    private void processTouchUp(DrawModel drawModel) {
        drawModel.endLine();
    }


    // GAN code
    private TensorFlowInferenceInterface inferenceInterface_GAN_O;
    private TensorFlowInferenceInterface inferenceInterface_GAN_X;


    //For X
    //input_node_name = 'noise_input_x'
    //output_node_name = 'gen_x'
    //For O
    //input_node_name = 'noise_input_o'
    //output_node_name = 'gen_o

    private static final String INPUT_NODE_GAN_X = "noise_input_x";
    private static final String OUTPUT_NODE_GAN_X = "gen_x";

    private static final String INPUT_NODE_GAN_O = "noise_input_o";
    private static final String OUTPUT_NODE_GAN_O = "gen_o";

    private void loadGANs(){
        inferenceInterface_GAN_O = new TensorFlowInferenceInterface(getAssets(),MODEL_GAN_O);
        inferenceInterface_GAN_X = new TensorFlowInferenceInterface(getAssets(),MODEL_GAN_X);
    }

    public void genXorO(DrawView d,ImageView iv,TextView t, char label){
        int [] int_image;
        if(label == 'x'){
            int_image = getGAN_X();
            t.setText("X");
            last_turn = 'x';

        }
        else{
            int_image = getGAN_O();
            t.setText("O");
            last_turn = 'o';
        }

        int[] pixels = new int[28*28];
        for (int i=0; i<784; i++) {
            int temp = (-1)*(int_image[i]-255);
            pixels[i] = Color.argb(255,temp,temp,temp);
        }
        Bitmap btp = Bitmap.createBitmap(28, 28, Bitmap.Config.ARGB_8888);
        btp.setPixels(pixels, 0, 28, 0, 0,28, 28);
        d.setVisibility(View.GONE);
        iv.setVisibility(View.VISIBLE);
        iv.setImageBitmap(btp);
    }

    public int[] getGAN_O(){
        float [] noise_input = new float [100];
        for(int i =0 ; i<noise_input.length; i++){
            float value = rand.nextFloat();
            noise_input[i] = (value*2)-1;
        }
        inferenceInterface_GAN_O.feed(INPUT_NODE_GAN_O, noise_input,1,100);
        inferenceInterface_GAN_O.run(new String[]{OUTPUT_NODE_GAN_O},false);

        float [] image = new float[784];
        int [] int_image = new int[784];
        //get the output
        inferenceInterface_GAN_O.fetch(OUTPUT_NODE_GAN_O,image);

        for(int i =0 ; i<image.length; i++){
            int_image[i] = (int)(image[i]*255);
        }
        return int_image;
    }

    public int[] getGAN_X(){
        float [] noise_input = new float [100];
        for(int i =0 ; i<noise_input.length; i++){
            float value = rand.nextFloat();
            noise_input[i] = (value*2)-1;
        }
        inferenceInterface_GAN_X.feed(INPUT_NODE_GAN_X, noise_input,1,100);
        inferenceInterface_GAN_X.run(new String[]{OUTPUT_NODE_GAN_X},false);

        float [] image = new float[784];
        int [] int_image = new int[784];
        //get the output
        inferenceInterface_GAN_X.fetch(OUTPUT_NODE_GAN_X,image);

        for(int i =0 ; i<image.length; i++){
            int_image[i] = (int)(image[i]*255);
        }
        return int_image;
    }

}
