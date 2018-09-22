package com.example.heng.rxjava_practice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

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
        observable1.subscribe(observer);
        observable2.subscribe(observer);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



}
