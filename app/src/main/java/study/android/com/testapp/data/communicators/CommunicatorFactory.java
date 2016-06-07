package study.android.com.testapp.data.communicators;

import study.android.com.testapp.data.interfaces.BackendCommunicator;

/**
 * Created by tony on 21.05.16.
 */
public class CommunicatorFactory {
    public static BackendCommunicator createBackendCommunicator() {
        return new BackendCommunicatorStub();
    }
}
