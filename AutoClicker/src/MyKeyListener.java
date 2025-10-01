import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class MyKeyListener implements NativeKeyListener {
    static MyKeyListener listener = new MyKeyListener();

    public static NativeKeyListener getListener() {
        return listener;
    }

    public static void startListern() {
        GlobalScreen.addNativeKeyListener(getListener());
    }

    public static void closeListener() {
        GlobalScreen.removeNativeKeyListener(listener);
    }
}
