package speech;

import java.util.Scanner;
import com.sun.speech.freetts.*;
import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.util.props.PropertyException;
import edu.cmu.sphinx.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
    



/**
 *
 * @author joaohenriquefeitosa
 */
public class Speech {
    private static final String VOICENAME="kevin16";
    
    public static void txtVoice(){
        
        
        Scanner teclado = new Scanner(System.in);
        String str;
        str = teclado.nextLine();
        
        Voice voice;
        VoiceManager vm = VoiceManager.getInstance();
        voice = vm.getVoice(VOICENAME);
        
        voice.allocate();
        System.out.println("2");
        try{
            voice.speak(str);
        }catch(Exception e){
            
        }
        teclado.close();
    }
    
    public static void voiceTxt(String[] args){
        ConfigurationManager cm;

        if (args.length > 0) {
            cm = new ConfigurationManager(args[0]);
        } else {
            cm = new ConfigurationManager(Speech.class.getResource("helloworld.config.xml"));
        }

        Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
        recognizer.allocate();

        // start the microphone or exit if the programm if this is not possible
        Microphone microphone = (Microphone) cm.lookup("microphone");
        if (!microphone.startRecording()) {
            System.out.println("Cannot start microphone.");
            recognizer.deallocate();
            System.exit(1);
        }

        System.out.println("Say: (Good morning | Hello) ( Bhiksha | Evandro | Paul | Philip | Rita | Will )");

        // loop the recognition until the programm exits.
        while (true) {
            System.out.println("Start speaking. Press Ctrl-C to quit.\n");

            Result result = recognizer.recognize();

            if (result != null) {
                String resultText = result.getBestFinalResultNoFiller();
                System.out.println("You said: " + resultText + '\n');
            } else {
                System.out.println("I can't hear what you said.\n");
            }
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int op;
        do {
            System.out.println("Escolha sua opcao:");
            System.out.println("1) Converter texto em voz;");
            System.out.println("2) Converter voz em texto;");
            op = entrada.nextInt();
            switch(op){
                case 1:
                    txtVoice();
                    break;
                case 2:
                    voiceTxt(args);
                    break;
                    
            }
            
        } while (op != 3);
        entrada.close();
    }
    
}
