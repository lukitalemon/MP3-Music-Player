import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class MusicPlayerGUI extends JFrame {

    // color configurations
    public static final Color FRAME_COLOR = Color.BLACK;
    public static final Color TEXT_COLOR = Color.WHITE;

    private MusicPlayer musicPlayer;

    // allow user to use file explorer in app
    private JFileChooser jFileChooser;

    private JLabel songTitle;
    private JLabel songArtist;

    public MusicPlayerGUI() {
        super("Music Player");

        // width and height
        setSize(400, 600);

        // exit application when closed
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // launch app at the center of the screen
        setLocationRelativeTo(null);

        // prevent app from being resized
        setResizable(false);

        // allow user to change the (x,y) coordinates of the window
        setLayout(null);

        // change the frame color
        getContentPane().setBackground(FRAME_COLOR);

        musicPlayer = new MusicPlayer();

        jFileChooser = new JFileChooser();

        // default path for file explorer
        jFileChooser.setCurrentDirectory(new File("src/assets"));

        addGuiComponents();
    }

    private void addGuiComponents() {
        // add toolbar
        addToolBar();

        // load record image
        JLabel songImage = new JLabel(loadImage("src/assets/record.png"));
        songImage.setBounds(0, 50, getWidth() - 20, 225);
        add(songImage);

        // song title
        songTitle = new JLabel("Song Title");
        songTitle.setBounds(0, 285, getWidth() - 10, 30);
        songTitle.setFont(new Font("Dialog", Font.BOLD, 24));
        songTitle.setForeground(TEXT_COLOR);
        songTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(songTitle);

        // Song Artist
        songArtist = new JLabel("Artist");
        songArtist.setBounds(0, 315, getWidth() -10, 30);
        songArtist.setFont(new Font("Dialog", Font.PLAIN, 24));
        songArtist.setForeground(TEXT_COLOR);
        songArtist.setHorizontalAlignment(SwingConstants.CENTER);
        add(songArtist);

        // playback slider
        JSlider playbackSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        playbackSlider.setBounds(getWidth()/2 - 300/2, 365, 300, 40);
        playbackSlider.setBackground(null);
        add(playbackSlider);

        // playback buttons (i.e. previous, play, next)
        addPlaybackBtns();

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

        //add the load song option in the menu
        JMenuItem loadSong = new JMenuItem("load Song");
        loadSong.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFileChooser.showOpenDialog(MusicPlayerGUI.this);
                File selectedFile = jFileChooser.getSelectedFile();

                if(selectedFile != null){
                    //create a song obj based on selected file
                    Song song = new Song(selectedFile.getPath());

                    //load song in music player
                    musicPlayer.loadSong(song);

                    // update song title and artist
                    updateSongTitleAndArtist(song);

                }
            }
        }));
        songMenu.add(loadSong);

        // add the playlist menu
        JMenu playlistMenu = new JMenu("Playlist");
        menuBar.add(playlistMenu);

        // add items to the playlist menu
        JMenuItem createPlaylist = new JMenuItem("Create Playlist");
        playlistMenu.add(createPlaylist);

        JMenuItem loadPlaylist = new JMenuItem("Load Playlist");
        playlistMenu.add(loadPlaylist);

        add(toolBar);
    }

    private void addPlaybackBtns(){
        JPanel playbackBtns = new JPanel();
        playbackBtns.setBounds(0, 435, getWidth() - 10, 80);
        playbackBtns.setBackground(null);

        // previous button
        JButton prevButton = new JButton(loadImage("src/assets/previous.png"));
        prevButton.setBorderPainted(false);
        prevButton.setBackground(null);
        playbackBtns.add(prevButton);

        // Play Button 
        JButton playButton = new JButton(loadImage("src/assets/play.png"));
        prevButton.setBorderPainted(false);
        playButton.setBackground(null);
        playbackBtns.add(playButton);

        // Pause Button 
        JButton pauseButton = new JButton(loadImage("src/assets/pause.png"));
        pauseButton.setBorderPainted(false);
        pauseButton.setBackground(null);
        pauseButton.setVisible(false);
        playbackBtns.add(pauseButton);


        // Next Button 
        JButton nextButton = new JButton(loadImage("src/assets/next.png"));
        nextButton.setBorderPainted(false);
        nextButton.setBackground(null);
        playbackBtns.add(nextButton);

        add(playbackBtns);

    }

    private void updateSongTitleAndArtist(Song song){
        songTitle.setText(song.getSongTitle());
        songArtist.setText(song.getSongArtist());

    }

    private ImageIcon loadImage(String imagePath){
        try{
            // read the image file from the given path
            BufferedImage image = ImageIO.read(new File(imagePath));

            // returns an image icon so that our component can render the image
            return new ImageIcon(image);
        }catch(Exception e){
            e.printStackTrace();
        }

        // could not find resource
        return null;
    }

}
