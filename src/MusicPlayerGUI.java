import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class MusicPlayerGUI extends JFrame {

    // color configurations
    public static final Color FRAME_COLOR = Color.BLACK;
    public static final Color TEXT_COLOR = Color.WHITE;

    public MusicPlayerGUI() {
        super("Music Player");

        // width and height
        setSize(400, 600);

        // exit applicatio nwhen closed
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // launch app at the center of the screen
        setLocationRelativeTo(null);

        // prevent app from being resized
        setResizable(false);

        // allow user to change the (x,y) coordinates of the window
        setLayout(null);

        // change the frame color
        getContentPane().setBackground(FRAME_COLOR);

        addGuiComponents();
    }

    private void addGuiComponents() {
        // add toolbar
        addToolBar();

        // load record image
        JLabel songImage = new JLabel(loadImage("C:/Users/teame/Desktop/MP3MusicPlayer/src/assets/record.png"));
        songImage.setBounds(0, 50, getWidth() - 20, 225);
        add(songImage);

        // song title
        JLabel songTitle = new JLabel("Song Title");
        songTitle.setBounds(0, 285, getWidth() - 10, 30);
        songTitle.setFont(new Font("Dialog", Font.BOLD, 24));
        songTitle.setForeground(TEXT_COLOR);
        songTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(songTitle);

        // Song Artist
        JLabel songArtist = new JLabel("Artist");
        songArtist.setBounds(0, 315, getWidth() -10, 30);
        songArtist.setFont(new Font("Dialog", Font.PLAIN, 24));
        songArtist.setForeground(TEXT_COLOR);
        songArtist.setHorizontalAlignment(SwingConstants.CENTER);
        add(songArtist);
    }

    private void addToolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setBounds(0, 0, getWidth(), 20);

        // Stop toolbar from being moved
        toolBar.setFloatable(false);

        // drop down menu
        JMenuBar menuBar = new JMenuBar();
        toolBar.add(menuBar);

        // song menu
        JMenu songMenu = new JMenu("Song");
        menuBar.add(songMenu);

        // add the load song option in the menu
        JMenuItem loadSong = new JMenuItem("load Song");
        songMenu.add(loadSong);

        // add the playlist menu
        JMenu playlistMenu = new JMenu("Playlist");
        menuBar.add(playlistMenu);

        // add items to the palylist menu
        JMenuItem createPlaylist = new JMenuItem("Create Playlist");
        playlistMenu.add(createPlaylist);

        JMenuItem loadPlaylist = new JMenuItem("Load Playlist");
        playlistMenu.add(loadPlaylist);

        add(toolBar);
    }

    private ImageIcon loadImage(String imagePath) {
        try {
            // read the image file from the given path
            BufferedImage image = ImageIO.read(new File(imagePath));

            // returns an image icon so that our component can render the image
            return new ImageIcon(image);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // cant find resource
        return null;
    }

}
