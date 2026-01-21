package MyTubeVideoProject;

public class VideoEncoder implements Encoding{
    @Override
    public void encode(Video video) {
        System.out.println("Encoding video...");
        System.out.println("Done!\n");
    }

}
