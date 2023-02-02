package me.nedori.capeparser.app;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import me.nedori.capeparser.app.util.CapeUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;

public class CapeParserApplication {

    public CapeParserApplication(String... args) {
        this.start(args);
    }

    private void start(String... args) {
        OptionParser optionparser = new OptionParser();
        optionparser.allowsUnrecognizedOptions();

        OptionSpec<File> optionspec1 = optionparser.accepts("input").withRequiredArg().ofType(File.class);
        OptionSpec<File> optionspec2 = optionparser.accepts("output").withRequiredArg().ofType(File.class).defaultsTo(new File("output.png"));
        OptionSet optionset = optionparser.parse(args);

        try {
            String jarPath = getClass()
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI()
                    .getPath();

            File jarFile = new File(jarPath);

            if (!optionset.has(optionspec1)) {
                System.err.println("Correct usage: java -jar " + jarFile.getName() + " --input <path> --output [path]");
                return;
            }

        } catch (URISyntaxException ex) {
            System.err.println("Failed to check current jar file path.");
            ex.printStackTrace();
        }

        File input = optionset.valueOf(optionspec1);
        File output = optionset.valueOf(optionspec2);

        System.out.println("Input: " + input.getAbsolutePath());
        System.out.println("Output: " + output.getAbsolutePath());

        try (InputStream is = Files.newInputStream(input.toPath())) {
            BufferedImage bufferedImage = ImageIO.read(is);
            BufferedImage parsedImage = CapeUtil.parseCape(bufferedImage);
            ImageIO.write(parsedImage, "png", Files.newOutputStream(output.toPath()));
            System.out.println("Successfully parsed image.");
        } catch (IOException ex) {
            System.err.println("Failed to read input file.");
            ex.printStackTrace();
        }
    }
}
