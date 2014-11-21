package ixdfreelance.aeropress_tba;


import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class MyFragment2 extends Fragment {
    // Inflate the layout for this fragment

     MyTimer mTimer;
     long remainMilli = 0;
     boolean isRunning=false;

    public MyFragment2() {
        // Required empty public constructor


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment2, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        final TextView tv = (TextView) getView().findViewById(R.id.counter);
        final Button cntbtn = (Button) getView().findViewById(R.id.startTimerBtn);
        cntbtn.setText("Start");
        tv.setText("");

        cntbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(isRunning) {
                    mTimer.cancel();
                    mTimer=null;
                    isRunning=false;
                    cntbtn.setText("Resume");
                }else{

                if (remainMilli == 0) {
                    //start timer from initial time
                    mTimer = new MyTimer(10032,1000);
                } else {
                    //resume timer from where it is paused
                    mTimer = new MyTimer(remainMilli, 1000);
                }
               cntbtn.setText("Pause");
                mTimer.start();
                isRunning=true;
            }


            }
        });


//        new CountDownTimer(10000, 997) {
//
//            public void onTick(long millisUntilFinished) {
//
//                    tv.setText(((millisUntilFinished/1000)) + " sec");
//                    System.out.println("Time: "+ millisUntilFinished);
//            }
//
//            @Override
//            public void onFinish() {
//                // TODO Auto-generated method stub
//                //action for when the timer has finished
//
//                tv.setText("Begin pressing");
//            }
//        }.start();


}
    public void StartBtn(View v) {


    }

    final class MyTimer extends CountDownTimer {


     //constructor for timer class
        public MyTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);

        }


        // this method called when timer is finished
        @Override
        public void onFinish() {
            // reset all variables
            final TextView tv = (TextView) getView().findViewById(R.id.counter);
            final Button cntbtn = (Button) getView().findViewById(R.id.startTimerBtn);
            tv.setText("Begin pressing");
            cntbtn.setText("Start");
            isRunning=false;
            remainMilli=0;
        }

        //this method is called for every iteration of time interval
        @Override
        public void onTick(long millisUntilFinished) {

            remainMilli = millisUntilFinished;

            //calculate minutes and seconds from milliseconds
            String second = "" + (millisUntilFinished / 1000) % 60;
            // update textview with remaining time
            final TextView tv = (TextView) getView().findViewById(R.id.counter);

            tv.setText(second + " sec");
            Log.d("timer: ", second + " millis " + remainMilli);
        }

    }

}
