import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.atomic.AtomicBoolean;

// Jnative for global listeners
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;



// Work on adding button functionality

public class Main {
    static boolean run = false;


    private static final AtomicBoolean running = new AtomicBoolean(false);

    public static void main(String[] args) throws Exception {
        // Silence library logs (jnative A BITCH (love u))
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);

        JFrame frame = new JFrame("JFrame Example");
        frame.setSize(800, 600);

        // Create a panel with a button
        JPanel panel = new JPanel();
        JButton start = new JButton("Start");
        panel.add(start);

        JButton end = new JButton("End");
        panel.add(end);



        // Add action to the button
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("[GLOBAL] ESC pressed — starting...");
                run = true;
                listenForKeys();
            }
        });

        end.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("[GLOBAL] ESC pressed — stopping...");
                run = false;
                listenForKeys();
            }
        });

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.add(start, BorderLayout.NORTH);
        frame.add(end, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private static void listenForKeys() {
        if (run) {
            try
            {
                GlobalScreen.registerNativeHook();
                GlobalScreen.addNativeKeyListener(new NativeKeyListener()
                {
                    @Override
                    public void nativeKeyPressed(NativeKeyEvent nativeEvent)
                    {
                        String keyText=NativeKeyEvent.getKeyText(nativeEvent.getKeyCode());
                        System.out.println("User pressed: "+keyText);
                    }
                });


            }
            catch (NativeHookException e)
            {
                e.printStackTrace();
            }
        } else {

            System.out.println("STOPPING");
        }
    }
}

