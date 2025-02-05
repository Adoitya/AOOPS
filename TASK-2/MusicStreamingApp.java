import java.util.Scanner;

interface MusicSource {
    void playMusic();
}

class LocalMusicSource {
    public void playLocalMusic() {
        System.out.println("Playing music from local files...");
    }
}

class LocalMusicAdapter implements MusicSource {
    private LocalMusicSource localMusicSource;

    public LocalMusicAdapter(LocalMusicSource localMusicSource) {
        this.localMusicSource = localMusicSource;
    }

    @Override
    public void playMusic() {
        localMusicSource.playLocalMusic();
    }
}

class OnlineMusicSource {
    public void playOnlineStream() {
        System.out.println("Streaming music online...");
    }
}

class OnlineMusicAdapter implements MusicSource {
    private OnlineMusicSource onlineMusicSource;

    public OnlineMusicAdapter(OnlineMusicSource onlineMusicSource) {
        this.onlineMusicSource = onlineMusicSource;
    }

    @Override
    public void playMusic() {
        onlineMusicSource.playOnlineStream();
    }
}

class RadioStationSource {
    public void playRadio() {
        System.out.println("Playing music from radio station...");
    }
}

class RadioStationAdapter implements MusicSource {
    private RadioStationSource radioStationSource;

    public RadioStationAdapter(RadioStationSource radioStationSource) {
        this.radioStationSource = radioStationSource;
    }

    @Override
    public void playMusic() {
        radioStationSource.playRadio();
    }
}

interface Playback {
    void play();
}

class MusicPlayback implements Playback {
    private MusicSource musicSource;

    public MusicPlayback(MusicSource musicSource) {
        this.musicSource = musicSource;
    }

    @Override
    public void play() {
        musicSource.playMusic();
    }
}

abstract class PlaybackDecorator implements Playback {
    protected Playback playback;

    public PlaybackDecorator(Playback playback) {
        this.playback = playback;
    }

    @Override
    public void play() {
        playback.play();
    }
}

class EqualizerDecorator extends PlaybackDecorator {
    public EqualizerDecorator(Playback playback) {
        super(playback);
    }

    @Override
    public void play() {
        super.play();
        System.out.println("Enhancing sound with Equalizer...");
    }
}

class VolumeControlDecorator extends PlaybackDecorator {
    public VolumeControlDecorator(Playback playback) {
        super(playback);
    }

    @Override
    public void play() {
        super.play();
        System.out.println("Adjusting volume...");
    }
}

public class MusicStreamingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean keepRunning = true;

        while (keepRunning) {
            System.out.println("Choose a music source:");
            System.out.println("1. Local Files");
            System.out.println("2. Online Streaming");
            System.out.println("3. Radio Stations");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();

            if (choice == 4) {
                keepRunning = false;
                System.out.println("Exiting application. Goodbye!");
                break;
            }

            MusicSource musicSource;

            switch (choice) {
                case 1 -> musicSource = new LocalMusicAdapter(new LocalMusicSource());
                case 2 -> musicSource = new OnlineMusicAdapter(new OnlineMusicSource());
                case 3 -> musicSource = new RadioStationAdapter(new RadioStationSource());
                default -> {
                    System.out.println("Invalid choice. Please try again.");
                    continue;
                }
            }

            Playback playback = new MusicPlayback(musicSource);

            System.out.println("Do you want to add features? (yes/no)");
            String addFeatures = scanner.next();

            if (addFeatures.equalsIgnoreCase("yes")) {
                System.out.println("Adding Equalizer...");
                playback = new EqualizerDecorator(playback);

                System.out.println("Adding Volume Control...");
                playback = new VolumeControlDecorator(playback);
            }

            System.out.println("Playing music:");
            playback.play();

            System.out.println("\n=== Application Summary ===");
            System.out.println("Source: " + (choice == 1 ? "Local Files" : choice == 2 ? "Online Streaming" : "Radio Stations"));
            System.out.println("Features: " + (addFeatures.equalsIgnoreCase("yes") ? "Equalizer, Volume Control" : "None"));

            System.out.println("\nDo you want to choose another source? (yes/no)");
            String continueChoice = scanner.next();
            if (!continueChoice.equalsIgnoreCase("yes")) {
                keepRunning = false;
                System.out.println("Exiting application. Goodbye!");
            }
        }

        scanner.close();
    }
}
