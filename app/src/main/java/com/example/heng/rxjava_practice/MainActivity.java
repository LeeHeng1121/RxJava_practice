package com.example.heng.rxjava_practice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {
    String tag = "MainActivity";

    Observer<String> observer = new Observer<String>() {

        @Override
        public void onNext(String s) {

            Log.d(tag, "Item: " + s);
        }

        @Override
        public void onCompleted() {
            Log.d(tag, "Completed!");
        }

        @Override
        public void onError(Throwable e) {
            Log.d(tag, "Error!");
        }
    };

    Subscriber<String> subscriber = new Subscriber<String>() {

        @Override
        public void onNext(String s) {

            Log.d(tag, "Item: " + s);
        }

        @Override
        public void onCompleted() {
            Log.d(tag, "Completed!");
        }

        @Override
        public void onError(Throwable e) {
            Log.d(tag, "Error!");
        }
    };

    Observable observable1 = Observable.create(new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {
            subscriber.onNext("Hello");
            subscriber.onNext("Hi");
            subscriber.onNext("Aloha");
            subscriber.onCompleted();
        }
    });

    Observable observable2 = Observable.just("Hello","Hi","Aloha");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        observable1.subscribe(observer);
//        observable2.subscribe(observer);


        Action1<String> onNextAction = new Action1<String>() {
            // onNext()
            @Override
            public void call(String s) {
                Log.d(tag, s);
            }
        };
        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            // onError()
            @Override
            public void call(Throwable throwable) {
                // Error handling
            }
        };
        Action0 onCompletedAction = new Action0() {
            // onCompleted()
            @Override
            public void call() {
                Log.d(tag, "completed");
            }
        };

// 自动创建 Subscriber ，并使用 onNextAction 来定义 onNext()
        Log.d(tag,"OnNext");
        observable1.subscribe(onNextAction);
// 自动创建 Subscriber ，并使用 onNextAction 和 onErrorAction 来定义 onNext() 和 onError()
        Log.d(tag,"OnNext + OnError");
        observable1.subscribe(onNextAction, onErrorAction);
// 自动创建 Subscriber ，并使用 onNextAction、 onErrorAction 和 onCompletedAction 来定义 onNext()、 onError() 和 onCompleted()
        Log.d(tag,"OnNext + OnError + OnCompleted");
        observable1.subscribe(onNextAction, onErrorAction, onCompletedAction);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



}
