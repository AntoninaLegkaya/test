package study.android.com.testapp.data.notification;

import android.database.Observable;
import android.os.Handler;

import study.android.com.testapp.data.interfaces.Observer;


/**
 * Created by tony on 21.05.16.
 */

public class ActionObservable extends Observable<Observer> {
    public Handler notifyStarted() {
        for (final Observer observer : mObservers) {
            observer.onStarted(this);
        }
        return null;
    }

    public void notifySucceeded() {
        for (final Observer observer : mObservers) {
            observer.onSucceeded(this);
        }
    }

    public void notifyFailed() {
        for (final Observer observer : mObservers) {
            observer.onFailed(this);
        }
    }
}